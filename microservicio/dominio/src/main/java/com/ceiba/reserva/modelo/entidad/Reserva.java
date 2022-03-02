package com.ceiba.reserva.modelo.entidad;


import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
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
    private static final String TIPO_VEHICULO_INVALIDO = "Tipo de vehiculo no valido";
    private static final int VALOR_DIA_AUTOMOVIL = 100000;
    private static final int VALOR_DIA_CAMIONETA = 150000;
    private static final int VALOR_DIA_VAN = 200000;
    private static final int UNO = 1;
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
        this.fechaFin = calcularFechaFin(fechaInicio,numeroDias);
        this.numeroDias = numeroDias;
        this.valor = calcularTarifa(fechaInicio,numeroDias,tipoVehiculo);
    }

    public LocalDate calcularFechaFin(LocalDate fechaInicio, Integer numeroDias) {
        int dias = numeroDias - UNO;
        return fechaInicio.plusDays(dias);
    }

    public Long calcularTarifa(LocalDate fechaInicio, Integer numeroDias, Integer tipoVehiculo) {
        LocalDate fechaCalculo = fechaInicio;
        Long tarifa = Long.valueOf(0);
        Long tipoTarifa = calculoTarifaTipoVehiculo(tipoVehiculo);

        for (int i = 0; i < numeroDias; i++) {
            if (fechaCalculo.getDayOfWeek() == DayOfWeek.SATURDAY || fechaCalculo.getDayOfWeek() == DayOfWeek.SUNDAY) {
                tarifa += tipoTarifa + ((30 * tipoTarifa) / 100);
            } else {
                tarifa += tipoTarifa;
            }
            fechaCalculo = fechaCalculo.plusDays(1);
        }
        return tarifa;
    }

    public Long calculoTarifaTipoVehiculo(Integer tipoVehiculo) {
        Long tipoTarifa;

        switch (tipoVehiculo) {
            case 1:
                tipoTarifa = Long.valueOf(VALOR_DIA_AUTOMOVIL);
                break;
            case 2:
                tipoTarifa = Long.valueOf(VALOR_DIA_CAMIONETA);
                break;
            case 3:
                tipoTarifa = Long.valueOf(VALOR_DIA_VAN);
                break;
            default:
                throw new ExcepcionValorInvalido(TIPO_VEHICULO_INVALIDO);
        }
        return tipoTarifa;
    }

}
