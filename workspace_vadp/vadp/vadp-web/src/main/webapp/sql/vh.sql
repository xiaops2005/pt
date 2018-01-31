/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.47
Source Server Version : 50556
Source Host           : 192.168.1.47:3306
Source Database       : vh

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2017-06-16 17:20:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `demo_annual_rating`
-- ----------------------------
DROP TABLE IF EXISTS `demo_annual_rating`;
CREATE TABLE `demo_annual_rating` (
  `id` varchar(255) NOT NULL,
  `annual` varchar(255) DEFAULT NULL,
  `res` varchar(255) DEFAULT NULL,
  `user_ID` varchar(255) DEFAULT NULL,
  `user_id_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5yk5ndr02l9wjpd3w5rjffl6k` (`user_ID`),
  KEY `FK_2lk2qupanuahxe1aqowdgqeu8` (`user_id_ID`),
  CONSTRAINT `FK_2lk2qupanuahxe1aqowdgqeu8` FOREIGN KEY (`user_id_ID`) REFERENCES `demo_user` (`ID`),
  CONSTRAINT `FK_5yk5ndr02l9wjpd3w5rjffl6k` FOREIGN KEY (`user_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_annual_rating
-- ----------------------------
INSERT INTO `demo_annual_rating` VALUES ('001', '2016', '良好', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);
INSERT INTO `demo_annual_rating` VALUES ('002', '2015', '优秀', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);
INSERT INTO `demo_annual_rating` VALUES ('003', '2014', '中等', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);

-- ----------------------------
-- Table structure for `demo_business_travel`
-- ----------------------------
DROP TABLE IF EXISTS `demo_business_travel`;
CREATE TABLE `demo_business_travel` (
  `id` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_ID` varchar(255) DEFAULT NULL,
  `user_id_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hdpr6m0xe87v4ymy7kfhg3wpa` (`user_ID`),
  KEY `FK_4k9qvtpy9phhvfjvrlsoygs7q` (`user_id_ID`),
  CONSTRAINT `FK_4k9qvtpy9phhvfjvrlsoygs7q` FOREIGN KEY (`user_id_ID`) REFERENCES `demo_user` (`ID`),
  CONSTRAINT `FK_hdpr6m0xe87v4ymy7kfhg3wpa` FOREIGN KEY (`user_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_business_travel
-- ----------------------------
INSERT INTO `demo_business_travel` VALUES ('t001', '2017-3-15', '2017-3-26', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);
INSERT INTO `demo_business_travel` VALUES ('t002', '2016-11-25', '2016-11-29', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);
INSERT INTO `demo_business_travel` VALUES ('t003', '2017-02-25', '2017-03-02', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', null);

-- ----------------------------
-- Table structure for `demo_company`
-- ----------------------------
DROP TABLE IF EXISTS `demo_company`;
CREATE TABLE `demo_company` (
  `ID` varchar(40) NOT NULL COMMENT '编号',
  `COMPANY` varchar(32) NOT NULL COMMENT '组织',
  `DEPARTMENT` varchar(32) DEFAULT NULL COMMENT '部门',
  `USER_ID` varchar(40) DEFAULT NULL COMMENT '人员编号',
  PRIMARY KEY (`ID`),
  KEY `FK_k12n0npn46jcqo6t8pgucbm4c` (`USER_ID`),
  CONSTRAINT `FK_k12n0npn46jcqo6t8pgucbm4c` FOREIGN KEY (`USER_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织表';

-- ----------------------------
-- Records of demo_company
-- ----------------------------
INSERT INTO `demo_company` VALUES ('b72eb965-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '1');
INSERT INTO `demo_company` VALUES ('b7311735-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10');
INSERT INTO `demo_company` VALUES ('b7332a2a-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb4ab-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7351ccb-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb681-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b73774dd-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb713-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7398d6e-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb786-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b73be8e1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb804-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b73e41d2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb886-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b740ac39-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb8e9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7431aea-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb968-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7457408-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afb9ca-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b747ba1f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afba29-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b74a1046-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afba84-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b74c2838-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbb79-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b74ed527-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbbd8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7510f4e-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbc3e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b75327cd-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbce8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b755175b-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbd3f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7573bde-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbd96-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b75960dd-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbde9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b75b7d90-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbe40-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b75dae09-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbe97-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b75fb1c1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbee6-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b761a1af-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbf3d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7640780-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbf94-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b767bf5a-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afbfe7-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b76b75ae-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc03e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b76f2fb5-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc09c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b773cf7c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc0f3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b779d286-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc14a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b77fba76-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc1a1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b785dc78-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc1f8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b78bc39d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc267-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b791dbc9-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc2c2-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7983183-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afc315-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b79e2c6d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afda7e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7a29931-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdb2b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7a86098-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdb9a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7ac545f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdbf5-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7b0dff4-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdc50-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7b36fed-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdc9f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7b5c0bc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdcee-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7b7b8bb-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afdd5c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7ba1df0-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afddb3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7bc3d89-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afde0e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7be4ab4-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '研发部', '10afde61-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7c18546-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '11');
INSERT INTO `demo_company` VALUES ('b7c56375-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '12');
INSERT INTO `demo_company` VALUES ('b7c95001-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '13');
INSERT INTO `demo_company` VALUES ('b7cd3d6d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '14');
INSERT INTO `demo_company` VALUES ('b7d16a91-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '15');
INSERT INTO `demo_company` VALUES ('b7d55979-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '16');
INSERT INTO `demo_company` VALUES ('b7d9471c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '17');
INSERT INTO `demo_company` VALUES ('b7dd6307-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '18');
INSERT INTO `demo_company` VALUES ('b7e1782d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '19');
INSERT INTO `demo_company` VALUES ('b7e4cde9-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6862c1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7e71106-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686415-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7e97b86-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686468-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7ebea8c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6864a7-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7ee4959-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6864fe-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7f05255-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686551-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7f29e33-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686584-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7f4d8c7-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6865b4-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7f6f32f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6865ef-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7f96c29-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686622-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7fba6b2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686652-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b7fe0736-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686685-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8007b90-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6866c8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b802c558-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686703-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8064f8c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68673b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b80ac561-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68676e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b80fc1fb-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68679d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8130f20-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6867d1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8156af6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686800-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b817b7cb-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686830-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b81a21b3-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68685f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b81d42fa-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686892-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b820c62f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6868c2-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b82478b0-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6868fd-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8280401-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68692c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b82c32cc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686960-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b83225e7-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b68698f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b835af89-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6869bf-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b839457a-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b6869ea-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b83e80b4-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686a1d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b84460f9-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686a51-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8482139-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686a80-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b84c1307-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686aac-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b84f1c35-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686adf-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b851789f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686b0e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b85377cd-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686b3e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b855c987-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686b69-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b859410c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686b9d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b85cc448-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686bcc-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8604366-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686bfb-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b863c216-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686c2b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8673768-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686c5a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b86ac991-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686c95-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b86e538c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '1b686cc5-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b871e995-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2');
INSERT INTO `demo_company` VALUES ('b8757859-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '20');
INSERT INTO `demo_company` VALUES ('b87901e1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '21');
INSERT INTO `demo_company` VALUES ('b87c9505-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22');
INSERT INTO `demo_company` VALUES ('b88055f4-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289589-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b883ea42-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '222896bd-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b887a5f3-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289714-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b88b874a-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228975f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b89002ae-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228979a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8930148-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '222897e9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b89543ca-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228981d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8985c1d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289850-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b89ac0dc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289883-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b89d416f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '222898b7-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b89f6253-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '222898ea-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8a19302-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228991d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8a3a8db-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289951-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8a761c1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '22289efb-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8ab7fd6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a01b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8af8273-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a104-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8b377bf-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a215-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8b7159c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a254-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8b97d6a-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a335-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8bbd9bc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a432-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8be45dc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a46d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8c17888-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a556-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8c5bde8-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a64b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8c9d596-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a683-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8ccdf49-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a6b6-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8d01cfc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a78b-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8d3627d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a7da-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8d6a9f2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a821-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8d8d119-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a859-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8db16d1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a88c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8de2c78-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a8c3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8e1ef52-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a8f7-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8e68652-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a92a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8ea5902-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228a95d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8ed39f7-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228aa1f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8ef7e2c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228aa5a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8f21735-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228aa8e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8f53848-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228aac9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8f89a02-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228ab18-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b8fd4d54-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '测试部', '2228abf9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b903a5a6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '2228acea-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b909fb1c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '2228ad3d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b90f5d9b-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '2228ad90-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9135ddc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '2228addb-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b91750ae-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '23');
INSERT INTO `demo_company` VALUES ('b91b5d11-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '24');
INSERT INTO `demo_company` VALUES ('b9219973-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '25');
INSERT INTO `demo_company` VALUES ('b92844b8-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '26');
INSERT INTO `demo_company` VALUES ('b92f279f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '27');
INSERT INTO `demo_company` VALUES ('b935a86f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '28');
INSERT INTO `demo_company` VALUES ('b93bf625-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '29');
INSERT INTO `demo_company` VALUES ('b940165b-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '3');
INSERT INTO `demo_company` VALUES ('b942267d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '30');
INSERT INTO `demo_company` VALUES ('b9447d60-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '31');
INSERT INTO `demo_company` VALUES ('b94705e9-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '32');
INSERT INTO `demo_company` VALUES ('b94946c2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '33');
INSERT INTO `demo_company` VALUES ('b94b84d1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '34');
INSERT INTO `demo_company` VALUES ('b94e5232-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '35');
INSERT INTO `demo_company` VALUES ('b9520fa0-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '36');
INSERT INTO `demo_company` VALUES ('b95590fc-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '37');
INSERT INTO `demo_company` VALUES ('b9595f88-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '38');
INSERT INTO `demo_company` VALUES ('b95d373f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '39');
INSERT INTO `demo_company` VALUES ('b961cb90-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4');
INSERT INTO `demo_company` VALUES ('b966c1de-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '40');
INSERT INTO `demo_company` VALUES ('b96a6023-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '41');
INSERT INTO `demo_company` VALUES ('b96dff03-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '42');
INSERT INTO `demo_company` VALUES ('b97195f3-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '43');
INSERT INTO `demo_company` VALUES ('b975766f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '44');
INSERT INTO `demo_company` VALUES ('b97ab103-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '45');
INSERT INTO `demo_company` VALUES ('b982b318-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18c87-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9872669-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18dd3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b98a511f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18e2e-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b98ca8bb-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18e79-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b98ebac4-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18eb8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b991c8ef-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18efb-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b996c166-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18f32-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b99b9013-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18f6a-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9a06fb2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18fa1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9a52a06-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd18fd8-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9aa73b6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1900c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9b119d5-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19043-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9b74b3f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19076-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9bdcc35-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd190aa-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9c2b732-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd190e5-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9c5cbd0-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1911c-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9c857e7-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19150-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9cdc798-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1919f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9d32824-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd191d6-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9d6cd9c-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19209-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9da5e7e-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1923d-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9dd33bf-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19288-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9dff685-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd192c3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9e299ca-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd192f6-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9e5302f-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd193e7-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9e8306d-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1941f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9ea9025-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19456-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9ed1162-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19489-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9efa070-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd194bd-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9f262e6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd194f0-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9f4ee55-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19527-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9f70554-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd1955f-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9fafab6-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19592-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('b9fee300-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd195c9-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba02c8da-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19605-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba0718e1-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19638-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba0bbed2-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19711-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba116260-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bd19754-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba17a011-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bdf85ee-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba1d86ca-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bdf88c1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba217289-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4bdf89f5-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba23ead7-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4be7fbb3-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba263aca-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4be7fde0-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba289505-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '4be7fee1-46aa-11e7-bf80-c85b76bc198e');
INSERT INTO `demo_company` VALUES ('ba2bf1f8-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '5');
INSERT INTO `demo_company` VALUES ('ba2ffad0-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '6');
INSERT INTO `demo_company` VALUES ('ba340e58-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '7');
INSERT INTO `demo_company` VALUES ('ba382389-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '8');
INSERT INTO `demo_company` VALUES ('ba3c105b-4b24-11e7-a0b1-000c29a47ca0', '三角集团公司', '销售部', '9');

-- ----------------------------
-- Table structure for `demo_demission`
-- ----------------------------
DROP TABLE IF EXISTS `demo_demission`;
CREATE TABLE `demo_demission` (
  `ID` varchar(32) NOT NULL COMMENT '编号',
  `DEMISSION_DATE` date DEFAULT NULL COMMENT '时间',
  `CAUSE_TYPE` char(1) DEFAULT NULL COMMENT '原因分类',
  `REMARK` varchar(1024) DEFAULT NULL COMMENT '描述',
  `USER_ID` varchar(40) DEFAULT NULL COMMENT '员工编号',
  PRIMARY KEY (`ID`),
  KEY `FK_94m178a18xj930qrx4b3a4et5` (`USER_ID`),
  CONSTRAINT `FK_94m178a18xj930qrx4b3a4et5` FOREIGN KEY (`USER_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='离职信息管理';

-- ----------------------------
-- Records of demo_demission
-- ----------------------------
INSERT INTO `demo_demission` VALUES ('1', '2017-01-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('10', '2017-01-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('100', '2017-07-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('101', '2017-07-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('102', '2017-07-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('103', '2017-07-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('104', '2017-07-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('105', '2017-07-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('106', '2017-08-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('107', '2017-08-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('108', '2017-08-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('109', '2017-08-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('11', '2017-01-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('110', '2017-08-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('111', '2017-08-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('112', '2017-08-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('113', '2017-08-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('114', '2017-08-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('115', '2017-08-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('116', '2017-08-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('117', '2017-08-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('118', '2017-08-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('119', '2017-08-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('12', '2017-01-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('13', '2017-01-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('14', '2017-01-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('15', '2017-01-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('16', '2017-02-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('166', '2017-09-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('167', '2017-09-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('168', '2017-09-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('169', '2017-09-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('17', '2017-02-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('170', '2017-09-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('171', '2017-09-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('172', '2017-09-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('173', '2017-09-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('174', '2017-09-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('175', '2017-09-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('176', '2017-09-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('177', '2017-09-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('178', '2017-09-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('179', '2017-09-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('18', '2017-02-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('180', '2017-09-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('181', '2017-10-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('182', '2017-10-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('183', '2017-10-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('184', '2017-10-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('185', '2017-10-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('186', '2017-10-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('187', '2017-10-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('188', '2017-10-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('189', '2017-10-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('19', '2017-02-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('190', '2017-10-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('191', '2017-10-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('192', '2017-10-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('193', '2017-10-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('194', '2017-10-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('195', '2017-10-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('196', '2017-11-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('197', '2017-11-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('198', '2017-11-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('199', '2017-11-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('2', '2017-01-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('20', '2017-02-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('200', '2017-11-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('201', '2017-11-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('202', '2017-11-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('203', '2017-11-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('204', '2017-11-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('205', '2017-11-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('206', '2017-11-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('207', '2017-11-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('208', '2017-11-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('209', '2017-11-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('21', '2017-02-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('210', '2017-11-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('211', '2017-12-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('212', '2017-12-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('213', '2017-12-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('214', '2017-12-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('215', '2017-12-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('216', '2017-12-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('217', '2017-12-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('218', '2017-12-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('219', '2017-12-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('22', '2017-02-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('220', '2017-12-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('221', '2017-12-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('222', '2017-12-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('223', '2017-12-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('224', '2017-12-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('225', '2017-12-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('23', '2017-02-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('24', '2017-02-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('25', '2017-02-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('26', '2017-02-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('27', '2017-02-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('28', '2017-02-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('29', '2017-02-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('3', '2017-01-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('30', '2017-02-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('31', '2017-03-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('32', '2017-03-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('33', '2017-03-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('34', '2017-03-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('35', '2017-03-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('36', '2017-03-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('37', '2017-03-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('38', '2017-03-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('39', '2017-03-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('4', '2017-01-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('40', '2017-03-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('41', '2017-03-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('42', '2017-03-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('43', '2017-03-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('44', '2017-03-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('45', '2017-03-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('46', '2017-04-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('47', '2017-04-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('48', '2017-04-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('49', '2017-04-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('5', '2017-01-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('50', '2017-04-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('51', '2017-04-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('52', '2017-04-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('53', '2017-04-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('54', '2017-04-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('55', '2017-04-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('56', '2017-04-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('57', '2017-04-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('58', '2017-04-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('59', '2017-04-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('6', '2017-01-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('60', '2017-04-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('61', '2017-05-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('62', '2017-05-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('63', '2017-05-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('64', '2017-05-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('65', '2017-05-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('66', '2017-05-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('67', '2017-05-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('68', '2017-05-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('69', '2017-05-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('7', '2017-01-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('70', '2017-05-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('71', '2017-05-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('72', '2017-05-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('73', '2017-05-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('74', '2017-05-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('75', '2017-05-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('76', '2017-06-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('77', '2017-06-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('78', '2017-06-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('79', '2017-06-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('8', '2017-01-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('80', '2017-06-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('81', '2017-06-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('82', '2017-06-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('83', '2017-06-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('84', '2017-06-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('85', '2017-06-23', '3', '', '10');
INSERT INTO `demo_demission` VALUES ('86', '2017-06-23', '4', '', '11');
INSERT INTO `demo_demission` VALUES ('87', '2017-06-23', '1', '', '12');
INSERT INTO `demo_demission` VALUES ('88', '2017-06-23', '2', '', '13');
INSERT INTO `demo_demission` VALUES ('89', '2017-06-23', '3', '', '14');
INSERT INTO `demo_demission` VALUES ('9', '2017-01-23', '2', '', '9');
INSERT INTO `demo_demission` VALUES ('90', '2017-06-23', '4', '', '15');
INSERT INTO `demo_demission` VALUES ('91', '2017-07-23', '1', '', '1');
INSERT INTO `demo_demission` VALUES ('92', '2017-07-23', '2', '', '2');
INSERT INTO `demo_demission` VALUES ('93', '2017-07-23', '3', '', '3');
INSERT INTO `demo_demission` VALUES ('94', '2017-07-23', '4', '', '4');
INSERT INTO `demo_demission` VALUES ('95', '2017-07-23', '5', '', '5');
INSERT INTO `demo_demission` VALUES ('96', '2017-07-23', '6', '', '6');
INSERT INTO `demo_demission` VALUES ('97', '2017-07-23', '7', '', '7');
INSERT INTO `demo_demission` VALUES ('98', '2017-07-23', '1', '', '8');
INSERT INTO `demo_demission` VALUES ('99', '2017-07-23', '2', '', '9');

-- ----------------------------
-- Table structure for `demo_dict`
-- ----------------------------
DROP TABLE IF EXISTS `demo_dict`;
CREATE TABLE `demo_dict` (
  `ID` varchar(40) NOT NULL COMMENT '编号',
  `table_name` varchar(32) DEFAULT NULL COMMENT '表名',
  `field_name` varchar(32) DEFAULT NULL COMMENT '列名',
  `field_value` varchar(32) DEFAULT NULL COMMENT '列值',
  `field_cn` varchar(32) DEFAULT NULL COMMENT '列中文值',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of demo_dict
-- ----------------------------
INSERT INTO `demo_dict` VALUES ('f5a73b72-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '1', '工作压力', '1', '');
INSERT INTO `demo_dict` VALUES ('f5a9881a-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '2', '薪资福利', '2', '');
INSERT INTO `demo_dict` VALUES ('f5abc68f-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '3', '职业发展', '3', '');
INSERT INTO `demo_dict` VALUES ('f5adf9fb-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '4', '个人原因', '4', '');
INSERT INTO `demo_dict` VALUES ('f5b06c14-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '5', '外部环境', '5', '');
INSERT INTO `demo_dict` VALUES ('f5b3020b-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '6', '裁员', '6', '');
INSERT INTO `demo_dict` VALUES ('f5b58014-4b26-11e7-a0b1-000c29a47ca0', 'demo_demission', 'cause_type', '7', '辞退', '7', '');
INSERT INTO `demo_dict` VALUES ('f5b80707-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '0', '研究生', '0', '');
INSERT INTO `demo_dict` VALUES ('f5ba989d-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '1', '大学本科', '1', '');
INSERT INTO `demo_dict` VALUES ('f5bceace-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '2', '大专和专科学校', '2', '');
INSERT INTO `demo_dict` VALUES ('f5bfbb2d-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '3', '中专和中技', '3', '');
INSERT INTO `demo_dict` VALUES ('f5c26807-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '4', '技工学校', '4', '');
INSERT INTO `demo_dict` VALUES ('f5c4c41e-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'education', '5', '无学历数据', '5', '');
INSERT INTO `demo_dict` VALUES ('f5c76fac-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'age', 'age<30', '30岁以下', '1', '辅助');
INSERT INTO `demo_dict` VALUES ('f5ca4ebd-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'age', 'and>=30 and age<40', '30岁(含)-40岁', '2', '辅助');
INSERT INTO `demo_dict` VALUES ('f5cccde9-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'age', 'and>=40 and age<50', '40岁(含)-50岁', '3', '辅助');
INSERT INTO `demo_dict` VALUES ('f5cf62e9-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'age', 'and>=50 and age<60', '50岁以上', '4', '辅助');
INSERT INTO `demo_dict` VALUES ('f5d18da8-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'status', '1', '是', '1', '');
INSERT INTO `demo_dict` VALUES ('f5d3bc1f-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'status', '0', '否', '0', '');
INSERT INTO `demo_dict` VALUES ('f5d5e1d7-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'sex', '0', '男', '0', '');
INSERT INTO `demo_dict` VALUES ('f5d7f807-4b26-11e7-a0b1-000c29a47ca0', 'demo_user', 'sex', '1', '女', '1', '');

-- ----------------------------
-- Table structure for `demo_org`
-- ----------------------------
DROP TABLE IF EXISTS `demo_org`;
CREATE TABLE `demo_org` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `description_ID` varchar(255) DEFAULT NULL,
  `is_major_ID` varchar(255) DEFAULT NULL,
  `position_level` int(11) DEFAULT NULL COMMENT '下属级别',
  PRIMARY KEY (`id`),
  KEY `FK_44ra45pjfripyvvjcxsuxp5ki` (`description_ID`),
  KEY `FK_l862nbhxryghkoswuvq3orjb6` (`is_major_ID`),
  CONSTRAINT `FK_44ra45pjfripyvvjcxsuxp5ki` FOREIGN KEY (`description_ID`) REFERENCES `demo_user` (`ID`),
  CONSTRAINT `FK_l862nbhxryghkoswuvq3orjb6` FOREIGN KEY (`is_major_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_org
-- ----------------------------
INSERT INTO `demo_org` VALUES ('1', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afbf94-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('10', '10afb968-46aa-11e7-bf80-c85b76bc198e', '1b68676e-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('11', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afb786-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_org` VALUES ('12', '10afb968-46aa-11e7-bf80-c85b76bc198e', '1b686800-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_org` VALUES ('13', '10afb968-46aa-11e7-bf80-c85b76bc198e', '2228a821-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_org` VALUES ('2', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afb9ca-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('3', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afbb79-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('4', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afbe40-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('5', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afc09c-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('6', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afc14a-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('7', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afdc9f-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('8', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afc315-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_org` VALUES ('9', '10afb968-46aa-11e7-bf80-c85b76bc198e', '10afde61-46aa-11e7-bf80-c85b76bc198e', '1');

-- ----------------------------
-- Table structure for `demo_product`
-- ----------------------------
DROP TABLE IF EXISTS `demo_product`;
CREATE TABLE `demo_product` (
  `id` varchar(255) NOT NULL,
  `creation_date` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_major` varchar(255) DEFAULT NULL,
  `last_updator` varchar(255) DEFAULT NULL,
  `nums` varchar(255) DEFAULT NULL,
  `overdue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_product
-- ----------------------------
INSERT INTO `demo_product` VALUES ('E00001', '厂商1', '供应商B', 'Foam', 'Scinie Honey', '3', '3.00', 'Honey Mask');
INSERT INTO `demo_product` VALUES ('M00001', '厂商1', '供应商A', 'Zero Sun(型号)', 'Missha All(名称)', '1', '4.00', 'Safe Block(规格)');
INSERT INTO `demo_product` VALUES ('M00002', '厂商1', '供应商A', 'Rream', 'Innisfree Green', '1', '10.00', 'Asd');
INSERT INTO `demo_product` VALUES ('M00003', '厂商1', '供应商A', 'Whitening Cream', 'Claire\'s Cloud', '1', '11.00', 'Tea Balancing');
INSERT INTO `demo_product` VALUES ('M00004', '厂商1', '供应商B', 'Snail', 'Skin79 Fresh', '1', '12.00', 'Eco Snail Moisture');
INSERT INTO `demo_product` VALUES ('Q00001', '厂商2', '供应商A', 'Yerf', 'Mrede', '6', '17.00', 'Sea');
INSERT INTO `demo_product` VALUES ('Q00002', '厂商2', '供应商A', 'Oxer', 'Hsdeat', '6', '9.00', 'Oly');
INSERT INTO `demo_product` VALUES ('S00001', '厂商2', '供应商A', 'Lue', 'Jertry Du', '5', '19.00', 'Green');
INSERT INTO `demo_product` VALUES ('S00002', '厂商2', '供应商A', 'Aer', 'Kee Wiren', '5', '14.00', 'Cosrx');
INSERT INTO `demo_product` VALUES ('Y00001', '厂商1', '供应商B', 'Cream', 'Cosrx Advanced', '2', '13.00', 'Garden Mask');
INSERT INTO `demo_product` VALUES ('Y00002', '厂商1', '供应商A', 'Gel', 'Tonymoly Pure', '2', '1.00', 'Blanc De');
INSERT INTO `demo_product` VALUES ('Y00003', '厂商1', '供应商C', 'Gef', 'Innisfree Canola', '2', '2.00', 'Snail 92 All in One');
INSERT INTO `demo_product` VALUES ('Z00001', '厂商1', '供应商C', 'Hierd', 'Nex Yue', '4', '4.00', 'Banana');
INSERT INTO `demo_product` VALUES ('Z00002', '厂商1', '供应商A', 'Kee', 'Olay', '4', '6.00', 'Cleaning');

-- ----------------------------
-- Table structure for `demo_reimb_document`
-- ----------------------------
DROP TABLE IF EXISTS `demo_reimb_document`;
CREATE TABLE `demo_reimb_document` (
  `id` varchar(255) NOT NULL,
  `apply_date` varchar(255) DEFAULT NULL,
  `matter` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `reimbursement_amount` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `write_down_amount` varchar(255) DEFAULT NULL,
  `user_ID` varchar(255) DEFAULT NULL,
  `deptid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rqqnkx0h202o3xcqe6qrdjf2` (`user_ID`),
  CONSTRAINT `FK_rqqnkx0h202o3xcqe6qrdjf2` FOREIGN KEY (`user_ID`) REFERENCES `demo_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_reimb_document
-- ----------------------------
INSERT INTO `demo_reimb_document` VALUES ('0729f5a3160d43cf9cf5113c0a7f16d6', '2017-06-10T16:00:00.000Z', '123', '报销单', null, '567', '345', null, '567', '10afb4ab-46aa-11e7-bf80-c85b76bc198e', '1');
INSERT INTO `demo_reimb_document` VALUES ('111', '2017-04-18T16:00:00.000Z', '111', '测试报告001', null, '356', '嗯嗯111', null, '1.00', '10afb786-46aa-11e7-bf80-c85b76bc198e', '3');
INSERT INTO `demo_reimb_document` VALUES ('222', '2017-05-31T16:00:00.000Z', '222', '测试报告002', null, '115', '哈哈', null, '1', '10afb4ab-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_reimb_document` VALUES ('333', '2017-05-31T16:00:00.000Z', '测试', '测试报告003', null, '187', '测试啊啊', null, '1', '10afb4ab-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_reimb_document` VALUES ('55f47ad10f644468b05ae275ca5b88af', '2017-06-07T16:00:00.000Z', '0000', '测试报告004', null, '100', '000', null, '26', '10afb713-46aa-11e7-bf80-c85b76bc198e', '2');
INSERT INTO `demo_reimb_document` VALUES ('5f38c35ba4324e94bdc44c67c59891d0', '2017-06-14T05:29:41.871Z', 'qwe', '报销单', null, '234', '234', null, '234', '1', '2');
INSERT INTO `demo_reimb_document` VALUES ('9a30dd488b354382a7b494f91a016de4', '2017-06-09T16:00:00.000Z', '1', '测试报告005', null, '111', '111', null, '23', '10', '2');
INSERT INTO `demo_reimb_document` VALUES ('a1ef8535e1dd4df4a686e51a10020887', '2017-06-06T16:00:00.000Z', '1', '测试报告006', null, '100', '1', null, '32', '10', '2');
INSERT INTO `demo_reimb_document` VALUES ('eaa033e36818476d8f8451f93d03dbc7', '2017-06-12T16:00:00.000Z', '出差', '99985', null, '40.00', '出差11', null, '8.00', '10', '2');

-- ----------------------------
-- Table structure for `demo_reimb_document_detail`
-- ----------------------------
DROP TABLE IF EXISTS `demo_reimb_document_detail`;
CREATE TABLE `demo_reimb_document_detail` (
  `id` varchar(255) NOT NULL,
  `amount` varchar(255) DEFAULT NULL,
  `bill_classification` varchar(255) DEFAULT NULL,
  `bill_date` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `document_ID` varchar(255) DEFAULT NULL,
  `user_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kal5w5fhnq8iralv5svi6qj87` (`document_ID`),
  KEY `FK_4rylcf443vycsm0umb9k6ilh7` (`user_ID`),
  CONSTRAINT `FK_4rylcf443vycsm0umb9k6ilh7` FOREIGN KEY (`user_ID`) REFERENCES `demo_user` (`ID`),
  CONSTRAINT `FK_kal5w5fhnq8iralv5svi6qj87` FOREIGN KEY (`document_ID`) REFERENCES `demo_reimb_document` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_reimb_document_detail
-- ----------------------------
INSERT INTO `demo_reimb_document_detail` VALUES ('1', '100', '1', '2017-06-29', 'eee', '111', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('2', '200', '2', '2017-06-08', '2', '111', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('2b9f7cf7eb464953abfeb5d37d0d3bf1', '0.2', '1', '2017-06-16', null, '0729f5a3160d43cf9cf5113c0a7f16d6', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('3', '1', '1', '2017-06-08', 'aaaa', '222', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('4', '1', '3', '2017-06-08', '555', '222', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('42b9999046f84fe29ffb7ed61152d0d2', '56', '3', '2017-06-14', null, '111', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('49526b5b61dd486aa62ad1f13b439e63', '60', '3', '2017-06-13', null, 'a1ef8535e1dd4df4a686e51a10020887', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('5', '144', '1', '2017-06-08', '66699', '333', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('6b72936c72ae402d91b24be3c28b913c', '1000', '1', '2017-06-16', null, 'eaa033e36818476d8f8451f93d03dbc7', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('925bf5ba1a2f41d38d69498a31b265c1', '23', '1', '2017-06-22', null, '0729f5a3160d43cf9cf5113c0a7f16d6', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('9d40b5e6cc5a42bda4b2611eec13e542', '12', '1', '2017-06-13', null, 'a1ef8535e1dd4df4a686e51a10020887', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('aa23fda58a8241798ce65c30a14b702e', '1000', '1', '2017-06-16', null, '55f47ad10f644468b05ae275ca5b88af', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('af88e534e3b24a1d9d12d2896b3b48f9', '0', '1', '2017-06-14', null, '9a30dd488b354382a7b494f91a016de4', null);
INSERT INTO `demo_reimb_document_detail` VALUES ('eed13585048445a29db253c32545f0c1', '23', '4', '2017-06-14', null, 'a1ef8535e1dd4df4a686e51a10020887', null);

-- ----------------------------
-- Table structure for `demo_user`
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `ID` varchar(40) NOT NULL COMMENT '编号',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '帐号',
  `NAME` varchar(100) NOT NULL COMMENT '姓名',
  `POSITION` varchar(32) DEFAULT NULL COMMENT '职位',
  `OUR_AAGE` int(11) DEFAULT NULL COMMENT '司龄',
  `RANK` int(11) DEFAULT NULL COMMENT '职级',
  `EDUCATION` int(11) DEFAULT NULL COMMENT '学历',
  `SEX` char(1) DEFAULT NULL COMMENT '性别',
  `BIRTHDATE` date DEFAULT NULL COMMENT '生日',
  `NATIONALITY` varchar(2) DEFAULT NULL COMMENT '国籍',
  `CREDENTIALS_TYPE` varchar(32) DEFAULT NULL COMMENT '证件类型',
  `CREDENTIALS_NUMBER` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `STATUS` int(11) DEFAULT NULL COMMENT '是否在职',
  `IN_DATE` date DEFAULT NULL COMMENT '入职时间',
  `DEPTID` varchar(32) DEFAULT NULL,
  `pic_src` varchar(60) DEFAULT NULL,
  `pic_src1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES ('1', 'A1', '张三', 'BI工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10', 'A10', '邓三', '网络规划师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb4ab-46aa-11e7-bf80-c85b76bc198e', 'A1', 'b张三', 'BI工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb681-46aa-11e7-bf80-c85b76bc198e', 'A10', 'b邓三', '网络规划师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb713-46aa-11e7-bf80-c85b76bc198e', 'A11', 'b阮五', 'HR', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2005-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb786-46aa-11e7-bf80-c85b76bc198e', 'A12', '张尚因', '采购师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb804-46aa-11e7-bf80-c85b76bc198e', 'A13', 'b花田', '律师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb886-46aa-11e7-bf80-c85b76bc198e', 'A15', 'b包子', '会计师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb8e9-46aa-11e7-bf80-c85b76bc198e', 'A16', 'b李四1', 'BI工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb968-46aa-11e7-bf80-c85b76bc198e', 'A17', 'b阮一峰1', 'Java工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afb9ca-46aa-11e7-bf80-c85b76bc198e', 'A18', '许达', 'Hive工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picb3.jpg', null);
INSERT INTO `demo_user` VALUES ('10afba29-46aa-11e7-bf80-c85b76bc198e', 'A19', 'b张飞1', 'Linux工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afba84-46aa-11e7-bf80-c85b76bc198e', 'A2', 'b李四', 'Java工程师', null, '3', '1', '0', '1984-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbb79-46aa-11e7-bf80-c85b76bc198e', 'A20', '王克俭', 'DBA', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2002-11-23', null, 'img/managerSelf/picb4.jpg', null);
INSERT INTO `demo_user` VALUES ('10afbbd8-46aa-11e7-bf80-c85b76bc198e', 'A21', 'b邓小平1', 'GIS工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbc3e-46aa-11e7-bf80-c85b76bc198e', 'A22', 'b毛泽东1', '系统架构师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbce8-46aa-11e7-bf80-c85b76bc198e', 'A23', 'b孙燕姿1', '项目经理', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2004-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbd3f-46aa-11e7-bf80-c85b76bc198e', 'A24', 'b邓三1', '系统分析师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbd96-46aa-11e7-bf80-c85b76bc198e', 'A25', 'b阮五1', '网络规划师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbde9-46aa-11e7-bf80-c85b76bc198e', 'A26', 'b张娜娜1', 'HR', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbe40-46aa-11e7-bf80-c85b76bc198e', 'A27', '张倩', '采购师', null, '4', '4', '1', '1993-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picg1.jpg', null);
INSERT INTO `demo_user` VALUES ('10afbe97-46aa-11e7-bf80-c85b76bc198e', 'A28', 'b韭菜', '保安', null, '4', '5', '1', '1995-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbee6-46aa-11e7-bf80-c85b76bc198e', 'A29', 'b包子1', 'CFO', null, '2', '1', '0', '1989-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbf3d-46aa-11e7-bf80-c85b76bc198e', 'A3', 'b阮一峰', 'Hive工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2003-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afbf94-46aa-11e7-bf80-c85b76bc198e', 'A30', '张志坚', '会计师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picb8.jpg', null);
INSERT INTO `demo_user` VALUES ('10afbfe7-46aa-11e7-bf80-c85b76bc198e', 'A31', 'b李四2', 'BI工程师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc03e-46aa-11e7-bf80-c85b76bc198e', 'A32', 'b阮一峰2', 'Java工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc09c-46aa-11e7-bf80-c85b76bc198e', 'A33', '林峰', 'Hive工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picb9.jpg', null);
INSERT INTO `demo_user` VALUES ('10afc0f3-46aa-11e7-bf80-c85b76bc198e', 'A34', 'b张飞2', 'Linux工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2014-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc14a-46aa-11e7-bf80-c85b76bc198e', 'A35', '王晓', 'DBA', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picg2.jpg', null);
INSERT INTO `demo_user` VALUES ('10afc1a1-46aa-11e7-bf80-c85b76bc198e', 'A36', 'b邓小平2', 'GIS工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2013-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc1f8-46aa-11e7-bf80-c85b76bc198e', 'A37', 'b毛泽东2', '系统架构师', null, '2', '1', '0', '1981-11-23', '中国', '身份证', null, '1', '2012-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc267-46aa-11e7-bf80-c85b76bc198e', 'A38', 'b孙燕姿2', '项目经理', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc2c2-46aa-11e7-bf80-c85b76bc198e', 'A39', 'b邓三2', '系统分析师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2010-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afc315-46aa-11e7-bf80-c85b76bc198e', 'A4', '马生金', 'Linux工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picg5.jpg', null);
INSERT INTO `demo_user` VALUES ('10afda7e-46aa-11e7-bf80-c85b76bc198e', 'A40', 'b阮五2', '网络规划师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2009-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdb2b-46aa-11e7-bf80-c85b76bc198e', 'A41', 'b张娜娜2', 'HR', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdb9a-46aa-11e7-bf80-c85b76bc198e', 'A42', 'b花田2', '采购师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdbf5-46aa-11e7-bf80-c85b76bc198e', 'A43', 'b馒头2', '律师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2006-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdc50-46aa-11e7-bf80-c85b76bc198e', 'A44', 'b包子2', 'CTO', null, '2', '2', '0', '1982-11-23', '中国', '身份证', null, '1', '2007-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdc9f-46aa-11e7-bf80-c85b76bc198e', 'A45', '刘大力', '会计师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2008-11-23', null, 'img/managerSelf/picb10.jpg', null);
INSERT INTO `demo_user` VALUES ('10afdcee-46aa-11e7-bf80-c85b76bc198e', 'A5', 'b张飞', 'DBA', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afdd5c-46aa-11e7-bf80-c85b76bc198e', 'A6', 'b王五', 'GIS工程师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afddb3-46aa-11e7-bf80-c85b76bc198e', 'A7', 'b邓小平', '系统架构师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afde0e-46aa-11e7-bf80-c85b76bc198e', 'A8', 'b毛泽东', '项目经理', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('10afde61-46aa-11e7-bf80-c85b76bc198e', 'A9', '孙小丽', '系统分析师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picg6.jpg', null);
INSERT INTO `demo_user` VALUES ('11', 'A11', '阮五', 'HR', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '0', '2005-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('12', 'A12', '张娜娜', '采购师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('13', 'A13', '花田', '律师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('14', 'A14', '馒头', 'CEO', null, '1', '0', '1', '1986-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('15', 'A15', '包子', '会计师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('16', 'A16', '李四1', 'BI工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('17', 'A17', '阮一峰1', 'Java工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('18', 'A18', '李晓1', 'Hive工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('19', 'A19', '张飞1', 'Linux工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6862c1-46aa-11e7-bf80-c85b76bc198e', 'A1', 'a张三', 'BI工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686415-46aa-11e7-bf80-c85b76bc198e', 'A10', 'a邓三', '网络规划师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686468-46aa-11e7-bf80-c85b76bc198e', 'A11', 'a阮五', 'HR', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2005-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6864a7-46aa-11e7-bf80-c85b76bc198e', 'A12', 'a张娜娜', '采购师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6864fe-46aa-11e7-bf80-c85b76bc198e', 'A13', 'a花田', '律师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686551-46aa-11e7-bf80-c85b76bc198e', 'A15', 'a包子', '会计师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686584-46aa-11e7-bf80-c85b76bc198e', 'A16', 'a李四1', 'BI工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6865b4-46aa-11e7-bf80-c85b76bc198e', 'A17', 'a阮一峰1', 'Java工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6865ef-46aa-11e7-bf80-c85b76bc198e', 'A18', 'a李晓1', 'Hive工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686622-46aa-11e7-bf80-c85b76bc198e', 'A19', 'a张飞1', 'Linux工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686652-46aa-11e7-bf80-c85b76bc198e', 'A2', 'a李四', 'Java工程师', null, '3', '1', '0', '1984-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686685-46aa-11e7-bf80-c85b76bc198e', 'A20', 'a王五1', 'DBA', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2002-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6866c8-46aa-11e7-bf80-c85b76bc198e', 'A21', 'a邓小平1', 'GIS工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686703-46aa-11e7-bf80-c85b76bc198e', 'A22', 'a毛泽东1', '系统架构师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b68673b-46aa-11e7-bf80-c85b76bc198e', 'A23', 'a孙燕姿1', '项目经理', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2004-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b68676e-46aa-11e7-bf80-c85b76bc198e', 'A24', '邓三生', '系统分析师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, 'img/managerSelf/picg7.jpg', null);
INSERT INTO `demo_user` VALUES ('1b68679d-46aa-11e7-bf80-c85b76bc198e', 'A25', 'a阮五1', '网络规划师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6867d1-46aa-11e7-bf80-c85b76bc198e', 'A26', 'a张娜娜1', 'HR', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686800-46aa-11e7-bf80-c85b76bc198e', 'A27', '刘达达一', '采购师', null, '4', '4', '1', '1993-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686830-46aa-11e7-bf80-c85b76bc198e', 'A28', 'a韭菜', '保安', null, '4', '5', '1', '1995-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b68685f-46aa-11e7-bf80-c85b76bc198e', 'A29', 'a包子1', 'CFO', null, '2', '1', '0', '1989-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686892-46aa-11e7-bf80-c85b76bc198e', 'A3', 'a阮一峰', 'Hive工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2003-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6868c2-46aa-11e7-bf80-c85b76bc198e', 'A30', 'a张三2', '会计师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6868fd-46aa-11e7-bf80-c85b76bc198e', 'A31', 'a李四2', 'BI工程师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b68692c-46aa-11e7-bf80-c85b76bc198e', 'A32', 'a阮一峰2', 'Java工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686960-46aa-11e7-bf80-c85b76bc198e', 'A33', 'a李晓2', 'Hive工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b68698f-46aa-11e7-bf80-c85b76bc198e', 'A34', 'a张飞2', 'Linux工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2014-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6869bf-46aa-11e7-bf80-c85b76bc198e', 'A35', 'a王五2', 'DBA', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b6869ea-46aa-11e7-bf80-c85b76bc198e', 'A36', 'a邓小平2', 'GIS工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2013-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686a1d-46aa-11e7-bf80-c85b76bc198e', 'A37', 'a毛泽东2', '系统架构师', null, '2', '1', '0', '1981-11-23', '中国', '身份证', null, '1', '2012-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686a51-46aa-11e7-bf80-c85b76bc198e', 'A38', 'a孙燕姿2', '项目经理', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686a80-46aa-11e7-bf80-c85b76bc198e', 'A39', 'a邓三2', '系统分析师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2010-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686aac-46aa-11e7-bf80-c85b76bc198e', 'A4', 'a李晓', 'Linux工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686adf-46aa-11e7-bf80-c85b76bc198e', 'A40', 'a阮五2', '网络规划师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2009-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686b0e-46aa-11e7-bf80-c85b76bc198e', 'A41', 'a张娜娜2', 'HR', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686b3e-46aa-11e7-bf80-c85b76bc198e', 'A42', 'a花田2', '采购师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686b69-46aa-11e7-bf80-c85b76bc198e', 'A43', 'a馒头2', '律师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2006-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686b9d-46aa-11e7-bf80-c85b76bc198e', 'A44', 'a包子2', 'CTO', null, '2', '2', '0', '1982-11-23', '中国', '身份证', null, '1', '2007-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686bcc-46aa-11e7-bf80-c85b76bc198e', 'A45', 'a伊莲', '会计师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686bfb-46aa-11e7-bf80-c85b76bc198e', 'A5', 'a张飞', 'DBA', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686c2b-46aa-11e7-bf80-c85b76bc198e', 'A6', 'a王五', 'GIS工程师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686c5a-46aa-11e7-bf80-c85b76bc198e', 'A7', 'a邓小平', '系统架构师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686c95-46aa-11e7-bf80-c85b76bc198e', 'A8', 'a毛泽东', '项目经理', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('1b686cc5-46aa-11e7-bf80-c85b76bc198e', 'A9', 'a孙燕姿', '系统分析师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2', 'A2', '李四', 'Java工程师', null, '3', '1', '0', '1984-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('20', 'A20', '王五1', 'DBA', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2002-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('21', 'A21', '邓小平1', 'GIS工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22', 'A22', '毛泽东1', '系统架构师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289589-46aa-11e7-bf80-c85b76bc198e', 'A1', 'c张三', 'BI工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('222896bd-46aa-11e7-bf80-c85b76bc198e', 'A10', 'c邓三', '网络规划师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289714-46aa-11e7-bf80-c85b76bc198e', 'A11', 'c阮五', 'HR', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2005-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228975f-46aa-11e7-bf80-c85b76bc198e', 'A12', 'c张娜娜', '采购师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228979a-46aa-11e7-bf80-c85b76bc198e', 'A13', 'c花田', '律师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('222897e9-46aa-11e7-bf80-c85b76bc198e', 'A15', 'c包子', '会计师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228981d-46aa-11e7-bf80-c85b76bc198e', 'A16', 'c李四1', 'BI工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289850-46aa-11e7-bf80-c85b76bc198e', 'A17', 'c阮一峰1', 'Java工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289883-46aa-11e7-bf80-c85b76bc198e', 'A18', 'c李晓1', 'Hive工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('222898b7-46aa-11e7-bf80-c85b76bc198e', 'A19', 'c张飞1', 'Linux工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('222898ea-46aa-11e7-bf80-c85b76bc198e', 'A2', 'c李四', 'Java工程师', null, '3', '1', '0', '1984-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228991d-46aa-11e7-bf80-c85b76bc198e', 'A20', 'c王五1', 'DBA', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2002-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289951-46aa-11e7-bf80-c85b76bc198e', 'A21', 'c邓小平1', 'GIS工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('22289efb-46aa-11e7-bf80-c85b76bc198e', 'A22', 'c毛泽东1', '系统架构师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a01b-46aa-11e7-bf80-c85b76bc198e', 'A23', 'c孙燕姿1', '项目经理', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2004-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a104-46aa-11e7-bf80-c85b76bc198e', 'A24', 'c邓三1', '系统分析师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a215-46aa-11e7-bf80-c85b76bc198e', 'A25', 'c阮五1', '网络规划师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a254-46aa-11e7-bf80-c85b76bc198e', 'A26', 'c张娜娜1', 'HR', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a335-46aa-11e7-bf80-c85b76bc198e', 'A27', 'c方便面', '采购师', null, '4', '4', '1', '1993-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a432-46aa-11e7-bf80-c85b76bc198e', 'A28', 'c韭菜', '保安', null, '4', '5', '1', '1995-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a46d-46aa-11e7-bf80-c85b76bc198e', 'A29', 'c包子1', 'CFO', null, '2', '1', '0', '1989-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a556-46aa-11e7-bf80-c85b76bc198e', 'A3', 'c阮一峰', 'Hive工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2003-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a64b-46aa-11e7-bf80-c85b76bc198e', 'A30', 'c张三2', '会计师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a683-46aa-11e7-bf80-c85b76bc198e', 'A31', 'c李四2', 'BI工程师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a6b6-46aa-11e7-bf80-c85b76bc198e', 'A32', 'c阮一峰2', 'Java工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a78b-46aa-11e7-bf80-c85b76bc198e', 'A33', 'c李晓2', 'Hive工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a7da-46aa-11e7-bf80-c85b76bc198e', 'A34', 'c张飞2', 'Linux工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2014-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a821-46aa-11e7-bf80-c85b76bc198e', 'A35', 'c王五2', 'DBA', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a859-46aa-11e7-bf80-c85b76bc198e', 'A36', 'c邓小平2', 'GIS工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2013-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a88c-46aa-11e7-bf80-c85b76bc198e', 'A37', 'c毛泽东2', '系统架构师', null, '2', '1', '0', '1981-11-23', '中国', '身份证', null, '1', '2012-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a8c3-46aa-11e7-bf80-c85b76bc198e', 'A38', 'c孙燕姿2', '项目经理', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a8f7-46aa-11e7-bf80-c85b76bc198e', 'A39', 'c邓三2', '系统分析师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2010-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a92a-46aa-11e7-bf80-c85b76bc198e', 'A4', 'c李晓', 'Linux工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228a95d-46aa-11e7-bf80-c85b76bc198e', 'A40', 'c阮五2', '网络规划师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2009-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228aa1f-46aa-11e7-bf80-c85b76bc198e', 'A41', 'c张娜娜2', 'HR', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228aa5a-46aa-11e7-bf80-c85b76bc198e', 'A42', 'c花田2', '采购师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228aa8e-46aa-11e7-bf80-c85b76bc198e', 'A43', 'c馒头2', '律师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2006-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228aac9-46aa-11e7-bf80-c85b76bc198e', 'A44', 'c包子2', 'CTO', null, '2', '2', '0', '1982-11-23', '中国', '身份证', null, '1', '2007-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228ab18-46aa-11e7-bf80-c85b76bc198e', 'A45', 'c伊莲', '会计师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228abf9-46aa-11e7-bf80-c85b76bc198e', 'A5', 'c张飞', 'DBA', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228acea-46aa-11e7-bf80-c85b76bc198e', 'A6', 'c王五', 'GIS工程师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228ad3d-46aa-11e7-bf80-c85b76bc198e', 'A7', 'c邓小平', '系统架构师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228ad90-46aa-11e7-bf80-c85b76bc198e', 'A8', 'c毛泽东', '项目经理', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('2228addb-46aa-11e7-bf80-c85b76bc198e', 'A9', 'c孙燕姿', '系统分析师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('23', 'A23', '孙燕姿1', '项目经理', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2004-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('24', 'A24', '邓三1', '系统分析师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('25', 'A25', '阮五1', '网络规划师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('26', 'A26', '张娜娜1', 'HR', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('27', 'A27', '方便面', '采购师', null, '4', '4', '1', '1993-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('28', 'A28', '韭菜', '保安', null, '4', '5', '1', '1995-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('29', 'A29', '包子1', 'CFO', null, '2', '1', '0', '1989-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('3', 'A3', '阮一峰', 'Hive工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '0', '2003-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('30', 'A30', '张三2', '会计师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('31', 'A31', '李四2', 'BI工程师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('32', 'A32', '阮一峰2', 'Java工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('33', 'A33', '李晓2', 'Hive工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('34', 'A34', '张飞2', 'Linux工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2014-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('35', 'A35', '王五2', 'DBA', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('36', 'A36', '邓小平2', 'GIS工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2013-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('37', 'A37', '毛泽东2', '系统架构师', null, '2', '1', '0', '1981-11-23', '中国', '身份证', null, '1', '2012-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('38', 'A38', '孙燕姿2', '项目经理', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('39', 'A39', '邓三2', '系统分析师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2010-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4', 'A4', '李晓', 'Linux工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('40', 'A40', '阮五2', '网络规划师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2009-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('41', 'A41', '张娜娜2', 'HR', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('42', 'A42', '花田2', '采购师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('43', 'A43', '馒头2', '律师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2006-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('44', 'A44', '包子2', 'CTO', null, '2', '2', '0', '1982-11-23', '中国', '身份证', null, '1', '2007-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('45', 'A45', '伊莲', '会计师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18c87-46aa-11e7-bf80-c85b76bc198e', 'A1', 'd张三', 'BI工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18dd3-46aa-11e7-bf80-c85b76bc198e', 'A10', 'd邓三', '网络规划师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18e2e-46aa-11e7-bf80-c85b76bc198e', 'A11', 'd阮五', 'HR', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2005-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18e79-46aa-11e7-bf80-c85b76bc198e', 'A12', 'd张娜娜', '采购师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18eb8-46aa-11e7-bf80-c85b76bc198e', 'A13', 'd花田', '律师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18efb-46aa-11e7-bf80-c85b76bc198e', 'A15', 'd包子', '会计师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18f32-46aa-11e7-bf80-c85b76bc198e', 'A16', 'd李四1', 'BI工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18f6a-46aa-11e7-bf80-c85b76bc198e', 'A17', 'd阮一峰1', 'Java工程师', null, '3', '2', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18fa1-46aa-11e7-bf80-c85b76bc198e', 'A18', 'd李晓1', 'Hive工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd18fd8-46aa-11e7-bf80-c85b76bc198e', 'A19', 'd张飞1', 'Linux工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1900c-46aa-11e7-bf80-c85b76bc198e', 'A2', 'd李四', 'Java工程师', null, '3', '1', '0', '1984-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19043-46aa-11e7-bf80-c85b76bc198e', 'A20', 'd王五1', 'DBA', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2002-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19076-46aa-11e7-bf80-c85b76bc198e', 'A21', 'd邓小平1', 'GIS工程师', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd190aa-46aa-11e7-bf80-c85b76bc198e', 'A22', 'd毛泽东1', '系统架构师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd190e5-46aa-11e7-bf80-c85b76bc198e', 'A23', 'd孙燕姿1', '项目经理', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2004-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1911c-46aa-11e7-bf80-c85b76bc198e', 'A24', 'd邓三1', '系统分析师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19150-46aa-11e7-bf80-c85b76bc198e', 'A25', 'd阮五1', '网络规划师', null, '2', '1', '1', '1988-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1919f-46aa-11e7-bf80-c85b76bc198e', 'A26', 'd张娜娜1', 'HR', null, '3', '3', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd191d6-46aa-11e7-bf80-c85b76bc198e', 'A27', 'd方便面', '采购师', null, '4', '4', '1', '1993-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19209-46aa-11e7-bf80-c85b76bc198e', 'A28', 'd韭菜', '保安', null, '4', '5', '1', '1995-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1923d-46aa-11e7-bf80-c85b76bc198e', 'A29', 'd包子1', 'CFO', null, '2', '1', '0', '1989-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19288-46aa-11e7-bf80-c85b76bc198e', 'A3', 'd阮一峰', 'Hive工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2003-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd192c3-46aa-11e7-bf80-c85b76bc198e', 'A30', 'd张三2', '会计师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd192f6-46aa-11e7-bf80-c85b76bc198e', 'A31', 'd李四2', 'BI工程师', null, '3', '1', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd193e7-46aa-11e7-bf80-c85b76bc198e', 'A32', 'd阮一峰2', 'Java工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1941f-46aa-11e7-bf80-c85b76bc198e', 'A33', 'd李晓2', 'Hive工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19456-46aa-11e7-bf80-c85b76bc198e', 'A34', 'd张飞2', 'Linux工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2014-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19489-46aa-11e7-bf80-c85b76bc198e', 'A35', 'd王五2', 'DBA', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd194bd-46aa-11e7-bf80-c85b76bc198e', 'A36', 'd邓小平2', 'GIS工程师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2013-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd194f0-46aa-11e7-bf80-c85b76bc198e', 'A37', 'd毛泽东2', '系统架构师', null, '2', '1', '0', '1981-11-23', '中国', '身份证', null, '1', '2012-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19527-46aa-11e7-bf80-c85b76bc198e', 'A38', 'd孙燕姿2', '项目经理', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2011-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd1955f-46aa-11e7-bf80-c85b76bc198e', 'A39', 'd邓三2', '系统分析师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2010-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19592-46aa-11e7-bf80-c85b76bc198e', 'A4', 'd李晓', 'Linux工程师', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd195c9-46aa-11e7-bf80-c85b76bc198e', 'A40', 'd阮五2', '网络规划师', null, '2', '2', '0', '1981-11-23', '中国', '身份证', null, '1', '2009-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19605-46aa-11e7-bf80-c85b76bc198e', 'A41', 'd张娜娜2', 'HR', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19638-46aa-11e7-bf80-c85b76bc198e', 'A42', 'd花田2', '采购师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19711-46aa-11e7-bf80-c85b76bc198e', 'A43', 'd馒头2', '律师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2006-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bd19754-46aa-11e7-bf80-c85b76bc198e', 'A44', 'd包子2', 'CTO', null, '2', '2', '0', '1982-11-23', '中国', '身份证', null, '1', '2007-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bdf85ee-46aa-11e7-bf80-c85b76bc198e', 'A45', 'd伊莲', '会计师', null, '3', '2', '1', '1987-11-23', '中国', '身份证', null, '1', '2008-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bdf88c1-46aa-11e7-bf80-c85b76bc198e', 'A5', 'd张飞', 'DBA', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4bdf89f5-46aa-11e7-bf80-c85b76bc198e', 'A6', 'd王五', 'GIS工程师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4be7fbb3-46aa-11e7-bf80-c85b76bc198e', 'A7', 'd邓小平', '系统架构师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4be7fde0-46aa-11e7-bf80-c85b76bc198e', 'A8', 'd毛泽东', '项目经理', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('4be7fee1-46aa-11e7-bf80-c85b76bc198e', 'A9', 'd孙燕姿', '系统分析师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '1', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('5', 'A5', '张飞', 'DBA', null, '3', '1', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('6', 'A6', '王五', 'GIS工程师', null, '3', '0', '0', '1983-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('7', 'A7', '邓小平', '系统架构师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('8', 'A8', '毛泽东', '项目经理', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);
INSERT INTO `demo_user` VALUES ('9', 'A9', '孙燕姿', '系统分析师', null, '2', '0', '1', '1985-11-23', '中国', '身份证', null, '0', '2001-11-23', null, null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', null);
INSERT INTO `sys_role` VALUES ('2', null);
INSERT INTO `sys_role` VALUES ('3', null);
INSERT INTO `sys_role` VALUES ('4', null);
INSERT INTO `sys_role` VALUES ('5', null);
INSERT INTO `sys_role` VALUES ('6', null);
INSERT INTO `sys_role` VALUES ('7', null);
INSERT INTO `sys_role` VALUES ('8', null);
INSERT INTO `sys_role` VALUES ('9', null);
INSERT INTO `sys_role` VALUES ('10', null);
INSERT INTO `sys_role` VALUES ('11', null);
INSERT INTO `sys_role` VALUES ('12', null);
INSERT INTO `sys_role` VALUES ('13', null);
INSERT INTO `sys_role` VALUES ('14', null);
INSERT INTO `sys_role` VALUES ('15', null);
INSERT INTO `sys_role` VALUES ('16', null);
INSERT INTO `sys_role` VALUES ('17', null);
INSERT INTO `sys_role` VALUES ('18', null);
INSERT INTO `sys_role` VALUES ('19', null);
INSERT INTO `sys_role` VALUES ('20', null);
INSERT INTO `sys_role` VALUES ('21', null);
INSERT INTO `sys_role` VALUES ('22', null);
INSERT INTO `sys_role` VALUES ('23', null);
INSERT INTO `sys_role` VALUES ('24', null);
INSERT INTO `sys_role` VALUES ('25', null);
INSERT INTO `sys_role` VALUES ('26', null);
INSERT INTO `sys_role` VALUES ('27', null);
INSERT INTO `sys_role` VALUES ('28', null);

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3xmp6q1rp7q1r7baucrlbf5l` (`role_id`),
  CONSTRAINT `FK_3xmp6q1rp7q1r7baucrlbf5l` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '你好2017!1497262272754', '2017-01-02', null);
INSERT INTO `sys_user` VALUES ('2', '李四', '2017-06-01', null);
INSERT INTO `sys_user` VALUES ('3', 'user', '2017-05-02', null);
INSERT INTO `sys_user` VALUES ('5', '张飞1', '2017-06-02', null);
INSERT INTO `sys_user` VALUES ('6', '王五', '2017-06-01', null);
INSERT INTO `sys_user` VALUES ('7', '更改', '2017-06-01', null);
INSERT INTO `sys_user` VALUES ('8', '的二次', '2017-06-01', null);
INSERT INTO `sys_user` VALUES ('9', 'ee', '2017-06-01', null);
INSERT INTO `sys_user` VALUES ('10', 'dd', '2017-05-16', null);
INSERT INTO `sys_user` VALUES ('11', 'ddtt', '2017-05-01', null);
INSERT INTO `sys_user` VALUES ('13', 'tt6', '2017-05-08', null);
INSERT INTO `sys_user` VALUES ('14', '66', '2017-05-16', null);
INSERT INTO `sys_user` VALUES ('15', '6u', '2017-05-08', null);
INSERT INTO `sys_user` VALUES ('16', '你好2017!1496284285391', '2017-01-02', '1');
INSERT INTO `sys_user` VALUES ('17', '你好2017!1496284288292', '2017-01-02', '2');
INSERT INTO `sys_user` VALUES ('18', '你好2017!1496284288903', '2017-01-02', '3');
INSERT INTO `sys_user` VALUES ('19', '你好2017!1496284289381', '2017-01-02', '4');
INSERT INTO `sys_user` VALUES ('20', '你好2017!1496284289885', '2017-01-02', '5');
INSERT INTO `sys_user` VALUES ('21', '你好2017!1496284290404', '2017-01-02', '6');
INSERT INTO `sys_user` VALUES ('22', '你好2017!1496284290941', '2017-01-02', '7');
INSERT INTO `sys_user` VALUES ('23', '你好2017!1496284291485', '2017-01-02', '8');
INSERT INTO `sys_user` VALUES ('24', '你好2017!1496284306407', '2017-01-02', '9');
INSERT INTO `sys_user` VALUES ('25', '你好2017!1496284307357', '2017-01-02', '10');
INSERT INTO `sys_user` VALUES ('26', '??2017!1496365769247', '2017-01-02', '18');
INSERT INTO `sys_user` VALUES ('27', '你好2017!1496910239294', '2017-01-02', '25');
INSERT INTO `sys_user` VALUES ('28', '你好2017!1497262270090', '2017-01-02', '26');
INSERT INTO `sys_user` VALUES ('29', '你好2017!1497337822579', '2017-01-02', '28');

-- ----------------------------
-- Table structure for `td_pt_permission`
-- ----------------------------
DROP TABLE IF EXISTS `td_pt_permission`;
CREATE TABLE `td_pt_permission` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` tinyblob NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` tinyblob,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_pt_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `td_pt_role`
-- ----------------------------
DROP TABLE IF EXISTS `td_pt_role`;
CREATE TABLE `td_pt_role` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` tinyblob NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` tinyblob,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_pt_role
-- ----------------------------

-- ----------------------------
-- Table structure for `td_pt_user`
-- ----------------------------
DROP TABLE IF EXISTS `td_pt_user`;
CREATE TABLE `td_pt_user` (
  `id` varchar(255) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` tinyblob NOT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` tinyblob,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of td_pt_user
-- ----------------------------
INSERT INTO `td_pt_user` VALUES ('37b27492-d71d-4e6d-81b9-8b3f3e22a9eb', 'system', 0xACED0005737200156F72672E6A6F64612E74696D652E496E7374616E742DC8BED0C60E9CCD0200014A0007694D696C6C697378700000015C8A51A349, null, 0xACED0005737200156F72672E6A6F64612E74696D652E496E7374616E742DC8BED0C60E9CCD0200014A0007694D696C6C697378700000015C8A51A349, null, null, null, null, 'M2E0ZWJmMTZhNDc5NWFkMjU4ZTU0MDhiYWU3YmUzNDE=', '12345678', 'admin');
INSERT INTO `td_pt_user` VALUES ('e9b0e4a7-6139-40d4-a83e-efb3fc22172d', 'system', 0xACED0005737200156F72672E6A6F64612E74696D652E496E7374616E742DC8BED0C60E9CCD0200014A0007694D696C6C697378700000015C8A51A4ED, null, 0xACED0005737200156F72672E6A6F64612E74696D652E496E7374616E742DC8BED0C60E9CCD0200014A0007694D696C6C697378700000015C8A51A4ED, null, null, null, null, 'M2E0ZWJmMTZhNDc5NWFkMjU4ZTU0MDhiYWU3YmUzNDE=', '12345678', 'test1');

-- ----------------------------
-- Table structure for `tl_pt_user_session`
-- ----------------------------
DROP TABLE IF EXISTS `tl_pt_user_session`;
CREATE TABLE `tl_pt_user_session` (
  `id` varchar(255) NOT NULL,
  `device` varchar(255) DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  `entity_id` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `last_access_datetime` datetime DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tl_pt_user_session
-- ----------------------------
