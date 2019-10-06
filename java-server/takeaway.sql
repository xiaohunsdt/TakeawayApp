/*
 Navicat Premium Data Transfer

 Source Server         : 虚拟机
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 192.168.0.17:3306
 Source Schema         : takeaway

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 07/10/2019 03:44:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `detail` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('49268c005a631e2d77b7b90a206fe0ec', '49268c005a631e2d77b7b90a206fe0eb', 'asdasdas', 'zxcz', '111111', 1);

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `login_date` timestamp(0) NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('8b99e2780bf8c3c43d8f95bf9e2492a0', 'admin', 'admin', NULL, '2019-09-20 03:35:16', '2019-09-20 03:48:40');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `deleted` int(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('0c309767046ac9952bda4ea5dee37770', '汤类', NULL, '2019-10-07 02:30:22', '2019-10-07 02:30:22', 0);
INSERT INTO `category` VALUES ('1447c360419f319bcd38e9ef043f07b8', '饮品类', NULL, '2019-10-07 02:30:40', '2019-10-07 02:30:40', 0);
INSERT INTO `category` VALUES ('1f1680134fc00750c5f432faa83644d3', '炖菜类', NULL, '2019-10-07 02:30:07', '2019-10-07 02:30:07', 0);
INSERT INTO `category` VALUES ('8410fe3eac3dd72c7b0aeb4f24cc05a8', '凉菜类', NULL, '2019-09-20 05:03:17', '2019-10-07 02:29:35', 0);
INSERT INTO `category` VALUES ('a8f1b3bae4c02e3208aa108603bdb3eb', '素菜小炒', NULL, '2019-10-07 02:29:46', '2019-10-07 02:29:46', 0);
INSERT INTO `category` VALUES ('c7ae0008e4c4b0b7f6cf5d270fa36e06', '荤菜小炒', NULL, '2019-10-07 02:30:00', '2019-10-07 02:30:00', 0);
INSERT INTO `category` VALUES ('e5f6c1eb9aeff5675d69ee33b9a2c946', '主食类', NULL, '2019-10-07 02:30:16', '2019-10-07 02:30:16', 0);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `desc` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `flags` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `month_sale` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `rate` int(1) UNSIGNED NOT NULL DEFAULT 10,
  `state` int(1) NOT NULL DEFAULT 0,
  `create_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(10) UNSIGNED NULL DEFAULT 0,
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '美味好吃的蒜蓉黄瓜!', '/upload/images/50408c6a9606435ab4e80d8915b7e6a2.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 0, '2019-09-22 04:06:33', '2019-10-07 03:25:16', 9, 0);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `number` int(11) UNSIGNED NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `goods_count` int(11) NULL DEFAULT NULL,
  `discount` int(11) NULL DEFAULT NULL,
  `discounted_prices` int(11) UNSIGNED NOT NULL DEFAULT 0,
  `all_price` int(11) UNSIGNED NOT NULL,
  `real_price` int(11) UNSIGNED NOT NULL,
  `payment_way` int(4) UNSIGNED NOT NULL,
  `ps` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pay_state` int(2) UNSIGNED NOT NULL DEFAULT 0,
  `order_state` int(2) UNSIGNED NULL DEFAULT 0,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) UNSIGNED NULL DEFAULT 0,
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('030139ac7dd581c329c8578d9060a43e', 1, '49268c005a631e2d77b7b90a206fe0eb', '49268c005a631e2d77b7b90a206fe0ec', NULL, NULL, 0, 1000, 1000, 2, NULL, 0, 0, '2019-10-07 01:07:54', '2019-10-07 01:18:25', 1, 0);
INSERT INTO `order` VALUES ('f4b4e5d5dbc88094db45ca98f3e81eec', 2, '49268c005a631e2d77b7b90a206fe0eb', '49268c005a631e2d77b7b90a206fe0ec', NULL, NULL, 0, 1000, 1000, 0, NULL, 0, 0, '2019-10-07 01:14:07', '2019-10-07 01:14:07', 0, 0);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `goods_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `goods_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_thumb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods_price` int(11) NULL DEFAULT NULL,
  `goods_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `money` int(11) UNSIGNED NULL DEFAULT 0,
  `open_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `birthday` timestamp(0) NULL DEFAULT NULL,
  `gender` tinyint(1) UNSIGNED NULL DEFAULT 0,
  `level` tinyint(2) UNSIGNED NULL DEFAULT 1,
  `last_login_date` timestamp(0) NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) UNSIGNED NULL DEFAULT 0,
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('49268c005a631e2d77b7b90a206fe0eb', 'test', NULL, 'jeremy', 0, NULL, 'http://www.baidu.com', NULL, 0, 1, NULL, '2019-10-06 03:34:32', '2019-10-06 03:36:31', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
