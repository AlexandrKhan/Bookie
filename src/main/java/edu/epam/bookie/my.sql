CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  'firstName' VARCHAR(255) NOT NULL,
  'lastName' VARCHAR(255) NOT NULL ,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into user(username,password) VALUES ('khan', 1234);
insert into user(username,password) VALUES ('gus', 1234);

CREATE TABLE `images` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`image_id`)
)
