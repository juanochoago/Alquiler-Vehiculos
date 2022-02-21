package com.ceiba.reserva.servicio.testdatabuilder;

import com.ceiba.reserva.modelo.entidad.Reserva;;

import java.time.LocalDate;

public class ReservaTestDataBuilder {

    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private Integer tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroDias;
    private Long valor;

    public ReservaTestDataBuilder() {
        idCliente = Long.valueOf(101112131);
        nombreCliente = "Pedro";
        tipoVehiculo = 1;
        fechaInicio = LocalDate.now();
        fechaFin = LocalDate.now();
        numeroDias = 3;
        valor = Long.valueOf(360000);
    }

    public ReservaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ReservaTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente = idCliente;
        return this;
    }

    public ReservaTestDataBuilder conNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
        return this;
    }

    public ReservaTestDataBuilder conTipoVehiculo(Integer tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public ReservaTestDataBuilder conFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public ReservaTestDataBuilder conFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public ReservaTestDataBuilder conNumeroDias(Integer numeroDias) {
        this.numeroDias = numeroDias;
        return this;
    }

    public ReservaTestDataBuilder conValor(Long valor) {
        this.valor = valor;
        return this;
    }

    public Reserva build() {
        return new Reserva(id, idCliente, nombreCliente, tipoVehiculo, fechaInicio, fechaFin, numeroDias, valor);
    }
}
