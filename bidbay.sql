use bidbay;

INSERT INTO categorias (nombre) values('Accesorios'),('Ropa de verano'),('Ropa de invierno'),('Pantalones'),('Calzado');
insert into modalidad (nombre) values ('Venta');
insert into modalidad (nombre) values ('Subasta');

insert into usuarios(apellido, nombre, direccion, nick, password, rating, rol, telefono, email) values ('Moderador', 'Moderador', 'Calle Falsa 123', 'Moderador', 123,0.0, 1, '123456789', 'algo@mail.com');

INSERT INTO productos (descripcion,imagen,nombre,precio,stock,categoria_id,modalidad_id,usuario_id) 
	VALUES
	 ('Camiseta de algodón','camisetaa.jpg','Camiseta Azul',19.99,10,2,1,1),
	 ('Pantalón vaquero','pantalon.jpg','Pantalón Negro',39.99,5,4,1,1),
	 ('Vestido estampado','vestido.jpg','Vestido Floral',49.99,8,2,1,1),
	 ('Zapatillas deportivas','zapatillas.jpg','Zapatillas Nike',79.99,3,5,1,1),
	 ('Bolso de cuero','bolzo.jpg','Bolso Marrón',59.99,1,2,1,1);