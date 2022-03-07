package com.ceiba.reserva.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.modelo.entidad.puerto.repositorio.RepositorioReserva;
import com.ceiba.reserva.servicio.ServicioCrearReserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaTest {

    @Test
    @DisplayName("Deberia crear correctamente la reserva")
    void deberiaCrearCorrectamenteLaReserva() {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2022, 03, 02);
        Integer uno = 1;
        //act
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaInicio(fechaInicio).build();
        //assert
        assertEquals(1, reserva.getId());
        assertEquals(101112131, reserva.getIdCliente());
        assertEquals("Pedro", reserva.getNombreCliente());
        assertEquals(1, reserva.getTipoVehiculo());
        assertEquals(fechaInicio, reserva.getFechaInicio());
        assertEquals(fechaInicio.plusDays(reserva.getNumeroDias() - uno), reserva.getFechaFin());
        assertEquals(3, reserva.getNumeroDias());
        assertEquals(300000, reserva.getValor());
    }

    @Test
    void deberiaFallarSinIdDeCliente() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conIdCliente(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id del cliente");
    }

    @Test
    void deberiaFallarSinNombreDeCliente() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNombreCliente(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el nombre del cliente");
    }

    @Test
    void deberiaFallarSinTipoVehiculo() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conTipoVehiculo(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el tipo de vehiculo");
    }

    @Test
    void deberiaFallarSinFechaInicio() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conFechaInicio(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar la fecha inicio de reservacion");
    }

    @Test
    void deberiaFallarSinNumeroDeDias() {

        //Arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroDias(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el numero de dias a alquilar");
    }

    @Test
    @DisplayName("Deberia calcular tarifa Automovil")
    void deberiaCalcularTarifaAutomovil() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conNumeroDias(1).conTipoVehiculo(1).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(100000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa Camioneta")
    void deberiaCalcularTarifaCamioneta() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conNumeroDias(1).conTipoVehiculo(2).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(150000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa Van")
    void deberiaCalcularTarifaVan() {
        // arrange
        Reserva reserva = new ReservaTestDataBuilder().conNumeroDias(1).conTipoVehiculo(3).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(200000L, tarifa);
    }

    @Test
    @DisplayName("No Deberia Calcular Tarifa")
    void noDeberiaCalcularTarifa() {
        // arrange
        ReservaTestDataBuilder reservaTestDataBuilder = new ReservaTestDataBuilder().conNumeroDias(1).conTipoVehiculo(4);

        // act- assert
        BasePrueba.assertThrows(() -> {
                    reservaTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "Tipo de vehiculo no valido");
    }

    @Test
    @DisplayName("Deberia calcular tarifa sabado")
    void deberiaCalcularTarifaSabado() {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2022, 03, 05);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicio(fechaInicio).conNumeroDias(1).conTipoVehiculo(1).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(130000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa domingo")
    void deberiaCalcularTarifaDomingo() {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2022, 03, 06);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicio(fechaInicio).conNumeroDias(1).conTipoVehiculo(1).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(130000L, tarifa);
    }

    @Test
    @DisplayName("Deberia calcular tarifa entre de semana")
    void deberiaCalcularTarifaEntreSemana() {
        // arrange
        LocalDate fechaInicio = LocalDate.of(2022, 03, 07);
        Reserva reserva = new ReservaTestDataBuilder().conFechaInicio(fechaInicio).conNumeroDias(2).conTipoVehiculo(1).build();
        // act
        Long tarifa = reserva.getValor();
        //- assert
        assertEquals(200000L, tarifa);
    }
}
