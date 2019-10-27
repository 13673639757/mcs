/*
Navicat MySQL Data Transfer

Source Server         : hwy
Source Server Version : 50727
Source Host           : 121.36.144.204:3306
Source Database       : mcs

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-10-27 11:35:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bind_info
-- ----------------------------
DROP TABLE IF EXISTS `bind_info`;
CREATE TABLE `bind_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `car_no` varchar(50) NOT NULL COMMENT '车牌号',
  `mobile_phone` varchar(45) NOT NULL COMMENT '手机号码',
  `secret_phone` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '0正常',
  `bind_date` varchar(30) DEFAULT NULL,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `car_no` (`car_no`) USING BTREE,
  UNIQUE KEY `mobile_phone` (`mobile_phone`) USING BTREE,
  UNIQUE KEY `secret_phone` (`secret_phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_link
-- ----------------------------
DROP TABLE IF EXISTS `car_link`;
CREATE TABLE `car_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `car_no` varchar(50) NOT NULL COMMENT '车牌号',
  `mobile_phone` varchar(45) DEFAULT NULL COMMENT '手机号码',
  `status` varchar(45) DEFAULT '0' COMMENT '0正常',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `modified_date` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `car_no` (`car_no`) USING BTREE,
  UNIQUE KEY `mobile_phone` (`mobile_phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for secret_phone
-- ----------------------------
DROP TABLE IF EXISTS `secret_phone`;
CREATE TABLE `secret_phone` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `phone_number` varchar(50) NOT NULL COMMENT '隐私号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_number` (`phone_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
