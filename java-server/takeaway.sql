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

 Date: 17/11/2019 02:32:53
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
  `x` double NULL DEFAULT NULL,
  `y` double NULL DEFAULT NULL,
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(1) UNSIGNED NULL DEFAULT 0,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('92ce8deba7e238df99b2526703b6f966', '4a6bb5a539ec498e2bf5117910af08e4', 'dsfaaaaaa', 'sdfsdf111', NULL, NULL, '1056511776', 0, '2019-10-18 18:53:56', 1);
INSERT INTO `address` VALUES ('a40134ee6354768e77a8d2d4d4787fa6', '4a6bb5a539ec498e2bf5117910af08e4', 'asd', 'asdas', NULL, NULL, 'asdas', 0, '2019-10-18 18:53:26', 1);
INSERT INTO `address` VALUES ('de40918ca9196fe12c34fd9aa8815ebe', '6157721ea097834ca8502a707e518dcc', '신촌로 150 포스빌 701', '放门口就行', NULL, NULL, '01056511996', 1, '2019-10-20 01:34:10', 0);
INSERT INTO `address` VALUES ('ef80e4fae51464af1b997fb449864d8f', '4a6bb5a539ec498e2bf5117910af08e4', '신촌로 150', '707호 楼下密码🗝1234#', 126.9424896, 37.5569659, '01056511996', 1, '2019-10-18 19:52:48', 0);

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('029d1b56aeb1aaad55440e4b1ac21498', '干煸肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:41', '2019-10-07 16:52:41', 0, 0);
INSERT INTO `goods` VALUES ('05444437734b79d597b8fff2c8fecd21', '麻辣烫（麻辣香锅）加菜+牛肉卷', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 3000, 0, 10, 1, '2019-10-07 17:27:46', '2019-10-07 17:27:46', 0, 0);
INSERT INTO `goods` VALUES ('05c71e0ca83929eb3577ccc60c55cc33', '素三丝', '人气凉菜，好看又好吃～', '/upload/images/20e8d90654234eaaadcf83afa6aa9963.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 7000, 0, 10, 1, '2019-10-07 16:25:39', '2019-10-18 15:17:58', 2, 0);
INSERT INTO `goods` VALUES ('063919d67bae60f500d48e18c30a422c', '酸菜炒粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:37:25', '2019-10-07 16:37:25', 0, 0);
INSERT INTO `goods` VALUES ('09ca2fca09f81554ef2947f3775dce3f', '尖椒鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:15', '2019-10-07 17:02:15', 0, 0);
INSERT INTO `goods` VALUES ('0a9eca37dd94af1a19c8f2eae7549817', '酸辣汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:29:48', '2019-10-07 17:29:48', 0, 0);
INSERT INTO `goods` VALUES ('0c92136340cb0e43237b09d00762225f', '牛肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:48', '2019-10-07 17:24:48', 0, 0);
INSERT INTO `goods` VALUES ('0cc106a304191a1dc1e27523b93b92e9', '番茄蛋汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:30:15', '2019-10-07 17:30:15', 0, 0);
INSERT INTO `goods` VALUES ('11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', '麻辣劲道的凉拌菜', '/upload/images/cb4c0cc249d943399435acad9e06c91e.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 12000, 0, 10, 1, '2019-10-07 16:26:32', '2019-10-18 15:27:59', 2, 0);
INSERT INTO `goods` VALUES ('13daa7235fc8c0ff68eb503223fe743f', '冰红茶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:34:43', '2019-10-07 17:34:43', 0, 0);
INSERT INTO `goods` VALUES ('152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 1000, 0, 10, 1, '2019-10-07 17:18:44', '2019-10-07 17:18:44', 0, 0);
INSERT INTO `goods` VALUES ('1616e0e42ceb30d70ae916909cfe365e', '麻辣烫（麻辣香锅）加菜+虾仁 ', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 2000, 0, 10, 1, '2019-10-07 17:28:53', '2019-10-07 17:28:53', 0, 0);
INSERT INTO `goods` VALUES ('170d701974dafba665b703d25c9958a2', '扬州炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:07', '2019-10-07 17:20:07', 0, 0);
INSERT INTO `goods` VALUES ('172794967e828961791eebf401ff03f8', '干煸大虾', NULL, '/upload/images/28614058d7eb4beba3a166f39a6ea8dc.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:08:09', '2019-10-18 02:50:08', 1, 0);
INSERT INTO `goods` VALUES ('18fbd399b81301c6128bd92548d7a349', '蚂蚁上树', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 7000, 0, 10, 1, '2019-10-07 16:41:25', '2019-10-07 16:41:25', 0, 0);
INSERT INTO `goods` VALUES ('1aa39f6d2967b8f3eb1d6820cbc7e2b8', '老干妈炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:28', '2019-10-07 17:20:28', 0, 0);
INSERT INTO `goods` VALUES ('1ad51cb674616fad33ee5ba37415304e', '香辣小排', NULL, '/upload/images/c9ca631c4fb54633a1af1512107c9a74.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:14', '2019-10-18 02:49:42', 1, 0);
INSERT INTO `goods` VALUES ('1b81de0fcc1c717da3495d9f00566293', '馄饨', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:45', '2019-10-07 17:19:45', 0, 0);
INSERT INTO `goods` VALUES ('1c2ff79b530aab6acad1148a2f27779b', '香辣鸡翅', NULL, '/upload/images/4909612e5c7346788bbe0bb854c77fde.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:57:46', '2019-10-18 02:42:32', 1, 0);
INSERT INTO `goods` VALUES ('1e8651ab5598730cd0275d7fca986e26', '辣子鸡', NULL, '/upload/images/ef1efabba84e4f67ae40b278cf27b8e5.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:58', '2019-10-18 02:38:54', 1, 0);
INSERT INTO `goods` VALUES ('1fa5e874f2b4562e9b3c03235c3c13b4', '虾仁青菜', '这是一道人气菜～', '/upload/images/9e16b831c67e4bba9f06402fce8d8201.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:39:02', '2019-10-18 15:46:11', 2, 0);
INSERT INTO `goods` VALUES ('212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', NULL, '/upload/images/4d2edcffc1bb4d8981e613318474894f.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 12000, 0, 10, 1, '2019-10-07 17:26:13', '2019-10-18 03:01:29', 1, 0);
INSERT INTO `goods` VALUES ('22da4b82121d50f90ddaff584859b969', '老干妈鸡丁', NULL, '/upload/images/0bd26d283b9c4be3a937a24070e9ce76.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:43:41', '2019-10-18 02:31:43', 1, 0);
INSERT INTO `goods` VALUES ('28d2ba9f82f275e5121820008c2e8a24', '土豆肉丝', '土豆搭配肉丝，有荤有素～', '', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 8000, 0, 10, 1, '2019-10-07 16:41:44', '2019-10-18 15:48:43', 1, 0);
INSERT INTO `goods` VALUES ('2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '爱吃皮蛋和日本豆腐的不容错过～', '/upload/images/bc3633b805314596bd0545637be3474b.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:22:52', '2019-10-18 15:15:51', 5, 0);
INSERT INTO `goods` VALUES ('2e2367630e6fbd6adffcc51d4b7a3806', '爆炒肝尖', NULL, '/upload/images/94da466287d64792b76bace4beea5838.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:48:07', '2019-10-18 02:35:24', 1, 0);
INSERT INTO `goods` VALUES ('309fbad34a43306e3c45e8cfd7d005b0', '羊肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:31', '2019-10-07 17:24:31', 0, 0);
INSERT INTO `goods` VALUES ('30b7ed3ce157e38e67a1ebd3bcff6c45', '三鲜汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 10000, 0, 10, 1, '2019-10-07 17:33:26', '2019-10-07 17:33:26', 0, 0);
INSERT INTO `goods` VALUES ('315ad4ec34ca27f6f5339ee5c193d8b4', '黄瓜拌牛肉', '脆脆的黄瓜配上劲道的牛腩肉，绝配哦', '', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 12000, 0, 10, 1, '2019-10-07 16:27:17', '2019-10-18 15:28:46', 1, 0);
INSERT INTO `goods` VALUES ('31a06a0038b97c891abe609b29c0eee8', '锅包肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:34', '2019-10-07 17:07:34', 0, 0);
INSERT INTO `goods` VALUES ('31e077c41e3d92cb099dd4cebb4fb11b', '湖南小炒肉', NULL, '/upload/images/87f2c6586e8840a2a0669c31c4a9ce4e.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:34', '2019-10-18 02:37:23', 1, 0);
INSERT INTO `goods` VALUES ('3291e2ad095c3354b82e33e9dbb4b7ad', '烧溜豆腐', '营养丰富的家常菜～', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 6000, 0, 10, 1, '2019-10-07 16:29:16', '2019-10-18 15:29:41', 1, 0);
INSERT INTO `goods` VALUES ('333a1a2f0f85b45aae879f31f2f975ce', '孜然里脊', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:21', '2019-10-07 16:53:21', 0, 0);
INSERT INTO `goods` VALUES ('3489f48cb26c95750813b55f0f54d0a8', '小鸡炖蘑菇', NULL, '/upload/images/f165f7fc49354901a05f2565d00a9097.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:13:50', '2019-10-18 02:53:20', 1, 0);
INSERT INTO `goods` VALUES ('34d64d55f6ddc14f8cda410d527691cc', '香辣肉丝', NULL, '/upload/images/a78d95aebde241d19741682cf3a0ed45.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:21', '2019-10-18 02:38:15', 1, 0);
INSERT INTO `goods` VALUES ('34e4acd099e554d58eb9e5c1935326c9', '冰糖雪梨', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:35:00', '2019-10-07 17:35:00', 0, 0);
INSERT INTO `goods` VALUES ('372f4db201871f20e81fa214dca621c5', '粉条炖牛腩', NULL, '/upload/images/242346497d1f4448beade1bff909dc3c.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:03', '2019-10-18 02:55:26', 1, 0);
INSERT INTO `goods` VALUES ('388d8774dc686d87f6656b9104c4c999', '清炒腐竹', '腐竹清炒，别具风味～', '/upload/images/4d58ee114d6648b8ad3a1aef9526546b.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:56', '2019-10-18 15:45:24', 2, 0);
INSERT INTO `goods` VALUES ('3be786d311bea4d1b6be7a1fc8fd498a', '水饺', NULL, '/upload/images/e3a6a8ac9edc4f6abf8872c8b37f3b1f.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:25', '2019-10-18 02:59:18', 1, 0);
INSERT INTO `goods` VALUES ('3cc50f52f9c51061b2f69d7535ee1a2a', '地三鲜', NULL, '/upload/images/ff6b4e9f09554f93860b67985308c109.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:40', '2019-10-18 02:26:28', 1, 0);
INSERT INTO `goods` VALUES ('3db0f8f9ce492d7149744ce7a2a3fa19', '酱油炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:48', '2019-10-07 17:20:48', 0, 0);
INSERT INTO `goods` VALUES ('3e416cfb3cdff1547842d4981afe818d', '鱼香茄子', '茄子的人气，不用我多说哦哈哈', '/upload/images/551d3c67b47c4b0ea3f94f31bfd53693.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:52', '2019-10-18 15:33:25', 2, 0);
INSERT INTO `goods` VALUES ('3e9ad83124e30144d65f318d2109732c', '肉圆粉丝汤', '', '/upload/images/cc6e8883cd11450ab74f5830b5c7be28.jpeg', '0c309767046ac9952bda4ea5dee37770', '', 9000, 0, 10, 1, '2019-10-07 17:33:06', '2019-10-18 03:03:55', 2, 0);
INSERT INTO `goods` VALUES ('434db0970f06d995764612c4a85c72b2', '爆炒腰花', NULL, '/upload/images/aa3d2988510548d89391229a0dcee24a.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:50:22', '2019-10-18 02:35:57', 1, 0);
INSERT INTO `goods` VALUES ('43eadde1ed3ea445a950676353ef7853', '红烧丸子', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:57:00', '2019-10-07 16:57:00', 0, 0);
INSERT INTO `goods` VALUES ('470a11753727382cca018843c7a5e49c', '凉拌腐竹', '清爽的腐竹凉拌菜', '/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:23:32', '2019-10-18 15:16:14', 2, 0);
INSERT INTO `goods` VALUES ('49b46e756cfb5a576b853f8ee871f607', '虾仁玉米', '人气美食，颜值第一哈哈', '/upload/images/3acbcdb2131e4e59b672b761fa529439.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:40:58', '2019-10-18 15:48:19', 2, 0);
INSERT INTO `goods` VALUES ('5384a3538ae8fd34593be6209f0380b5', '尖椒炒鸡蛋', '这个是真的下饭神器', '/upload/images/74d0f41bc9884ce49d8410865993bb60.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:39:53', '2019-10-18 15:46:34', 2, 0);
INSERT INTO `goods` VALUES ('544d84af8501517caaa24eeb1593eced', '孜然羊肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:57', '2019-10-07 16:59:57', 0, 0);
INSERT INTO `goods` VALUES ('54a33ae4ef409d42dd196a26f30d2c0f', '可乐', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2000, 0, 10, 1, '2019-10-07 17:34:04', '2019-10-07 17:34:04', 0, 0);
INSERT INTO `goods` VALUES ('551bce6cad97153d9220cfdcaba51325', '口水鸡', '口水鸡，名气远扬～', '/upload/images/15c8d3ec42624838b900499e85dec763.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 9000, 0, 10, 1, '2019-10-07 16:26:04', '2019-10-18 15:18:36', 2, 0);
INSERT INTO `goods` VALUES ('55485bf569e0aac16b0770d2a6f9b327', '肉丝炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:01', '2019-10-07 17:23:01', 0, 0);
INSERT INTO `goods` VALUES ('5c09ec5d8d6c38ea729908e38fd5f37b', '尖椒火腿', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:47:10', '2019-10-07 16:47:10', 0, 0);
INSERT INTO `goods` VALUES ('5d4a3d59fbc7167e648f57c95c37e01e', '土豆红烧肉', NULL, '/upload/images/9b16243d82cc4b0598d46ede90e5b1ed.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:01', '2019-10-18 02:46:00', 1, 0);
INSERT INTO `goods` VALUES ('5f361deb700360d98452d5b9632a40ab', '番茄炖牛腩', NULL, '/upload/images/2d5ec50bfb0e45a9bac2efad14e51d9e.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:32', '2019-10-18 02:55:41', 1, 0);
INSERT INTO `goods` VALUES ('62eaf34022a211ab237d4f45d12bc175', '腐竹红烧肉', NULL, '/upload/images/668f224150f544a58ba4a6958fddf679.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:24', '2019-10-18 02:46:50', 1, 0);
INSERT INTO `goods` VALUES ('650632fb49a091669f41fbfaaf2d53fb', '糖醋里脊', NULL, '/upload/images/50fb7cf2ddf44fdd83b1529eee84d86d.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:39', '2019-10-18 02:40:11', 1, 0);
INSERT INTO `goods` VALUES ('67a7987dcbafd64a946e9ed70903bba7', '萝卜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:55', '2019-10-07 17:12:55', 0, 0);
INSERT INTO `goods` VALUES ('6e36a9126991fefc401d3799a923ca29', '酸辣土豆丝', '酸辣口感，停不下来～', '/upload/images/444c9f06ad40488db1bbf35ca73e4c1f.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:29:46', '2019-10-18 15:29:59', 2, 0);
INSERT INTO `goods` VALUES ('6fb3bb8dd013108c39463f4924027bcb', '青椒炒鸡蛋', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:40:16', '2019-10-07 16:40:16', 0, 0);
INSERT INTO `goods` VALUES ('71928e9de8afc4b91b3dc1c76cba80fb', '椰汁', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:48', '2019-10-07 17:35:48', 0, 0);
INSERT INTO `goods` VALUES ('72e0fc62ce10c04e4c01afae5f94a217', '番茄鸡蛋', NULL, '/upload/images/1b496739e8634f8a961a0c3adbec6350.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:15', '2019-10-18 02:25:39', 1, 0);
INSERT INTO `goods` VALUES ('761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '美味好吃的蒜蓉黄瓜!', '/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', ',新品,热卖', 6000, 0, 10, 1, '2019-09-22 04:06:33', '2019-10-18 02:24:02', 14, 0);
INSERT INTO `goods` VALUES ('77769b218df210092e5467134b774646', '猪肉炖粉条', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:03', '2019-10-07 17:12:03', 0, 0);
INSERT INTO `goods` VALUES ('7a85eff463de0c1eeb836cccefb76a6b', '黄瓜炒鸡蛋', '黄瓜炒蛋，简单美味～', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:31:33', '2019-10-18 15:32:16', 1, 0);
INSERT INTO `goods` VALUES ('7c066108cc4c0877750d163c32b3ef22', '青椒土豆丝', '清香口感～', '/upload/images/178659cd1eb14af287acb04741b62fa2.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:30:14', '2019-10-18 15:30:25', 2, 0);
INSERT INTO `goods` VALUES ('7c6d50fb3c0b6dbf8cd52b607a43d958', '茶派', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:36:06', '2019-10-07 17:36:06', 0, 0);
INSERT INTO `goods` VALUES ('7f1eeea4a086244907da5e8cfb303cb8', '皮蛋瘦肉粥', NULL, '/upload/images/677c8564aa0a47ebaf8592583f5e2842.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:38', '2019-10-18 03:00:02', 1, 0);
INSERT INTO `goods` VALUES ('7f460dc38492286587549276aaa6e552', '花甲粉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:25:15', '2019-10-07 17:25:15', 0, 0);
INSERT INTO `goods` VALUES ('8028633e923bba71a540559b404e960f', '香菇肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:54', '2019-10-07 16:49:54', 0, 0);
INSERT INTO `goods` VALUES ('80d1d16712ee9988341ccc5602f10292', '酸菜白肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:56', '2019-10-07 17:10:56', 0, 0);
INSERT INTO `goods` VALUES ('814865ead95fc474425b65a56940f3c3', '尖椒干豆腐', '这个口感真的刺激，贼下饭', '/upload/images/67e2df2a005042649e0dfa34d54d1fc9.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:31:07', '2019-10-18 15:31:35', 2, 0);
INSERT INTO `goods` VALUES ('814976d852be95b06660e78f107bf031', '旺仔牛奶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:29', '2019-10-07 17:35:29', 0, 0);
INSERT INTO `goods` VALUES ('835a89bc3f317a32a6b92e7397b9da33', '茄条肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:16', '2019-10-07 16:45:16', 0, 0);
INSERT INTO `goods` VALUES ('85b4cbb5f09b087c27a06e5527ebe039', '青椒肉丝', NULL, '/upload/images/1de7690c8dc345ba9c9fcf5fd976741b.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:50:46', '2019-10-18 02:39:15', 1, 0);
INSERT INTO `goods` VALUES ('861baf559ae479a7faadf125d2842fc2', '毛血旺', NULL, '/upload/images/e4b7f32c03584691b7e024b9243108ad.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:18:21', '2019-10-18 02:58:46', 1, 0);
INSERT INTO `goods` VALUES ('885e94d1daf481e72d633dfc7ec5462b', '辣炒鸡心', NULL, '/upload/images/d599b5f1441949d49f9c6a0ab39392ed.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:29', '2019-10-18 02:32:54', 1, 0);
INSERT INTO `goods` VALUES ('8c106901edec65a169aa7b84aa307ed6', '干煸牛腿肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:57:23', '2019-10-07 16:57:23', 0, 0);
INSERT INTO `goods` VALUES ('8ef386c437d239e990177089afda080c', '香辣猪蹄', NULL, '/upload/images/e369b59ea3e34f21aa1e7995ed725388.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:50', '2019-10-18 02:49:14', 1, 0);
INSERT INTO `goods` VALUES ('8f3883855e6321762c324a8825312c59', '牛肉蛋炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:39', '2019-10-07 17:23:39', 0, 0);
INSERT INTO `goods` VALUES ('956176f39dfa2222d6a84aedc46eefd1', '三鲜疙瘩汤', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:01', '2019-10-07 17:22:01', 0, 0);
INSERT INTO `goods` VALUES ('959be79aec14a643c6bab1d08601866e', '四川回锅肉', NULL, '/upload/images/277c0ccc69a64748aac0615241ab5744.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:00', '2019-10-18 02:37:43', 1, 0);
INSERT INTO `goods` VALUES ('96d06c954a6f4b40cf8c995a0cbfb0de', '麻辣烫（麻辣香锅）加菜+西蓝花', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 2000, 0, 10, 1, '2019-10-07 17:29:24', '2019-10-07 17:29:24', 0, 0);
INSERT INTO `goods` VALUES ('9ac7b907fe66e1879ab719d4ddcadf19', '油焖大虾', NULL, '/upload/images/bb2920ed3cfa45058351c71b535fd239.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:08:33', '2019-10-18 02:50:23', 1, 0);
INSERT INTO `goods` VALUES ('9cba928b7b27de0456a74e966796e205', '黄菜烧牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:11:36', '2019-10-07 17:11:36', 0, 0);
INSERT INTO `goods` VALUES ('9cd096d249efc6cfc37f4522a2a072c0', '辣炒花甲', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:28', '2019-10-07 17:06:28', 0, 0);
INSERT INTO `goods` VALUES ('9f0bb6f711f618624a94a3a43909e7bf', '香菇青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:38:31', '2019-10-07 16:38:31', 0, 0);
INSERT INTO `goods` VALUES ('9fd1e6b7a4cb343a1826ef4f947f7907', '酸辣粉', NULL, '/upload/images/f149417c1ddd4776b9b444f5f647b093.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:21:04', '2019-10-18 02:59:41', 1, 0);
INSERT INTO `goods` VALUES ('a56e4dda92915cd75be0a32fdc4be10a', '孜然肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:46', '2019-10-07 16:45:46', 0, 0);
INSERT INTO `goods` VALUES ('a5aab1591e53f67d13cf9a683114842a', '豆芽粉丝', '豆芽粉丝拌一拌，也是简单的美味～', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 6000, 0, 10, 1, '2019-10-07 16:27:47', '2019-10-18 15:29:17', 1, 0);
INSERT INTO `goods` VALUES ('a6d83178e10446a2f29a5c5c304462e8', '麻辣烫', NULL, '/upload/images/e109e206e17349d7a86889e43cfdcd12.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:25:54', '2019-10-18 03:01:10', 1, 0);
INSERT INTO `goods` VALUES ('a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', NULL, '/upload/images/42f1016517654f8daf1a327742000f94.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:00', '2019-10-18 03:00:43', 1, 0);
INSERT INTO `goods` VALUES ('a9845eb443b77479459a765f3038ebfc', '水煮牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:14:53', '2019-10-07 17:14:53', 0, 0);
INSERT INTO `goods` VALUES ('ab4b0c39cc46b53deb0498dd4e6599d2', '皮蛋豆腐羹', NULL, '/upload/images/e02b06257a9f48c2ae468c753efea716.jpeg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:32:41', '2019-10-18 03:03:05', 1, 0);
INSERT INTO `goods` VALUES ('abe3a6acc45ecc03f3c8cafd74251f9f', '麻辣牛肚 ', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:05:38', '2019-10-07 17:05:38', 0, 0);
INSERT INTO `goods` VALUES ('ac830dbc81a0a0e78a1b77e0d6d64921', '手撕包菜', '名气远扬的干锅菜～', '/upload/images/35aab4be651a451fba484bd71ec1b1cb.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:30:41', '2019-10-18 15:30:47', 2, 0);
INSERT INTO `goods` VALUES ('aced92326fe9e237c264ad821888107e', '尖椒鸡', NULL, '/upload/images/943c9b2b059d4aabaad66d6725974ddb.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:04:04', '2019-10-18 02:48:00', 1, 0);
INSERT INTO `goods` VALUES ('ad36856f9242f438d0fabaf24e479c2d', '银牙肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:43:15', '2019-10-07 16:43:15', 0, 0);
INSERT INTO `goods` VALUES ('ade51e3b9e880abc00cbcc369890322d', '孜然心管', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:08', '2019-10-07 17:06:08', 0, 0);
INSERT INTO `goods` VALUES ('b0cbede48db1b84041ea6ab32f6a51ec', '麻婆豆腐', '麻辣鲜香～', '/upload/images/1803476ae4864da397d446967ec48464.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:26', '2019-10-18 15:44:44', 2, 0);
INSERT INTO `goods` VALUES ('b132f10d83515369a9e753fde3d39257', '宫保鸡丁', NULL, '/upload/images/7abb813ab9eb4c01a5a5693fab104efb.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:05', '2019-10-18 02:34:21', 1, 0);
INSERT INTO `goods` VALUES ('b1427e2373345a6c954d3a8c3de152d3', '酸菜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:09:59', '2019-10-07 17:09:59', 0, 0);
INSERT INTO `goods` VALUES ('b2776fe0ef5d43dfa4228dd51ceef530', '肥肠豆腐煲', NULL, '/upload/images/44763682917a42ff91b488aa69154bcc.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:17:56', '2019-10-18 02:58:21', 1, 0);
INSERT INTO `goods` VALUES ('b27935ecc22c836f6f390883b47671b5', '韭菜鸡蛋', NULL, '/upload/images/5e2ea2c1558748e08ada2d1b9c6ae62f.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:47', '2019-10-18 02:25:16', 1, 0);
INSERT INTO `goods` VALUES ('b6bdb4d57bd008b200c713b361a7ef75', '孜然牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:39', '2019-10-07 16:59:39', 0, 0);
INSERT INTO `goods` VALUES ('b7d74a6ce8ca9b7a4b02cd619f6ad98a', '辣炒牛肉', NULL, '/upload/images/c67c4ece14d642ffbffcf80b67e0bae2.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:00:23', '2019-10-18 02:44:01', 1, 0);
INSERT INTO `goods` VALUES ('bb08daaca3ff7e0cade0919a0cf7c544', '三鲜丸子', NULL, '/upload/images/4bc6aef5ade14b27a85528dc1513699d.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:27', '2019-10-18 02:51:35', 1, 0);
INSERT INTO `goods` VALUES ('bee2b3fec6a7a54170f5c6364446a676', '可乐鸡翅', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:09', '2019-10-07 16:59:09', 0, 0);
INSERT INTO `goods` VALUES ('beeabd2a0a1a967266a428e382709e4f', '油焖茄子', '茄子的人气，不用我多说哦哈哈', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:30', '2019-10-18 15:33:15', 1, 0);
INSERT INTO `goods` VALUES ('bef58e4b36ed0f79837696659ca7a1a6', '木须肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:34', '2019-10-07 16:49:34', 0, 0);
INSERT INTO `goods` VALUES ('c3ca238fa4cd5eb8b742ef2823e72d00', '豆皮烧牛肉', NULL, '/upload/images/00d4273478774556856255f63dc79200.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:42', '2019-10-18 02:45:46', 1, 0);
INSERT INTO `goods` VALUES ('c3f795510d0bdcd1d7cfd315878ac412', '外婆牛肉丁', '10000', '/upload/images/d13ee534f82d462c8dcfb30eef60118d.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:04:52', '2019-10-18 02:48:37', 1, 0);
INSERT INTO `goods` VALUES ('c43e80c97efc78363090815ac501d7b1', '回锅牛肉', NULL, '/upload/images/d48032685fb24115b95271d7221f1f4a.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:56:35', '2019-10-18 02:41:40', 1, 0);
INSERT INTO `goods` VALUES ('c506378d3a7faafd6d719d666f409111', '辣炒鸡胗', NULL, '/upload/images/5aade156c230420492fb44611dfff240.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:47', '2019-10-18 02:33:39', 1, 0);
INSERT INTO `goods` VALUES ('c54e7030f3c2f78ed10ed989ff4c2829', '蒜蓉青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:39:32', '2019-10-07 16:39:32', 0, 0);
INSERT INTO `goods` VALUES ('c5db94a3b4b96c10471ed5cb9939668b', '沸腾牛肚', NULL, '/upload/images/2d75fea8751641eeb379a1152addc6b2.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:54', '2019-10-18 02:56:11', 1, 0);
INSERT INTO `goods` VALUES ('c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', '经典家常菜～', '/upload/images/0594a52ab60f4ee0902f9a4748fd1565.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 8000, 0, 10, 1, '2019-10-07 16:42:00', '2019-10-18 15:49:20', 2, 0);
INSERT INTO `goods` VALUES ('c88499d0ad3d4f3d5a214f87617b582f', '水煮肉片', NULL, '/upload/images/19ce9a016b014ef3899ac5c3cef431df.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:14:12', '2019-10-18 02:53:57', 1, 0);
INSERT INTO `goods` VALUES ('ca369ca09e5d4bb8f489aff2e73fdf6c', '芹菜炒肉', NULL, '/upload/images/4b10411d06c241f49bff2a5ddd617d24.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:56', '2019-10-18 02:30:52', 1, 0);
INSERT INTO `goods` VALUES ('cd69a09bd278dc062392d62407c525b0', '肉末茄子', '茄子的人气，不用我多说哦哈哈', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:08', '2019-10-18 15:33:05', 1, 0);
INSERT INTO `goods` VALUES ('ce08cb7a2bb750b17ae398a8798b2b76', '油炸小花卷', NULL, '/upload/images/88d668ff48ad432d9da0e67f1005510c.jpeg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 3000, 0, 10, 1, '2019-10-07 17:19:08', '2019-10-18 02:59:09', 1, 0);
INSERT INTO `goods` VALUES ('cea32d01e13c056a448f4b2d8bcd12b9', '酸汤肥牛', NULL, '/upload/images/6f880dfb08564b81921d3ec83ff43353.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:15:19', '2019-10-18 02:54:18', 1, 0);
INSERT INTO `goods` VALUES ('cea6ec8aad7077e1bcd23ba1abe0f1f8', '白菜豆腐汤', NULL, '/upload/images/5111fec8488a43389197627dbff04c93.jpeg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:30:35', '2019-10-18 03:04:33', 1, 0);
INSERT INTO `goods` VALUES ('cf8fd3a6e3ea714f998a7423f00bdd18', '香辣肥肠', NULL, '/upload/images/e8e78557d70842ad8eb4aeeb01b376fe.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:09:04', '2019-10-18 02:50:39', 1, 0);
INSERT INTO `goods` VALUES ('d2991ea4c0dc65764d934188f1c441c5', '辣炒羊肉', NULL, '/upload/images/5349aa4b3bc649c18c56200dcf151193.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:30', '2019-10-18 02:44:19', 1, 0);
INSERT INTO `goods` VALUES ('d52a110f824dcb18cb3b420b6039e9a0', '土豆炖排骨', NULL, '/upload/images/05e50997de1145138b485443c0209970.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:13:27', '2019-10-18 02:52:18', 1, 0);
INSERT INTO `goods` VALUES ('d7afc24ccd36da2139000aab9c78a0ae', '千叶红烧肉', NULL, '/upload/images/f56a51f1ff39468fb186c6603d316ec8.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:03:44', '2019-10-18 02:47:17', 1, 0);
INSERT INTO `goods` VALUES ('d88891e17c565d017df0415bbce0b9dc', '蒜蓉西蓝花', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:36:18', '2019-10-07 16:36:18', 0, 0);
INSERT INTO `goods` VALUES ('da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', '家常凉拌菜酸甜口味', '', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:24:38', '2019-10-18 15:16:58', 1, 0);
INSERT INTO `goods` VALUES ('db90ea94e33bc790bc682d320c808d21', '玉米蛋花汤', NULL, '/upload/images/c0f9e9a92ae745bb9d5742ecf39d445d.jpeg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:31:02', '2019-10-18 03:02:08', 1, 0);
INSERT INTO `goods` VALUES ('dcbc5470125f3ead0db55eb26a61376e', '金针菇粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:16', '2019-10-07 16:33:16', 0, 0);
INSERT INTO `goods` VALUES ('e10081ad2fe80dd437c67e6ece595b14', '雪碧', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2000, 0, 10, 1, '2019-10-07 17:34:22', '2019-10-07 17:34:22', 0, 0);
INSERT INTO `goods` VALUES ('e35b2b982530f091016cb1a57f4c2adb', '尖椒肉丝', NULL, '/upload/images/c49c014c7f5e4fe39f6e6781ca2b32dc.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:11', '2019-10-18 02:36:59', 1, 0);
INSERT INTO `goods` VALUES ('e5a7e0a001111392d98d25b5e8fa030f', '红烧鸡翅', NULL, '/upload/images/c5d801acc8224166af3a85d8d27f2b14.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:58:51', '2019-10-18 02:42:55', 1, 0);
INSERT INTO `goods` VALUES ('e8b9e920786822151845d68655dbe130', '肉末蒸蛋', NULL, '/upload/images/677bc82ddc204cbc979cf4bc7b90931c.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:29', '2019-10-18 02:30:00', 1, 0);
INSERT INTO `goods` VALUES ('ebff38868d14209e9c3ecfb13120566d', '干煸鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:49', '2019-10-07 17:01:49', 0, 0);
INSERT INTO `goods` VALUES ('ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', '土豆丝的凉拌菜～', '/upload/images/76b7e0c16fd54582a2d8d991ce5e44b5.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 7000, 0, 10, 1, '2019-10-07 16:25:14', '2019-10-18 15:17:28', 3, 0);
INSERT INTO `goods` VALUES ('f8a262b82cf30521999f75053ef5dc24', '干煸土豆片', '光看图片就已经很有食欲啦', '/upload/images/131edd6f7c9d4fb198cc732d624384a9.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:03', '2019-10-18 15:44:27', 2, 0);
INSERT INTO `goods` VALUES ('f8cf2f6fb3b6af7f9b9417fe8b1f33f2', '素三鲜', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 9000, 0, 10, 1, '2019-10-07 17:09:28', '2019-10-07 17:09:28', 0, 0);
INSERT INTO `goods` VALUES ('f9896e09387a6b91abca11146fe655a6', '沸腾牛五花', NULL, '/upload/images/9be712ee95574d78a497c8d0b93adde0.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:17:17', '2019-10-18 02:56:50', 1, 0);
INSERT INTO `goods` VALUES ('facffddf7c0cf642c8498f139b7f3315', '蒜薹炒肉', NULL, '/upload/images/8155a541160046609ef55a3f922f726e.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:46:32', '2019-10-18 02:35:02', 1, 0);

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
  `is_commented` tinyint(1) NOT NULL DEFAULT 0,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `version` int(11) UNSIGNED NULL DEFAULT 0,
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_index1`(`pay_state`, `order_state`, `deleted`) USING BTREE,
  INDEX `order_index2`(`order_state`, `is_commented`, `deleted`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('06cd76f348cf4ab223e53eeb9a008baf', 10, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 33000, 33000, 2, '', 1, 0, 0, '2019-11-02 03:27:13', '2019-11-05 00:51:31', 0, 0);
INSERT INTO `order` VALUES ('0d251be157a86a0c8c25ee6500e98743', 9, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 30000, 30000, 2, '', 1, 0, 0, '2019-11-02 03:27:06', '2019-11-05 00:51:31', 0, 0);
INSERT INTO `order` VALUES ('1732d28aa907147f57a407761825b6a5', 2, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 18000, 18000, 2, '', 1, 0, 0, '2019-11-02 03:25:13', '2019-11-05 00:51:32', 0, 0);
INSERT INTO `order` VALUES ('1a23e173793fcd8d92ebfe1d6961d063', 4, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 1, 0, 0, 6000, 6000, 2, '', 1, 6, 0, '2019-10-20 23:18:21', '2019-11-05 00:51:32', 0, 0);
INSERT INTO `order` VALUES ('2112e5d7dcdd12972709d30d517bf1c3', 8, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 4, 0, 0, 40000, 40000, 2, '', 1, 0, 0, '2019-11-02 03:27:01', '2019-11-05 00:51:33', 0, 0);
INSERT INTO `order` VALUES ('2427ac02fe71f0f6d20d91ef78249190', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 2, '', 0, 0, 0, '2019-11-17 02:28:58', '2019-11-17 02:28:58', 0, 0);
INSERT INTO `order` VALUES ('2a30c242c3a729bea911c3fe65d5f813', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 1, 0, 0, 6000, 6000, 2, '', 1, 0, 0, '2019-11-02 03:19:22', '2019-11-05 00:51:33', 0, 0);
INSERT INTO `order` VALUES ('5c4038872373fc85f7e4fa4375d9b724', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 5, 0, 0, 33000, 33000, 2, '', 1, 0, 0, '2019-10-31 00:25:27', '2019-11-05 00:51:34', 0, 0);
INSERT INTO `order` VALUES ('5f86a8bc34d4febc9199d9d4da55e3da', 12, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 34000, 34000, 1, '', 1, 0, 0, '2019-11-02 03:29:05', '2019-11-05 00:51:34', 0, 0);
INSERT INTO `order` VALUES ('6597ed0bee86a29149a4606240b34c69', 1, '4a6bb5a539ec498e2bf5117910af08e4', '92ce8deba7e238df99b2526703b6f966', 2, 0, 0, 12000, 12000, 2, '', 0, 5, 0, '2019-11-07 21:50:11', '2019-11-07 22:05:11', 1, 0);
INSERT INTO `order` VALUES ('6f64f6fe03081ec26d61d4e6ace16c83', 1, '4a6bb5a539ec498e2bf5117910af08e4', '92ce8deba7e238df99b2526703b6f966', 2, 0, 0, 4000, 4000, 2, '', 0, 5, 0, '2019-11-05 00:53:49', '2019-11-05 00:58:49', 1, 0);
INSERT INTO `order` VALUES ('760409e795ceeb2c4d448f4d2d224ef3', 2, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 8, 0, 0, 59000, 59000, 3, '', 1, 1, 0, '2019-10-20 23:13:10', '2019-10-31 00:24:09', 0, 0);
INSERT INTO `order` VALUES ('7b89e0e17f21a974d4416068748622a1', 11, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 5, 0, 0, 57000, 57000, 3, '', 1, 0, 0, '2019-11-02 03:27:38', '2019-11-05 00:51:36', 0, 0);
INSERT INTO `order` VALUES ('856deaabe59e7556e9be974f2783ffcb', 5, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 23000, 23000, 2, '', 1, 0, 0, '2019-11-02 03:26:37', '2019-11-05 00:51:37', 0, 0);
INSERT INTO `order` VALUES ('87696c5a71687ed7aa75e51655d0fdea', 3, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 4, '', 1, 2, 0, '2019-10-20 23:17:50', '2019-10-31 01:33:47', 0, 0);
INSERT INTO `order` VALUES ('a134a60edfe17b9b10248f897d7f2502', 3, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 4, 0, 0, 26000, 26000, 4, '', 2, 0, 0, '2019-11-02 03:25:33', '2019-11-02 03:25:33', 0, 0);
INSERT INTO `order` VALUES ('a6ae31dd78ff82df686239fe936958cb', 4, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 30000, 30000, 5, '', 2, 0, 0, '2019-11-02 03:26:10', '2019-11-02 03:26:10', 0, 0);
INSERT INTO `order` VALUES ('a7f8401def178977b4126b6a178a91a5', 2, '4a6bb5a539ec498e2bf5117910af08e4', '92ce8deba7e238df99b2526703b6f966', 1, 0, 0, 6000, 6000, 2, '', 0, 5, 0, '2019-11-05 10:49:32', '2019-11-05 10:54:29', 1, 0);
INSERT INTO `order` VALUES ('af207e91935db461abb5a2654efaa288', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 1, 0, 0, 6000, 6000, 2, '', 1, 3, 0, '2019-10-21 20:28:29', '2019-10-31 01:33:48', 0, 0);
INSERT INTO `order` VALUES ('b1bce690cf6114333275367747dd65c6', 7, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 27000, 27000, 2, '', 1, 0, 0, '2019-11-02 03:26:54', '2019-11-05 00:51:39', 0, 0);
INSERT INTO `order` VALUES ('cb5378238fbbfff539d6d8bb0c3c37c2', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 9, 0, 0, 56000, 56000, 2, '', 1, 4, 0, '2019-10-27 20:53:06', '2019-10-31 01:33:49', 0, 0);
INSERT INTO `order` VALUES ('d78dec67ea2c8cba2f45bcb7bfdf91a1', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 8, 0, 0, 59000, 59000, 2, '', 1, 5, 0, '2019-10-20 23:01:39', '2019-10-31 01:33:51', 0, 0);
INSERT INTO `order` VALUES ('f435ddc9ea88e9becfcc7865af272561', 1, '4a6bb5a539ec498e2bf5117910af08e4', '92ce8deba7e238df99b2526703b6f966', 1, 0, 0, 6000, 6000, 2, '', 0, 5, 0, '2019-11-11 00:34:51', '2019-11-11 00:49:43', 1, 0);
INSERT INTO `order` VALUES ('fd226e284f9bdfee6fdb4dc2bb6b938b', 6, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 1, 0, 0, 9000, 9000, 2, '', 1, 0, 0, '2019-11-02 03:26:49', '2019-11-05 00:51:46', 0, 0);

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
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('09445f214f8dc5bc1a418186b4e68fc1', 'b1bce690cf6114333275367747dd65c6', '5c09ec5d8d6c38ea729908e38fd5f37b', '尖椒火腿', 'http://cxy.novaborn.net:8080null', 9000, 1);
INSERT INTO `order_item` VALUES ('0c95b1183c2a61e7b2f5bac3fd92779e', 'cb5378238fbbfff539d6d8bb0c3c37c2', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 4);
INSERT INTO `order_item` VALUES ('14dbf1f051293e415136d088355ec0f1', '2112e5d7dcdd12972709d30d517bf1c3', '959be79aec14a643c6bab1d08601866e', '四川回锅肉', 'http://cxy.novaborn.net:8080/upload/images/277c0ccc69a64748aac0615241ab5744.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('17c20b5b616a359c7aee34e18b5b7c79', '7b89e0e17f21a974d4416068748622a1', 'ade51e3b9e880abc00cbcc369890322d', '孜然心管', 'http://cxy.novaborn.net:8080null', 12000, 1);
INSERT INTO `order_item` VALUES ('1b613fed207dc9d5c7a1b0b7bd177833', '87696c5a71687ed7aa75e51655d0fdea', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('21821a4c1789b5ea53ad7da18337bdcc', '760409e795ceeb2c4d448f4d2d224ef3', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 1);
INSERT INTO `order_item` VALUES ('21d12acdc6f5f99c4d008cff5892f922', '760409e795ceeb2c4d448f4d2d224ef3', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('2245f2b5b3d29fcfab2b0727afaafa42', '2112e5d7dcdd12972709d30d517bf1c3', '34d64d55f6ddc14f8cda410d527691cc', '香辣肉丝', 'http://cxy.novaborn.net:8080/upload/images/a78d95aebde241d19741682cf3a0ed45.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('23d90f6badf159c35ce863f987811bfe', 'f435ddc9ea88e9becfcc7865af272561', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('2525c69489cdb8cd2be8b020e2e9de32', 'a134a60edfe17b9b10248f897d7f2502', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 1);
INSERT INTO `order_item` VALUES ('255b0f3f95be1954eb22b431cb7fec01', '2427ac02fe71f0f6d20d91ef78249190', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('2890dfd445fb701b5492b34e8726b1aa', '5c4038872373fc85f7e4fa4375d9b724', '05444437734b79d597b8fff2c8fecd21', '麻辣烫（麻辣香锅）加菜+牛肉卷', 'http://cxy.novaborn.net:8080null', 3000, 1);
INSERT INTO `order_item` VALUES ('319d313e01dbc6b1e0944dc5fb9607e9', 'fd226e284f9bdfee6fdb4dc2bb6b938b', 'c506378d3a7faafd6d719d666f409111', '辣炒鸡胗', 'http://cxy.novaborn.net:8080/upload/images/5aade156c230420492fb44611dfff240.jpeg', 9000, 1);
INSERT INTO `order_item` VALUES ('333b1e8a7f4cea5467f023311547fba1', 'a7f8401def178977b4126b6a178a91a5', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('347e4e953bb88460be353e02b441db91', '5c4038872373fc85f7e4fa4375d9b724', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('43a3572a8c2d03c9c6760ffee635dfc2', '7b89e0e17f21a974d4416068748622a1', 'abe3a6acc45ecc03f3c8cafd74251f9f', '麻辣牛肚 ', 'http://cxy.novaborn.net:8080null', 11000, 1);
INSERT INTO `order_item` VALUES ('4bd3a952251eb7c70a0c6e2654e70497', '760409e795ceeb2c4d448f4d2d224ef3', '11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', 'http://cxy.novaborn.net:8080/upload/images/cb4c0cc249d943399435acad9e06c91e.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('5362fe64d769b0ee1a715ea9a941ce91', '2427ac02fe71f0f6d20d91ef78249190', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('5397ac4c797633d81036efdce76b8e28', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 1);
INSERT INTO `order_item` VALUES ('54632a94a0184654832d454f6147c60a', '760409e795ceeb2c4d448f4d2d224ef3', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('5b16d0e733648db4898ceb47e5992517', 'a134a60edfe17b9b10248f897d7f2502', '05c71e0ca83929eb3577ccc60c55cc33', '素三丝', 'http://cxy.novaborn.net:8080/upload/images/20e8d90654234eaaadcf83afa6aa9963.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('5d4beed9c3c292dbd6765049aea30593', '1732d28aa907147f57a407761825b6a5', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 1);
INSERT INTO `order_item` VALUES ('5de6e7ddde34adacac0fe19e69cff945', '760409e795ceeb2c4d448f4d2d224ef3', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('6dc1485001d81b3e66f4112eb97fe962', '0d251be157a86a0c8c25ee6500e98743', '1e8651ab5598730cd0275d7fca986e26', '辣子鸡', 'http://cxy.novaborn.net:8080/upload/images/ef1efabba84e4f67ae40b278cf27b8e5.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('6f15169f2b0b6c0cc395a969bc13f5d2', '7b89e0e17f21a974d4416068748622a1', '1ad51cb674616fad33ee5ba37415304e', '香辣小排', 'http://cxy.novaborn.net:8080/upload/images/c9ca631c4fb54633a1af1512107c9a74.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('70a21d26415bbf9b903401d67116b23b', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('7590819e06b419a66285c7d25867e4c3', '06cd76f348cf4ab223e53eeb9a008baf', '1c2ff79b530aab6acad1148a2f27779b', '香辣鸡翅', 'http://cxy.novaborn.net:8080/upload/images/4909612e5c7346788bbe0bb854c77fde.jpeg', 11000, 1);
INSERT INTO `order_item` VALUES ('778af5dad4231a37d72cddaba5b3592c', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', 'http://cxy.novaborn.net:8080/upload/images/cb4c0cc249d943399435acad9e06c91e.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('86599252a3aebb4ef64f4737e4ae3ba7', 'af207e91935db461abb5a2654efaa288', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('89fa0526c63b262bc4693028df24a735', '5c4038872373fc85f7e4fa4375d9b724', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 1);
INSERT INTO `order_item` VALUES ('8e37ff04cf9e881c279e4c815ee7c9e0', '5f86a8bc34d4febc9199d9d4da55e3da', '9ac7b907fe66e1879ab719d4ddcadf19', '油焖大虾', 'http://cxy.novaborn.net:8080/upload/images/bb2920ed3cfa45058351c71b535fd239.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('8f3030bf9f77ad76e9adbe6af0ffedf2', '6597ed0bee86a29149a4606240b34c69', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('919185a6c276062409e4181f9f0367e2', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '551bce6cad97153d9220cfdcaba51325', '口水鸡', 'http://cxy.novaborn.net:8080/upload/images/15c8d3ec42624838b900499e85dec763.jpeg', 9000, 1);
INSERT INTO `order_item` VALUES ('94124fa0d3739cbb55831d373503d67c', '856deaabe59e7556e9be974f2783ffcb', '28d2ba9f82f275e5121820008c2e8a24', '土豆肉丝', 'http://cxy.novaborn.net:8080', 8000, 1);
INSERT INTO `order_item` VALUES ('987df7e8a5feb116fa61cad001354ed7', '2a30c242c3a729bea911c3fe65d5f813', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('99e9d4852d06f532c67349d9d3feee9e', '856deaabe59e7556e9be974f2783ffcb', 'c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', 'http://cxy.novaborn.net:8080/upload/images/0594a52ab60f4ee0902f9a4748fd1565.jpeg', 8000, 1);
INSERT INTO `order_item` VALUES ('9fd7288c1d90bb2a1ca414f79518de9c', 'cb5378238fbbfff539d6d8bb0c3c37c2', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('a18d161b1fc2353c53779d51a25267a3', 'b1bce690cf6114333275367747dd65c6', 'bef58e4b36ed0f79837696659ca7a1a6', '木须肉', 'http://cxy.novaborn.net:8080null', 9000, 1);
INSERT INTO `order_item` VALUES ('a2e637c53cfa75bd9b79b554b5e111b2', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '05c71e0ca83929eb3577ccc60c55cc33', '素三丝', 'http://cxy.novaborn.net:8080/upload/images/20e8d90654234eaaadcf83afa6aa9963.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('a552228cb7fd672164f17587b329737d', '0d251be157a86a0c8c25ee6500e98743', '34d64d55f6ddc14f8cda410d527691cc', '香辣肉丝', 'http://cxy.novaborn.net:8080/upload/images/a78d95aebde241d19741682cf3a0ed45.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('a61c07de4ff3779653993d8ef6d32f5f', 'a134a60edfe17b9b10248f897d7f2502', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('ac1106aacdcbd003022e8718195c82e7', '5c4038872373fc85f7e4fa4375d9b724', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('ae30f701e441de3743dd8363608133a8', '856deaabe59e7556e9be974f2783ffcb', '18fbd399b81301c6128bd92548d7a349', '蚂蚁上树', 'http://cxy.novaborn.net:8080null', 7000, 1);
INSERT INTO `order_item` VALUES ('af391b207595f6e75d42f1e7ebae3e9b', 'cb5378238fbbfff539d6d8bb0c3c37c2', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', 'http://cxy.novaborn.net:8080/upload/images/76b7e0c16fd54582a2d8d991ce5e44b5.jpeg', 7000, 2);
INSERT INTO `order_item` VALUES ('b4805367e451bb36f7e5d2864ce0c35f', '6597ed0bee86a29149a4606240b34c69', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('b68927ef392f506200deb84acd7cf587', '0d251be157a86a0c8c25ee6500e98743', '029d1b56aeb1aaad55440e4b1ac21498', '干煸肉丝', 'http://cxy.novaborn.net:8080null', 10000, 1);
INSERT INTO `order_item` VALUES ('b8d6ae92164e41d79d67c60133f02f51', 'a134a60edfe17b9b10248f897d7f2502', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', 'http://cxy.novaborn.net:8080/upload/images/76b7e0c16fd54582a2d8d991ce5e44b5.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('b9eb1a31f8ac7874e9e0cc32ad52f51a', '5f86a8bc34d4febc9199d9d4da55e3da', '77769b218df210092e5467134b774646', '猪肉炖粉条', 'http://cxy.novaborn.net:8080null', 10000, 1);
INSERT INTO `order_item` VALUES ('bbfa352cb22dcd3bbc7f52fd57f09438', 'a6ae31dd78ff82df686239fe936958cb', '315ad4ec34ca27f6f5339ee5c193d8b4', '黄瓜拌牛肉', 'http://cxy.novaborn.net:8080', 12000, 1);
INSERT INTO `order_item` VALUES ('bee02bd0e2c2571c8d6c01685cf3d445', '06cd76f348cf4ab223e53eeb9a008baf', '544d84af8501517caaa24eeb1593eced', '孜然羊肉', 'http://cxy.novaborn.net:8080null', 11000, 1);
INSERT INTO `order_item` VALUES ('c088f6a2082487f6224e9b50752a8ffb', '5c4038872373fc85f7e4fa4375d9b724', '212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', 'http://cxy.novaborn.net:8080/upload/images/4d2edcffc1bb4d8981e613318474894f.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('c5bdb07e90f9706f9189a08d0142f081', 'a6ae31dd78ff82df686239fe936958cb', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('c64dd00d6593f136c5404096d7dc7f18', '760409e795ceeb2c4d448f4d2d224ef3', '551bce6cad97153d9220cfdcaba51325', '口水鸡', 'http://cxy.novaborn.net:8080/upload/images/15c8d3ec42624838b900499e85dec763.jpeg', 9000, 1);
INSERT INTO `order_item` VALUES ('c734c973c532bd4aea52f6d205d43195', '2112e5d7dcdd12972709d30d517bf1c3', '85b4cbb5f09b087c27a06e5527ebe039', '青椒肉丝', 'http://cxy.novaborn.net:8080/upload/images/1de7690c8dc345ba9c9fcf5fd976741b.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('cde8f34b6bcec411c6e0879e506bdfd6', '2112e5d7dcdd12972709d30d517bf1c3', 'e35b2b982530f091016cb1a57f4c2adb', '尖椒肉丝', 'http://cxy.novaborn.net:8080/upload/images/c49c014c7f5e4fe39f6e6781ca2b32dc.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('cf070c228b291e68a589a73913d9b375', '7b89e0e17f21a974d4416068748622a1', 'c3f795510d0bdcd1d7cfd315878ac412', '外婆牛肉丁', 'http://cxy.novaborn.net:8080/upload/images/d13ee534f82d462c8dcfb30eef60118d.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('d1f2f7e5f2626252fa2b7fad0006641f', '6f64f6fe03081ec26d61d4e6ace16c83', '152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', 'http://cxy.novaborn.net:8080null', 1000, 1);
INSERT INTO `order_item` VALUES ('d3e53e78f03bf25926df57eb9e3b58ef', '5f86a8bc34d4febc9199d9d4da55e3da', 'cf8fd3a6e3ea714f998a7423f00bdd18', '香辣肥肠', 'http://cxy.novaborn.net:8080/upload/images/e8e78557d70842ad8eb4aeeb01b376fe.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('d79712975ec62bbc72217f1d5d8e7ba6', '1732d28aa907147f57a407761825b6a5', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('d892f0769c6d8ccd6018bc045f31e11d', 'b1bce690cf6114333275367747dd65c6', '2e2367630e6fbd6adffcc51d4b7a3806', '爆炒肝尖', 'http://cxy.novaborn.net:8080/upload/images/94da466287d64792b76bace4beea5838.jpeg', 9000, 1);
INSERT INTO `order_item` VALUES ('daa10ed3d288eefa7adcf4a279e00654', 'cb5378238fbbfff539d6d8bb0c3c37c2', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', 'http://cxy.novaborn.net:8080', 6000, 2);
INSERT INTO `order_item` VALUES ('e0c7b5ceeae68fb3185958325623f98c', 'a6ae31dd78ff82df686239fe936958cb', '11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', 'http://cxy.novaborn.net:8080/upload/images/cb4c0cc249d943399435acad9e06c91e.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('e4fac6c83518c86786286f2a783833f3', '760409e795ceeb2c4d448f4d2d224ef3', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', 'http://cxy.novaborn.net:8080/upload/images/76b7e0c16fd54582a2d8d991ce5e44b5.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('e5d2063a9cd5fccbd06ed2d1a7ecc20a', '760409e795ceeb2c4d448f4d2d224ef3', '05c71e0ca83929eb3577ccc60c55cc33', '素三丝', 'http://cxy.novaborn.net:8080/upload/images/20e8d90654234eaaadcf83afa6aa9963.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('e6b4fddaf37e116be47463df704331f8', '87696c5a71687ed7aa75e51655d0fdea', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('ea79e8473be4605db201641cc3ccc12b', '1a23e173793fcd8d92ebfe1d6961d063', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('ee3d70e5f9da7f48569f6496bf51ac90', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', 'http://cxy.novaborn.net:8080/upload/images/4db8120bef9c43dd8812af71eaa1266d.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('eef1d461993268fd48b796e1c64f7479', '06cd76f348cf4ab223e53eeb9a008baf', '8c106901edec65a169aa7b84aa307ed6', '干煸牛腿肉', 'http://cxy.novaborn.net:8080null', 11000, 1);
INSERT INTO `order_item` VALUES ('f20cafb176b6c861db35e3100396c552', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', 'http://cxy.novaborn.net:8080/upload/images/bc3633b805314596bd0545637be3474b.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('f659ff4913063b30b38ad00b69edd4e3', '6f64f6fe03081ec26d61d4e6ace16c83', 'ce08cb7a2bb750b17ae398a8798b2b76', '油炸小花卷', 'http://cxy.novaborn.net:8080/upload/images/88d668ff48ad432d9da0e67f1005510c.jpeg', 3000, 1);
INSERT INTO `order_item` VALUES ('f8596a40c77c5b0027b1a1cd80bab6cd', 'd78dec67ea2c8cba2f45bcb7bfdf91a1', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', 'http://cxy.novaborn.net:8080/upload/images/76b7e0c16fd54582a2d8d991ce5e44b5.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('fdaa850041e7f8499647c91b9d40cd16', '1732d28aa907147f57a407761825b6a5', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', 'http://cxy.novaborn.net:8080/upload/images/48e28286a56b45f1ad6a5f675bc44ebc.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('fedfff9d02a3eebc093638df1fb4c3a3', '7b89e0e17f21a974d4416068748622a1', '9cd096d249efc6cfc37f4522a2a072c0', '辣炒花甲', 'http://cxy.novaborn.net:8080null', 12000, 1);

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `scope` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `key`(`key`, `scope`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setting
-- ----------------------------
INSERT INTO `setting` VALUES ('a3d0207724f77af64775c6e7b63e7b11', 'service_close_notice', 'system', '当前订单过多，暂停服务!!!');
INSERT INTO `setting` VALUES ('e10a0d661701f4449d89aba5d5f404a8', 'goods_page_notice', 'system', '家的味道,优惠的价格! 留学的期间, 有我有你! 让我们共同成长!');
INSERT INTO `setting` VALUES ('61d98184310b33daf3e1b22796d68c5f', 'service_running', 'system', 'true');
INSERT INTO `setting` VALUES ('d45537897868f12f36558fc24f425453', 'goods_page_tags', 'system', '免费配送,满2w送饮料,可以退款');
INSERT INTO `setting` VALUES ('389e2080d5fb9069c33638e89cbbdad8', 'store_address_x', 'store', '126.9357696');
INSERT INTO `setting` VALUES ('f4571b0a5786c8bd8e95e48322ccd1c3', 'average_express_time', 'express', '10');
INSERT INTO `setting` VALUES ('815243cd4f1dd35776b0b6d231519c8f', 'base_express_time', 'express', '25');
INSERT INTO `setting` VALUES ('1c18f17f34d321d5ecf0b04012674597', 'courier_count', 'express', '1');
INSERT INTO `setting` VALUES ('a571deb0a24ba2af5ed4686b64a5f792', 'store_open_date', 'store', '2,3,6,7,5,1');
INSERT INTO `setting` VALUES ('2d5050c2cde8ddc6e93bba51f822bb18', 'store_address_y', 'store', '37.5586305');
INSERT INTO `setting` VALUES ('697c4cc50394e24ef88c52e6c7cc4778', 'store_address', 'store', '창천동 52-31');
INSERT INTO `setting` VALUES ('d898a0d388c7ec6fe9567f035631ea7e', 'store_close_time', 'store', '2019-11-15T13:30:00.000Z');
INSERT INTO `setting` VALUES ('7a47a582b317f77a9c391dcf0c144a8e', 'store_open_time', 'store', '2019-11-15T01:30:00.000Z');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('49268c005a631e2d77b7b90a206fe0eb', 'test', NULL, 0, NULL, 'http://www.baidu.com', NULL, 0, 1, NULL, '2019-10-06 03:34:32', '2019-11-15 02:03:16', 0, 1, NULL);
INSERT INTO `user` VALUES ('4a6bb5a539ec498e2bf5117910af08e4', NULL, 'Jeremy.', 0, 'o9UA_5abDk-kn7KSaMAoriIlvg6c', 'https://wx.qlogo.cn/mmopen/vi_32/UL02ia2qHNyA6UvWNf2Yia5KMOxAh4Kp6icf2ibSOWMiaP8iadSoGgEdE5vbDENG2GVCu97ics161tgrl2cAoGBmX4acg/132', NULL, 1, 1, '2019-11-07 21:26:57', '2019-10-14 23:39:12', '2019-11-07 21:26:56', 61, 0, NULL);
INSERT INTO `user` VALUES ('6157721ea097834ca8502a707e518dcc', NULL, '感觉才才萌萌哒', 0, 'o9UA_5TRxLFe0PnsTbeutriJ569M', 'https://wx.qlogo.cn/mmopen/vi_32/p4rFoMFGBKZTIB9ibbM9Yo3RLTg8rDgfOiaavDHKuNjtNaN3AKzHWrwagmYmoBqF3pSD520TDFFkXVFhRo1cSgbQ/132', NULL, 2, 1, '2019-10-29 16:29:28', '2019-10-18 02:07:04', '2019-10-29 16:29:28', 4, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
