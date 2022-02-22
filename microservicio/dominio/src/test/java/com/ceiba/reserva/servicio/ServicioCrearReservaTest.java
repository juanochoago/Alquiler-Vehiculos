package com.ceiba.reserva.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ServicioCrearReservaTest {

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia de la Reserva")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDeLaReserva() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong(), any(LocalDate.class))).thenReturn(true);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearReserva.ejecutar(reserva), ExcepcionDuplicidad.class, "La reserva ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia crear la reserva de manera correcta")
    void deberiaCrearLaReservaDeManeraCorrecta() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);
        Mockito.when(repositorioReserva.existe(Mockito.anyLong(), any(LocalDate.class))).thenReturn(false);
        Mockito.when(repositorioReserva.crear(reserva)).thenReturn(10L);
        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long idReserva = servicioCrearReserva.ejecutar(reserva);
        //- assert
        assertEquals(10L, idReserva);
        Mockito.verify(repositorioReserva, Mockito.times(1)).crear(reserva);
    }

    @Test
    @DisplayName("Deberia calcular tarifa Automovil")
    void deberiaCalcularTarifaAutomovil() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calculoTarifaTipoVehiculo(reserva);
        //- assert
        assertEquals(100000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa Camioneta")
    void deberiaCalcularTarifaCamioneta() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(2).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calculoTarifaTipoVehiculo(reserva);
        //- assert
        assertEquals(150000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa Automovil")
    void deberiaCalcularTarifaVan() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(3).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calculoTarifaTipoVehiculo(reserva);
        //- assert
        assertEquals(200000L, tarifa);
    }

    @Test
    @DisplayName("No Deberia Calcular Tarifa")
    void noDeberiaCalcularTarifa() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(4).conNumeroDias(1).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calculoTarifaTipoVehiculo(reserva);
        //- assert
        assertEquals(0L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa fin de semana")
    void deberiaCalcularTarifaFinDeSemana() {
        // arrange
        LocalDate fecha = LocalDate.of(2022, 02, 26);
        Reserva reserva = new ReservaTestDataBuilder().conTipoVehiculo(1).conNumeroDias(1).conFechaInicio(fecha).build();
        RepositorioReserva repositorioReserva = Mockito.mock(RepositorioReserva.class);

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calcularTarifa(reserva);
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

        ServicioCrearReserva servicioCrearReserva = new ServicioCrearReserva(repositorioReserva);
        // act
        Long tarifa = servicioCrearReserva.calcularTarifa(reserva);
        //- assert
        assertEquals(100000L, tarifa);
    }
}
