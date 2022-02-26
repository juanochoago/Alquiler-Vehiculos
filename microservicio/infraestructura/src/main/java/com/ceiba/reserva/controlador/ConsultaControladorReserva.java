package com.ceiba.reserva.controlador;

import com.ceiba.reserva.consulta.ManejadorListarReservas;
import com.ceiba.reserva.modelo.dto.DtoReserva;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = {"Controlador consulta reservas"})
public class ConsultaControladorReserva {

    private final ManejadorListarReservas manejadorListarReservas;

    public ConsultaControladorReserva(ManejadorListarReservas manejadorListarReservas) {
        this.manejadorListarReservas = manejadorListarReservas;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Listar Reservas")
    public DtoReserva listar(@PathVariable Long id) {
        return this.manejadorListarReservas.ejecutar(id);
    }

}
