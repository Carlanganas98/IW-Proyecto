-- insert admin (username a, password aa)
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (1, TRUE, 'ADMIN,USER', 'a',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
--INSERT INTO IWUser (id, enabled, roles, username, password)
--VALUES (2, TRUE, 'USER', 'b',
  --  '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
    
INSERT INTO "IWUSER" VALUES
(1, 'a@gmail.com', TRUE, 'Nombre1', 'Apellido1', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'ADMIN,USER,CLIENTE', 'a'),
(2, 'b@gmail.com', TRUE, 'Nombre2', 'Apellido2', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER,EMPLEADO', 'b');     


-- start id numbering from a value that is larger than any assigned above
ALTER SEQUENCE "PUBLIC"."GEN" RESTART WITH 1024;


INSERT INTO Vehiculo (id, matricula, tipo, modelo, anyo, propietario_id) VALUES
(1, '1234BCD', 'COCHE', 'Audi Q2', 2000, 1),
(2, '5678EFG', 'MOTO', 'Yamaha FZ 10', 2010, 1);
