package com.ceiba.reserva.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoReserva {

    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private Integer tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroDias;
    private Long valor;
}
