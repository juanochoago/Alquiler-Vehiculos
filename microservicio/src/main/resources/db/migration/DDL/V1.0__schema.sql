create table reservacion (
 id int(11) not null auto_increment,
 idCliente int(11) not null,
 nombreCliente varchar(60) not null,
 tipoVehiculo int(2) not null,
 fecha_inicio date not null,
 fecha_fin date not null,
 numero_dias int(2) not null,
 valor int(20) not null,
 primary key (id)
);