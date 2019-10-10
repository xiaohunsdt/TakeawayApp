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

 Date: 10/10/2019 15:07:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `address` varchar(128) DEFAULT NULL,
  `detail` varchar(254) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `is_default` tinyint(1) unsigned DEFAULT '0',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of address
-- ----------------------------
BEGIN;
INSERT INTO `address` VALUES ('49268c005a631e2d77b7b90a206fe0ec', '49268c005a631e2d77b7b90a206fe0eb', 'asdasdas', 'zxcz', '111111', 1, '2019-10-09 00:24:31', 0);
COMMIT;

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
INSERT INTO `category` VALUES ('0c309767046ac9952bda4ea5dee37770', '汤类', NULL, '2019-10-07 02:30:22', '2019-10-07 02:30:22', 0);
INSERT INTO `category` VALUES ('1447c360419f319bcd38e9ef043f07b8', '饮品类', NULL, '2019-10-07 02:30:40', '2019-10-07 02:30:40', 0);
INSERT INTO `category` VALUES ('1f1680134fc00750c5f432faa83644d3', '炖菜类', NULL, '2019-10-07 02:30:07', '2019-10-07 02:30:07', 0);
INSERT INTO `category` VALUES ('8410fe3eac3dd72c7b0aeb4f24cc05a8', '凉菜类', NULL, '2019-09-20 05:03:17', '2019-10-07 02:29:35', 0);
INSERT INTO `category` VALUES ('a8f1b3bae4c02e3208aa108603bdb3eb', '素菜小炒', NULL, '2019-10-07 02:29:46', '2019-10-07 02:29:46', 0);
INSERT INTO `category` VALUES ('c7ae0008e4c4b0b7f6cf5d270fa36e06', '荤菜小炒', NULL, '2019-10-07 02:30:00', '2019-10-07 02:30:00', 0);
INSERT INTO `category` VALUES ('e5f6c1eb9aeff5675d69ee33b9a2c946', '主食类', NULL, '2019-10-07 02:30:16', '2019-10-07 02:30:16', 0);
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
  `flags` varchar(32) DEFAULT NULL,
  `price` int(10) unsigned NOT NULL DEFAULT '0',
  `month_sale` int(10) unsigned NOT NULL DEFAULT '0',
  `rate` int(1) unsigned NOT NULL DEFAULT '10',
  `state` int(1) NOT NULL DEFAULT '0',
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
INSERT INTO `goods` VALUES ('029d1b56aeb1aaad55440e4b1ac21498', '干煸肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:41', '2019-10-07 16:52:41', 0, 0);
INSERT INTO `goods` VALUES ('05444437734b79d597b8fff2c8fecd21', '麻辣烫（麻辣香锅）加菜+牛肉卷', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 3000, 0, 10, 1, '2019-10-07 17:27:46', '2019-10-07 17:27:46', 0, 0);
INSERT INTO `goods` VALUES ('05c71e0ca83929eb3577ccc60c55cc33', '素三丝', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 7000, 0, 10, 1, '2019-10-07 16:25:39', '2019-10-07 16:25:39', 0, 0);
INSERT INTO `goods` VALUES ('063919d67bae60f500d48e18c30a422c', '酸菜炒粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:37:25', '2019-10-07 16:37:25', 0, 0);
INSERT INTO `goods` VALUES ('09ca2fca09f81554ef2947f3775dce3f', '尖椒鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:15', '2019-10-07 17:02:15', 0, 0);
INSERT INTO `goods` VALUES ('0a9eca37dd94af1a19c8f2eae7549817', '酸辣汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:29:48', '2019-10-07 17:29:48', 0, 0);
INSERT INTO `goods` VALUES ('0c92136340cb0e43237b09d00762225f', '牛肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:48', '2019-10-07 17:24:48', 0, 0);
INSERT INTO `goods` VALUES ('0cc106a304191a1dc1e27523b93b92e9', '番茄蛋汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:30:15', '2019-10-07 17:30:15', 0, 0);
INSERT INTO `goods` VALUES ('11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 12000, 0, 10, 1, '2019-10-07 16:26:32', '2019-10-07 16:26:32', 0, 0);
INSERT INTO `goods` VALUES ('13daa7235fc8c0ff68eb503223fe743f', '冰红茶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:34:43', '2019-10-07 17:34:43', 0, 0);
INSERT INTO `goods` VALUES ('152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 1000, 0, 10, 1, '2019-10-07 17:18:44', '2019-10-07 17:18:44', 0, 0);
INSERT INTO `goods` VALUES ('1616e0e42ceb30d70ae916909cfe365e', '麻辣烫（麻辣香锅）加菜+虾仁 ', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 2000, 0, 10, 1, '2019-10-07 17:28:53', '2019-10-07 17:28:53', 0, 0);
INSERT INTO `goods` VALUES ('170d701974dafba665b703d25c9958a2', '扬州炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:07', '2019-10-07 17:20:07', 0, 0);
INSERT INTO `goods` VALUES ('172794967e828961791eebf401ff03f8', '干煸大虾', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:08:09', '2019-10-07 17:08:09', 0, 0);
INSERT INTO `goods` VALUES ('18fbd399b81301c6128bd92548d7a349', '蚂蚁上树', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 7000, 0, 10, 1, '2019-10-07 16:41:25', '2019-10-07 16:41:25', 0, 0);
INSERT INTO `goods` VALUES ('1aa39f6d2967b8f3eb1d6820cbc7e2b8', '老干妈炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:28', '2019-10-07 17:20:28', 0, 0);
INSERT INTO `goods` VALUES ('1ad51cb674616fad33ee5ba37415304e', '香辣小排', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:14', '2019-10-07 17:07:14', 0, 0);
INSERT INTO `goods` VALUES ('1b81de0fcc1c717da3495d9f00566293', '馄饨', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:45', '2019-10-07 17:19:45', 0, 0);
INSERT INTO `goods` VALUES ('1c2ff79b530aab6acad1148a2f27779b', '香辣鸡翅', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:57:46', '2019-10-07 16:57:46', 0, 0);
INSERT INTO `goods` VALUES ('1e8651ab5598730cd0275d7fca986e26', '辣子鸡', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:58', '2019-10-07 16:52:58', 0, 0);
INSERT INTO `goods` VALUES ('1fa5e874f2b4562e9b3c03235c3c13b4', '虾仁青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:39:02', '2019-10-07 16:39:02', 0, 0);
INSERT INTO `goods` VALUES ('212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 12000, 0, 10, 1, '2019-10-07 17:26:13', '2019-10-07 17:26:13', 0, 0);
INSERT INTO `goods` VALUES ('22da4b82121d50f90ddaff584859b969', '老干妈鸡丁', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:43:41', '2019-10-07 16:43:41', 0, 0);
INSERT INTO `goods` VALUES ('28d2ba9f82f275e5121820008c2e8a24', '土豆肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:41:44', '2019-10-07 16:41:44', 0, 0);
INSERT INTO `goods` VALUES ('2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '', '', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:22:52', '2019-10-07 19:14:25', 2, 0);
INSERT INTO `goods` VALUES ('2e2367630e6fbd6adffcc51d4b7a3806', '爆炒肝尖', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:48:07', '2019-10-07 16:48:07', 0, 0);
INSERT INTO `goods` VALUES ('309fbad34a43306e3c45e8cfd7d005b0', '羊肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:31', '2019-10-07 17:24:31', 0, 0);
INSERT INTO `goods` VALUES ('30b7ed3ce157e38e67a1ebd3bcff6c45', '三鲜汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 10000, 0, 10, 1, '2019-10-07 17:33:26', '2019-10-07 17:33:26', 0, 0);
INSERT INTO `goods` VALUES ('315ad4ec34ca27f6f5339ee5c193d8b4', '黄瓜拌牛肉', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 12000, 0, 10, 1, '2019-10-07 16:27:17', '2019-10-07 16:27:17', 0, 0);
INSERT INTO `goods` VALUES ('31a06a0038b97c891abe609b29c0eee8', '锅包肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:34', '2019-10-07 17:07:34', 0, 0);
INSERT INTO `goods` VALUES ('31e077c41e3d92cb099dd4cebb4fb11b', '湖南小炒肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:34', '2019-10-07 16:51:34', 0, 0);
INSERT INTO `goods` VALUES ('3291e2ad095c3354b82e33e9dbb4b7ad', '烧溜豆腐', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 6000, 0, 10, 1, '2019-10-07 16:29:16', '2019-10-07 16:29:16', 0, 0);
INSERT INTO `goods` VALUES ('333a1a2f0f85b45aae879f31f2f975ce', '孜然里脊', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:21', '2019-10-07 16:53:21', 0, 0);
INSERT INTO `goods` VALUES ('3489f48cb26c95750813b55f0f54d0a8', '小鸡炖蘑菇', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:13:50', '2019-10-07 17:13:50', 0, 0);
INSERT INTO `goods` VALUES ('34d64d55f6ddc14f8cda410d527691cc', '香辣肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:21', '2019-10-07 16:52:21', 0, 0);
INSERT INTO `goods` VALUES ('34e4acd099e554d58eb9e5c1935326c9', '冰糖雪梨', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:35:00', '2019-10-07 17:35:00', 0, 0);
INSERT INTO `goods` VALUES ('372f4db201871f20e81fa214dca621c5', '粉条炖牛腩', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:03', '2019-10-07 17:16:03', 0, 0);
INSERT INTO `goods` VALUES ('388d8774dc686d87f6656b9104c4c999', '清炒腐竹', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:35:56', '2019-10-07 16:35:56', 0, 0);
INSERT INTO `goods` VALUES ('3be786d311bea4d1b6be7a1fc8fd498a', '水饺', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:25', '2019-10-07 17:19:25', 0, 0);
INSERT INTO `goods` VALUES ('3cc50f52f9c51061b2f69d7535ee1a2a', '地三鲜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:40', '2019-10-07 16:34:40', 0, 0);
INSERT INTO `goods` VALUES ('3db0f8f9ce492d7149744ce7a2a3fa19', '酱油炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:48', '2019-10-07 17:20:48', 0, 0);
INSERT INTO `goods` VALUES ('3e416cfb3cdff1547842d4981afe818d', '鱼香茄子', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:32:52', '2019-10-07 16:32:52', 0, 0);
INSERT INTO `goods` VALUES ('3e9ad83124e30144d65f318d2109732c', '肉圆粉丝汤', '', '', '0c309767046ac9952bda4ea5dee37770', '', 9000, 0, 10, 1, '2019-10-07 17:33:06', '2019-10-07 17:33:45', 1, 0);
INSERT INTO `goods` VALUES ('434db0970f06d995764612c4a85c72b2', '爆炒腰花', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:50:22', '2019-10-07 16:50:22', 0, 0);
INSERT INTO `goods` VALUES ('43eadde1ed3ea445a950676353ef7853', '红烧丸子', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:57:00', '2019-10-07 16:57:00', 0, 0);
INSERT INTO `goods` VALUES ('470a11753727382cca018843c7a5e49c', '凉拌腐竹', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 6000, 0, 10, 1, '2019-10-07 16:23:32', '2019-10-07 16:23:32', 0, 0);
INSERT INTO `goods` VALUES ('49b46e756cfb5a576b853f8ee871f607', '虾仁玉米', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:40:58', '2019-10-07 16:40:58', 0, 0);
INSERT INTO `goods` VALUES ('5384a3538ae8fd34593be6209f0380b5', '尖椒炒鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:39:53', '2019-10-07 16:39:53', 0, 0);
INSERT INTO `goods` VALUES ('544d84af8501517caaa24eeb1593eced', '孜然羊肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:57', '2019-10-07 16:59:57', 0, 0);
INSERT INTO `goods` VALUES ('54a33ae4ef409d42dd196a26f30d2c0f', '可乐', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2000, 0, 10, 1, '2019-10-07 17:34:04', '2019-10-07 17:34:04', 0, 0);
INSERT INTO `goods` VALUES ('551bce6cad97153d9220cfdcaba51325', '口水鸡', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 9000, 0, 10, 1, '2019-10-07 16:26:04', '2019-10-07 16:26:04', 0, 0);
INSERT INTO `goods` VALUES ('55485bf569e0aac16b0770d2a6f9b327', '肉丝炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:01', '2019-10-07 17:23:01', 0, 0);
INSERT INTO `goods` VALUES ('5c09ec5d8d6c38ea729908e38fd5f37b', '尖椒火腿', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:47:10', '2019-10-07 16:47:10', 0, 0);
INSERT INTO `goods` VALUES ('5d4a3d59fbc7167e648f57c95c37e01e', '土豆红烧肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:01', '2019-10-07 17:03:01', 0, 0);
INSERT INTO `goods` VALUES ('5f361deb700360d98452d5b9632a40ab', '番茄炖牛腩', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:32', '2019-10-07 17:16:32', 0, 0);
INSERT INTO `goods` VALUES ('62eaf34022a211ab237d4f45d12bc175', '腐竹红烧肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:24', '2019-10-07 17:03:24', 0, 0);
INSERT INTO `goods` VALUES ('650632fb49a091669f41fbfaaf2d53fb', '糖醋里脊', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:39', '2019-10-07 16:53:39', 0, 0);
INSERT INTO `goods` VALUES ('67a7987dcbafd64a946e9ed70903bba7', '萝卜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:55', '2019-10-07 17:12:55', 0, 0);
INSERT INTO `goods` VALUES ('6e36a9126991fefc401d3799a923ca29', '酸辣土豆丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:29:46', '2019-10-07 16:29:46', 0, 0);
INSERT INTO `goods` VALUES ('6fb3bb8dd013108c39463f4924027bcb', '青椒炒鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:40:16', '2019-10-07 16:40:16', 0, 0);
INSERT INTO `goods` VALUES ('71928e9de8afc4b91b3dc1c76cba80fb', '椰汁', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:48', '2019-10-07 17:35:48', 0, 0);
INSERT INTO `goods` VALUES ('72e0fc62ce10c04e4c01afae5f94a217', '番茄鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:15', '2019-10-07 16:34:15', 0, 0);
INSERT INTO `goods` VALUES ('761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '美味好吃的蒜蓉黄瓜!', '/upload/images/50408c6a9606435ab4e80d8915b7e6a2.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-09-22 04:06:33', '2019-10-07 16:20:29', 10, 0);
INSERT INTO `goods` VALUES ('77769b218df210092e5467134b774646', '猪肉炖粉条', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:03', '2019-10-07 17:12:03', 0, 0);
INSERT INTO `goods` VALUES ('7a85eff463de0c1eeb836cccefb76a6b', '黄瓜炒鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:31:33', '2019-10-07 16:31:33', 0, 0);
INSERT INTO `goods` VALUES ('7c066108cc4c0877750d163c32b3ef22', '青椒土豆丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:30:14', '2019-10-07 16:30:14', 0, 0);
INSERT INTO `goods` VALUES ('7c6d50fb3c0b6dbf8cd52b607a43d958', '茶派', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:36:06', '2019-10-07 17:36:06', 0, 0);
INSERT INTO `goods` VALUES ('7f1eeea4a086244907da5e8cfb303cb8', '皮蛋瘦肉粥', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:38', '2019-10-07 17:22:38', 0, 0);
INSERT INTO `goods` VALUES ('7f460dc38492286587549276aaa6e552', '花甲粉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:25:15', '2019-10-07 17:25:15', 0, 0);
INSERT INTO `goods` VALUES ('8028633e923bba71a540559b404e960f', '香菇肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:54', '2019-10-07 16:49:54', 0, 0);
INSERT INTO `goods` VALUES ('80d1d16712ee9988341ccc5602f10292', '酸菜白肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:56', '2019-10-07 17:10:56', 0, 0);
INSERT INTO `goods` VALUES ('814865ead95fc474425b65a56940f3c3', '尖椒干豆腐', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:31:07', '2019-10-07 16:31:07', 0, 0);
INSERT INTO `goods` VALUES ('814976d852be95b06660e78f107bf031', '旺仔牛奶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:29', '2019-10-07 17:35:29', 0, 0);
INSERT INTO `goods` VALUES ('835a89bc3f317a32a6b92e7397b9da33', '茄条肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:16', '2019-10-07 16:45:16', 0, 0);
INSERT INTO `goods` VALUES ('85b4cbb5f09b087c27a06e5527ebe039', '青椒肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:50:46', '2019-10-07 16:50:46', 0, 0);
INSERT INTO `goods` VALUES ('861baf559ae479a7faadf125d2842fc2', '毛血旺', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:18:21', '2019-10-07 17:18:21', 0, 0);
INSERT INTO `goods` VALUES ('885e94d1daf481e72d633dfc7ec5462b', '辣炒鸡心', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:29', '2019-10-07 16:44:29', 0, 0);
INSERT INTO `goods` VALUES ('8c106901edec65a169aa7b84aa307ed6', '干煸牛腿肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:57:23', '2019-10-07 16:57:23', 0, 0);
INSERT INTO `goods` VALUES ('8ef386c437d239e990177089afda080c', '香辣猪蹄', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:50', '2019-10-07 17:06:50', 0, 0);
INSERT INTO `goods` VALUES ('8f3883855e6321762c324a8825312c59', '牛肉蛋炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:39', '2019-10-07 17:23:39', 0, 0);
INSERT INTO `goods` VALUES ('956176f39dfa2222d6a84aedc46eefd1', '三鲜疙瘩汤', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:01', '2019-10-07 17:22:01', 0, 0);
INSERT INTO `goods` VALUES ('959be79aec14a643c6bab1d08601866e', '四川回锅肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:00', '2019-10-07 16:52:00', 0, 0);
INSERT INTO `goods` VALUES ('96d06c954a6f4b40cf8c995a0cbfb0de', '麻辣烫（麻辣香锅）加菜+西蓝花', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 2000, 0, 10, 1, '2019-10-07 17:29:24', '2019-10-07 17:29:24', 0, 0);
INSERT INTO `goods` VALUES ('9ac7b907fe66e1879ab719d4ddcadf19', '油焖大虾', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:08:33', '2019-10-07 17:08:33', 0, 0);
INSERT INTO `goods` VALUES ('9cba928b7b27de0456a74e966796e205', '黄菜烧牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:11:36', '2019-10-07 17:11:36', 0, 0);
INSERT INTO `goods` VALUES ('9cd096d249efc6cfc37f4522a2a072c0', '辣炒花甲', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:28', '2019-10-07 17:06:28', 0, 0);
INSERT INTO `goods` VALUES ('9f0bb6f711f618624a94a3a43909e7bf', '香菇青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:38:31', '2019-10-07 16:38:31', 0, 0);
INSERT INTO `goods` VALUES ('9fd1e6b7a4cb343a1826ef4f947f7907', '酸辣粉', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:21:04', '2019-10-07 17:21:04', 0, 0);
INSERT INTO `goods` VALUES ('a56e4dda92915cd75be0a32fdc4be10a', '孜然肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:46', '2019-10-07 16:45:46', 0, 0);
INSERT INTO `goods` VALUES ('a5aab1591e53f67d13cf9a683114842a', '豆芽粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 6000, 0, 10, 1, '2019-10-07 16:27:47', '2019-10-07 16:27:47', 0, 0);
INSERT INTO `goods` VALUES ('a6d83178e10446a2f29a5c5c304462e8', '麻辣烫', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:25:54', '2019-10-07 17:25:54', 0, 0);
INSERT INTO `goods` VALUES ('a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:00', '2019-10-07 17:24:00', 0, 0);
INSERT INTO `goods` VALUES ('a9845eb443b77479459a765f3038ebfc', '水煮牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:14:53', '2019-10-07 17:14:53', 0, 0);
INSERT INTO `goods` VALUES ('ab4b0c39cc46b53deb0498dd4e6599d2', '皮蛋豆腐羹', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:32:41', '2019-10-07 17:32:41', 0, 0);
INSERT INTO `goods` VALUES ('abe3a6acc45ecc03f3c8cafd74251f9f', '麻辣牛肚 ', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:05:38', '2019-10-07 17:05:38', 0, 0);
INSERT INTO `goods` VALUES ('ac830dbc81a0a0e78a1b77e0d6d64921', '手撕包菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:30:41', '2019-10-07 16:30:41', 0, 0);
INSERT INTO `goods` VALUES ('aced92326fe9e237c264ad821888107e', '尖椒鸡', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:04:04', '2019-10-07 17:04:04', 0, 0);
INSERT INTO `goods` VALUES ('ad36856f9242f438d0fabaf24e479c2d', '银牙肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:43:15', '2019-10-07 16:43:15', 0, 0);
INSERT INTO `goods` VALUES ('ade51e3b9e880abc00cbcc369890322d', '孜然心管', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:08', '2019-10-07 17:06:08', 0, 0);
INSERT INTO `goods` VALUES ('b0cbede48db1b84041ea6ab32f6a51ec', '麻婆豆腐', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:35:26', '2019-10-07 16:35:26', 0, 0);
INSERT INTO `goods` VALUES ('b132f10d83515369a9e753fde3d39257', '宫保鸡丁', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:05', '2019-10-07 16:44:05', 0, 0);
INSERT INTO `goods` VALUES ('b1427e2373345a6c954d3a8c3de152d3', '酸菜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:09:59', '2019-10-07 17:09:59', 0, 0);
INSERT INTO `goods` VALUES ('b2776fe0ef5d43dfa4228dd51ceef530', '肥肠豆腐煲', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:17:56', '2019-10-07 17:17:56', 0, 0);
INSERT INTO `goods` VALUES ('b27935ecc22c836f6f390883b47671b5', '韭菜鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:47', '2019-10-07 16:33:47', 0, 0);
INSERT INTO `goods` VALUES ('b6bdb4d57bd008b200c713b361a7ef75', '孜然牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:39', '2019-10-07 16:59:39', 0, 0);
INSERT INTO `goods` VALUES ('b7d74a6ce8ca9b7a4b02cd619f6ad98a', '辣炒牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:00:23', '2019-10-07 17:00:23', 0, 0);
INSERT INTO `goods` VALUES ('bb08daaca3ff7e0cade0919a0cf7c544', '三鲜丸子', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:27', '2019-10-07 17:10:27', 0, 0);
INSERT INTO `goods` VALUES ('bee2b3fec6a7a54170f5c6364446a676', '可乐鸡翅', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:09', '2019-10-07 16:59:09', 0, 0);
INSERT INTO `goods` VALUES ('beeabd2a0a1a967266a428e382709e4f', '油焖茄子', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:32:30', '2019-10-07 16:32:30', 0, 0);
INSERT INTO `goods` VALUES ('bef58e4b36ed0f79837696659ca7a1a6', '木须肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:34', '2019-10-07 16:49:34', 0, 0);
INSERT INTO `goods` VALUES ('c3ca238fa4cd5eb8b742ef2823e72d00', '豆皮烧牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:42', '2019-10-07 17:02:42', 0, 0);
INSERT INTO `goods` VALUES ('c3f795510d0bdcd1d7cfd315878ac412', '外婆牛肉丁', '10000', NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:04:52', '2019-10-07 17:04:52', 0, 0);
INSERT INTO `goods` VALUES ('c43e80c97efc78363090815ac501d7b1', '回锅牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:56:35', '2019-10-07 16:56:35', 0, 0);
INSERT INTO `goods` VALUES ('c506378d3a7faafd6d719d666f409111', '辣炒鸡胗', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:47', '2019-10-07 16:44:47', 0, 0);
INSERT INTO `goods` VALUES ('c54e7030f3c2f78ed10ed989ff4c2829', '蒜蓉青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:39:32', '2019-10-07 16:39:32', 0, 0);
INSERT INTO `goods` VALUES ('c5db94a3b4b96c10471ed5cb9939668b', '沸腾牛肚', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:54', '2019-10-07 17:16:54', 0, 0);
INSERT INTO `goods` VALUES ('c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:00', '2019-10-07 16:42:00', 0, 0);
INSERT INTO `goods` VALUES ('c88499d0ad3d4f3d5a214f87617b582f', '水煮肉片', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:14:12', '2019-10-07 17:14:12', 0, 0);
INSERT INTO `goods` VALUES ('ca369ca09e5d4bb8f489aff2e73fdf6c', '芹菜炒肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:56', '2019-10-07 16:42:56', 0, 0);
INSERT INTO `goods` VALUES ('cd69a09bd278dc062392d62407c525b0', '肉末茄子', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:32:08', '2019-10-07 16:32:08', 0, 0);
INSERT INTO `goods` VALUES ('ce08cb7a2bb750b17ae398a8798b2b76', '油炸小花卷', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 3000, 0, 10, 1, '2019-10-07 17:19:08', '2019-10-07 17:19:08', 0, 0);
INSERT INTO `goods` VALUES ('cea32d01e13c056a448f4b2d8bcd12b9', '酸汤肥牛', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:15:19', '2019-10-07 17:15:19', 0, 0);
INSERT INTO `goods` VALUES ('cea6ec8aad7077e1bcd23ba1abe0f1f8', '白菜豆腐汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:30:35', '2019-10-07 17:30:35', 0, 0);
INSERT INTO `goods` VALUES ('cf8fd3a6e3ea714f998a7423f00bdd18', '香辣肥肠', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:09:04', '2019-10-07 17:09:04', 0, 0);
INSERT INTO `goods` VALUES ('d2991ea4c0dc65764d934188f1c441c5', '辣炒羊肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:30', '2019-10-07 17:01:30', 0, 0);
INSERT INTO `goods` VALUES ('d52a110f824dcb18cb3b420b6039e9a0', '土豆炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:13:27', '2019-10-07 17:13:27', 0, 0);
INSERT INTO `goods` VALUES ('d7afc24ccd36da2139000aab9c78a0ae', '千叶红烧肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:44', '2019-10-07 17:03:44', 0, 0);
INSERT INTO `goods` VALUES ('d88891e17c565d017df0415bbce0b9dc', '蒜蓉西蓝花', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:36:18', '2019-10-07 16:36:18', 0, 0);
INSERT INTO `goods` VALUES ('da70b2f737737e8de82a8a5c108f6e55', '糖拌土豆丝', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 6000, 0, 10, 1, '2019-10-07 16:24:38', '2019-10-07 16:24:38', 0, 0);
INSERT INTO `goods` VALUES ('db90ea94e33bc790bc682d320c808d21', '玉米蛋花汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:31:02', '2019-10-07 17:31:02', 0, 0);
INSERT INTO `goods` VALUES ('dcbc5470125f3ead0db55eb26a61376e', '金针菇粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:16', '2019-10-07 16:33:16', 0, 0);
INSERT INTO `goods` VALUES ('e10081ad2fe80dd437c67e6ece595b14', '雪碧', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2000, 0, 10, 1, '2019-10-07 17:34:22', '2019-10-07 17:34:22', 0, 0);
INSERT INTO `goods` VALUES ('e35b2b982530f091016cb1a57f4c2adb', '尖椒肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:11', '2019-10-07 16:51:11', 0, 0);
INSERT INTO `goods` VALUES ('e5a7e0a001111392d98d25b5e8fa030f', '红烧鸡翅', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:58:51', '2019-10-07 16:58:51', 0, 0);
INSERT INTO `goods` VALUES ('e8b9e920786822151845d68655dbe130', '肉末蒸蛋', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:29', '2019-10-07 16:42:29', 0, 0);
INSERT INTO `goods` VALUES ('ebff38868d14209e9c3ecfb13120566d', '干煸鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:49', '2019-10-07 17:01:49', 0, 0);
INSERT INTO `goods` VALUES ('ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', NULL, NULL, '8410fe3eac3dd72c7b0aeb4f24cc05a8', NULL, 7000, 0, 10, 1, '2019-10-07 16:25:14', '2019-10-07 16:25:14', 0, 0);
INSERT INTO `goods` VALUES ('f8a262b82cf30521999f75053ef5dc24', '干煸土豆片', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:35:03', '2019-10-07 16:35:03', 0, 0);
INSERT INTO `goods` VALUES ('f8cf2f6fb3b6af7f9b9417fe8b1f33f2', '素三鲜', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 9000, 0, 10, 1, '2019-10-07 17:09:28', '2019-10-07 17:09:28', 0, 0);
INSERT INTO `goods` VALUES ('f9896e09387a6b91abca11146fe655a6', '沸腾牛五花', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:17:17', '2019-10-07 17:17:17', 0, 0);
INSERT INTO `goods` VALUES ('facffddf7c0cf642c8498f139b7f3315', '蒜薹炒肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:46:32', '2019-10-07 16:46:32', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` varchar(32) NOT NULL,
  `number` int(11) unsigned NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `address_id` varchar(32) NOT NULL,
  `goods_count` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT NULL,
  `discounted_prices` int(11) unsigned NOT NULL DEFAULT '0',
  `all_price` int(11) unsigned NOT NULL,
  `real_price` int(11) unsigned NOT NULL,
  `payment_way` int(4) unsigned NOT NULL,
  `ps` varchar(254) DEFAULT NULL,
  `pay_state` int(2) unsigned NOT NULL DEFAULT '0',
  `order_state` int(2) unsigned DEFAULT '0',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) unsigned DEFAULT '0',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES ('030139ac7dd581c329c8578d9060a43e', 1, '49268c005a631e2d77b7b90a206fe0eb', '49268c005a631e2d77b7b90a206fe0ec', 2, NULL, 0, 1000, 1000, 2, NULL, 0, 0, '2019-10-07 01:07:54', '2019-10-09 03:29:38', 1, 0);
INSERT INTO `order` VALUES ('f4b4e5d5dbc88094db45ca98f3e81eec', 2, '49268c005a631e2d77b7b90a206fe0eb', '49268c005a631e2d77b7b90a206fe0ec', 1, NULL, 0, 1000, 1000, 0, NULL, 0, 0, '2019-10-07 01:14:07', '2019-10-09 03:29:40', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `goods_id` varchar(32) NOT NULL,
  `goods_name` varchar(32) DEFAULT NULL,
  `goods_thumb` varchar(255) DEFAULT NULL,
  `goods_price` int(11) DEFAULT NULL,
  `goods_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `key` varchar(255) NOT NULL,
  `value` text,
  PRIMARY KEY (`key`) USING BTREE,
  KEY `key` (`key`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `money` int(11) unsigned DEFAULT '0',
  `open_id` varchar(128) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `gender` tinyint(1) unsigned DEFAULT '0',
  `level` tinyint(2) unsigned DEFAULT '1',
  `last_login_date` timestamp NULL DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` int(11) unsigned DEFAULT '0',
  `deleted` tinyint(1) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('49268c005a631e2d77b7b90a206fe0eb', 'test', NULL, 0, NULL, 'http://www.baidu.com', NULL, 0, 1, NULL, '2019-10-06 03:34:32', '2019-10-08 01:23:20', 0, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
