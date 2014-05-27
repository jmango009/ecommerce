-- ----------------------------
-- Table structure for `newsCategory`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`newsCategory`;
CREATE TABLE `ecommerce`.`newsCategory` (
  `id` bigint NOT NULL auto_increment,
  `categoryType` smallint(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `isDeleted` bit(1) NOT NULL default 0,
  `whoCreated` varchar(100) default NULL,
  `dateCreated` datetime default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `categoryType_unique` (`categoryType`, `name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `ecommerce`.`news`;
CREATE  TABLE `ecommerce`.`news` (
  `id` smallint NOT NULL auto_increment,
  `newsCategoryId` bigint NOT NULL default 1,
  `title` varchar(255) NOT NULL,
  `content` longtext default NULL,
  `url` varchar(255) NOT NULL,
  `whoCreated` varchar(100) default NULL,
  `whoUpdated` varchar(100) default NULL,
  `dateCreated` datetime default NULL,
  `dateUpdated` datetime default NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`newsCategoryId`) REFERENCES `ecommerce`.`newsCategory` (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of newsCategory
-- ----------------------------
INSERT INTO `newsCategory` VALUES (1, 1, '新闻', 0, 'me', '2013-05-15 15:44:39');
INSERT INTO `newsCategory` VALUES (2, 2, '评论', 0, 'me', '2013-05-15 15:44:39');
INSERT INTO `newsCategory` VALUES (3, 3, '资讯', 0, 'me', '2013-05-15 15:44:39');

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, 1, '新闻标题一', '新闻内容一', 'url1', 'richard', '', '2013-04-04 05:43:54', null);
INSERT INTO `news` VALUES (2, 1, '新闻标题二', '新闻内容二', 'url2', 'richard', '', '2013-04-04 05:43:54', null);
INSERT INTO `news` VALUES (3, 2, '评论标题二', '评论内容二', 'url2', 'richard', '', '2013-04-04 05:43:54', null);
INSERT INTO `news` VALUES (4, 3, '资讯标题二', '资讯内容二', 'url2', 'richard', '', '2013-04-04 05:43:54', null);
