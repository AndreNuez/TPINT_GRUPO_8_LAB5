
use tpint_grupo_8_lab5;

-- Insertamos 2 usuarios administradores
INSERT INTO `usuarios` (`Activo`, `Nombre`, `Password`, `perfil`)
VALUES 
    (1, 'admin', 'admin', 1),
    (1, 'admin1', 'admin1', 1);

-- Insertar 4 provincias 

INSERT INTO provincia (nombre) VALUES
    ('Buenos Aires'),
    ('Cordoba'),
    ('Santa Fe'),
    ('Mendoza');       


-- insertar 5 localidades para cada provincia

-- Insertar localidades para la provincia de Buenos Aires

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('La Plata', 1),
    ('Mar del Plata', 1),
    ('Bahia Blanca', 1),
    ('Quilmes', 1);

-- Insertar localidades para la provincia de Cordoba

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('Cordoba', 2),
    ('Villa Maria', 2),
    ('Rio Cuarto', 2),
    ('Alta Gracia', 2);

-- Insertar localidades para la provincia de Santa Fe

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('Rosario', 3),
    ('Santa Fe', 3),
    ('Rafaela', 3),
    ('Venado Tuerto', 3);

-- Insertar localidades para la provincia de Mendoza

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('Mendoza', 4),
    ('San Rafael', 4),
    ('Godoy Cruz', 4),
    ('Lujan de Cuyo', 4);
    
    
INSERT INTO jornadas (id,Descripcion,InicioLunes,FinLunes,InicioMartes,FinMartes,InicioMiercoles,FinMiercoles,InicioJueves,FinJueves,InicioViernes,FinViernes,
InicioSabado,FinSabado,InicioDomingo,FinDomingo)
VALUES
(1, 'Lunes a viernes, 8 a 12',8,12,8,12,8,12,8,12,8,12,0,0,0,0),
(2, 'Lunes a miercoles, 15 a 19',15,19,15,19,15,19,0,0,0,0,0,0,0,0),
(3, 'Viernes, sÃ¡bado y domingo, 18 a 21',0,0,0,0,0,0,0,0,18,21,18,21,18,21); 

INSERT INTO especialidad (id, Nombre)
VALUES
    (1, 'Cardiología'),
    (2, 'Dermatología'),
    (3, 'Gastroenterología'),
    (4, 'Neurología');


