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

    @Test
    @DisplayName("Deberia calcular tarifa base automovil")
    void deberiaCalcularTarifaAutomovil() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(100000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa base camioneta")
    void deberiaCalcularTarifaCamioneta() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(2).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(150000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa base van")
    void deberiaCalcularTarifaVan() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(3).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(200000L, tarifa);
    }

    @Test
    @DisplayName("No deberia calcular tarifa Base")
    void noDeberiaCalcularTarifa() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(4).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(0L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa sabado")
    void deberiaCalcularTarifaSabado() {
        // arrange
        LocalDate fecha = LocalDate.of(2022, 02, 26);
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).conFechaInicio(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(130000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa domingo")
    void deberiaCalcularTarifaDomingo() {
        // arrange
        LocalDate fecha = LocalDate.of(2022, 02, 27);
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).conFechaInicio(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(130000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa entre de semana")
    void deberiaCalcularTarifaEntreSemana() {
        // arrange
        LocalDate fecha = LocalDate.of(2022, 02, 25);
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).conFechaInicio(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioActualizarReserva servicioActualizarReserva = new ServicioActualizarReserva(repositorioReserva);
        // act
        Long tarifa = servicioActualizarReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(100000L, tarifa);
    }
}
