-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 12-09-2023 a las 00:09:22
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dan`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_CLIENTES`
--

CREATE TABLE `USR_CLIENTES` (
  `ID` int NOT NULL,
  `RAZON_SOCIAL` varchar(250) NOT NULL,
  `CUIT` varchar(12) NOT NULL,
  `CORREO_ELECTRONICO` varchar(255) NOT NULL,
  `MAX_CTA_CTE` double NOT NULL,
  `HABILITADO_ONLINE` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_TIPO_USUARIO`
--

CREATE TABLE `USR_TIPO_USUARIO` (
  `ID` int NOT NULL,
  `TIPO` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `USR_TIPO_USUARIO`
--

INSERT INTO `USR_TIPO_USUARIO` (`ID`, `TIPO`) VALUES
(1, 'ADMIN'),
(2, 'EMPLEADO'),
(3, 'CLIENTE');


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_USUARIOS`
--

CREATE TABLE `USR_USUARIOS` (
  `ID` int NOT NULL,
  `USER_NAME` varchar(80) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `CORREO_ELECTRONICO` varchar(255) NOT NULL,
  `ID_CLIENTE` int NOT NULL,
  `ID_TIPO_USUARIO` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `USR_CLIENTES`
--
ALTER TABLE `USR_CLIENTES`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `USR_TIPO_USUARIO`
--
ALTER TABLE `USR_TIPO_USUARIO`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `USR_USUARIOS`
--
ALTER TABLE `USR_USUARIOS`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_CLIENTE` (`ID_CLIENTE`),
  ADD KEY `FK_TIPO_USUARIO` (`ID_TIPO_USUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `USR_CLIENTES`
--
ALTER TABLE `USR_CLIENTES`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `USR_USUARIOS`
--
ALTER TABLE `USR_USUARIOS`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `USR_USUARIOS`
--
ALTER TABLE `USR_USUARIOS`
  ADD CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `USR_CLIENTES` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_TIPO_USUARIO` FOREIGN KEY (`ID_TIPO_USUARIO`) REFERENCES `USR_TIPO_USUARIO` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
