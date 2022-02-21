package com.ceiba.reserva.testdatabuilder;

import com.ceiba.reserva.comando.ComandoReserva;

import java.time.LocalDate;
import java.util.UUID;

public class ComandoReservaTestDataBuilder {

    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private Integer tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroDias;
    private Long valor;

    public ComandoReservaTestDataBuilder() {
        idCliente = Long.valueOf(1010101010);
        nombreCliente = "Juan";
        tipoVehiculo = 1;
        fechaInicio = LocalDate.now();
        fechaFin = LocalDate.now();
        numeroDias = 3;
        valor = Long.valueOf(300000);

    }

    public ComandoReservaTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ComandoReservaTestDataBuilder conTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public ComandoReservaTestDataBuilder conFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public ComandoReservaTestDataBuilder conNumeroDeDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
        return this;
    }

    public ComandoReservaTestDataBuilder conValor(Long valor) {
        this.valor = valor;
        return this;
    }

    public ComandoReserva build() {
        return new ComandoReserva(id, idCliente, nombreCliente, tipoVehiculo, fechaInicio, fechaFin, numeroDias, valor);
    }
}
