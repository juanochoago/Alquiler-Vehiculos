package com.ceiba.reserva.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Reserva {

    private static final String SE_DEBE_INGRESAR_ID_CLIENTE = "Se debe ingresar el id del cliente";
    private static final String SE_DEBE_INGRESAR_NOMBRE_CLIENTE = "Se debe ingresar el nombre del cliente";
    private static final String SE_DEBE_INGRESAR_TIPO_VEHICULO = "Se debe ingresar el tipo de vehiculo";
    private static final String SE_DEBE_INGRESAR_FECHA_INICIO = "Se debe ingresar la fecha inicio de reservacion";
    private static final String SE_DEBE_INGRESAR_NUMERO_DIAS = "Se debe ingresar el numero de dias a alquilar";

    private Long id;
    private Long idCliente;
    private String nombreCliente;
    private Integer tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer numeroDias;
    private Long valor;

    public Reserva(Long id, Long idCliente, String nombreCliente, Integer tipoVehiculo, LocalDate fechaInicio, LocalDate fechaFin, Integer numeroDias, Long valor) {
        validarObligatorio(idCliente, SE_DEBE_INGRESAR_ID_CLIENTE);
        validarObligatorio(nombreCliente, SE_DEBE_INGRESAR_NOMBRE_CLIENTE);
        validarObligatorio(tipoVehiculo, SE_DEBE_INGRESAR_TIPO_VEHICULO);
        validarObligatorio(fechaInicio, SE_DEBE_INGRESAR_FECHA_INICIO);
        validarObligatorio(numeroDias, SE_DEBE_INGRESAR_NUMERO_DIAS);

        this.id = id;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numeroDias = numeroDias;
        this.valor = valor;
    }

}
