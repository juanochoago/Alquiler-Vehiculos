package com.ceiba.reserva.puerto.repositorio;

import com.ceiba.reserva.modelo.entidad.Reserva;

import java.time.LocalDate;

public interface RepositorioReserva {
    /**
     * Permite crear una reserva
     * @param reserva
     * @return el id generado
     */
    Long crear(Reserva reserva);

    /**
     * Permite actualizar una reserva
     * @param reserva
     */
    void actualizar(Reserva reserva);

    /**
     * Permite validar si existe una reseva con un idCliente
     * @param idCliente
     * @return si existe o no
     */
    boolean existe(Long idCliente, LocalDate fechaFin);

    /**
     * Permite validar si existe una reserva con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);


}
