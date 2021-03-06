package com.ceiba.reserva.consulta;

import com.ceiba.reserva.modelo.dto.DtoReserva;
import com.ceiba.reserva.modelo.entidad.puerto.dao.DaoReserva;
import org.springframework.stereotype.Component;

@Component
public class ManejadorListarReservas {

    private final DaoReserva daoReserva;

    public ManejadorListarReservas(DaoReserva daoReserva) {
        this.daoReserva = daoReserva;
    }

    public DtoReserva ejecutar(Long id) {
        return this.daoReserva.listar(id);
    }
}
