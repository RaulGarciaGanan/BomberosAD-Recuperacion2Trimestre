CREATE TABLE `bomberos` (
  `codigo` varchar(6) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(20) NOT NULL,
  `edad` int DEFAULT NULL,
  `estado_actual` varchar(40) NOT NULL,
  `rango` varchar(20) DEFAULT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `emergencias` (
  `codigo` varchar(6) NOT NULL,
  `ubicacion` varchar(40) NOT NULL,
  `ciudad` varchar(40) NOT NULL,
  `tipo` varchar(40) NOT NULL,
  `estado_actual` varchar(40) NOT NULL,
  `nivel_gravedad` varchar(40) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `gestionparque` (
  `codbombero` varchar(6) NOT NULL,
  `codvehiculo` varchar(6) NOT NULL,
  `codemergencia` varchar(6) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`codbombero`,`codvehiculo`,`codemergencia`),
  KEY `codbombero_idx` (`codbombero`),
  KEY `codvehiculo_idx` (`codvehiculo`),
  KEY `codemergencia_idx` (`codemergencia`),
  CONSTRAINT `codbombero` FOREIGN KEY (`codbombero`) REFERENCES `bomberos` (`codigo`),
  CONSTRAINT `codemergencia` FOREIGN KEY (`codemergencia`) REFERENCES `emergencias` (`codigo`),
  CONSTRAINT `codvehiculo` FOREIGN KEY (`codvehiculo`) REFERENCES `vehiculos` (`codio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehiculos` (
  `codigo` varchar(6) NOT NULL,
  `matricula` varchar(10) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `disponibilidad` varchar(45) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`codio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

