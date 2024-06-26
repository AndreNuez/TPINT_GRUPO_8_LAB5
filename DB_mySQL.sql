--Creates de DB 
use tpint_grupo_8_lab5;

CREATE TABLE `especialidad` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `medico` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `medicos` (
  `Legajo` int NOT NULL AUTO_INCREMENT,
  `Apellido` varchar(255) DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `FNac` tinyblob,
  `Localidad` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Sexo` char(1) DEFAULT NULL,
  `Telefono` varchar(255) DEFAULT NULL,
  `Id_Especialidad` bigint DEFAULT NULL,
  `Id_Usuario` bigint DEFAULT NULL,
  `Activo` tinyint DEFAULT NULL,
  `Jornada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Legajo`),
  KEY `FK9B4BB42A7415813` (`Id_Usuario`),
  KEY `FK9B4BB42A756D7B0D` (`Id_Especialidad`),
  CONSTRAINT `FK9B4BB42A7415813` FOREIGN KEY (`Id_Usuario`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK9B4BB42A756D7B0D` FOREIGN KEY (`Id_Especialidad`) REFERENCES `especialidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

INSERT INTO `dbhibernate`.`usuarios` (`Nombre`,`Password`,`medico`)
VALUES('admin','admin',null);
