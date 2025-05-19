-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 06-05-2025 a las 12:53:44
-- Versión del servidor: 11.5.2-MariaDB
-- Versión de PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hotel_db`
--

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `automaticReservations`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `automaticReservations` (IN `numLines` INT)   BEGIN
    DECLARE varCounter INT DEFAULT 0;
    DECLARE varCustomerId INT;
    DECLARE varRoomId INT;
    DECLARE varCategory INT;
    DECLARE varPrice INT;
    DECLARE varCheckIn DATE;
    DECLARE varCheckOut DATE;
    DECLARE hayConflictos INT;

    WHILE varCounter < numLines DO
        -- Generar fechas aleatorias
        SET varCheckIn = ADDDATE(CURRENT_DATE(), INTERVAL FLOOR(1 + RAND() * 100) DAY);
        SET varCheckOut = ADDDATE(varCheckIn, INTERVAL FLOOR(RAND() * 7 + 1) DAY);

        -- Seleccionar cliente aleatorio
        SELECT customer_id
        INTO varCustomerId
        FROM customers
        ORDER BY RAND()
        LIMIT 1;

        -- Buscar habitación disponible sin solapamiento
        SELECT room_id, category_id
        INTO varRoomId, varCategory
        FROM rooms
       WHERE room_id NOT IN (
    		SELECT room_id
    		FROM reservations
    		WHERE NOT (
        		check_out <= varCheckIn OR
        		check_in >= varCheckOut
    		)
		)
        ORDER BY RAND()
        LIMIT 1;

        -- Verificar si se encontró una habitación
        IF varRoomId IS NOT NULL THEN
           
                -- Obtener el precio de la categoría
                SELECT category_price
                INTO varPrice
                FROM categories
                WHERE category_id = varCategory
                LIMIT 1;

                -- Insertar la reserva
                INSERT INTO reservations(customer_id, room_id, price_per_night, check_in, check_out)
                VALUES (varCustomerId, varRoomId, varPrice, varCheckIn, varCheckOut);

                -- Incrementar contador solo si se insertó bien
                SET varCounter = varCounter + 1;
        END IF;

    END WHILE;
END$$

DROP PROCEDURE IF EXISTS `generateInvoice`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `generateInvoice` (IN `varReservationId` INT)   BEGIN
	DECLARE i INT DEFAULT 0;
    DECLARE varLengthServices INT DEFAULT 0;
    DECLARE varJsonData JSON;
    DECLARE varService VARCHAR(100);
    DECLARE varServiceCategory VARCHAR(50);
    DECLARE varServiceName VARCHAR(50);
    DECLARE varServicePrice DECIMAL(10,2);
    DECLARE varRoomCategory VARCHAR(100);
    DECLARE varRoomSubtotal DECIMAL(10,2);
    DECLARE varJsonNull JSON;
    
    SELECT c.forname, c.surname, c.email, c.dni, c.phone
    FROM customers AS c
    INNER JOIN reservations AS r ON c.customer_id = r.customer_id
    WHERE r.reservation_id = varReservationId;
    

	CREATE TEMPORARY TABLE invoice (
    	element VARCHAR(100),
        price DECIMAL(10,2)
    );
    
   	SELECT c.category_name
    INTO varRoomCategory
	FROM reservations AS res
	INNER JOIN rooms AS roo ON res.room_id = roo.room_id
    INNER JOIN categories AS c ON roo.category_id = c.category_id
    WHERE res.reservation_id = varReservationId;
    
    SELECT subtotal
    INTO varRoomSubtotal
    FROM reservations_view
    WHERE reservation_id = varReservationId;
    
    INSERT INTO invoice(element, price)
    VALUES(varRoomCategory, varRoomSubtotal);
    
    SELECT extras_json 
    INTO varJsonNull
    FROM reservations 
    WHERE reservation_id = varReservationId ;
    
    IF varJsonNull IS NULL THEN
    	SELECT *
        FROM invoice;
 		
        SELECT SUM(price) AS total
        FROM invoice;
    ELSE
     	-- Obtener el JSON
   		SELECT extras_json INTO varJsonData
    	FROM reservations
    	WHERE reservation_id = varReservationId;

    	-- Cuántos elementos tiene el array
    	SET varLengthServices = JSON_LENGTH(JSON_KEYS(varJsonData));
    	
    	IF varLengthServices > 0 THEN
    
        	-- Recorrer el array
        	WHILE i < varLengthServices DO
           		-- Extraer el nombre del servicio (clave)
            	SET varService = JSON_UNQUOTE(
                	JSON_EXTRACT(JSON_KEYS(varJsonData), CONCAT('$[', i, ']'))
            	);

            	-- Extraer cada campo del JSON
            	SET varServiceName = varService;
            	SET varServicePrice = CAST(
                	JSON_UNQUOTE(JSON_EXTRACT(varJsonData, CONCAT('$.', varService, '.total'))) AS DECIMAL(10,2)
            	);

           		-- Insertar en la tabla temporal
            	INSERT INTO invoice(element, price)
            	VALUES (varServiceName, varServicePrice);
         
            	SET i = i + 1;
        	END WHILE;
            
        	SELECT *
       	 	FROM invoice;
 		
        	SELECT SUM(price) AS total
        	FROM invoice;
    	END IF;
    END IF;
END$$

DROP PROCEDURE IF EXISTS `GetServices`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetServices` (IN `varReservationId` INT)   BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE varLengthServices INT DEFAULT 0;
    DECLARE varJsonData JSON;
    DECLARE varService VARCHAR(100);
    DECLARE varServiceCategory VARCHAR(50);
    DECLARE varServiceName VARCHAR(50);
    DECLARE varServicePrice DECIMAL(10,2);

    -- Obtener el JSON
    SELECT extras_json INTO varJsonData
    FROM reservations
    WHERE reservation_id = varReservationId;

    -- Cuántos elementos tiene el array
    SET varLengthServices = JSON_LENGTH(JSON_KEYS(varJsonData));
    
    IF varLengthServices > 0 THEN
        CREATE TEMPORARY TABLE myservices (
            service_category VARCHAR(100),
            service_price DECIMAL(10,2)
        );
        
        -- Recorrer el array
        WHILE i < varLengthServices DO
            -- Extraer el nombre del servicio (clave)
            SET varService = JSON_UNQUOTE(
                JSON_EXTRACT(JSON_KEYS(varJsonData), CONCAT('$[', i, ']'))
            );

            -- Extraer cada campo del JSON
            SET varServiceName = varService;
            SET varServicePrice = CAST(
                JSON_UNQUOTE(JSON_EXTRACT(varJsonData, CONCAT('$.', varService, '.total'))) AS DECIMAL(10,2)
            );

            -- Insertar en la tabla temporal
            INSERT INTO myservices(service_category, service_price)
            VALUES (varServiceName, varServicePrice);
         
            SET i = i + 1;
        END WHILE;
        
        -- Seleccionar la suma de los precios por categoría
        SELECT *
        FROM myservices;
  
    END IF;
END$$

DROP PROCEDURE IF EXISTS `swapRooms`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `swapRooms` (IN `varReservationId` INT)   BEGIN
	DECLARE varInitialRoom INT;
    DECLARE varFinalRoom INT DEFAULT 0;
    DECLARE varDateIn DATE;
    DECLARE varDateOut DATE;
    DECLARE varCategoryId INT;
    
    SELECT room_id, date_in, date_out
    INTO varInitialRoom, varDateIn, varDateOut
    FROM reservations
    WHERE reservation_id = varReservationId;
    
    SELECT category_id
    INTO varCategoryId
    FROM rooms
    WHERE room_id = varInitialRoom;
    
    SELECT room_id
    INTO varFinalRoom
    FROM rooms
    WHERE room_id NOT IN(
    	SELECT room_id
        FROM reservations
        WHERE date_in < varDateOut AND date_out > varDateIn AND isUnswappable = 0
    ) AND category_id = varCategoryId
    ORDER BY RAND();
    
    IF varFinalRoom != 0 THEN
    
    	UPDATE reservations
        SET tag = "current"
        WHERE room_id = varInitialRoom;
        
        UPDATE reservations
        SET tag = "new"
        WHERE room_id = varFinalRoom;
        
        UPDATE reservations
        SET room_id = varFinalRoom
        WHERE tag = "current";
        
        UPDATE reservations
        SET room_id = varInitialRoom
        WHERE tag = "new";
        
        UPDATE reservations
        SET tag = NULL;

	END IF;
END$$

