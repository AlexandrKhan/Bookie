CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `role` ENUM('ADMIN','USER','GUEST') NOT NULL DEFAULT 'GUEST',
  `money_balance` DOUBLE DEFAULT 0,
  PRIMARY KEY (`id`)
);

CREATE TABLE `match` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first` VARCHAR(255) NOT NULL,
  `second` VARCHAR(255) NOT NULL,
  `start_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `bet` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

