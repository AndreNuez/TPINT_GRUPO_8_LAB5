--Creates de DB 
use tpint_grupo_8_lab5;

CREATE TABLE `especialidad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `perfil` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `medicos` (
  `Legajo` int(11) NOT NULL AUTO_INCREMENT,
  `Activo` tinyint(1) DEFAULT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `FNac` varchar(255) DEFAULT NULL,
  `Localidad` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Sexo` char(1) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `Id_Especialidad` bigint(20) DEFAULT NULL,
  `IdJornada` int(11) DEFAULT NULL,
  `Id_Usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Legajo`),
  KEY `FK9B4BB42A7415813` (`Id_Usuario`),
  KEY `FK9B4BB42A756D7B0D` (`Id_Especialidad`),
  KEY `FK9B4BB42A2824E4DA` (`IdJornada`),
  CONSTRAINT `FK9B4BB42A2824E4DA` FOREIGN KEY (`IdJornada`) REFERENCES `jornadas` (`id`),
  CONSTRAINT `FK9B4BB42A7415813` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK9B4BB42A756D7B0D` FOREIGN KEY (`Id_Especialidad`) REFERENCES `especialidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


CREATE TABLE `pacientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Apellido` varchar(255) DEFAULT NULL,
  `CorreoElectronico` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `FechaNacimiento` varchar(255) DEFAULT NULL,
  `Localidad` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Provincia` varchar(255) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `Activo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `turnos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Estado` varchar(255) DEFAULT NULL,
  `Fecha` varchar(255) DEFAULT NULL,
  `Hora` varchar(255) DEFAULT NULL,
  `Observacion` varchar(255) DEFAULT NULL,
  `Legajo` int DEFAULT NULL,
  `Paciente_id` bigint DEFAULT NULL,
  `id_Turno` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK95FD4F411E3A71A6` (`Legajo`),
  KEY `FK95FD4F41BB6C029F` (`Paciente_id`),
  KEY `FK95FD4F41629BA564` (`id_Turno`),
  CONSTRAINT `FK95FD4F411E3A71A6` FOREIGN KEY (`Legajo`) REFERENCES `medicos` (`Legajo`),
  CONSTRAINT `FK95FD4F41629BA564` FOREIGN KEY (`id_Turno`) REFERENCES `pacientes` (`id`),
  CONSTRAINT `FK95FD4F41BB6C029F` FOREIGN KEY (`Paciente_id`) REFERENCES `pacientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `jornadas` (
  `IdJornada` int NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(50) NOT NULL,
  `Estado` bit(1) NOT NULL,
  `InicioLunes` int NOT NULL,
  `FinLunes` int NOT NULL,
  `InicioMartes` int NOT NULL,
  `FinMartes` int NOT NULL,
  `InicioMiercoles` int NOT NULL,
  `FinMiercoles` int NOT NULL,
  `InicioJueves` int NOT NULL,
  `FinJueves` int NOT NULL,
  `InicioViernes` int NOT NULL,
  `FinViernes` int NOT NULL,
  `InicioSabado` int NOT NULL,
  `FinSabado` int NOT NULL,
  `InicioDomingo` int NOT NULL,
  `FinDomingo` int NOT NULL,
  PRIMARY KEY (`IdJornada`),
  UNIQUE KEY `UK_InicioFin` (`InicioLunes`,`FinLunes`,`InicioMartes`,`FinMartes`,`InicioMiercoles`,`FinMiercoles`,`InicioJueves`,`FinJueves`,`InicioViernes`,`FinViernes`,`InicioSabado`,`FinSabado`,`InicioDomingo`,`FinDomingo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuarios` (`Nombre`,`Password`,`perfil`,`activo`)
VALUES('admin','admin',1,1);

-- Insertar 4 provincias 

INSERT INTO provincia (nombre) VALUES
    ('Buenos Aires'),
    ('Córdoba'),
    ('Santa Fe'),
    ('Mendoza');    


-- insertar 5 localidades para cada provincia

-- Insertar localidades para la provincia de Buenos Aires

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('La Plata', 1),
    ('Mar del Plata', 1),
    ('Bahía Blanca', 1),
    ('Quilmes', 1);

-- Insertar localidades para la provincia de Córdoba

INSERT INTO localidad (nombre, Id_Provincia) VALUES
    ('Córdoba', 2),
    ('Villa María', 2),
    ('Río Cuarto', 2),
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
    ('Luján de Cuyo', 4);
    
    
INSERT INTO jornadas (id,Descripcion,InicioLunes,FinLunes,InicioMartes,FinMartes,InicioMiercoles,FinMiercoles,InicioJueves,FinJueves,InicioViernes,FinViernes,
InicioSabado,FinSabado,InicioDomingo,FinDomingo)
VALUES
(1, 'Lunes a viernes, 8 a 12',8,12,8,12,8,12,8,12,8,12,0,0,0,0),
(2, 'Lunes a miercoles, 15 a 19',15,19,15,19,15,19,0,0,0,0,0,0,0,0),
(3, 'Viernes, sábado y domingo, 18 a 21',0,0,0,0,0,0,0,0,18,21,18,21,18,21); 
