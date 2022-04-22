-- insert admin (username a, password aa)
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (1, TRUE, 'ADMIN,USER', 'a',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (2, TRUE, 'USER', 'b',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
    
INSERT INTO "IWUSER" VALUES
(1, 'a@gmail.com', TRUE, 'Nombre1', 'Apellido1', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'ADMIN', 'a'),
(2, 'b@gmail.com', TRUE, 'Nombre2', 'Apellido2', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'CLIENTE', 'b'),
(4, 'd@gmail.com', TRUE, 'Manolo', 'Lama', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'd'),
(3, 'd@gmail.com', TRUE, 'Paco', 'Garcia', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'c');


-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;


INSERT INTO Vehiculo (id, activo, matricula, tipo, modelo, anyo, propietario_id) VALUES
(1, TRUE, '1234BCD', 'COCHE', 'Audi Q2', 2000, 2),
(2, TRUE, '5678EFG', 'MOTO', 'Yamaha FZ 10', 2010, 1);

INSERT INTO "TEXTO_TALLER" VALUES (1, '	<ul>
<li>Bird</li>
<li>Magic</li>
</ul>', '<h1>Titulo1</h1>
<h2>Titulo2</h2>
<h3>Titulo3</h3>');

-- INSERT INTO Reparacion (id, estado, fecha_inicio, fecha_fin, empleado_id, vehiculo_id) VALUES
-- (1, 'PENDIENTE', '2022-03-22 16:00:00', '2022-03-31 20:00:00', '4', '1');

INSERT INTO "REPARACION" VALUES 
(1, TRUE,'Hace un ruido muy raro', 'ACEPTADO', NULL, '2022-05-05', 4, 1),
(2, TRUE,'Parabrisas roto', 'PENDIENTE', NULL, '2022-05-05', NULL, 2),
(3, TRUE, 'Rueda pinchada', 'PENDIENTE', '2022-04-01', '2022-03-31 20:00:00', NULL, 1);


insert into servicio values
(1, TRUE, 'condensador', 2, 100, 1),
(2, FALSE, 'motor', 5, 250, 1);


INSERT INTO "MESSAGE" VALUES 
(1,'2007-12-03T10:15:30' '2007-12-03T10:15:30', 'Hola buenos dias', 1, 2),
(2,'2007-12-03T10:15:30', '2007-12-03T10:15:30', 'Hola a ti tambien', 2, 1);

