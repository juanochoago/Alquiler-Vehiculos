package com.ceiba.reserva.modelo.entidad.puerto.repositorio;

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
     * Permite validar si existe una reseva activa
     * @param idCliente
     * @param fechaFin
     * @return si existe o no
     */
    boolean existe(Long idCliente, LocalDate fechaFin);

    /**
     * Permite validar si existe una reserva en el sistema con id
     * @param id
     * @return si existe o no
     */
    boolean existePorId(Long id);


}