DROP PROCEDURE IF EXISTS `updateState`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateState` ()  DETERMINISTIC BEGIN

    UPDATE reservations
    SET state = 'check_in'
    WHERE date_in = CURRENT_DATE();

    UPDATE reservations
    SET state = 'check_out'
    WHERE date_out = CURRENT_DATE();


END$$

--
-- Funciones
--
DROP FUNCTION IF EXISTS `downgradeRoom`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `downgradeRoom` (IN `varReservationId` INT, IN `varFinalRoom` INT) RETURNS TINYINT(1) DETERMINISTIC BEGIN
	IF varFinalRoom = 0 THEN
    	RETURN FALSE;
    ELSE
    	UPDATE reservations
        SET room_id = varFinalRoom
        WHERE reservation_id = varReservationId;
        
        RETURN TRUE;
   END IF;
END$$

DROP FUNCTION IF EXISTS `upgradeRoom`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `upgradeRoom` (IN `varReservationId` INT, IN `varFinalRoom` INT) RETURNS TINYINT(1) DETERMINISTIC BEGIN
	IF varFinalRoom = 0 THEN
    	RETURN FALSE;
    ELSE
    	UPDATE reservations
        SET room_id = varFinalRoom
        WHERE reservation_id = varReservationId;
        
        RETURN TRUE;
   END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(100) DEFAULT NULL,
  `category_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`, `category_price`) VALUES
(1, 'Habitación estándar', 100.00),
(2, 'Habitación superior', 150.00),
(3, 'Suite presidencial', 300.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `forname` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `dni` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `pwd` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `comp_pwd` varbinary(50) NOT NULL,
  `uncomp_pwd` varchar(100) NOT NULL,
  `has_pwd` varchar(128) NOT NULL,
  `encrypt_pwd` varbinary(100) NOT NULL,
  `decrypt_pwd` varchar(100) NOT NULL,
  `mysalt` varchar(100) NOT NULL,
  `aes_key` varchar(100) NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `dni` (`dni`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `customers`
--

INSERT INTO `customers` (`customer_id`, `forname`, `surname`, `dni`, `email`, `pwd`, `phone`, `birthdate`, `comp_pwd`, `uncomp_pwd`, `has_pwd`, `encrypt_pwd`, `decrypt_pwd`, `mysalt`, `aes_key`) VALUES
(1, 'Carlos', 'Gomez', '12345678A', 'carlos.gomez@example.com', 'carlos798', '600123456', '1990-05-14', 0x09000000789c4b4e2ccac92f36b7b400001178032d, 'carlos798', 'adb04b0351dc424038e56608121e8c2ca4b342c43baed8afffb449f517838b8fe3ff35fe6cf5c74970ee33c1b68fe526b36d5c082499220ae550250468c8971a', 0x5666291b6a81f6e766697fc23e7ab785, 'carlos798', 'xyz', 'My cat has 3 legs'),
(2, 'Laura', 'Fernandez', '23456789B', 'laura.fernandez@example.com', 'lauraiantonio', '611234567', '1985-11-23', 0x0d000000789ccb492c2d4acc4ccc2bc9cfcbcc070026070577, 'lauraiantonio', 'ec037abcbaba4f6c5a94c4041086c6fe9ced57383e801e9277ab20e438d8a60a6430f2623a863a5a778cb2bbe9f220197d6d6cee48f02a46dd726d5a47b472c5', 0x81b688e4c508b31e7505185f976b888e, 'lauraiantonio', 'xyz', 'My cat has 3 legs'),
(3, 'Pedro', 'Lopez', '34567890C', 'pedro.lopez@example.com', 'pedroelcapataz19', '622345678', '1992-07-08', 0x10000000789c2b484d29ca4fcd494e2c482c49ac32b4040037aa063a, 'pedroelcapataz19', 'c37e31d355ac9abd950b6cca04fe32e6c519d653c2db7078914e035979d6942aa9a02e0271b71c931b24cd279f1d7aa079d1e1327a97a32d99350e57d57b0e27', 0xb397f2852e014720dd220e01807f9bf449fe7f9674096993432b233c74a7d6d1, 'pedroelcapataz19', 'xyz', 'My cat has 3 legs'),
(4, 'Ana', 'Martinez', '45678901D', 'ana.martinez@example.com', 'guardian21', '633456789', '1995-09-17', 0x0a000000789c4b2f4d2c4ac94ccc33320400161b03af, 'guardian21', 'b81916c58240d91ef9e138c72df03f35ffd0045b4cd86dd77553a8c4932e5e15e7f5e01be016ab8e44fd0b8ba195a784414619d461b20e8ed5f2507d48fa8776', 0x43ec7f65a7e05a8910132b5e5d7d11cb, 'guardian21', 'xyz', 'My cat has 3 legs'),
(5, 'Javier', 'Sanchez', '56789012E', 'javier.sanchez@example.com', 'Javier2342', '644567890', '1988-02-26', 0x0a000000789cf34a2ccb4c2d3232363102001379032d, 'Javier2342', '6f58c81ee92e5c51e55a5f8c804e8a9bcf63302c45fc68ba2b3cb8db24dd28f73ca64bf85a24105014a7c4ec554dd4e6c699497f5557f5290f9b4d7debdad265', 0xe118068d91d9767f8eba76a2fe7096bb, 'Javier2342', 'xyz', 'My cat has 3 legs'),
(6, 'Marta', 'Diaz', '67890123F', 'marta.diaz@example.com', 'MartaDiazyAlpha', '655678901', '1993-04-05', 0x0f000000789cf34d2c2a4974c94cacaa74cc29c84804002e4805dd, 'MartaDiazyAlpha', '5fe66efbe45f2d1bafc1c6fb40042604293c6fd2678c6ec6b1a2aa758a3d33eae1f91cb54dd56adc07b176ba511ccd1119829a6bd3036dc8e6c614cd5a283540', 0xc1332debf739d938d2777ee2c42a64f3, 'MartaDiazyAlpha', 'xyz', 'My cat has 3 legs'),
(7, 'Sergio', 'Ruiz', '78901234G', 'sergio.ruiz@example.com', 'SergioSergio', '666789012', '1996-12-30', 0x0c000000789c0b4e2d4acfcc0f0693001ed404d3, 'SergioSergio', '953a4e6a64bcf6ffbbd7c8ff24badaa5cb59dc0cb05eb633346928785e75961af63e73812cde494ef451a4cd002dc4edd73139ff06215dfe9ed534f5dafd1a16', 0xb0112d551776349c94a4ab079e484f73, 'SergioSergio', 'xyz', 'My cat has 3 legs'),
(8, 'Elena', 'Hernandez', '89012345H', 'elena.hernandez@example.com', 'Elenaherna1987', '677890123', '1991-06-21', 0x0e000000789c73cd49cd4bcc482dca4b34b4b4300700270d04cd, 'Elenaherna1987', '10cfbdf3adfe43305f873e6887ccb1039f8ec74488c87a6ac227fd03422836ba45f35030259f3b3bf05f8f04c8ffbef4314c0720e3ddc3e45ae011d6cdae468f', 0x3be1daeead703cc3690143e69b463903, 'Elenaherna1987', 'xyz', 'My cat has 3 legs'),
(9, 'Roberto', 'Jimenez', '90123456I', 'roberto.jimenez@example.com', 'roberte.video23', '688901234', '1987-08-15', 0x0f000000789c2bca4f4a2d2a49d52bcb4c49cd37320600300c059e, 'roberte.video23', '5f704fa2edabc3e36a0e7833fec15e8c612a71c002b22359db5686d6c3148b48680ded791f075d3974cef15458971b7a465c1dfabd88ae00bf3fcf7e28349d0d', 0x214bcdf05685885350ef02da02ea7198, 'roberte.video23', 'xyz', 'My cat has 3 legs'),
(10, 'Isabel', 'Moreno', '01234567J', 'isabel.moreno@example.com', 'isabelPantoja', '699012345', '1994-03-10', 0x0d000000789ccb2c4e4c4acd0948cc2bc9cf4a040024a0053e, 'isabelPantoja', '99c7b187856fafdb9b0d1514a399649488e00cb9e7f24d74f37521ed4a340b3bfdd8a6a7734f938c7d1abf4588df6075de058a20fc80563728275789c36d95ce', 0xa4d0da8a64df24c3bd702264d643f31d, 'isabelPantoja', 'xyz', 'My cat has 3 legs');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `role` enum('clean','maintenance') DEFAULT NULL,
  `dni` varchar(20) NOT NULL,
  `birthdate` date NOT NULL,
  `phone` varchar(20) NOT NULL,
  `hire_date` date NOT NULL,
  `shift` enum('morning','afternoon','night') DEFAULT NULL,
  `status` enum('active','inactive') DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `room_id` int(11) DEFAULT NULL,
  `price_per_night` decimal(10,2) NOT NULL,
  `date_in` date NOT NULL,
  `date_out` date NOT NULL,
  `state` enum('book','canceled','check_in','check_out') NOT NULL DEFAULT 'book',
  `isUnswappable` tinyint(4) NOT NULL DEFAULT 0,
  `tag` enum('new','current') DEFAULT NULL,
  `extras_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`extras_json`)),
  `guests_json` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`guests_json`)),
  PRIMARY KEY (`reservation_id`),
  KEY `customer_id` (`customer_id`),
  KEY `room_id` (`room_id`)
) ENGINE=MyISAM AUTO_INCREMENT=595 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `customer_id`, `room_id`, `price_per_night`, `date_in`, `date_out`, `state`, `isUnswappable`, `tag`, `extras_json`, `guests_json`) VALUES
(1, 5, 16, 150.00, '2025-06-04', '2025-06-09', 'book', 1, 'current', '{\n    \"restaurant\":{\n      \"total\": 50.00,\n      \"tickets\":[\n        {\n          \"ticket_number\": 432,\n          \"service_name\": \"breakfast\",\n          \"service_qty\": 1,\n          \"service_price\": 20.00,\n          \"service_date\": \"2025-06-10\",\n          \"ticket_details\" : null\n        },\n        {\n          \"ticket_number\": 455,\n          \"service_name\": \"dinner\",\n          \"service_qty\": 1,\n          \"service_price\": 30.00,\n          \"service_date\": \"2025-06-10\",\n          \"ticket_details\" : null\n        }\n      ] \n    },\n  \n    \"gym\":{\n      \"total\": 20.00,\n      \"tickets\":[\n        {\n          \"service_name\": \"1h gym session\",\n          \"service_qty\": 2,\n          \"service_price\": 10.00,\n          \"service_date\": \"2025-06-10\",\n          \"ticket_details\": null\n        }\n      ]\n    }\n  }', NULL),
(2, 5, 13, 300.00, '2025-08-01', '2025-08-08', 'book', 0, NULL, '{\n    \"spa\": {\n      \"total\": 30.00,\n      \"tickets\": [\n        {\n          \"service_name\": \"1h session\",\n          \"service_qty\": 3,\n          \"service_price\": 10.00,\n          \"service_date\": \"2025-06-11\",\n          \"ticket_details\": null\n        }\n      ]\n    },\n          \n    \"restaurant\": {\n      \"total\": 60.00,\n      \"tickets\": [\n        {\n          \"ticket_number\": 123,\n          \"service_name\": \"dinner\",\n          \"service_qty\": 1 ,\n          \"service_price\": 60.00,\n          \"service_date\": \"2025-06-12\",\n          \"ticket_details\": null\n        }\n      ]\n    }\n  }', NULL),
(3, 4, 6, 100.00, '2025-07-25', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(4, 10, 3, 150.00, '2025-06-23', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(5, 10, 1, 100.00, '2025-07-28', '2025-07-30', 'book', 0, NULL, NULL, NULL),
(6, 10, 14, 150.00, '2025-06-08', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(7, 3, 2, 100.00, '2025-05-04', '2025-05-05', 'book', 0, NULL, NULL, NULL),
(8, 1, 13, 300.00, '2025-05-27', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(9, 8, 6, 100.00, '2025-05-19', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(10, 4, 3, 150.00, '2025-06-10', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(11, 1, 9, 100.00, '2025-05-30', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(12, 9, 16, 150.00, '2025-07-05', '2025-07-10', 'book', 0, 'current', NULL, NULL),
(13, 9, 6, 100.00, '2025-07-08', '2025-07-15', 'book', 0, NULL, NULL, NULL),
(14, 1, 13, 300.00, '2025-07-03', '2025-07-05', 'book', 0, NULL, NULL, NULL),
(15, 5, 3, 150.00, '2025-07-17', '2025-07-20', 'book', 0, NULL, NULL, NULL),
(16, 10, 2, 100.00, '2025-08-01', '2025-08-05', 'book', 0, NULL, NULL, NULL),
(17, 8, 7, 300.00, '2025-06-03', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(18, 9, 15, 100.00, '2025-07-04', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(19, 5, 7, 300.00, '2025-05-19', '2025-05-21', 'book', 0, NULL, NULL, NULL),
(20, 2, 10, 150.00, '2025-07-12', '2025-07-16', 'book', 0, NULL, NULL, NULL),
(21, 8, 16, 150.00, '2025-06-30', '2025-07-04', 'book', 0, 'current', NULL, NULL),
(22, 5, 2, 100.00, '2025-05-19', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(23, 10, 14, 150.00, '2025-05-07', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(24, 7, 16, 150.00, '2025-07-30', '2025-08-06', 'book', 0, 'current', NULL, NULL),
(25, 4, 10, 150.00, '2025-05-28', '2025-06-04', 'book', 0, NULL, NULL, NULL),
(26, 4, 15, 100.00, '2025-07-29', '2025-08-03', 'book', 0, NULL, NULL, NULL),
(27, 6, 11, 100.00, '2025-07-25', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(28, 10, 2, 100.00, '2025-07-03', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(29, 2, 10, 150.00, '2025-06-24', '2025-06-27', 'book', 0, NULL, NULL, NULL),
(30, 3, 3, 150.00, '2025-07-09', '2025-07-15', 'book', 0, NULL, NULL, NULL),
(31, 7, 14, 150.00, '2025-07-16', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(32, 9, 3, 150.00, '2025-05-04', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(33, 5, 11, 100.00, '2025-05-09', '2025-05-14', 'book', 0, NULL, NULL, NULL),
(34, 8, 11, 100.00, '2025-06-03', '2025-06-08', 'book', 0, NULL, NULL, NULL),
(35, 10, 9, 100.00, '2025-06-30', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(36, 1, 9, 100.00, '2025-06-06', '2025-06-11', 'book', 0, NULL, NULL, NULL),
(37, 10, 1, 100.00, '2025-04-25', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(38, 9, 7, 300.00, '2025-06-19', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(39, 9, 7, 300.00, '2025-08-01', '2025-08-02', 'book', 0, NULL, NULL, NULL),
(40, 1, 12, 150.00, '2025-05-24', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(41, 1, 13, 300.00, '2025-07-19', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(42, 2, 2, 100.00, '2025-06-08', '2025-06-11', 'book', 0, NULL, NULL, NULL),
(43, 9, 7, 300.00, '2025-05-27', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(44, 3, 14, 150.00, '2025-05-02', '2025-05-05', 'check_in', 0, NULL, NULL, NULL),
(45, 2, 12, 150.00, '2025-06-03', '2025-06-06', 'book', 0, NULL, NULL, NULL),
(46, 1, 4, 150.00, '2025-04-28', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(47, 8, 1, 100.00, '2025-05-21', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(48, 1, 5, 300.00, '2025-06-01', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(49, 7, 4, 150.00, '2025-07-22', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(50, 3, 7, 300.00, '2025-07-23', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(51, 4, 10, 150.00, '2025-07-29', '2025-07-30', 'book', 0, NULL, NULL, NULL),
(52, 8, 4, 150.00, '2025-06-03', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(53, 8, 1, 100.00, '2025-05-04', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(54, 7, 5, 300.00, '2025-07-04', '2025-07-05', 'book', 0, NULL, NULL, NULL),
(55, 2, 6, 100.00, '2025-05-01', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(56, 3, 5, 300.00, '2025-07-13', '2025-07-20', 'book', 0, NULL, NULL, NULL),
(57, 9, 16, 150.00, '2025-07-16', '2025-07-21', 'book', 0, 'current', NULL, NULL),
(58, 6, 6, 100.00, '2025-06-21', '2025-06-26', 'book', 0, NULL, NULL, NULL),
(59, 5, 9, 100.00, '2025-04-25', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(60, 2, 11, 100.00, '2025-07-13', '2025-07-17', 'book', 0, NULL, NULL, NULL),
(61, 10, 9, 100.00, '2025-07-27', '2025-08-02', 'book', 0, NULL, NULL, NULL),
(62, 6, 12, 150.00, '2025-07-06', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(63, 4, 12, 150.00, '2025-04-24', '2025-04-29', 'check_in', 0, NULL, NULL, NULL),
(64, 1, 10, 150.00, '2025-06-22', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(65, 6, 15, 100.00, '2025-05-26', '2025-05-28', 'book', 0, NULL, NULL, NULL),
(66, 3, 3, 150.00, '2025-06-03', '2025-06-10', 'book', 0, NULL, NULL, NULL),
(67, 6, 14, 150.00, '2025-07-26', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(68, 7, 13, 300.00, '2025-07-15', '2025-07-19', 'book', 0, NULL, NULL, NULL),
(69, 5, 13, 300.00, '2025-07-07', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(70, 10, 12, 150.00, '2025-06-30', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(71, 3, 15, 100.00, '2025-05-11', '2025-05-15', 'book', 0, NULL, NULL, NULL),
(72, 1, 10, 150.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(73, 3, 5, 300.00, '2025-04-29', '2025-04-30', 'check_in', 0, NULL, NULL, NULL),
(74, 4, 4, 150.00, '2025-05-02', '2025-05-07', 'check_in', 0, NULL, NULL, NULL),
(75, 5, 4, 150.00, '2025-07-04', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(76, 1, 5, 300.00, '2025-07-31', '2025-08-05', 'book', 0, NULL, NULL, NULL),
(77, 10, 11, 100.00, '2025-07-30', '2025-07-31', 'book', 0, NULL, NULL, NULL),
(78, 8, 6, 100.00, '2025-07-16', '2025-07-21', 'book', 0, NULL, NULL, NULL),
(79, 8, 9, 100.00, '2025-05-13', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(80, 8, 10, 150.00, '2025-07-18', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(81, 8, 15, 100.00, '2025-05-01', '2025-05-08', 'book', 0, NULL, NULL, NULL),
(82, 10, 5, 300.00, '2025-07-25', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(83, 4, 2, 100.00, '2025-05-13', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(84, 7, 12, 150.00, '2025-07-29', '2025-08-03', 'book', 0, NULL, NULL, NULL),
(85, 8, 11, 100.00, '2025-04-30', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(86, 7, 7, 300.00, '2025-04-25', '2025-04-27', 'check_out', 0, NULL, NULL, NULL),
(87, 3, 10, 150.00, '2025-05-11', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(88, 10, 1, 100.00, '2025-06-15', '2025-06-19', 'book', 0, NULL, NULL, NULL),
(89, 9, 2, 100.00, '2025-06-24', '2025-06-30', 'book', 0, NULL, NULL, NULL),
(90, 3, 4, 150.00, '2025-06-21', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(91, 2, 15, 100.00, '2025-04-25', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(92, 1, 16, 150.00, '2025-05-30', '2025-06-04', 'book', 0, 'current', NULL, NULL),
(93, 2, 9, 100.00, '2025-07-14', '2025-07-16', 'book', 0, NULL, NULL, NULL),
(94, 4, 15, 100.00, '2025-07-08', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(95, 6, 13, 300.00, '2025-05-13', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(96, 1, 15, 100.00, '2025-06-13', '2025-06-16', 'book', 0, NULL, NULL, NULL),
(97, 3, 14, 150.00, '2025-07-03', '2025-07-04', 'book', 0, NULL, NULL, NULL),
(98, 2, 7, 300.00, '2025-06-06', '2025-06-11', 'book', 0, NULL, NULL, NULL),
(99, 8, 15, 100.00, '2025-07-14', '2025-07-17', 'book', 0, NULL, NULL, NULL),
(100, 5, 5, 300.00, '2025-05-06', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(101, 8, 12, 150.00, '2025-07-13', '2025-07-19', 'book', 0, NULL, NULL, NULL),
(102, 5, 7, 300.00, '2025-07-06', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(103, 3, 14, 150.00, '2025-06-23', '2025-06-25', 'book', 0, NULL, NULL, NULL),
(104, 2, 2, 100.00, '2025-04-26', '2025-05-03', 'check_in', 0, NULL, NULL, NULL),
(105, 1, 5, 300.00, '2025-05-29', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(106, 9, 5, 300.00, '2025-06-14', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(107, 7, 11, 100.00, '2025-07-04', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(108, 5, 13, 300.00, '2025-05-01', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(109, 6, 4, 150.00, '2025-06-14', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(110, 8, 2, 100.00, '2025-07-10', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(111, 6, 5, 300.00, '2025-06-23', '2025-06-28', 'book', 0, NULL, NULL, NULL),
(112, 9, 10, 150.00, '2025-06-15', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(113, 7, 4, 150.00, '2025-07-13', '2025-07-18', 'book', 0, NULL, NULL, NULL),
(114, 10, 15, 100.00, '2025-05-31', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(115, 1, 5, 300.00, '2025-07-06', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(116, 10, 1, 100.00, '2025-05-12', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(117, 2, 16, 150.00, '2025-05-05', '2025-05-06', 'book', 0, 'current', NULL, NULL),
(118, 3, 9, 100.00, '2025-07-06', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(119, 2, 9, 100.00, '2025-06-16', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(120, 10, 10, 150.00, '2025-05-02', '2025-05-06', 'check_in', 0, NULL, NULL, NULL),
(121, 4, 2, 100.00, '2025-07-17', '2025-07-20', 'book', 0, NULL, NULL, NULL),
(122, 9, 9, 100.00, '2025-06-21', '2025-06-25', 'book', 0, NULL, NULL, NULL),
(123, 6, 16, 150.00, '2025-05-15', '2025-05-22', 'book', 0, 'current', NULL, NULL),
(124, 9, 11, 100.00, '2025-07-18', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(125, 7, 3, 150.00, '2025-06-30', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(126, 8, 3, 150.00, '2025-07-25', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(127, 10, 13, 300.00, '2025-06-05', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(128, 8, 15, 100.00, '2025-05-29', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(129, 8, 4, 150.00, '2025-05-27', '2025-05-28', 'book', 0, NULL, NULL, NULL),
(130, 9, 14, 150.00, '2025-05-29', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(131, 2, 16, 150.00, '2025-04-28', '2025-05-03', 'check_in', 0, 'current', NULL, NULL),
(132, 9, 14, 150.00, '2025-07-06', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(133, 7, 4, 150.00, '2025-07-06', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(134, 4, 1, 100.00, '2025-05-27', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(135, 4, 15, 100.00, '2025-07-17', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(136, 3, 4, 150.00, '2025-06-07', '2025-06-10', 'book', 0, NULL, NULL, NULL),
(137, 6, 5, 300.00, '2025-05-23', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(138, 9, 13, 300.00, '2025-05-05', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(139, 5, 9, 100.00, '2025-07-21', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(140, 10, 10, 150.00, '2025-07-26', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(141, 8, 6, 100.00, '2025-05-05', '2025-05-07', 'book', 0, NULL, NULL, NULL),
(142, 3, 9, 100.00, '2025-05-06', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(143, 2, 10, 150.00, '2025-06-05', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(144, 4, 7, 300.00, '2025-06-17', '2025-06-19', 'book', 0, NULL, NULL, NULL),
(145, 2, 16, 150.00, '2025-06-10', '2025-06-14', 'book', 0, 'current', NULL, NULL),
(146, 6, 7, 300.00, '2025-05-11', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(147, 2, 5, 300.00, '2025-04-25', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(148, 10, 4, 150.00, '2025-05-14', '2025-05-15', 'book', 0, NULL, NULL, NULL),
(149, 1, 12, 150.00, '2025-06-10', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(150, 10, 15, 100.00, '2025-04-28', '2025-04-29', 'check_in', 0, NULL, NULL, NULL),
(151, 9, 11, 100.00, '2025-06-10', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(152, 3, 1, 100.00, '2025-07-18', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(153, 6, 14, 150.00, '2025-06-19', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(154, 6, 10, 150.00, '2025-06-29', '2025-07-05', 'book', 0, NULL, NULL, NULL),
(155, 5, 4, 150.00, '2025-04-26', '2025-04-27', 'check_out', 0, NULL, NULL, NULL),
(156, 1, 4, 150.00, '2025-06-29', '2025-07-04', 'book', 0, NULL, NULL, NULL),
(157, 9, 16, 150.00, '2025-05-11', '2025-05-14', 'book', 0, 'current', NULL, NULL),
(158, 4, 3, 150.00, '2025-05-20', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(159, 9, 1, 100.00, '2025-05-13', '2025-05-15', 'book', 0, NULL, NULL, NULL),
(160, 6, 7, 300.00, '2025-07-16', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(161, 2, 12, 150.00, '2025-06-17', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(162, 1, 4, 150.00, '2025-07-29', '2025-07-31', 'book', 0, NULL, NULL, NULL),
(163, 6, 4, 150.00, '2025-05-29', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(164, 2, 1, 100.00, '2025-07-03', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(165, 8, 5, 300.00, '2025-06-05', '2025-06-12', 'book', 0, NULL, NULL, NULL),
(166, 10, 4, 150.00, '2025-05-18', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(167, 2, 7, 300.00, '2025-07-12', '2025-07-15', 'book', 0, NULL, NULL, NULL),
(168, 6, 9, 100.00, '2025-07-17', '2025-07-21', 'book', 0, NULL, NULL, NULL),
(169, 5, 11, 100.00, '2025-06-19', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(170, 9, 15, 100.00, '2025-06-09', '2025-06-12', 'book', 0, NULL, NULL, NULL),
(171, 4, 2, 100.00, '2025-07-15', '2025-07-17', 'book', 0, NULL, NULL, NULL),
(172, 9, 10, 150.00, '2025-07-05', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(173, 8, 4, 150.00, '2025-07-18', '2025-07-20', 'book', 0, NULL, NULL, NULL),
(174, 6, 9, 100.00, '2025-05-09', '2025-05-12', 'book', 0, NULL, NULL, NULL),
(175, 10, 12, 150.00, '2025-05-03', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(176, 6, 11, 100.00, '2025-05-15', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(177, 10, 1, 100.00, '2025-06-03', '2025-06-10', 'book', 0, NULL, NULL, NULL),
(178, 9, 14, 150.00, '2025-07-13', '2025-07-16', 'book', 0, NULL, NULL, NULL),
(179, 2, 6, 100.00, '2025-06-07', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(180, 2, 9, 100.00, '2025-07-11', '2025-07-14', 'book', 0, NULL, NULL, NULL),
(181, 9, 14, 150.00, '2025-05-10', '2025-05-15', 'book', 0, NULL, NULL, NULL),
(182, 8, 12, 150.00, '2025-07-26', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(183, 2, 13, 300.00, '2025-05-07', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(184, 7, 3, 150.00, '2025-05-02', '2025-05-04', 'check_in', 0, NULL, NULL, NULL),
(185, 10, 5, 300.00, '2025-04-28', '2025-04-29', 'check_in', 0, NULL, NULL, NULL),
(186, 9, 16, 150.00, '2025-07-24', '2025-07-30', 'book', 0, 'current', NULL, NULL),
(187, 7, 7, 300.00, '2025-07-09', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(188, 7, 7, 300.00, '2025-04-28', '2025-05-03', 'check_in', 0, NULL, NULL, NULL),
(189, 3, 6, 100.00, '2025-05-11', '2025-05-16', 'book', 0, NULL, NULL, NULL),
(190, 1, 10, 150.00, '2025-04-25', '2025-04-30', 'check_in', 0, NULL, NULL, NULL),
(191, 4, 9, 100.00, '2025-05-21', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(192, 8, 6, 100.00, '2025-05-27', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(193, 7, 12, 150.00, '2025-07-19', '2025-07-20', 'book', 0, NULL, NULL, NULL),
(194, 10, 13, 300.00, '2025-07-09', '2025-07-14', 'book', 0, NULL, NULL, NULL),
(195, 7, 2, 100.00, '2025-07-21', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(196, 5, 1, 100.00, '2025-05-02', '2025-05-04', 'check_in', 0, NULL, NULL, NULL),
(197, 2, 12, 150.00, '2025-04-29', '2025-05-01', 'check_in', 0, NULL, NULL, NULL),
(198, 2, 12, 150.00, '2025-05-17', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(199, 5, 12, 150.00, '2025-05-09', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(200, 3, 7, 300.00, '2025-06-22', '2025-06-28', 'book', 0, NULL, NULL, NULL),
(201, 4, 15, 100.00, '2025-05-21', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(202, 4, 1, 100.00, '2025-05-25', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(203, 2, 1, 100.00, '2025-06-20', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(204, 4, 14, 150.00, '2025-04-28', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(205, 3, 13, 300.00, '2025-06-09', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(206, 9, 13, 300.00, '2025-06-02', '2025-06-04', 'book', 0, NULL, NULL, NULL),
(207, 3, 3, 150.00, '2025-05-10', '2025-05-12', 'book', 0, NULL, NULL, NULL),
(208, 2, 10, 150.00, '2025-07-31', '2025-08-05', 'book', 0, NULL, NULL, NULL),
(209, 5, 2, 100.00, '2025-06-12', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(210, 6, 2, 100.00, '2025-05-26', '2025-06-01', 'book', 0, NULL, NULL, NULL),
(211, 4, 1, 100.00, '2025-06-29', '2025-07-01', 'book', 0, NULL, NULL, NULL),
(212, 3, 13, 300.00, '2025-06-17', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(213, 9, 5, 300.00, '2025-04-30', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(214, 6, 13, 300.00, '2025-07-23', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(215, 1, 10, 150.00, '2025-05-18', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(216, 3, 7, 300.00, '2025-05-04', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(217, 3, 12, 150.00, '2025-05-06', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(218, 2, 13, 300.00, '2025-05-23', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(219, 2, 3, 150.00, '2025-07-29', '2025-08-02', 'book', 0, NULL, NULL, NULL),
(220, 5, 2, 100.00, '2025-06-03', '2025-06-08', 'book', 0, NULL, NULL, NULL),
(221, 4, 3, 150.00, '2025-07-06', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(222, 1, 13, 300.00, '2025-06-25', '2025-06-30', 'book', 0, NULL, NULL, NULL),
(223, 10, 7, 300.00, '2025-07-29', '2025-07-31', 'book', 0, NULL, NULL, NULL),
(224, 9, 6, 100.00, '2025-05-16', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(225, 8, 6, 100.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(226, 1, 6, 100.00, '2025-06-03', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(227, 10, 13, 300.00, '2025-04-24', '2025-04-30', 'check_in', 0, NULL, NULL, NULL),
(228, 8, 13, 300.00, '2025-06-20', '2025-06-25', 'book', 0, NULL, NULL, NULL),
(229, 10, 1, 100.00, '2025-08-01', '2025-08-02', 'book', 0, NULL, NULL, NULL),
(230, 4, 14, 150.00, '2025-06-05', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(231, 3, 10, 150.00, '2025-07-09', '2025-07-11', 'book', 0, NULL, NULL, NULL),
(232, 6, 1, 100.00, '2025-07-09', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(233, 5, 14, 150.00, '2025-05-19', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(234, 1, 4, 150.00, '2025-08-01', '2025-08-04', 'book', 0, NULL, NULL, NULL),
(235, 7, 5, 300.00, '2025-07-23', '2025-07-25', 'book', 0, NULL, NULL, NULL),
(236, 8, 16, 150.00, '2025-05-07', '2025-05-08', 'book', 0, 'current', NULL, NULL),
(237, 3, 12, 150.00, '2025-06-24', '2025-06-26', 'book', 0, NULL, NULL, NULL),
(238, 2, 1, 100.00, '2025-07-13', '2025-07-18', 'book', 0, NULL, NULL, NULL),
(239, 3, 9, 100.00, '2025-06-14', '2025-06-16', 'book', 0, NULL, NULL, NULL),
(240, 8, 3, 150.00, '2025-07-20', '2025-07-25', 'book', 0, NULL, NULL, NULL),
(241, 3, 14, 150.00, '2025-08-01', '2025-08-04', 'book', 0, NULL, NULL, NULL),
(242, 7, 10, 150.00, '2025-06-09', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(243, 4, 7, 300.00, '2025-06-11', '2025-06-16', 'book', 0, NULL, NULL, NULL),
(244, 2, 5, 300.00, '2025-07-10', '2025-07-11', 'book', 0, NULL, NULL, NULL),
(245, 2, 6, 100.00, '2025-07-02', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(246, 10, 3, 150.00, '2025-04-25', '2025-05-01', 'check_in', 0, NULL, NULL, NULL),
(247, 4, 6, 100.00, '2025-05-25', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(248, 6, 2, 100.00, '2025-05-06', '2025-05-12', 'book', 0, NULL, NULL, NULL),
(249, 8, 12, 150.00, '2025-07-23', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(250, 7, 15, 100.00, '2025-05-08', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(251, 3, 16, 150.00, '2025-05-25', '2025-05-30', 'book', 0, 'current', NULL, NULL),
(252, 4, 13, 300.00, '2025-05-09', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(253, 4, 11, 100.00, '2025-06-25', '2025-07-02', 'book', 0, NULL, NULL, NULL),
(254, 5, 15, 100.00, '2025-06-27', '2025-07-04', 'book', 0, NULL, NULL, NULL),
(255, 3, 5, 300.00, '2025-06-18', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(256, 3, 5, 300.00, '2025-07-26', '2025-07-31', 'book', 0, NULL, NULL, NULL),
(257, 4, 14, 150.00, '2025-06-28', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(258, 9, 7, 300.00, '2025-07-03', '2025-07-05', 'book', 0, NULL, NULL, NULL),
(259, 3, 15, 100.00, '2025-06-18', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(260, 10, 5, 300.00, '2025-07-05', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(261, 1, 14, 150.00, '2025-04-25', '2025-04-27', 'check_out', 0, NULL, NULL, NULL),
(262, 2, 1, 100.00, '2025-04-30', '2025-05-01', 'book', 0, NULL, NULL, NULL),
(263, 3, 4, 150.00, '2025-05-08', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(264, 1, 7, 300.00, '2025-05-25', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(265, 4, 11, 100.00, '2025-05-26', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(266, 10, 16, 150.00, '2025-06-19', '2025-06-26', 'book', 0, 'current', NULL, NULL),
(267, 2, 15, 100.00, '2025-07-26', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(268, 2, 3, 150.00, '2025-05-27', '2025-05-31', 'book', 0, NULL, NULL, NULL),
(269, 6, 5, 300.00, '2025-07-22', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(270, 5, 16, 150.00, '2025-07-12', '2025-07-15', 'book', 0, 'current', NULL, NULL),
(271, 9, 1, 100.00, '2025-06-24', '2025-06-27', 'book', 0, NULL, NULL, NULL),
(272, 3, 9, 100.00, '2025-06-03', '2025-06-06', 'book', 0, NULL, NULL, NULL),
(273, 9, 6, 100.00, '2025-08-01', '2025-08-03', 'book', 0, NULL, NULL, NULL),
(274, 5, 15, 100.00, '2025-05-18', '2025-05-21', 'book', 0, NULL, NULL, NULL),
(275, 1, 11, 100.00, '2025-08-01', '2025-08-04', 'book', 0, NULL, NULL, NULL),
(276, 8, 9, 100.00, '2025-07-08', '2025-07-11', 'book', 0, NULL, NULL, NULL),
(277, 4, 10, 150.00, '2025-07-08', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(278, 8, 15, 100.00, '2025-05-10', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(279, 8, 7, 300.00, '2025-07-10', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(280, 1, 13, 300.00, '2025-05-19', '2025-05-21', 'book', 0, NULL, NULL, NULL),
(281, 9, 6, 100.00, '2025-06-27', '2025-07-01', 'book', 0, NULL, NULL, NULL),
(282, 10, 4, 150.00, '2025-06-10', '2025-06-13', 'book', 0, NULL, NULL, NULL),
(283, 3, 1, 100.00, '2025-07-24', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(284, 10, 4, 150.00, '2025-06-06', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(285, 5, 2, 100.00, '2025-06-20', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(286, 9, 12, 150.00, '2025-06-07', '2025-06-10', 'book', 0, NULL, NULL, NULL),
(287, 1, 16, 150.00, '2025-05-09', '2025-05-11', 'book', 0, 'current', NULL, NULL),
(288, 3, 3, 150.00, '2025-06-02', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(289, 4, 3, 150.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(290, 3, 14, 150.00, '2025-05-22', '2025-05-28', 'book', 0, NULL, NULL, NULL),
(291, 8, 11, 100.00, '2025-06-22', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(292, 10, 1, 100.00, '2025-06-10', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(293, 9, 10, 150.00, '2025-05-25', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(294, 8, 13, 300.00, '2025-07-28', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(295, 9, 5, 300.00, '2025-05-17', '2025-05-23', 'book', 0, NULL, NULL, NULL),
(296, 5, 1, 100.00, '2025-07-30', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(297, 9, 3, 150.00, '2025-07-15', '2025-07-17', 'book', 0, NULL, NULL, NULL),
(298, 1, 6, 100.00, '2025-07-01', '2025-07-02', 'book', 0, NULL, NULL, NULL),
(299, 9, 9, 100.00, '2025-06-11', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(300, 3, 16, 150.00, '2025-07-21', '2025-07-23', 'book', 0, 'current', NULL, NULL),
(301, 1, 16, 150.00, '2025-06-09', '2025-06-10', 'book', 0, 'current', NULL, NULL),
(302, 6, 6, 100.00, '2025-04-27', '2025-05-01', 'check_in', 0, NULL, NULL, NULL),
(303, 1, 11, 100.00, '2025-04-26', '2025-04-27', 'check_out', 0, NULL, NULL, NULL),
(304, 3, 9, 100.00, '2025-06-26', '2025-06-30', 'book', 0, NULL, NULL, NULL),
(305, 1, 15, 100.00, '2025-06-23', '2025-06-26', 'book', 0, NULL, NULL, NULL),
(306, 1, 2, 100.00, '2025-07-30', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(307, 2, 5, 300.00, '2025-06-29', '2025-07-04', 'book', 0, NULL, NULL, NULL),
(308, 7, 4, 150.00, '2025-06-25', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(309, 8, 1, 100.00, '2025-05-20', '2025-05-21', 'book', 0, NULL, NULL, NULL),
(310, 10, 12, 150.00, '2025-05-02', '2025-05-03', 'check_in', 0, NULL, NULL, NULL),
(311, 5, 16, 150.00, '2025-06-15', '2025-06-19', 'book', 0, 'current', NULL, NULL),
(312, 3, 9, 100.00, '2025-05-19', '2025-05-21', 'book', 0, NULL, NULL, NULL),
(313, 1, 12, 150.00, '2025-06-29', '2025-06-30', 'book', 0, NULL, NULL, NULL),
(314, 10, 12, 150.00, '2025-06-14', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(315, 5, 4, 150.00, '2025-06-18', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(316, 2, 9, 100.00, '2025-05-05', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(317, 6, 6, 100.00, '2025-07-22', '2025-07-25', 'book', 0, NULL, NULL, NULL),
(318, 10, 7, 300.00, '2025-06-28', '2025-07-01', 'book', 0, NULL, NULL, NULL),
(319, 3, 5, 300.00, '2025-05-11', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(320, 4, 5, 300.00, '2025-05-13', '2025-05-16', 'book', 0, NULL, NULL, NULL),
(321, 8, 9, 100.00, '2025-05-27', '2025-05-30', 'book', 0, NULL, NULL, NULL),
(322, 10, 9, 100.00, '2025-05-04', '2025-05-05', 'book', 0, NULL, NULL, NULL),
(323, 2, 16, 150.00, '2025-06-26', '2025-06-27', 'book', 0, 'current', NULL, NULL),
(324, 2, 11, 100.00, '2025-05-06', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(325, 3, 3, 150.00, '2025-06-29', '2025-06-30', 'book', 0, NULL, NULL, NULL),
(326, 1, 11, 100.00, '2025-06-09', '2025-06-10', 'book', 0, NULL, NULL, NULL),
(327, 2, 11, 100.00, '2025-04-28', '2025-04-29', 'check_in', 0, NULL, NULL, NULL),
(328, 6, 14, 150.00, '2025-05-16', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(329, 7, 14, 150.00, '2025-05-05', '2025-05-07', 'book', 0, NULL, NULL, NULL),
(330, 7, 14, 150.00, '2025-07-11', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(331, 4, 12, 150.00, '2025-07-20', '2025-07-21', 'book', 0, NULL, NULL, NULL),
(332, 4, 4, 150.00, '2025-05-22', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(333, 9, 10, 150.00, '2025-05-09', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(334, 3, 10, 150.00, '2025-06-07', '2025-06-09', 'book', 0, NULL, NULL, NULL),
(335, 1, 15, 100.00, '2025-04-29', '2025-05-01', 'check_in', 0, NULL, NULL, NULL),
(336, 4, 12, 150.00, '2025-06-02', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(337, 2, 7, 300.00, '2025-05-22', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(338, 4, 12, 150.00, '2025-06-26', '2025-06-27', 'book', 0, NULL, NULL, NULL),
(339, 3, 7, 300.00, '2025-05-21', '2025-05-22', 'book', 0, NULL, NULL, NULL),
(340, 7, 3, 150.00, '2025-05-12', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(341, 1, 15, 100.00, '2025-07-07', '2025-07-08', 'book', 0, NULL, NULL, NULL),
(342, 3, 10, 150.00, '2025-05-08', '2025-05-09', 'book', 0, NULL, NULL, NULL),
(343, 7, 14, 150.00, '2025-06-25', '2025-06-28', 'book', 0, NULL, NULL, NULL),
(344, 5, 14, 150.00, '2025-06-15', '2025-06-19', 'book', 0, NULL, NULL, NULL),
(345, 9, 12, 150.00, '2025-05-30', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(346, 10, 15, 100.00, '2025-07-27', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(347, 6, 11, 100.00, '2025-07-28', '2025-07-30', 'book', 0, NULL, NULL, NULL),
(348, 3, 7, 300.00, '2025-05-10', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(349, 8, 15, 100.00, '2025-07-24', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(350, 8, 7, 300.00, '2025-06-05', '2025-06-06', 'book', 0, NULL, NULL, NULL),
(351, 5, 11, 100.00, '2025-06-08', '2025-06-09', 'book', 0, NULL, NULL, NULL),
(352, 8, 4, 150.00, '2025-07-31', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(353, 7, 3, 150.00, '2025-05-31', '2025-06-01', 'book', 0, NULL, NULL, NULL),
(354, 2, 15, 100.00, '2025-07-23', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(355, 9, 6, 100.00, '2025-06-16', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(356, 4, 3, 150.00, '2025-05-09', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(357, 10, 12, 150.00, '2025-05-04', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(358, 2, 11, 100.00, '2025-06-17', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(359, 2, 12, 150.00, '2025-05-13', '2025-05-16', 'book', 0, NULL, NULL, NULL),
(360, 7, 14, 150.00, '2025-07-04', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(361, 7, 2, 100.00, '2025-07-28', '2025-07-30', 'book', 0, NULL, NULL, NULL),
(362, 6, 11, 100.00, '2025-06-02', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(363, 10, 16, 150.00, '2025-04-27', '2025-04-28', 'check_out', 0, 'current', NULL, NULL),
(364, 1, 12, 150.00, '2025-07-12', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(365, 6, 5, 300.00, '2025-06-12', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(366, 2, 10, 150.00, '2025-05-06', '2025-05-08', 'book', 0, NULL, NULL, NULL),
(367, 4, 16, 150.00, '2025-06-27', '2025-06-30', 'book', 0, 'current', NULL, NULL),
(368, 10, 11, 100.00, '2025-07-03', '2025-07-04', 'book', 0, NULL, NULL, NULL),
(369, 10, 9, 100.00, '2025-05-02', '2025-05-03', 'check_in', 0, NULL, NULL, NULL),
(370, 9, 1, 100.00, '2025-05-11', '2025-05-12', 'book', 0, NULL, NULL, NULL),
(371, 3, 2, 100.00, '2025-06-17', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(372, 6, 3, 150.00, '2025-06-18', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(373, 10, 10, 150.00, '2025-07-24', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(374, 9, 14, 150.00, '2025-07-24', '2025-07-25', 'book', 0, NULL, NULL, NULL),
(375, 2, 15, 100.00, '2025-05-24', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(376, 6, 5, 300.00, '2025-06-17', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(377, 7, 1, 100.00, '2025-05-30', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(378, 2, 9, 100.00, '2025-06-18', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(379, 2, 2, 100.00, '2025-06-30', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(380, 10, 4, 150.00, '2025-04-27', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(381, 5, 2, 100.00, '2025-07-13', '2025-07-15', 'book', 0, NULL, NULL, NULL),
(382, 2, 12, 150.00, '2025-06-06', '2025-06-07', 'book', 0, NULL, NULL, NULL),
(383, 5, 6, 100.00, '2025-05-02', '2025-05-03', 'check_in', 0, NULL, NULL, NULL),
(384, 6, 1, 100.00, '2025-05-26', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(385, 5, 10, 150.00, '2025-05-10', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(386, 6, 6, 100.00, '2025-04-25', '2025-04-27', 'check_out', 0, NULL, NULL, NULL),
(387, 1, 1, 100.00, '2025-07-02', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(388, 2, 13, 300.00, '2025-06-30', '2025-07-01', 'book', 0, NULL, NULL, NULL),
(389, 10, 10, 150.00, '2025-07-16', '2025-07-18', 'book', 0, NULL, NULL, NULL),
(390, 6, 10, 150.00, '2025-06-21', '2025-06-22', 'book', 0, NULL, NULL, NULL),
(391, 1, 11, 100.00, '2025-07-10', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(392, 9, 11, 100.00, '2025-05-31', '2025-06-01', 'book', 0, NULL, NULL, NULL),
(393, 2, 1, 100.00, '2025-07-01', '2025-07-02', 'book', 0, NULL, NULL, NULL),
(394, 8, 15, 100.00, '2025-05-25', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(395, 10, 11, 100.00, '2025-07-23', '2025-07-25', 'book', 0, NULL, NULL, NULL),
(396, 2, 1, 100.00, '2025-07-08', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(397, 1, 5, 300.00, '2025-05-27', '2025-05-29', 'book', 0, NULL, NULL, NULL),
(398, 8, 9, 100.00, '2025-07-16', '2025-07-17', 'book', 0, NULL, NULL, NULL),
(399, 3, 12, 150.00, '2025-05-23', '2025-05-24', 'book', 0, NULL, NULL, NULL),
(400, 1, 6, 100.00, '2025-05-07', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(401, 6, 11, 100.00, '2025-05-22', '2025-05-23', 'book', 0, NULL, NULL, NULL),
(402, 2, 5, 300.00, '2025-07-20', '2025-07-22', 'book', 0, NULL, NULL, NULL),
(403, 4, 16, 150.00, '2025-05-23', '2025-05-24', 'book', 0, 'current', NULL, NULL),
(404, 9, 9, 100.00, '2025-05-18', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(405, 2, 1, 100.00, '2025-05-19', '2025-05-20', 'book', 0, NULL, NULL, NULL),
(406, 3, 6, 100.00, '2025-07-15', '2025-07-16', 'book', 0, NULL, NULL, NULL),
(407, 8, 4, 150.00, '2025-04-25', '2025-04-26', 'check_out', 0, NULL, NULL, NULL),
(408, 9, 9, 100.00, '2025-05-03', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(409, 7, 1, 100.00, '2025-06-19', '2025-06-20', 'book', 0, NULL, NULL, NULL),
(410, 5, 11, 100.00, '2025-05-25', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(411, 8, 2, 100.00, '2025-07-09', '2025-07-10', 'book', 0, NULL, NULL, NULL),
(412, 4, 10, 150.00, '2025-06-04', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(413, 4, 1, 100.00, '2025-05-16', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(414, 6, 5, 300.00, '2025-06-28', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(415, 9, 16, 150.00, '2025-05-06', '2025-05-07', 'book', 0, 'current', NULL, NULL),
(416, 2, 4, 150.00, '2025-06-23', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(417, 1, 4, 150.00, '2025-07-20', '2025-07-22', 'book', 0, NULL, NULL, NULL),
(418, 9, 11, 100.00, '2025-06-18', '2025-06-19', 'book', 0, NULL, NULL, NULL),
(419, 4, 14, 150.00, '2025-04-27', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(420, 3, 1, 100.00, '2025-05-22', '2025-05-24', 'book', 0, NULL, NULL, NULL),
(421, 4, 9, 100.00, '2025-05-12', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(422, 10, 13, 300.00, '2025-05-31', '2025-06-01', 'book', 0, NULL, NULL, NULL),
(423, 1, 10, 150.00, '2025-05-27', '2025-05-28', 'book', 0, NULL, NULL, NULL),
(424, 3, 2, 100.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(425, 8, 15, 100.00, '2025-06-16', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(426, 5, 16, 150.00, '2025-07-23', '2025-07-24', 'book', 0, 'current', NULL, NULL),
(427, 1, 13, 300.00, '2025-05-06', '2025-05-07', 'book', 0, NULL, NULL, NULL),
(428, 5, 9, 100.00, '2025-07-24', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(429, 3, 12, 150.00, '2025-07-22', '2025-07-23', 'book', 0, NULL, NULL, NULL),
(430, 7, 7, 300.00, '2025-07-02', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(431, 9, 1, 100.00, '2025-05-01', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(432, 6, 2, 100.00, '2025-06-01', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(433, 9, 16, 150.00, '2025-06-14', '2025-06-15', 'book', 0, 'current', NULL, NULL),
(434, 3, 3, 150.00, '2025-06-22', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(435, 2, 7, 300.00, '2025-07-05', '2025-07-06', 'book', 0, NULL, NULL, NULL),
(436, 10, 5, 300.00, '2025-05-10', '2025-05-11', 'book', 0, NULL, NULL, NULL),
(437, 9, 1, 100.00, '2025-04-28', '2025-04-30', 'check_in', 0, NULL, NULL, NULL),
(438, 10, 9, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(439, 1, 4, 150.00, '2025-07-12', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(440, 3, 4, 150.00, '2025-05-13', '2025-05-14', 'book', 0, NULL, NULL, NULL),
(441, 6, 15, 100.00, '2025-06-12', '2025-06-13', 'book', 0, NULL, NULL, NULL),
(442, 4, 15, 100.00, '2025-06-08', '2025-06-09', 'book', 0, NULL, NULL, NULL),
(443, 5, 4, 150.00, '2025-06-13', '2025-06-14', 'book', 0, NULL, NULL, NULL),
(444, 4, 9, 100.00, '2025-07-26', '2025-07-27', 'book', 0, NULL, NULL, NULL),
(445, 9, 12, 150.00, '2025-07-24', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(446, 2, 13, 300.00, '2025-04-30', '2025-05-01', 'book', 0, NULL, NULL, NULL),
(447, 3, 15, 100.00, '2025-07-28', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(448, 1, 13, 300.00, '2025-07-01', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(449, 10, 16, 150.00, '2025-05-14', '2025-05-15', 'book', 0, 'current', NULL, NULL),
(450, 8, 11, 100.00, '2025-05-14', '2025-05-15', 'book', 0, NULL, NULL, NULL),
(451, 4, 13, 300.00, '2025-06-01', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(452, 3, 5, 300.00, '2025-06-15', '2025-06-16', 'book', 0, NULL, NULL, NULL),
(453, 1, 4, 150.00, '2025-06-02', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(454, 3, 11, 100.00, '2025-05-23', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(455, 4, 2, 100.00, '2025-05-25', '2025-05-26', 'book', 0, NULL, NULL, NULL),
(456, 4, 16, 150.00, '2025-07-11', '2025-07-12', 'book', 0, 'current', NULL, NULL),
(457, 2, 14, 150.00, '2025-06-21', '2025-06-23', 'book', 0, NULL, NULL, NULL),
(458, 6, 3, 150.00, '2025-06-21', '2025-06-22', 'book', 0, NULL, NULL, NULL),
(459, 4, 11, 100.00, '2025-06-21', '2025-06-22', 'book', 0, NULL, NULL, NULL),
(460, 2, 7, 300.00, '2025-07-01', '2025-07-02', 'book', 0, NULL, NULL, NULL),
(461, 1, 4, 150.00, '2025-05-15', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(462, 2, 10, 150.00, '2025-06-28', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(463, 3, 11, 100.00, '2025-04-29', '2025-04-30', 'check_in', 0, NULL, NULL, NULL),
(464, 8, 12, 150.00, '2025-06-27', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(465, 1, 5, 300.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(466, 6, 11, 100.00, '2025-07-31', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(467, 8, 12, 150.00, '2025-06-15', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(468, 10, 2, 100.00, '2025-05-05', '2025-05-06', 'book', 0, NULL, NULL, NULL),
(469, 5, 4, 150.00, '2025-05-28', '2025-05-29', 'book', 0, NULL, NULL, NULL),
(470, 5, 3, 150.00, '2025-06-01', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(471, 6, 15, 100.00, '2025-05-22', '2025-05-24', 'book', 0, NULL, NULL, NULL),
(472, 3, 16, 150.00, '2025-04-24', '2025-04-27', 'check_out', 0, 'current', NULL, NULL),
(473, 2, 14, 150.00, '2025-07-25', '2025-07-26', 'book', 0, NULL, NULL, NULL),
(474, 6, 14, 150.00, '2025-05-15', '2025-05-16', 'book', 0, NULL, NULL, NULL),
(475, 5, 15, 100.00, '2025-07-06', '2025-07-07', 'book', 0, NULL, NULL, NULL),
(476, 7, 15, 100.00, '2025-05-15', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(477, 1, 15, 100.00, '2025-05-28', '2025-05-29', 'book', 0, NULL, NULL, NULL),
(478, 8, 16, 150.00, '2025-05-22', '2025-05-23', 'book', 0, 'current', NULL, NULL),
(479, 6, 13, 300.00, '2025-06-15', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(480, 3, 5, 300.00, '2025-06-16', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(481, 7, 4, 150.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(482, 2, 13, 300.00, '2025-07-08', '2025-07-09', 'book', 0, NULL, NULL, NULL),
(483, 8, 13, 300.00, '2025-07-05', '2025-07-07', 'book', 0, NULL, NULL, NULL),
(484, 1, 11, 100.00, '2025-07-02', '2025-07-03', 'book', 0, NULL, NULL, NULL),
(485, 3, 5, 300.00, '2025-05-30', '2025-06-01', 'book', 0, NULL, NULL, NULL),
(486, 1, 1, 100.00, '2025-06-28', '2025-06-29', 'book', 0, NULL, NULL, NULL),
(487, 1, 12, 150.00, '2025-07-27', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(488, 3, 1, 100.00, '2025-06-02', '2025-06-03', 'book', 0, NULL, NULL, NULL),
(489, 10, 10, 150.00, '2025-04-30', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(490, 2, 15, 100.00, '2025-05-30', '2025-05-31', 'book', 0, NULL, NULL, NULL),
(491, 3, 13, 300.00, '2025-05-18', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(492, 1, 7, 300.00, '2025-04-27', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(493, 4, 7, 300.00, '2025-06-21', '2025-06-22', 'book', 0, NULL, NULL, NULL),
(494, 4, 16, 150.00, '2025-05-08', '2025-05-09', 'book', 0, 'current', NULL, NULL),
(495, 8, 9, 100.00, '2025-06-25', '2025-06-26', 'book', 0, NULL, NULL, NULL),
(496, 10, 13, 300.00, '2025-05-21', '2025-05-23', 'book', 0, NULL, NULL, NULL),
(497, 4, 6, 100.00, '2025-06-15', '2025-06-16', 'book', 0, NULL, NULL, NULL),
(498, 9, 12, 150.00, '2025-05-22', '2025-05-23', 'book', 0, NULL, NULL, NULL),
(499, 10, 2, 100.00, '2025-06-11', '2025-06-12', 'book', 0, NULL, NULL, NULL),
(500, 10, 11, 100.00, '2025-04-25', '2025-04-26', 'check_out', 0, NULL, NULL, NULL),
(501, 7, 1, 100.00, '2025-05-15', '2025-05-16', 'book', 0, NULL, NULL, NULL),
(502, 2, 15, 100.00, '2025-05-09', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(503, 5, 7, 300.00, '2025-06-16', '2025-06-17', 'book', 0, NULL, NULL, NULL),
(504, 2, 1, 100.00, '2025-05-24', '2025-05-25', 'book', 0, NULL, NULL, NULL),
(505, 2, 16, 150.00, '2025-05-24', '2025-05-25', 'book', 0, 'current', NULL, NULL),
(506, 4, 7, 300.00, '2025-05-03', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(507, 5, 9, 100.00, '2025-06-17', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(508, 7, 1, 100.00, '2025-06-23', '2025-06-24', 'book', 0, NULL, NULL, NULL),
(509, 5, 7, 300.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(510, 4, 6, 100.00, '2025-05-03', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(511, 7, 13, 300.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(512, 10, 11, 100.00, '2025-07-17', '2025-07-18', 'book', 0, NULL, NULL, NULL),
(513, 2, 12, 150.00, '2025-07-10', '2025-07-11', 'book', 0, NULL, NULL, NULL),
(514, 4, 14, 150.00, '2025-07-23', '2025-07-24', 'book', 0, NULL, NULL, NULL),
(515, 6, 9, 100.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(516, 1, 12, 150.00, '2025-07-21', '2025-07-22', 'book', 0, NULL, NULL, NULL),
(517, 5, 7, 300.00, '2025-07-28', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(518, 4, 7, 300.00, '2025-07-15', '2025-07-16', 'book', 0, NULL, NULL, NULL),
(519, 3, 13, 300.00, '2025-06-04', '2025-06-05', 'book', 0, NULL, NULL, NULL),
(520, 8, 16, 150.00, '2025-07-04', '2025-07-05', 'book', 0, 'current', NULL, NULL),
(521, 1, 1, 100.00, '2025-06-27', '2025-06-28', 'book', 0, NULL, NULL, NULL),
(522, 8, 16, 150.00, '2025-05-03', '2025-05-05', 'book', 0, 'current', NULL, NULL),
(523, 8, 14, 150.00, '2025-05-28', '2025-05-29', 'book', 0, NULL, NULL, NULL),
(524, 2, 7, 300.00, '2025-07-31', '2025-08-01', 'book', 0, NULL, NULL, NULL),
(525, 8, 10, 150.00, '2025-07-30', '2025-07-31', 'book', 0, NULL, NULL, NULL),
(526, 4, 10, 150.00, '2025-07-28', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(527, 2, 5, 300.00, '2025-05-16', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(528, 8, 2, 100.00, '2025-05-03', '2025-05-04', 'book', 0, NULL, NULL, NULL),
(529, 5, 10, 150.00, '2025-07-11', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(530, 8, 13, 300.00, '2025-06-08', '2025-06-09', 'book', 0, NULL, NULL, NULL),
(531, 3, 16, 150.00, '2025-07-15', '2025-07-16', 'book', 0, 'current', NULL, NULL),
(532, 1, 15, 100.00, '2025-06-07', '2025-06-08', 'book', 0, NULL, NULL, NULL),
(533, 9, 11, 100.00, '2025-05-30', '2025-05-31', 'book', 0, NULL, NULL, NULL),
(534, 9, 4, 150.00, '2025-05-26', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(535, 1, 14, 150.00, '2025-05-09', '2025-05-10', 'book', 0, NULL, NULL, NULL),
(536, 3, 6, 100.00, '2025-07-21', '2025-07-22', 'book', 0, NULL, NULL, NULL),
(537, 8, 10, 150.00, '2025-07-27', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(538, 4, 11, 100.00, '2025-04-27', '2025-04-28', 'check_out', 0, NULL, NULL, NULL),
(539, 2, 6, 100.00, '2025-05-18', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(540, 10, 5, 300.00, '2025-07-11', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(541, 10, 4, 150.00, '2025-06-05', '2025-06-06', 'book', 0, NULL, NULL, NULL),
(542, 6, 7, 300.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(543, 6, 2, 100.00, '2025-04-25', '2025-04-26', 'check_out', 0, NULL, NULL, NULL),
(544, 7, 12, 150.00, '2025-05-16', '2025-05-17', 'book', 0, NULL, NULL, NULL),
(545, 5, 13, 300.00, '2025-05-26', '2025-05-27', 'book', 0, NULL, NULL, NULL),
(546, 9, 13, 300.00, '2025-05-30', '2025-05-31', 'book', 0, NULL, NULL, NULL),
(547, 3, 3, 150.00, '2025-05-19', '2025-05-20', 'book', 0, NULL, NULL, NULL),
(548, 4, 6, 100.00, '2025-06-26', '2025-06-27', 'book', 0, NULL, NULL, NULL),
(549, 6, 6, 100.00, '2025-05-04', '2025-05-05', 'book', 0, NULL, NULL, NULL),
(550, 6, 13, 300.00, '2025-06-07', '2025-06-08', 'book', 0, NULL, NULL, NULL),
(551, 10, 2, 100.00, '2025-07-20', '2025-07-21', 'book', 0, NULL, NULL, NULL),
(552, 2, 15, 100.00, '2025-07-13', '2025-07-14', 'book', 0, NULL, NULL, NULL),
(553, 3, 13, 300.00, '2025-05-04', '2025-05-05', 'book', 0, NULL, NULL, NULL),
(554, 2, 7, 300.00, '2025-07-27', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(555, 6, 3, 150.00, '2025-07-27', '2025-07-28', 'book', 0, NULL, NULL, NULL),
(556, 10, 12, 150.00, '2025-07-11', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(557, 10, 10, 150.00, '2025-06-27', '2025-06-28', 'book', 0, NULL, NULL, NULL),
(558, 8, 5, 300.00, '2025-07-12', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(559, 3, 10, 150.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(560, 4, 10, 150.00, '2025-06-14', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(561, 8, 3, 150.00, '2025-07-28', '2025-07-29', 'book', 0, NULL, NULL, NULL),
(562, 10, 4, 150.00, '2025-07-10', '2025-07-12', 'book', 0, NULL, NULL, NULL),
(563, 4, 2, 100.00, '2025-05-12', '2025-05-13', 'book', 0, NULL, NULL, NULL),
(564, 9, 11, 100.00, '2025-07-12', '2025-07-13', 'book', 0, NULL, NULL, NULL),
(565, 3, 11, 100.00, '2025-06-24', '2025-06-25', 'book', 0, NULL, NULL, NULL),
(566, 6, 13, 300.00, '2025-07-14', '2025-07-15', 'book', 0, NULL, NULL, NULL),
(567, 9, 4, 150.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(568, 1, 16, 150.00, '2025-07-10', '2025-07-11', 'book', 0, 'current', NULL, NULL),
(569, 6, 2, 100.00, '2025-05-18', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(570, 2, 11, 100.00, '2025-06-01', '2025-06-02', 'book', 0, NULL, NULL, NULL),
(571, 10, 15, 100.00, '2025-05-17', '2025-05-18', 'book', 0, NULL, NULL, NULL),
(572, 8, 3, 150.00, '2025-06-17', '2025-06-18', 'book', 0, NULL, NULL, NULL),
(573, 4, 2, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(574, 4, 3, 150.00, '2025-05-01', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(575, 7, 14, 150.00, '2025-06-07', '2025-06-08', 'book', 0, NULL, NULL, NULL),
(576, 10, 7, 300.00, '2025-05-18', '2025-05-19', 'book', 0, NULL, NULL, NULL),
(577, 6, 14, 150.00, '2025-07-10', '2025-07-11', 'book', 0, NULL, NULL, NULL),
(578, 10, 6, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(579, 5, 12, 150.00, '2025-05-01', '2025-05-02', 'check_out', 0, NULL, NULL, NULL),
(580, 2, 15, 100.00, '2025-06-26', '2025-06-27', 'book', 0, NULL, NULL, NULL),
(581, 9, 4, 150.00, '2025-06-24', '2025-06-25', 'book', 0, NULL, NULL, NULL),
(582, 10, 7, 300.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(583, 10, 14, 150.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(584, 1, 6, 100.00, '2025-06-14', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(585, 9, 4, 150.00, '2025-05-07', '2025-05-08', 'book', 0, NULL, NULL, NULL),
(586, 6, 14, 150.00, '2025-06-14', '2025-06-15', 'book', 0, NULL, NULL, NULL),
(587, 3, 9, 100.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(588, 2, 6, 100.00, '2025-06-20', '2025-06-21', 'book', 0, NULL, NULL, NULL),
(589, 3, 11, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(590, 5, 14, 150.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(591, 9, 3, 150.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(592, 1, 15, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(593, 10, 1, 100.00, '2025-04-24', '2025-04-25', 'check_out', 0, NULL, NULL, NULL),
(594, 4, 8, 150.00, '2025-06-10', '2025-06-14', 'book', 0, 'new', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `reservations_view`
-- (Véase abajo para la vista actual)
--
DROP VIEW IF EXISTS `reservations_view`;
CREATE TABLE IF NOT EXISTS `reservations_view` (
`reservation_id` int(11)
,`customer_id` int(11)
,`room_id` int(11)
,`price_per_night` decimal(10,2)
,`date_in` date
,`date_out` date
,`state` enum('book','canceled','check_in','check_out')
,`isUnswappable` tinyint(4)
,`tag` enum('new','current')
,`extras_json` longtext
,`guests_json` longtext
,`total_days` int(8)
,`subtotal` decimal(17,2)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `room_number` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_number` (`room_number`),
  KEY `category_id` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `rooms`
--

INSERT INTO `rooms` (`room_id`, `room_number`, `category_id`) VALUES
(1, 101, 1),
(2, 102, 1),
(3, 103, 2),
(4, 104, 2),
(5, 105, 3),
(6, 106, 1),
(7, 107, 3),
(8, 108, 2),
(9, 109, 1),
(10, 110, 2),
(11, 201, 1),
(12, 202, 2),
(13, 203, 3),
(14, 204, 2),
(15, 205, 1),
(16, 206, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_category` varchar(100) NOT NULL,
  `service_name` varchar(100) NOT NULL,
  `service_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`service_id`, `service_category`, `service_name`, `service_price`) VALUES
(1, 'spa', '1h session', 40.00),
(2, 'spa', '1h massage', 10.00),
(3, 'gym', '1h session', 10.00),
(4, 'restaurant', 'breakfast', 20.00),
(5, 'restaurant', 'lunch', 30.00),
(6, 'restaurant', 'dinner', 30.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services_reservations`
--

DROP TABLE IF EXISTS `services_reservations`;
CREATE TABLE IF NOT EXISTS `services_reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  `qty` int(11) NOT NULL DEFAULT 1,
  `date_in` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reservation_id` (`reservation_id`),
  KEY `service_id` (`service_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `services_reservations`
--

INSERT INTO `services_reservations` (`id`, `reservation_id`, `service_id`, `qty`, `date_in`) VALUES
(1, 55, 1, 2, '2025-04-25'),
(2, 186, 2, 1, '2025-04-26'),
(3, 121, 3, 4, '2025-04-27'),
(4, 965, 4, 3, '2025-04-28'),
(5, 423, 5, 1, '2025-04-29');

-- --------------------------------------------------------

--
-- Estructura para la vista `reservations_view`
--
DROP TABLE IF EXISTS `reservations_view`;

DROP VIEW IF EXISTS `reservations_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `reservations_view`  AS SELECT `r`.`reservation_id` AS `reservation_id`, `r`.`customer_id` AS `customer_id`, `r`.`room_id` AS `room_id`, `r`.`price_per_night` AS `price_per_night`, `r`.`date_in` AS `date_in`, `r`.`date_out` AS `date_out`, `r`.`state` AS `state`, `r`.`isUnswappable` AS `isUnswappable`, `r`.`tag` AS `tag`, `r`.`extras_json` AS `extras_json`, `r`.`guests_json` AS `guests_json`, to_days(`r`.`date_out`) - to_days(`r`.`date_in`) AS `total_days`, (to_days(`r`.`date_out`) - to_days(`r`.`date_in`)) * `r`.`price_per_night` AS `subtotal` FROM `reservations` AS `r` ;

DELIMITER $$
--
-- Eventos
--
DROP EVENT IF EXISTS `checkStateReservations`$$
CREATE DEFINER=`root`@`localhost` EVENT `checkStateReservations` ON SCHEDULE EVERY 1 DAY STARTS '2025-04-30 11:20:10' ENDS '2025-05-29 20:55:34' ON COMPLETION PRESERVE ENABLE DO CALL updateState()$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
