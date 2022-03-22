-- insert admin (username a, password aa)
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (1, TRUE, 'ADMIN,USER', 'a',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (2, TRUE, 'USER', 'b',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
    
INSERT INTO "IWUSER" VALUES
(1, 'a@gmail.com', TRUE, 'Nombre1', 'Apellido1', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,ADMIN', 'a'),
(2, 'b@gmail.com', TRUE, 'Nombre2', 'Apellido2', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,CLIENTE', 'b'),
(4, 'd@gmail.com', TRUE, 'Nombre4', 'Apellido4', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,EMPLEADO', 'd');


-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;


INSERT INTO Vehiculo (id, activo, matricula, tipo, modelo, anyo, propietario_id) VALUES
(1, TRUE, '1234BCD', 'COCHE', 'Audi Q2', 2000, 2),
(2, TRUE, '5678EFG', 'MOTO', 'Yamaha FZ 10', 2010, 2);

INSERT INTO "TEXTO_TALLER" VALUES (1, 'asdf');

-- INSERT INTO Reparacion (id, estado, fecha_inicio, fecha_fin, empleado_id, vehiculo_id) VALUES
-- (1, 'PENDIENTE', '2022-03-22 16:00:00', '2022-03-31 20:00:00', '4', '1');

INSERT INTO "REPARACION" VALUES 
(1,'Hace un ruido muy raro', 'PENDIENTE', NULL, '2022-05-05', 4, 2),
(2,'Parabrisas roto', 'PENDIENTE', NULL, '2022-05-05', 4, 1),
(3, 'Rueda pinchada', 'PENDIENTE', NULL, '2022-03-31 20:00:00', 4, 1);
