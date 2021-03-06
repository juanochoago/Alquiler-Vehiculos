package com.ceiba.reserva.modelo.entidad.puerto.dao;

import com.ceiba.reserva.modelo.dto.DtoReserva;


public interface DaoReserva {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    DtoReserva listar(Long id);
}
