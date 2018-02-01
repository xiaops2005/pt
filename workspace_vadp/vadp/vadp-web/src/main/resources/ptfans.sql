﻿/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.56 : Database - ptfans
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ptfans` /*!40100 DEFAULT CHARACTER SET UTF8 */;

USE `ptfans`;

/*Table structure for table `movie_info` */

DROP TABLE IF EXISTS `movie_info`;

CREATE TABLE `movie_info` (
  `id` varchar(32) CHARACTER SET UTF8 NOT NULL COMMENT '豆瓣ID',
  `title` varchar(200) CHARACTER SET UTF8 DEFAULT NULL COMMENT '电影名称',
  `rating` decimal(10,0) DEFAULT NULL COMMENT '评分',
  `year` varchar(4) CHARACTER SET UTF8 DEFAULT NULL COMMENT '年份',
  `small_image` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '小图片URL',
  `medium_image` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '中图片URL',
  `large_image` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '大图片URL',
  `alt` varchar(50) CHARACTER SET UTF8 DEFAULT NULL COMMENT '豆瓣URL',
  `countries` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '国家（/分割）',
  `genres` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '类型（/分割）',
  `current_season` varchar(5) CHARACTER SET UTF8 DEFAULT NULL COMMENT '第几季',
  `original_title` varchar(100) CHARACTER SET UTF8 DEFAULT NULL COMMENT '原始标题',
  `summary` text CHARACTER SET UTF8 COMMENT '介绍',
  `subtype` varchar(10) CHARACTER SET UTF8 DEFAULT NULL COMMENT '类型',
  `akas` varchar(200) CHARACTER SET UTF8 DEFAULT NULL COMMENT '别名（/分割）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `casts` varchar(500) CHARACTER SET UTF8 DEFAULT NULL COMMENT '演员（/分割）',
  `directors` varchar(200) CHARACTER SET UTF8 DEFAULT NULL COMMENT '导演（/分割）',
  `response_json` text CHARACTER SET UTF8 COMMENT '豆瓣返回值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movie_info` */

