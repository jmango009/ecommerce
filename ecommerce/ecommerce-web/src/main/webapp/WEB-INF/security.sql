-- ----------------------------
-- Table structure for `ec_users`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_users`;
CREATE TABLE `ecommerce`.`ec_users` (
  `id` bigint NOT NULL auto_increment,
  `role_id` bigint NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL default 1,
  `descn` varchar(200),
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `ec_roles`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_roles`;
CREATE  TABLE `ecommerce`.`ec_roles` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `descn` varchar(200),
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table `ecommerce`.`ec_users`
add constraint fk_users_roles foreign key (`role_id`)
references `ecommerce`.`ec_roles` (`id`);


-- ----------------------------
-- insert test data
-- ----------------------------
insert into ec_roles(name,descn) values('ROLE_ADMIN','管理员角色');
insert into ec_roles(name,descn) values('ROLE_USER','用户角色');

insert into ec_users(role_id,username,password,enabled,descn) values(1,'admin','ceb4f32325eda6142bd65215f4c0f371',1,'管理员');
insert into ec_users(role_id,username,password,enabled,descn) values(2,'user' ,'47a733d60998c719cf3526ae7d106d13',1,'用户');

