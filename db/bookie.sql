CREATE DATABASE bookie
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `role` ENUM('ADMIN','USER','GUEST') NOT NULL DEFAULT 'GUEST',
  `money` DECIMAL(7,2) NOT NULL DEFAULT 0,
  `passport_scan` VARCHAR(255) NOT NULL,
  `status` ENUM('ACTIVE','BLOCKED','NOT_ACTIVATED') NOT NULL DEFAULT 'NOT_ACTIVATED',
  PRIMARY KEY (`id`)
);

CREATE TABLE `match` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `home_team` VARCHAR(255) NOT NULL,
  `away_team` VARCHAR(255) NOT NULL,
  `start_date` DATE NOT NULL,
  `start_time` TIME NOT NULL,
  `home_coeff` DOUBLE PRECISION(4,2) NOT NULL,
  `draw_coeff` DOUBLE PRECISION(4,2) NOT NULL,
  `away_coeff` DOUBLE PRECISION(4,2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `match_result` (
  `id` BIGINT(20),
  `home_team_goals` INT(10) NOT NULL DEFAULT 0,
  `away_team_goals` INT(10) NOT NULL DEFAULT 0,
  `result` ENUM('HOME', 'DRAW', 'AWAY') NOT NULL DEFAULT 'DRAW',
  `match_progress` ENUM('NOT_STARTED', 'OVER') NOT NULL DEFAULT 'NOT_STARTED',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id`) REFERENCES bookie.match(id) ON DELETE CASCADE
);

CREATE TABLE `bet` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `match_id` BIGINT(20) NOT NULL,
  `bet_date` DATE NOT NULL,
  `bet_time` TIME NOT NULL,
  `bet_amount` DECIMAL(7,2) NOT NULL,
  `bet_on_result` ENUM('HOME', 'DRAW', 'AWAY') DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES bookie.user(id),
  FOREIGN KEY (match_id) REFERENCES bookie.match(id)
);

INSERT INTO user (username, first_name, last_name, email, password, date_of_birth, role, passport_scan) VALUES
  ('admin', 'Alexandr', 'Khan', 'alexandrhan22@gmail.com', 'sasha6685321', '1995-06-28', 'ADMIN', 'pass.jpg');
