-- insert admin (username a, password aa)
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (1, TRUE, 'ADMIN,USER', 'a',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (2, TRUE, 'USER', 'b',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
    
INSERT INTO "IWUSER" VALUES
(1, TRUE, 'Nombre1', 'Apellido1', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,ADMIN,EMPLEADO', 'a'),
(2, TRUE, 'Nombre2', 'Apellido2', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,CLIENTE', 'b'),     
(3, TRUE, 'Nombre3', 'Apellido3', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,ADMIN,EMPLEADO', 'c');


-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;


INSERT INTO Vehiculo (id, activo, matricula, tipo, modelo, propietario_id) VALUES
(1, TRUE, '1234BCD', 'COCHE', 'Audi Q2', 1),
(2, TRUE, '5678EFG', 'MOTO', 'Yamaha FZ 10', 1);

INSERT INTO "TEXTO_TALLER" VALUES (1, 'asdf');

INSERT INTO "REPARACION" VALUES 
(1,'Hace un ruido muy raro', TRUE, NULL, '2022-05-05', NULL, 2),
(2,'Parabrisas roto', TRUE, NULL, '2022-05-05', NULL, 2);