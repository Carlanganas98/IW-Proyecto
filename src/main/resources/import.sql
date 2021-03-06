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
(3, 'd@gmail.com', TRUE, 'Paco', 'Garcia', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'c'),
(4, 'd@gmail.com', TRUE, 'Manolo', 'Lama', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'd'),
(5, 'estherfdez@gmail.com', TRUE, 'Esther', 'Fernandez', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'CLIENTE', 'estherFdz'),
(6, 'juanmm@gmail.com', TRUE, 'Juan', 'Martin', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'CLIENTE', 'jmm'),
(7, 'a_lopez@gmail.com', TRUE, 'Alvaro', 'Lopez', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'CLIENTE', 'alopez'),
(8, 'mtena@gmail.com', TRUE, 'Marta', 'Tena', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'mtena'),
(9, 'josemar@gmail.com', TRUE, 'Jose', 'Maria', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'CLIENTE', 'jsma99'),
(10, 'luissw@gmail.com', TRUE, 'Luis', 'Sabio', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'EMPLEADO', 'lssw');


-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;


INSERT INTO Vehiculo (id, activo, matricula, tipo, modelo, anyo, propietario_id) VALUES
(1, TRUE, '1234BCD', 'COCHE', 'Audi Q2', 2000, 2),
(2, TRUE, '5678EFG', 'MOTO', 'Yamaha FZ 10', 2010, 1),
(3, TRUE, '3964ESD', 'COCHE', 'Audi A5', 2005, 2),
(4, TRUE, '0725JRD', 'MOTO', 'Yamaha FZ 9', 2010, 9),
(5, TRUE, '4599IJK', 'MOTO', 'Mitsubhisi', 2008, 8),
(6, TRUE, '7925LQE', 'COCHE', 'Ferrari enzo', 2005, 9),
(7, TRUE, '2569STE', 'COCHE', 'Toyota prius', 2010, 3),
(8, TRUE, '4322MKI', 'MOTO', 'Suzuki e99', 2009, 2),
(9, TRUE, '9214SWQ', 'COCHE', 'Ford focus', 2003, 5),
(10, TRUE, '5678HSM', 'COCHE', 'Wolswagen golf', 2022, 9);



INSERT INTO "TEXTO_TALLER" VALUES (1, '', '<h1>DataCar</h1>
<h2>Bienvenidos al mejor taller de la ciudad.</h2>', 1010);

-- INSERT INTO Reparacion (id, estado, fecha_inicio, fecha_fin, empleado_id, vehiculo_id) VALUES
-- (1, 'PENDIENTE', '2022-03-22 16:00:00', '2022-03-31 20:00:00', '4', '1');

INSERT INTO "REPARACION" VALUES 
(1, TRUE,'Hace un ruido muy raro', 'ACEPTADO', NULL, '2022-05-05',390, 4, 1),
(2, TRUE,'Parabrisas roto', 'PENDIENTE', NULL, '2022-05-05',0, NULL, 2),
(3, TRUE, 'Rueda pinchada', 'PENDIENTE', '2022-04-01', '2022-03-31 20:00:00',0, NULL, 1),
(4, TRUE,'Cambio chapa', 'PENDIENTE', '2022-04-01', '2022-05-05',0, NULL, 2),
(5, TRUE,'No arranca', 'ACEPTADO', NULL, '2022-05-05',400, 4, 1),
(6, TRUE,'Cambio de llantas', 'ACEPTADO', NULL, '2022-05-05',1500, 3, 4),
(7, TRUE, 'Pintura', 'ACEPTADO', '2022-04-01', '2022-03-31 20:00:00',100, 3, 5),
(8, TRUE,'Actualizacion de GPS', 'PENDIENTE', NULL, '2022-05-05',0, NULL, 1),
(9, TRUE,'Puesta a punto', 'PENDIENTE', '2022-04-09', '2022-04-15',0, NULL, 2),
(10, TRUE,'Cambio de aceite', 'PENDIENTE', '2022-04-09', '2022-04-09',0, NULL, 2);

insert into servicio values
(1, TRUE, 'condensador', 2, 100, 1),
(2, FALSE, 'motor', 5, 250, 1),
(3, TRUE, 'aceite', 1, 40, 1),
(4, FALSE, 'frenos', 2, 70, 5),
(5, TRUE, 'espejo retrovisor', 5, 250, 5),
(6, FALSE, 'chapa', 9, 1000, 6),
(7, FALSE, 'pintura', 4, 500, 6),
(8, TRUE, 'liquido de frenos', 1, 40, 7),
(9, FALSE, 'cambio de ruedas', 1, 80, 5),
(10, FALSE, 'cambio de llantas', 1, 60, 7);
