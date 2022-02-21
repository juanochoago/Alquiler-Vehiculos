package com.ceiba.reserva.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.reserva.modelo.entidad.Reserva;
import com.ceiba.reserva.servicio.testdatabuilder.ReservaTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservaTest {

    @Test
    @DisplayName("Deberia crear correctamente la reserva")
    void deberiaCrearCorrectamenteLaReserva() {
        // arrange
        LocalDate fecha = LocalDate.now();
        //act
        Reserva reserva = new ReservaTestDataBuilder().conId(1L).conFechaInicio(fecha).conFechaFin(fecha).build();
        //assert
        assertEquals(1, reserva.getId());
        assertEquals(101112131, reserva.getIdCliente());
        assertEquals("Pedro", reserva.getNombreCliente());
        assertEquals(1, reserva.getTipoVehiculo());
        assertEquals(fecha, reserva.getFechaInicio());
        assertEquals(fecha, reserva.getFechaFin());
        assertEquals(3, reserva.getNumeroDias());
        assertEquals(360000, reserva.getValor());
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

}
