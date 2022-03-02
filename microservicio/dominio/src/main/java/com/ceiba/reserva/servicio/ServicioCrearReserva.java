package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.entidad.puerto.repositorio.RepositorioReserva;



public class ServicioCrearReserva {

    private static final String LA_RESERVA_YA_EXISTE_EN_EL_SISTEMA = "El cliente tiene una reserva activa actualmente";
    private final RepositorioReserva respositorioReserva;

    public ServicioCrearReserva(RepositorioReserva respositorioReserva) {
        this.respositorioReserva = respositorioReserva;
    }

    public Long ejecutar(Reserva reserva) {
        validarReservasActivas(reserva);
        return this.respositorioReserva.crear(reserva);
    }

    private void validarReservasActivas(Reserva reserva) {
        boolean existe = this.respositorioReserva.existe(reserva.getIdCliente(), reserva.getFechaInicio());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_RESERVA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}