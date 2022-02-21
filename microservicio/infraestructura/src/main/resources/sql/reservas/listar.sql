select id,idCliente,nombreCliente,tipoVehiculo,fecha_inicio,fecha_fin,numero_dias,valor
from reservacion
where id = :id