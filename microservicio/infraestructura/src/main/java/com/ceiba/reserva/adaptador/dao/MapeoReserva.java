package com.ceiba.reserva.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MapeoReserva implements RowMapper<DtoReserva>, MapperResult {

    @Override
    public DtoReserva mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        Long idCliente = resultSet.getLong("idCliente");
        String nombreCliente = resultSet.getString("nombreCliente");
        Integer tipoVehiculo = resultSet.getInt("tipoVehiculo");
        LocalDate fechaInicio = extraerLocalDate(resultSet, "fecha_inicio");
        LocalDate fechaFin = extraerLocalDate(resultSet, "fecha_fin");
        Integer numeroDias = resultSet.getInt("numero_dias");
        Long valor = resultSet.getLong("valor");

        return new DtoReserva(id, idCliente, nombreCliente, tipoVehiculo, fechaInicio, fechaFin, numeroDias, valor);
    }

}
