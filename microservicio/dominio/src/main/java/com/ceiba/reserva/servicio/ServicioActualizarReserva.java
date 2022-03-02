package com.ceiba.reserva.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.entidad.puerto.repositorio.RepositorioReserva;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ServicioActualizarReserva {

    private static final String LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA = "La reserva no existe en el sistema";
    private static final int VALOR_DIA_AUTOMOVIL = 100000;
    private static final int VALOR_DIA_CAMIONETA = 150000;
    private static final int VALOR_DIA_VAN = 200000;
    private static final int UNO = 1;
    private final RepositorioReserva repositorioReserva;

    public ServicioActualizarReserva(RepositorioReserva repositorioReserva) {
        this.repositorioReserva = repositorioReserva;
    }

    public void ejecutar(Reserva reserva) {
        validarExistenciaPrevia(reserva);
        this.repositorioReserva.actualizar(reserva);
    }

    private void validarExistenciaPrevia(Reserva reserva) {
        boolean existe = this.repositorioReserva.existePorId(reserva.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(LA_RESERVA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
