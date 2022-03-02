package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.entidad.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioActualizarReservaTest {

    @Test
    @DisplayName("Deberia validar la existencia previa de la reserva")
    void deberiaValidarLaExistenciaPreviaDeLaReserva() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarReserva.ejecutar(reserva), ExcepcionDuplicidad.class, "La reserva no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente en el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existePorId(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        servicioActualizarReserva.ejecutar(reserva);
        //assert
        Mockito.verify(repositorioReserva, Mockito.times(1)).actualizar(reserva);
    }
}