insert  into `movie_info`(`id`,`title`,`rating`,`year`,`small_image`,`medium_image`,`large_image`,`alt`,`countries`,`genres`,`current_season`,`original_title`,`summary`,`subtype`,`akas`,`create_time`,`update_time`,`casts`,`directors`,`response_json`) values ('1764796',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'{\"rating\": {\"max\": 10, \"average\": 7.4, \"stars\": \"40\", \"min\": 0}, \"reviews_count\": 301, \"wish_count\": 15651, \"douban_site\": \"\", \"year\": \"2009\", \"images\": {\"small\": \"http://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p494268647.jpg\", \"large\": \"http://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p494268647.jpg\", \"medium\": \"http://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p494268647.jpg\"}, \"alt\": \"https:\\/\\/movie.douban.com\\/subject\\/1764796\\/\", \"id\": \"1764796\", \"mobile_url\": \"https:\\/\\/movie.douban.com\\/subject\\/1764796\\/mobile\", \"title\": \"\\u673a\\u5668\\u4eba9\\u53f7\", \"do_count\": null, \"share_url\": \"http:\\/\\/m.douban.com\\/movie\\/subject\\/1764796\", \"seasons_count\": null, \"schedule_url\": \"\", \"episodes_count\": null, \"countries\": [\"\\u7f8e\\u56fd\"], \"genres\": [\"\\u52a8\\u753b\", \"\\u5192\\u9669\", \"\\u5947\\u5e7b\"], \"collect_count\": 74039, \"casts\": [{\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1054395\\/\", \"avatars\": {\"small\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p51597.jpg\", \"large\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p51597.jpg\", \"medium\": \"http://img3.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p51597.jpg\"}, \"name\": \"\\u4f0a\\u5229\\u4e9a\\u00b7\\u4f0d\\u5fb7\", \"id\": \"1054395\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1016673\\/\", \"avatars\": {\"small\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p33305.jpg\", \"large\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p33305.jpg\", \"medium\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p33305.jpg\"}, \"name\": \"\\u8a79\\u59ae\\u5f17\\u00b7\\u5eb7\\u7eb3\\u5229\", \"id\": \"1016673\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1017907\\/\", \"avatars\": {\"small\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p55994.jpg\", \"large\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p55994.jpg\", \"medium\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p55994.jpg\"}, \"name\": \"\\u7ea6\\u7ff0\\u00b7C\\u00b7\\u8d56\\u5229\", \"id\": \"1017907\"}, {\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1036321\\/\", \"avatars\": {\"small\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p42033.jpg\", \"large\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p42033.jpg\", \"medium\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p42033.jpg\"}, \"name\": \"\\u514b\\u91cc\\u65af\\u6258\\u5f17\\u00b7\\u666e\\u5362\\u9ed8\", \"id\": \"1036321\"}], \"current_season\": null, \"original_title\": \"9\", \"summary\": \"\\u673a\\u5668\\u4eba9\\u53f7\\uff08\\u4f0a\\u5229\\u4e9a\\u2022\\u4f0d\\u5fb7 Elijah Wood \\u9970\\uff09\\u7a81\\u7136\\u9192\\u6765\\uff0c\\u53d1\\u73b0\\u8eab\\u8fb9\\u7684\\u4e16\\u754c\\u5145\\u6ee1\\u5371\\u673a\\uff0c\\u56db\\u5904\\u6b8b\\u8d25\\uff0c\\u4e00\\u7247\\u672b\\u4e16\\u666f\\u8c61\\u30029\\u53f7\\u5e26\\u7740\\u4e00\\u4e2a\\u753b\\u6709\\u4e09\\u4e2a\\u5947\\u602a\\u7b26\\u53f7\\u7684\\u5706\\u5f62\\u7269\\u4f53\\u9003\\u5230\\u8857\\u4e0a\\uff0c\\u5e78\\u9047\\u53d1\\u660e\\u5bb6\\u673a\\u5668\\u4eba2\\u53f7\\uff08\\u9a6c\\u4e01\\u2022\\u5170\\u9053 Martin Landau \\u9970\\uff09\\u7ed9\\u81ea\\u5df1\\u88c5\\u4e0a\\u4e86\\u58f0\\u97f3\\uff0c\\u4f462\\u53f7\\u5374\\u4e0d\\u5e78\\u88ab\\u673a\\u5668\\u602a\\u517d\\u6293\\u8d70\\u30029\\u53f7\\u627e\\u5230\\u4e86\\u8001\\u51751\\u53f7\\uff08\\u514b\\u91cc\\u65af\\u6258\\u5f17\\u2022\\u666e\\u5362\\u9ed8 Christopher Plummer \\u9970\\uff09\\u3001\\u673a\\u68b0\\u5de55\\u53f7\\uff08\\u7ea6\\u7ff0\\u2022\\u96f7\\u5229 John C. Reilly \\u9970\\uff09\\u3001\\u75af\\u766b\\u753b\\u5bb66\\u53f7\\uff08\\u514b\\u91cc\\u65af\\u54c1\\u2022\\u683c\\u62c9\\u592b Crispin Glover \\u9970\\uff09\\u548c\\u5927\\u529b\\u58eb8\\u53f7\\uff08\\u5f17\\u96f7\\u5fb7\\u2022\\u5854\\u5854\\u7ecd\\u5c14 Fred Tatasciore \\u9970\\uff09\\u30029\\u53f7\\u4e0e5\\u53f7\\u64c5\\u81ea\\u51fa\\u884c\\u63f4\\u65512\\u53f7\\uff0c\\u5371\\u6025\\u65f6\\u88ab\\u5973\\u6b66\\u58eb7\\u53f7\\uff08\\u8a79\\u59ae\\u4f5b\\u2022\\u5eb7\\u7eb3\\u5229 Jennifer Connelly \\u9970\\uff09\\u6551\\u4e0b\\uff0c\\u4f46\\u65e0\\u610f\\u4e2d9\\u53f7\\u5374\\u4ee4\\u7ec8\\u6781\\u673a\\u5668\\u517d\\u590d\\u6d3b\\u3002\\u5e26\\u7740\\u81ea\\u5df1\\u4ece\\u54ea\\u91cc\\u6765\\u4ee5\\u53ca\\u751f\\u5b58\\u4f7f\\u547d\\u7684\\u95ee\\u9898\\uff0c9\\u53f7\\u51b3\\u5b9a\\u60f3\\u5c3d\\u529e\\u6cd5\\u5236\\u670d\\u673a\\u5668\\u517d\\uff0c\\u62ef\\u6551\\u5168\\u4e16\\u754c\\u2026\\u2026\\u00a9\\u8c46\\u74e3\", \"subtype\": \"movie\", \"directors\": [{\"alt\": \"https:\\/\\/movie.douban.com\\/celebrity\\/1276787\\/\", \"avatars\": {\"small\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1351678808.44.jpg\", \"large\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1351678808.44.jpg\", \"medium\": \"http://img7.doubanio.com\\/view\\/celebrity\\/s_ratio_celebrity\\/public\\/p1351678808.44.jpg\"}, \"name\": \"\\u7533\\u00b7\\u963f\\u514b\", \"id\": \"1276787\"}], \"comments_count\": 11913, \"ratings_count\": 59524, \"aka\": [\"9\\uff1a\\u672b\\u4e16\\u51b3\\u6218\", \"\\u4e5d\", \"Number 9\", \"\\u673a\\u5668\\u4eba9\\u53f7\"]}');

/*Table structure for table `movie_star` */

DROP TABLE IF EXISTS `movie_star`;

CREATE TABLE `movie_star` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `movie_id` varchar(32) DEFAULT NULL COMMENT '电影ID',
  `star_id` varchar(32) DEFAULT NULL COMMENT '演员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*Data for the table `movie_star` */

/*Table structure for table `star_info` */

DROP TABLE IF EXISTS `star_info`;

CREATE TABLE `star_info` (
  `id` varchar(32) NOT NULL COMMENT '豆瓣ID',
  `name` varchar(100) DEFAULT NULL COMMENT '演员名称',
  `alt` varchar(100) DEFAULT NULL COMMENT '豆瓣URL',
  `small_image` varchar(100) DEFAULT NULL COMMENT '小图片URL',
  `medium_image` varchar(100) DEFAULT NULL COMMENT '中图片URL',
  `large_image` varchar(100) DEFAULT NULL COMMENT '大图片URL',
  `type` varchar(1) DEFAULT NULL COMMENT '演员类型(1导演2演员)',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*Data for the table `star_info` */

/*Table structure for table `torrent_info` */

DROP TABLE IF EXISTS `torrent_info`;

CREATE TABLE `torrent_info` (
  `id` varchar(32) NOT NULL COMMENT 'pk',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `magnet` varchar(500) DEFAULT NULL COMMENT '磁力链URL',
  `movie_id` varchar(32) DEFAULT NULL COMMENT '电影ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*Data for the table `torrent_info` */

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `type` varchar(1) DEFAULT NULL COMMENT '类型(1管理员2普通用户)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

/*Data for the table `user_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
