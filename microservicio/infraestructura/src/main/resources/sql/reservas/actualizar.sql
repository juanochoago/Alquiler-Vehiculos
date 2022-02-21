update reservacion
set idCliente = :idCliente,
	nombreCliente = :nombreCliente,
	tipoVehiculo = :tipoVehiculo,
	fecha_inicio = :fechaInicio,
	fecha_fin = :fechaFin,
	numero_dias = :numeroDias,
	valor = :valor
where id = :id