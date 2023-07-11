use bidbay;
CREATE VIEW operacioncv AS
SELECT c.id AS compra,
       c.id_pago AS pago,
       c.id_usuario AS usuario_compra,
       c.fecha,
       dc.cantidad,
       dc.precio_compra,
       p.id AS producto,
       p.descripcion AS descripcion_producto,
       p.imagen AS imagen_producto,
       p.usuario_id AS usuario_vende
FROM detalle_compras dc
JOIN compras c ON dc.compra_id = c.id
JOIN productos p ON dc.productos = p.id
where c.id_pago is not null;

insert into modalidad (nombre) values ('Venta');
insert into modalidad (nombre) values ('Subasta');