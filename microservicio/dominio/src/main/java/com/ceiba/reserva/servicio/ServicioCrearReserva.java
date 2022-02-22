package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class ServicioCrearReserva {

    private static final String LA_RESERVA_YA_EXISTE_EN_EL_SISTEMA = "La reserva ya existe en el sistema";
    private static final int VALOR_DIA_AUTOMOVIL = 100000;
    private static final int VALOR_DIA_CAMIONETA = 150000;
    private static final int VALOR_DIA_VAN = 200000;
    private static final int UNO = 1;
    private final RepositorioReserva respositorioReserva;

    public ServicioCrearReserva(RepositorioReserva respositorioReserva) {
        this.respositorioReserva = respositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarReservasActivas(reserva);
        reserva.setFechaFin(calcularFechaFin(reserva));
        reserva.setValor(calcularTarifa(reserva));
        return this.respositorioReserva.crear(reserva);
    }

    private void validarReservasActivas(Reserva reserva) {
        boolean existe = this.respositorioReserva.existe(reserva.getIdCliente(), reserva.getFechaInicio());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_RESERVA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    public LocalDate calcularFechaFin(Reserva reserva) {
        int dias = reserva.getNumeroDias() - UNO;
        return reserva.getFechaInicio().plusDays(dias);
    }

    public Long calcularTarifa(Reserva reserva) {
        LocalDate fechaCalculo = reserva.getFechaInicio();
        Long tarifa = Long.valueOf(0);
        Long tipoTarifa;

        switch (reserva.getTipoVehiculo()) {
            case 1:
                tipoTarifa = Long.valueOf(VALOR_DIA_AUTOMOVIL);
                break;
            case 2:
                tipoTarifa = Long.valueOf(VALOR_DIA_CAMIONETA);
                break;
            case 3:
                tipoTarifa = Long.valueOf(VALOR_DIA_VAN);
                break;
            default:
                tipoTarifa = Long.valueOf(0);
        }

        for (int i = 0; i < reserva.getNumeroDias(); i++) {
            if (fechaCalculo.getDayOfWeek() == DayOfWeek.SATURDAY || fechaCalculo.getDayOfWeek() == DayOfWeek.SUNDAY) {
                tarifa += tipoTarifa + ((30 * tipoTarifa) / 100);
            } else {
                tarifa += tipoTarifa;
            }
            fechaCalculo = fechaCalculo.plusDays(1);
        }
        return tarifa;
    }
}