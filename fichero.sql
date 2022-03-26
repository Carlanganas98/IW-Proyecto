-- H2 2.1.210; 
;              
CREATE USER IF NOT EXISTS "SA" SALT '646424a309a1243c' HASH '478da8db514a814b67205a220ca1fc6b5cecb687b8edc272ef6245ee305f4889' ADMIN;          
CREATE SEQUENCE "PUBLIC"."GEN" START WITH 1 RESTART WITH 1024 INCREMENT BY 50; 
CREATE MEMORY TABLE "PUBLIC"."IWUSER"(
    "ID" BIGINT NOT NULL,
    "ENABLED" BOOLEAN NOT NULL,
    "FIRST_NAME" CHARACTER VARYING(255),
    "LAST_NAME" CHARACTER VARYING(255),
    "PASSWORD" CHARACTER VARYING(255) NOT NULL,
    "ROLES" CHARACTER VARYING(255),
    "USERNAME" CHARACTER VARYING(255) NOT NULL
);        
ALTER TABLE "PUBLIC"."IWUSER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ID");        
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.IWUSER;   
INSERT INTO "PUBLIC"."IWUSER" VALUES
(1, TRUE, 'Nombre1', 'Apellido1', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'ADMIN,USER', 'a'),
(2, TRUE, 'Nombre2', 'Apellido2', '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W', 'USER', 'b');     
CREATE MEMORY TABLE "PUBLIC"."MESSAGE"(
    "ID" BIGINT NOT NULL,
    "DATE_READ" TIMESTAMP,
    "DATE_SENT" TIMESTAMP,
    "TEXT" CHARACTER VARYING(255),
    "RECIPIENT_ID" BIGINT,
    "SENDER_ID" BIGINT
);
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_6" PRIMARY KEY("ID");       
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MESSAGE;  
CREATE MEMORY TABLE "PUBLIC"."REPARACION"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "FECHA_FIN" TIMESTAMP,
    "FECHA_INICIO" TIMESTAMP,
    "EMPLEADO_ID" BIGINT,
    "VEHICULO_ID" BIGINT
);             
ALTER TABLE "PUBLIC"."REPARACION" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_A" PRIMARY KEY("ID");    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.REPARACION;               
CREATE MEMORY TABLE "PUBLIC"."SERVICIO"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "INFO" CHARACTER VARYING(255),
    "NUM_HORAS" INTEGER NOT NULL,
    "PRECIO" CHARACTER VARYING(255),
    "REPARACION_ID" BIGINT,
    "SERVICIO_ID" BIGINT
);            
ALTER TABLE "PUBLIC"."SERVICIO" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_80" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.SERVICIO; 
CREATE MEMORY TABLE "PUBLIC"."TALLER"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL
);              
ALTER TABLE "PUBLIC"."TALLER" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_9" PRIMARY KEY("ID");        
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TALLER;   
CREATE MEMORY TABLE "PUBLIC"."VEHICULO"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "MATRICULA" CHARACTER VARYING(255),
    "MODELO" CHARACTER VARYING(255),
    "TIPO" CHARACTER VARYING(255),
    "ANYO" BIGINT,
    "PROPIETARIO_ID" BIGINT
);               
ALTER TABLE "PUBLIC"."VEHICULO" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_A6" PRIMARY KEY("ID");     
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.VEHICULO; 
ALTER TABLE "PUBLIC"."IWUSER" ADD CONSTRAINT "PUBLIC"."UK_D2IR61JG0CAX4VX7AB3EA3F9G" UNIQUE("USERNAME");       
ALTER TABLE "PUBLIC"."VEHICULO" ADD CONSTRAINT "PUBLIC"."FK806HIDHDPTBANI5BH2MJOCYG0" FOREIGN KEY("PROPIETARIO_ID") REFERENCES "PUBLIC"."IWUSER"("ID") NOCHECK;
ALTER TABLE "PUBLIC"."SERVICIO" ADD CONSTRAINT "PUBLIC"."FKOG1188HDMRRPM9I7M9TIEARO7" FOREIGN KEY("SERVICIO_ID") REFERENCES "PUBLIC"."REPARACION"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKNCAXW3LCT4F38N1NXO5BCAR54" FOREIGN KEY("RECIPIENT_ID") REFERENCES "PUBLIC"."IWUSER"("ID") NOCHECK;   
ALTER TABLE "PUBLIC"."MESSAGE" ADD CONSTRAINT "PUBLIC"."FKXQR9WQ16K3403YBEPWGPGY0N" FOREIGN KEY("SENDER_ID") REFERENCES "PUBLIC"."IWUSER"("ID") NOCHECK;       
ALTER TABLE "PUBLIC"."REPARACION" ADD CONSTRAINT "PUBLIC"."FK68YABWANNYTEJJ4L5B3HGL7QE" FOREIGN KEY("VEHICULO_ID") REFERENCES "PUBLIC"."VEHICULO"("ID") NOCHECK;               
ALTER TABLE "PUBLIC"."REPARACION" ADD CONSTRAINT "PUBLIC"."FKARJE140F1BH2DNAQO055JI4DD" FOREIGN KEY("EMPLEADO_ID") REFERENCES "PUBLIC"."IWUSER"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."SERVICIO" ADD CONSTRAINT "PUBLIC"."FK156D7IIXVSWSFLLSYJHXOFWU3" FOREIGN KEY("REPARACION_ID") REFERENCES "PUBLIC"."REPARACION"("ID") NOCHECK;             
