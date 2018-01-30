-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: localhost    Database: portal
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `pid` varchar(20) DEFAULT NULL,
  `pname` varchar(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES ('1','中文',2,'');
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_domain`
--

DROP TABLE IF EXISTS `td_pt_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_domain` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_domain`
--

LOCK TABLES `td_pt_domain` WRITE;
/*!40000 ALTER TABLE `td_pt_domain` DISABLE KEYS */;
INSERT INTO `td_pt_domain` VALUES ('402882825f0a53b9015f0a53d3630000','system','2017-10-11 15:26:27','test1','2017-10-16 17:16:30',NULL,NULL,NULL,NULL,'默认组织',NULL,NULL),('402882825f0a93d1015f0a9c36ef0001','402882825f0a53b9015f0a53d3910001','2017-10-11 16:45:31','test1','2017-10-16 17:01:48',NULL,NULL,NULL,NULL,'客户A',NULL,NULL),('402889105f2999cb015f2999df730000','test1','2017-10-17 17:11:11',NULL,'2017-10-17 17:11:11',NULL,NULL,NULL,NULL,'组织0',NULL,NULL),('402889105f2999cb015f2999dfac0001','test1','2017-10-17 17:11:11',NULL,'2017-10-17 17:11:11',NULL,NULL,NULL,NULL,'组织1',NULL,NULL),('402889105f4d0c73015f4d0e4d930001','402882825f0a53b9015f0a53d3910001','2017-10-24 14:25:04',NULL,'2017-10-24 14:25:04',NULL,NULL,NULL,NULL,'东软望海',NULL,NULL);
/*!40000 ALTER TABLE `td_pt_domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_domain_user`
--

DROP TABLE IF EXISTS `td_pt_domain_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_domain_user` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `domain_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_domain_user`
--

LOCK TABLES `td_pt_domain_user` WRITE;
/*!40000 ALTER TABLE `td_pt_domain_user` DISABLE KEYS */;
INSERT INTO `td_pt_domain_user` VALUES ('402889105f2999cb015f2999dff30003','tests','2017-10-17 17:11:11',NULL,'2017-10-17 17:11:11','402889105f2999cb015f2999dfac0001','402882825f0a93d1015f0a95e89f0000');
/*!40000 ALTER TABLE `td_pt_domain_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_menu`
--

DROP TABLE IF EXISTS `td_pt_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_menu` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `parent_name` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_menu`
--

LOCK TABLES `td_pt_menu` WRITE;
/*!40000 ALTER TABLE `td_pt_menu` DISABLE KEYS */;
INSERT INTO `td_pt_menu` VALUES ('402882825f0a53b9015f0a559baa0003','402882825f0a53b9015f0a53d3910001','2017-10-11 15:28:23','402882825f0a53b9015f0a53d3910001','2017-10-19 15:18:42',NULL,'原形应用',NULL,NULL,'',NULL,NULL,'/'),('402882825f0a53b9015f0a56a0eb0004','402882825f0a53b9015f0a53d3910001','2017-10-11 15:29:30','402882825f0a53b9015f0a53d3910001','2017-10-19 15:40:05',NULL,'api控制',NULL,'402882825f0a53b9015f0a559baa0003','根节点',NULL,NULL,'api/admin/'),('402882825f0a53b9015f0a56f69c0005','402882825f0a53b9015f0a53d3910001','2017-10-11 15:29:52','402882825f0a53b9015f0a53d3910001','2017-10-24 14:27:07',NULL,'管理控制台',NULL,'402882825f0a53b9015f0a559baa0003','',NULL,NULL,'/admin/'),('402882825f0a7826015f0a83d30b0000','402882825f0a53b9015f0a53d3910001','2017-10-11 16:18:52','402882825f0a53b9015f0a53d3910001','2017-10-19 15:43:01',NULL,'角色管理api',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:role:list,sys:role:create,sys:role:read,sys:role:update,sys:role:delete',NULL,'/api/admin/role/'),('402882825f0a7826015f0a8f23530004','402882825f0a53b9015f0a53d3910001','2017-10-11 16:31:14',NULL,'2017-10-11 16:31:14',NULL,'组织管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:domain:list,sys:domain:create,sys:domain:read,sys:domain:update,sys:domain:delete',NULL,'/api/admin/domain/'),('402882825f0a7826015f0a9042970005','402882825f0a53b9015f0a53d3910001','2017-10-11 16:32:27',NULL,'2017-10-11 16:32:27',NULL,'用户管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:user:list,sys:user:create,sys:user:read,sys:user:update,sys:user:delete',NULL,'/api/admin/user/'),('402882825f0a7826015f0a911a9b0006','402882825f0a53b9015f0a53d3910001','2017-10-11 16:33:23',NULL,'2017-10-11 16:33:23',NULL,'菜单管理',NULL,'402882825f0a53b9015f0a56a0eb0004','管理控制台','sys:menu:list,sys:menu:create,sys:menu:read,sys:menu:update,sys:menu:delete',NULL,'/api/admin/menu/'),('402889105f37c22c015f48422309000a','402882825f0a53b9015f0a53d3910001','2017-10-23 16:03:35','402882825f0a53b9015f0a53d3910001','2017-10-23 16:05:32','plus','菜单管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'*',NULL,'/admin/menus'),('402889105f4d0c73015f4d0dbc1d0000','402882825f0a53b9015f0a53d3910001','2017-10-24 14:24:27',NULL,'2017-10-24 14:24:27','appstore','组织管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:domain:list,sys:domain:create,sys:domain:read,sys:domain:update,sys:domain:delete',NULL,'/admin/domains'),('402889105f4d0c73015f4d0ed89c0004','402882825f0a53b9015f0a53d3910001','2017-10-24 14:25:39','402882825f0a53b9015f0a53d3910001','2017-10-24 14:37:07','trademark','角色管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:role:list;sys:role:create;sys:role:read;sys:role:update;sys:role:delete',NULL,'/admin/roles'),('402889105f4d0c73015f4d1b04940005','402882825f0a53b9015f0a53d3910001','2017-10-24 14:38:57','402882825f0a53b9015f0a53d3910001','2017-10-24 14:39:30','user-add','用户管理',NULL,'402882825f0a53b9015f0a56f69c0005',NULL,'sys:user:list,sys:user:create,sys:user:read,sys:user:update,sys:user:delete',NULL,'/admin/users');
/*!40000 ALTER TABLE `td_pt_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_role`
--

DROP TABLE IF EXISTS `td_pt_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_role` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `domain_id` varchar(255) DEFAULT NULL,
  `menu_ids` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `systemed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_role`
--

LOCK TABLES `td_pt_role` WRITE;
/*!40000 ALTER TABLE `td_pt_role` DISABLE KEYS */;
INSERT INTO `td_pt_role` VALUES ('402882825f0a7826015f0a87f9ba0001','402882825f0a53b9015f0a53d3910001','2017-10-11 16:23:19','test1','2017-10-24 17:29:25','402882825f0a53b9015f0a53d3630000',NULL,'administrators','超级管理员sss',NULL,''),('402882825f0a7826015f0a8b80ad0002','402882825f0a53b9015f0a53d3910001','2017-10-11 16:27:15',NULL,'2017-10-11 16:27:15','402882825f0a53b9015f0a53d3630000',NULL,'managers','组织管理员',1,''),('402882825f0a7826015f0a8c46480003','402882825f0a53b9015f0a53d3910001','2017-10-11 16:28:06',NULL,'2017-10-11 16:28:06','402882825f0a53b9015f0a53d3630000',NULL,'users','普通用户',1,''),('402882825f0a93d1015f1375aed00006','402882825f0a53b9015f0a53d3910001','2017-10-13 10:00:00',NULL,'2017-10-13 10:00:00',NULL,NULL,'hhh',NULL,1,NULL),('402889105f4d0c73015f4d0e4db20002','402882825f0a53b9015f0a53d3910001','2017-10-24 14:25:04',NULL,'2017-10-24 14:25:04','402889105f4d0c73015f4d0e4d930001',NULL,'managers','组织管理员',1,''),('402889105f4d0c73015f4d0e4dc50003','402882825f0a53b9015f0a53d3910001','2017-10-24 14:25:04',NULL,'2017-10-24 14:25:04','402889105f4d0c73015f4d0e4d930001',NULL,'users','员工',1,'');
/*!40000 ALTER TABLE `td_pt_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_role_menu`
--

DROP TABLE IF EXISTS `td_pt_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_role_menu` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `menu_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_role_menu`
--

LOCK TABLES `td_pt_role_menu` WRITE;
/*!40000 ALTER TABLE `td_pt_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `td_pt_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_user`
--

DROP TABLE IF EXISTS `td_pt_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_user` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `domain_id` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5knrfoq7s40o50w7f3vv5vchl` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_user`
--

LOCK TABLES `td_pt_user` WRITE;
/*!40000 ALTER TABLE `td_pt_user` DISABLE KEYS */;
INSERT INTO `td_pt_user` VALUES ('402882825f0a53b9015f0a53d3910001','system','2017-10-11 15:26:27',NULL,'2017-10-11 15:26:27','402882825f0a53b9015f0a53d3630000',NULL,NULL,'ac0e7d037817094e9e0b4441f9bae3209d67b02fa484917065f71b16109a1a78','admin',1,'admin'),('402882825f0a93d1015f0a95e89f0000','402882825f0a53b9015f0a53d3910001','2017-10-11 16:38:19',NULL,'2017-10-11 16:38:19','402882825f0a53b9015f0a53d3630000','test1@example.com',NULL,'8bd2a2d6e18774586909be919ec6ae37b84ef9de8c7501bbe416aec9ece38023','test1',1,'test1'),('402882825f0a93d1015f0a9ca9b90002','402882825f0a53b9015f0a53d3910001','2017-10-11 16:46:00',NULL,'2017-10-11 16:46:00','402882825f0a93d1015f0a9c36ef0001',NULL,NULL,'b7c1410f16d494c7420dda685b40129ff3df3a5dc62c2d6ccbec38921e72b40c','manager1',NULL,'manager1'),('402882825f0a93d1015f0aa68a4e0003','402882825f0a53b9015f0a53d3910001','2017-10-11 16:56:47',NULL,'2017-10-11 16:56:47','402882825f0a93d1015f0a9c36ef0001',NULL,NULL,'d4663ff0bffd10251f1c26a0a79a4708307032ecbb5180c4687319b52060b951','test11',NULL,'test11'),('402882825f0a93d1015f13744d9b0005','402882825f0a53b9015f0a53d3910001','2017-10-13 09:58:30',NULL,'2017-10-13 09:58:30','402882825f0a53b9015f0a53d3630000',NULL,NULL,'8d5fbeb1cb70e428e5b39f9a0415bfed31c891840c684af8ff553c356a880b20','ttt',NULL,'ttt'),('402889105f2999cb015f2999dfd50002','tests','2017-10-17 17:11:11',NULL,'2017-10-17 17:11:11',NULL,NULL,NULL,'ba77a08dfe703be1a0bb4575e24c8b67b2b53b0ded8d706b24ace0237e1b8c77','test2',NULL,'test2');
/*!40000 ALTER TABLE `td_pt_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_user_role`
--

DROP TABLE IF EXISTS `td_pt_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_user_role` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `domain_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_user_role`
--

LOCK TABLES `td_pt_user_role` WRITE;
/*!40000 ALTER TABLE `td_pt_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `td_pt_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `td_pt_user_token`
--

DROP TABLE IF EXISTS `td_pt_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `td_pt_user_token` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `expire_time` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `td_pt_user_token`
--

LOCK TABLES `td_pt_user_token` WRITE;
/*!40000 ALTER TABLE `td_pt_user_token` DISABLE KEYS */;
INSERT INTO `td_pt_user_token` VALUES ('402882825f0a53b9015f0a5554ae0002','system','2017-10-11 15:28:05',NULL,'2017-10-25 10:01:46','2017-10-25 22:01:46','cd68f74c7e57ef4a16cb42cb802e9385','2017-10-25 10:01:46','402882825f0a53b9015f0a53d3910001');
/*!40000 ALTER TABLE `td_pt_user_token` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-25  2:15:12
