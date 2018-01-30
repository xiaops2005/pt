/*
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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ptfans` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ptfans`;

/*Table structure for table `movie_info` */

DROP TABLE IF EXISTS `movie_info`;

CREATE TABLE `movie_info` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '电影名称',
  `rating` decimal(10,0) DEFAULT NULL COMMENT '评分',
  `year` varchar(4) DEFAULT NULL COMMENT '年份',
  `small_image` varchar(100) DEFAULT NULL COMMENT '小图片URL',
  `medium_image` varchar(100) DEFAULT NULL COMMENT '中图片URL',
  `large_image` varchar(100) DEFAULT NULL COMMENT '大图片URL',
  `alt` varchar(50) DEFAULT NULL COMMENT '豆瓣URL',
  `countries` varchar(100) DEFAULT NULL COMMENT '国家（/分割）',
  `genres` varchar(100) DEFAULT NULL COMMENT '类型（/分割）',
  `current_season` varchar(5) DEFAULT NULL COMMENT '第几季',
  `original_title` varchar(100) DEFAULT NULL COMMENT '原始标题',
  `summary` text COMMENT '介绍',
  `subtype` varchar(10) DEFAULT NULL COMMENT '类型',
  `akas` varchar(200) DEFAULT NULL COMMENT '别名（/分割）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `casts` varchar(500) DEFAULT NULL COMMENT '演员',
  `directors` varchar(200) DEFAULT NULL COMMENT '导演',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `movie_info` */

/*Table structure for table `movie_star` */

DROP TABLE IF EXISTS `movie_star`;

CREATE TABLE `movie_star` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `movie_id` varchar(32) DEFAULT NULL COMMENT '电影ID',
  `star_id` varchar(32) DEFAULT NULL COMMENT '演员ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `star_info` */

/*Table structure for table `torrent_info` */

DROP TABLE IF EXISTS `torrent_info`;

CREATE TABLE `torrent_info` (
  `id` varchar(32) NOT NULL COMMENT 'pk',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `magnet` varchar(500) DEFAULT NULL COMMENT '磁力链URL',
  `movie_id` varchar(32) DEFAULT NULL COMMENT '电影ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_info` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
