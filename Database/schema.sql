CREATE TABLE `user_table` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile` bigint NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `aadhar_card` varchar(255) NOT NULL,
  `mini_bio` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(45) NOT NULL DEFAULT 'Passenger',
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `publisher_table` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `driving_license` varchar(255) NOT NULL,
  `vehicle_info` varchar(85) NOT NULL,
  `vehicle_seater` int NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`publisher_id`),
  KEY `fk1_idx` (`user_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `publisher_ride_table` (
  `publisher_ride_id` int NOT NULL AUTO_INCREMENT,
  `publisher_id` int NOT NULL,
  `from_location` varchar(255) NOT NULL,
  `to_location` varchar(255) NOT NULL,
  `distance` float NOT NULL,
  `journey_hours` float NOT NULL,
  `available_seats` int NOT NULL,
  `date_of_journey` date NOT NULL,
  `time_of_journey` time NOT NULL,
  `fare_per_seats` float NOT NULL,
  `about_ride` mediumtext,
  `status` varchar(45) NOT NULL DEFAULT 'Empty',
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`publisher_ride_id`),
  KEY `fk2_idx` (`publisher_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`publisher_id`) REFERENCES `publisher_table` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `passengers_table` (
  `passenger_id` int NOT NULL AUTO_INCREMENT,
  `publisher_ride_id` int NOT NULL,
  `passenger_first_name` varchar(45) NOT NULL,
  `passenger_last_name` varchar(45) NOT NULL,
  `passenger_mobile` varchar(45) NOT NULL,
  `approval_status` varchar(45) NOT NULL DEFAULT 'Pending',
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`passenger_id`),
  KEY `fk3_idx` (`publisher_ride_id`),
  CONSTRAINT `fk3` FOREIGN KEY (`publisher_ride_id`) REFERENCES `publisher_ride_table` (`publisher_ride_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment_table` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `trip_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `passenger_id` int NOT NULL,
  `amount` float NOT NULL,
  `mode_of_payment` varchar(45) NOT NULL,
  `commission` float NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk4_idx` (`trip_id`),
  KEY `fk5_idx` (`publisher_id`),
  KEY `fk6_idx` (`passenger_id`),
  CONSTRAINT `fk4` FOREIGN KEY (`trip_id`) REFERENCES `publisher_ride_table` (`publisher_ride_id`),
  CONSTRAINT `fk5` FOREIGN KEY (`publisher_id`) REFERENCES `publisher_table` (`publisher_id`),
  CONSTRAINT `fk6` FOREIGN KEY (`passenger_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review_table` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `reviwer_id` int NOT NULL,
  `reviewee_id` int NOT NULL,
  `review_content` tinytext,
  `rating` int NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `fk7_idx` (`reviwer_id`),
  KEY `fk8_idx` (`reviewee_id`),
  CONSTRAINT `fk7` FOREIGN KEY (`reviwer_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `fk8` FOREIGN KEY (`reviewee_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `distance_table` (
  `distance_id` int NOT NULL AUTO_INCREMENT,
  `from_location` varchar(100) NOT NULL,
  `to_location` varchar(100) NOT NULL,
  `distance` float NOT NULL,
  `fare_per_seats` float NOT NULL,
  PRIMARY KEY (`distance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `vehicle_table` (
  `vehicle_id` int NOT NULL AUTO_INCREMENT,
  `types_of_vehicle` varchar(50) NOT NULL,
  `model_of_vehicle` varchar(100) NOT NULL,
  `max_no_of_seats` int NOT NULL,
  PRIMARY KEY (`vehicle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

