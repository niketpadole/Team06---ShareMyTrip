CREATE TABLE `publisher_table` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile` bigint NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `driving_license` varchar(255) NOT NULL,
  `aadhar_card` varchar(255) NOT NULL,
  `mini_bio` varchar(300) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `vehicle_model_name` varchar(45) NOT NULL,
  `vehicle_no` varchar(45) NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `passenger_table` (
  `passenger_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `mobile` bigint NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `aadhar_card` varchar(255) NOT NULL,
  `mini_bio` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `distance_table` (
  `distance_id` int NOT NULL AUTO_INCREMENT,
  `from_location` varchar(100) NOT NULL,
  `to_location` varchar(100) NOT NULL,
  `distance` float NOT NULL,
  `fare_per_seats` float NOT NULL,
  PRIMARY KEY (`distance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee_table` (
  `employee_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `employee_full_name` varchar(45) NOT NULL,
  PRIMARY KEY (`employee_id`)
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
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`publisher_ride_id`),
  KEY `fk1_idx` (`publisher_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher_table` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `passenger_ride_table` (
  `passenger_ride_id` int NOT NULL AUTO_INCREMENT,
  `publisher_ride_id` int NOT NULL,
  `passenger_id` int NOT NULL,
  `no_of_passengers` int NOT NULL,
  PRIMARY KEY (`passenger_ride_id`),
  KEY `fk2_idx` (`publisher_ride_id`),
  KEY `fk3_idx` (`passenger_id`),
  CONSTRAINT `fk2` FOREIGN KEY (`publisher_ride_id`) REFERENCES `publisher_ride_table` (`publisher_ride_id`),
  CONSTRAINT `fk3` FOREIGN KEY (`passenger_id`) REFERENCES `passenger_table` (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `payment_table` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `publisher_ride_id` int NOT NULL,
  `publisher_id` int NOT NULL,
  `passenger_id` int NOT NULL,
  `total_amount` float NOT NULL,
  `commission_amount` float NOT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk4_idx` (`publisher_ride_id`),
  KEY `fk5_idx` (`publisher_id`),
  KEY `fk6_idx` (`passenger_id`),
  CONSTRAINT `fk4` FOREIGN KEY (`publisher_ride_id`) REFERENCES `publisher_ride_table` (`publisher_ride_id`),
  CONSTRAINT `fk5` FOREIGN KEY (`publisher_id`) REFERENCES `publisher_table` (`publisher_id`),
  CONSTRAINT `fk6` FOREIGN KEY (`passenger_id`) REFERENCES `passenger_table` (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `review_table` (
  `review_id` int NOT NULL AUTO_INCREMENT,
  `payment_id` int NOT NULL,
  `publisher_rating` int NOT NULL,
  `publisher_review` varchar(300) DEFAULT NULL,
  `passenger_rating` int NOT NULL,
  `passenger_review` varchar(300) DEFAULT NULL,
  `timestamp` timestamp(6) NOT NULL,
  PRIMARY KEY (`review_id`),
  KEY `fk7_idx` (`payment_id`),
  CONSTRAINT `fk7` FOREIGN KEY (`payment_id`) REFERENCES `payment_table` (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






