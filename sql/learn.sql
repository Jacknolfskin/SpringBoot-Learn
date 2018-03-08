INSERT INTO `spring`.`user` (`id`, `password`, `username`) VALUES ('1', '$2a$04$5EzhLxOthTOMc5pN/Ozg1.wzrbijftcgEqXPouJt7igKIoxAmVWtO', 'liuyan');
INSERT INTO `spring`.`user` (`id`, `password`, `username`) VALUES ('2', '$2a$04$2/GN1BIPc6agihyHMQce4OSr34CgPCTRgiTQYEr51Wc1zm7u5eOTm', 'dongdong');
INSERT INTO `spring`.`user` (`id`, `password`, `username`) VALUES ('3', '$2a$04$3Cksa3X0bYdYDkDm231w/uZ8BWJiV57OKTG9PZo8iFHTxNZ1jT77a', 'admin');
INSERT INTO `spring`.`user` (`id`, `password`, `username`) VALUES ('4', '$2a$04$3Cksa3X0bYdYDkDm231w/uZ8BWJiV57OKTG9PZo8iFHTxNZ1jT77a', 'jason');

INSERT INTO `spring`.`role` (`id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `spring`.`role` (`id`, `role_name`) VALUES ('2', 'ADMIN');
INSERT INTO `spring`.`role` (`id`, `role_name`) VALUES ('3', 'VIP');
INSERT INTO `spring`.`role` (`id`, `role_name`) VALUES ('4', 'SUPER');


INSERT INTO `spring`.`permission` (`id`, `permission_name`) VALUES ('1', '/vip');
INSERT INTO `spring`.`permission` (`id`, `permission_name`) VALUES ('2', '/user');
INSERT INTO `spring`.`permission` (`id`, `permission_name`) VALUES ('3', '/admin');
INSERT INTO `spring`.`permission` (`id`, `permission_name`) VALUES ('4', '/super');


INSERT INTO `spring`.`user_roles` (`user_id`, `roles_id`) VALUES ('3', '2');
INSERT INTO `spring`.`user_roles` (`user_id`, `roles_id`) VALUES ('1', '3');
INSERT INTO `spring`.`user_roles` (`user_id`, `roles_id`) VALUES ('2', '1');
INSERT INTO `spring`.`user_roles` (`user_id`, `roles_id`) VALUES ('4', '4');


INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('1', '2', '1');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('2', '1', '3');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('3', '3', '2');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('4', '1', '2');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('5', '4', '4');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('6', '2', '4');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('7', '3', '4');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('8', '1', '4');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('9', '2', '2');
INSERT INTO `spring`.`permission_role` (`id`, `permission_id`, `role_id`) VALUES ('10', '2', '3');

CREATE TABLE persistent_logins (
  username VARCHAR (64) NOT NULL,
  series VARCHAR (64) PRIMARY KEY,
  token VARCHAR (64) NOT NULL,
  last_used TIMESTAMP NOT NULL
)