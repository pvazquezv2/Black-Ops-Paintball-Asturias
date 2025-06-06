-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 03-06-2025 a las 16:31:01
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `paintball_pelayo_proyectodaw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contactos`
--

CREATE TABLE `contactos` (
  `id` bigint(20) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `asunto` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fecha_contacto` datetime(6) NOT NULL,
  `mensaje` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escenarios`
--

CREATE TABLE `escenarios` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `ubicacion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `escenarios`
--

INSERT INTO `escenarios` (`id`, `nombre`, `ubicacion`) VALUES
(1, 'El Bastión del Norte', 'Castillo de Gaurón (Castrillón)'),
(2, 'La Casona Perdida', 'Pueblo de Sietes (Villaviciosa)'),
(3, 'Lagos De Covadonga', 'Lagos de Covadonga (Cangas de Onís)'),
(4, 'Fuerza Minera', 'Pozo Sotón (San Martín del Rey Aurelio)'),
(5, 'Selva Astur', 'Bosque de Muniellos (Cangas de Narcea)'),
(6, 'Playa De Frexulfe', 'Playa de Frejulfe (Navia)');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id` bigint(20) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `id_escenario` bigint(20) DEFAULT NULL,
  `hora_fin` time(6) DEFAULT NULL,
  `hora_inicio` time(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`id`, `categoria`, `descripcion`, `fecha`, `imagen_url`, `titulo`, `id_escenario`, `hora_fin`, `hora_inicio`) VALUES
(6, 'Torneo Oficial', 'El torneo más importante del año. Equipos de toda España\r\n                compiten en intensas partidas de eliminación y asalto. Premios,\r\n                trofeos y más.', '2025-06-25', '/img/castillo8.jpg', 'Torneo Nacional de Black Ops', 1, '19:00:00.000000', '10:00:00.000000'),
(7, 'Evento Temático', 'Sobrevive a una horda de jugadores zombis en un entorno oscuro y\r\n                apocalíptico. ¡Trae linterna y nervios de acero!', '2025-07-01', '/img/evento_noche.jpg', 'La Noche de los No Muertos', 2, '00:00:00.000000', '20:00:00.000000'),
(8, 'Evento Corporativo', 'Mejora el trabajo en equipo, la comunicación y la diversión con\r\n                este evento especial para empresas. Se incluye guía y\r\n                equipamiento.', '2025-07-18', '/img/mina3.jpg', 'Black Ops Team Building', 4, '13:00:00.000000', '10:00:00.000000'),
(9, 'Familiar / Infantil', 'Actividades para todas las edades, juegos de airsoft con\r\n                réplicas ligeras, zona picnic y castillos inflables. ¡Diversión\r\n                asegurada!', '2025-08-07', '/img/bosque1.jpg', 'Día Familiar de Black Ops', 5, '17:00:00.000000', '11:00:00.000000'),
(10, 'Liga', 'Comienza la liga regional. Formato por equipos, con sistema de\r\n                puntuación y árbitros oficiales. Muchos premios y mucha\r\n                competencia', '2025-09-13', '/img/lago1.jpg', 'Liga Black Ops Regional - Jornada 1', 3, '16:00:00.000000', '10:00:00.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ofertas`
--

INSERT INTO `ofertas` (`id`, `descripcion`, `titulo`) VALUES
(1, '50% de descuento en reservas de lunes.', 'Lunes Locos'),
(2, 'El cumpleañero juega GRATIS con grupos de 8 o más.', 'Pack Cumpleaños'),
(3, '¡Juega 2 horas al precio de 1 durante julio y agosto!', 'Promoción Verano'),
(4, 'Partida extra gratuita con chocolate caliente incluido.', 'Desafío Invierno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` bigint(20) NOT NULL,
  `apellido1` varchar(255) NOT NULL,
  `apellido2` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `rol` enum('ADMIN','USUARIO') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `apellido1`, `apellido2`, `email`, `nombre`, `nombre_usuario`, `password`, `rol`) VALUES
(1, 'admin', 'admin', 'admin@admin.es', 'admin', 'admin', '$2a$10$TrvnhlFF84P99MMTOTCmfOZFkKKS4d3Q7qaZVnJSY8TbCGTq7S1/.', 'ADMIN'),
(3, 'Vázquez', 'Vidal', 'pelavv97@gmail.com', 'Pelayo', 'pelayovv20', '$2a$10$OnJP7QIaPf20bJOUSg3PxePfMoYirhs7tz5yT9cizLezehjO04m6G', 'USUARIO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` bigint(20) NOT NULL,
  `estado` enum('CANCELADA','CONFIRMADA','PENDIENTE','REALIZADA') NOT NULL,
  `fecha_realizada` date NOT NULL,
  `fecha_reserva` datetime(6) NOT NULL,
  `info_adicional` varchar(500) DEFAULT NULL,
  `modo_juego` varchar(255) NOT NULL,
  `numero_personas` int(11) NOT NULL,
  `pack` varchar(255) NOT NULL,
  `id_escenario` bigint(20) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscripciones`
--

CREATE TABLE `suscripciones` (
  `id` bigint(20) NOT NULL,
  `fecha_realizada` date NOT NULL,
  `id_evento` bigint(20) DEFAULT NULL,
  `id_persona` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `contactos`
--
ALTER TABLE `contactos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `escenarios`
--
ALTER TABLE `escenarios`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5kqgc9t9eu0d8km1oy7b2mcr6` (`id_escenario`);

--
-- Indices de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKlrw7flsg11d8nhgyvueqtnp8e` (`email`),
  ADD UNIQUE KEY `UKmjjtvw1f52la4ved9j6jhtyia` (`nombre_usuario`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt3xycarfu0j7h8m8t8m80u72t` (`id_escenario`),
  ADD KEY `FKbi0px6iin8co9a7uonadw6dbv` (`id_persona`);

--
-- Indices de la tabla `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indices de la tabla `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indices de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9wnjm9fmtw9m2sh8y9v55kslr` (`id_evento`),
  ADD KEY `FKhsgp6dmvmm77kw171bhavjwa6` (`id_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contactos`
--
ALTER TABLE `contactos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `escenarios`
--
ALTER TABLE `escenarios`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `FK5kqgc9t9eu0d8km1oy7b2mcr6` FOREIGN KEY (`id_escenario`) REFERENCES `escenarios` (`id`);

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `FKbi0px6iin8co9a7uonadw6dbv` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`),
  ADD CONSTRAINT `FKt3xycarfu0j7h8m8t8m80u72t` FOREIGN KEY (`id_escenario`) REFERENCES `escenarios` (`id`);

--
-- Filtros para la tabla `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;

--
-- Filtros para la tabla `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD CONSTRAINT `FK9wnjm9fmtw9m2sh8y9v55kslr` FOREIGN KEY (`id_evento`) REFERENCES `eventos` (`id`),
  ADD CONSTRAINT `FKhsgp6dmvmm77kw171bhavjwa6` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
