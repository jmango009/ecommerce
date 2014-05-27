-- ----------------------------
-- Table structure for `ec_user`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_user`;
CREATE TABLE `ecommerce`.`ec_user` (
  `id` bigint NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` char(1) NOT NULL default '1',
  `descn` varchar(200),
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `ec_role`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_role`;
CREATE  TABLE `ecommerce`.`ec_role` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `descn` varchar(200),
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `ec_resource`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_resource`;
CREATE  TABLE `ecommerce`.`ec_resource` (
  `id` bigint NOT NULL auto_increment,
  `name` varchar(50),
  `res_type` varchar(50),
  `res_url` varchar(200),
  `descn` varchar(200),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_unique` (`name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `ec_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_user_role`;
CREATE  TABLE `ecommerce`.`ec_user_role` (
  `id` bigint NOT NULL auto_increment,
  `user_id` bigint,
  `role_id` bigint,
  PRIMARY KEY (`id`),
  foreign key(user_id) references ec_user(`id`) on delete cascade on update cascade,
  foreign key(role_id) references ec_role(`id`) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `ec_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`ec_role_resource`;
CREATE  TABLE `ecommerce`.`ec_role_resource` (
  `id` bigint NOT NULL auto_increment,
  `resc_id` bigint,
  `role_id` bigint,
  PRIMARY KEY (`id`),
  foreign key(`resc_id`) references ec_resource(`id`) on delete cascade on update cascade,
  foreign key(`role_id`) references ec_role(`id`) on delete cascade on update cascade
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- insert test data
-- ----------------------------
insert into ec_user(username,password,enabled,descn) values('admin','admin',1,'管理员');
insert into ec_user(username,password,enabled,descn) values('user','user',1,'用户');

insert into ec_role(name,descn) values('ROLE_ADMIN','管理员角色');
insert into ec_role(name,descn) values('ROLE_USER','用户角色');

insert into ec_resource(id,name,res_type,res_string,descn) values(1,'','URL','/admin/index.jsp','');
insert into ec_resource(id,name,res_type,res_string,descn) values(2,'','URL','/**','');

insert into ec_role_resource(resc_id,role_id) values(1,1);
insert into ec_role_resource(resc_id,role_id) values(2,1);
insert into ec_role_resource(resc_id,role_id) values(2,2);
insert into ec_user_role(user_id,role_id) values(1,1);
insert into ec_user_role(user_id,role_id) values(1,2);
insert into ec_user_role(user_id,role_id) values(2,2);
