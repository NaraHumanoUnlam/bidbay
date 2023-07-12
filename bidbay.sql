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

INSERT INTO categorias (nombre) values('Accesorios'),('Ropa de verano'),('Ropa de invierno'),('Pantalones'),('Calzado');
insert into modalidad (nombre) values ('Venta');
insert into modalidad (nombre) values ('Subasta');

insert into usuarios(apellido, nombre, direccion, nick, password, rating, rol, telefono, mail) values ('Moderador', 'Moderador', 'Calle Falsa 123', 'Moderador', 123,0.0, 1, '123456789', 'algo@mail.com');
INSERT INTO productos (descripcion, imagen, nombre, precio, stock, categoria_id, usuario_id)
VALUES
    ('Camiseta de algodón', 'negro.jpg', 'Camiseta Azul', 19.99, 10, 2, 1),
    ('Pantalón vaquero', 'negro.jpg', 'Pantalón Negro', 39.99, 5, 4, 1),
    ('Vestido estampado', 'negro.jpg', 'Vestido Floral', 49.99, 8, 2, 1),
    ('Zapatillas deportivas', 'negro.jpg', 'Zapatillas Nike', 79.99, 3, 5, 1),
    ('Bolso de cuero', 'negro.jpg', 'Bolso Marrón', 59.99, 2, 2, 1);