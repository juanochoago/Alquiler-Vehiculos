package com.ceiba.reserva.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoReserva {
    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private Integer tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroDias;
    private Long valor;

}
