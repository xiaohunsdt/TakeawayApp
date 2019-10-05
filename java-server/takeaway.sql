/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : takeaway

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 06/10/2019 02:44:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `detail` varchar(254) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `is_default` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `login_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES ('8b99e2780bf8c3c43d8f95bf9e2492a0', 'admin', 'admin', NULL, '2019-09-20 03:35:16', '2019-09-20 03:48:40');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` int(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('8410fe3eac3dd72c7b0aeb4f24cc05a8', '素菜小炒', NULL, '2019-09-20 05:03:17', '2019-09-20 05:04:11', 0);
COMMIT;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `desc` varchar(32) DEFAULT NULL,
  `thumb` varchar(255) DEFAULT NULL,
  `category_id` varchar(32) DEFAULT NULL,
  `flag` varchar(32) DEFAULT NULL,
  `price` int(10) unsigned NOT NULL DEFAULT '0',
  `month_sale` int(10) unsigned NOT NULL DEFAULT '0',
  `rate` int(1) unsigned NOT NULL DEFAULT '10',
  `state` tinyint(1) NOT NULL DEFAULT '0',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(10) unsigned DEFAULT '0',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
BEGIN;
INSERT INTO `goods` VALUES ('761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜1', '美味好吃的蒜蓉黄瓜!', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1569665705&di=2ba082452b0304e60a4b4d2bf58cc23d&imgtype=jpg&er=1&src=http%3A%2F%2Fpic16.nipic.com%2F20110820%2F128199_122056177000_2.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 6000, 0, 10, 0, '2019-09-22 04:06:33', '2019-09-22 04:07:11', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `discounted_prices` int(11) DEFAULT NULL,
  `all_price` int(11) DEFAULT NULL,
  `real_price` int(11) DEFAULT NULL,
  `payment_way` int(4) DEFAULT NULL,
  `ps` varchar(254) DEFAULT NULL,
  `pay_state` int(4) DEFAULT NULL,
  `order_state` int(4) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `version` int(11) unsigned DEFAULT '0',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL,
  `goods_id` varchar(32) DEFAULT NULL,
  `goods_name` varchar(32) DEFAULT NULL,
  `goods_thumb` varchar(255) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `open_id` varchar(128) DEFAULT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `last_login_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `version` int(11) unsigned DEFAULT '0',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
