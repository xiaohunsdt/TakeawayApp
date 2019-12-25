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

 Date: 26/12/2019 05:12:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `main_img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `start_date` timestamp(0) NULL DEFAULT NULL,
  `end_date` timestamp(0) NULL DEFAULT NULL,
  `is_show` tinyint(1) NOT NULL DEFAULT 0,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('19e78012ffb587463d412f2945563f02', '测试1', '', '', '2019-12-26 00:00:00', '2019-12-27 00:00:00', 0, '2019-12-26 04:07:16', 1);
INSERT INTO `activity` VALUES ('a9df6fae08658200bd2132f49e367b7d', '点赞排名', '/upload/images/activity/fd39087ac9ff45e7868ecce3e804cd66.png', '<p>点赞排名~~~！@！！！！！！zxczxczxcxz</p>', '2019-12-26 00:00:00', '2019-12-27 00:00:00', 0, '2019-12-26 04:07:51', 0);

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
INSERT INTO `address` VALUES ('115713a7d2f3ed93a6a69a19beb45cd4', 'e187c6afd9ae890c0d81583848159748', '서울특별시 서대문구 성산로17길 7-33 연회동', '201호', 126.931677, 37.5635194, '1067160257', 1, '2019-12-04 16:11:18', 0);
INSERT INTO `address` VALUES ('14be9972393b3482b9a26694ae0333ca', 'f955ba46c7700e218af38e8e5ef305ec', '서대문구 연희동 334-73', '101호', 126.9289216, 37.5632896, '01056630224', 1, '2019-12-08 21:03:58', 0);
INSERT INTO `address` VALUES ('15a1d6c99ba5ff587dac68b47d47b21a', 'de4d141b74de1acf2ba111aa3618dd83', '서울시 마포구 독막로 111& #40;상수동& #41;', ' 국제교육관 V동', 126.9258014, 37.5486198, '01076356626', 1, '2019-12-01 14:12:27', 0);
INSERT INTO `address` VALUES ('60a3810d86f9f62c9905cba271f8f524', '2f33af0d978c616a9ad0fbaffe5e7d5e', '연희동 80-7', '파라다이스텔 에스빌 816호', 126.9348368, 37.5712365, '01085886977', 1, '2019-11-25 15:09:47', 0);
INSERT INTO `address` VALUES ('8214ced91719e75c9b445b42ec8dbea5', '5c4f0b3d173724428b224b0d874f2a74', '서울시 마포구 백범로 35', '서강대학교 곤자가국제학사 ', 126.9410634, 37.5517132, '01034590814', 1, '2019-11-25 13:21:56', 0);
INSERT INTO `address` VALUES ('8c94d4f2e1f92b18d5ab4fe538a800f9', '4a6bb5a539ec498e2bf5117910af08e4', '연희로36길 32', '105호', 126.938475, 37.5777495, '01056511996', 0, '2019-11-18 15:06:50', 0);
INSERT INTO `address` VALUES ('92ce8deba7e238df99b2526703b6f966', '4a6bb5a539ec498e2bf5117910af08e4', 'dsfaaaaaa', 'sdfsdf111', NULL, NULL, '1056511776', 0, '2019-10-18 18:53:56', 1);
INSERT INTO `address` VALUES ('9aa2e78303e956aa06c553b71a9841ac', '1ec05cf9d97a71c84b472472ed3e602b', '서울 마포구 와우산로 94', 'E505', 126.9255396, 37.5512242, '01095505200', 1, '2019-12-22 02:09:08', 0);
INSERT INTO `address` VALUES ('a40134ee6354768e77a8d2d4d4787fa6', '4a6bb5a539ec498e2bf5117910af08e4', 'asd', 'asdas', NULL, NULL, 'asdas', 0, '2019-10-18 18:53:26', 1);
INSERT INTO `address` VALUES ('ab5b8b82845168248a77cb3b4a7030f8', '6e3237cabde082a5910e799beabb1b92', '마포구 노고산동', '신촌포스빌 702', 126.938, 37.5541, '01036105206', 1, '2019-11-26 17:04:25', 0);
INSERT INTO `address` VALUES ('ae48fd0d70dad0479689526613320f53', 'f3e0280aa31435a3df2c1d717dd671ca', '마포구 신촌로150 신촌포스빌', '1413', 126.9419671, 37.5562134, '01096349127', 1, '2019-12-04 19:57:57', 0);
INSERT INTO `address` VALUES ('bb2f1a769382a9eca1a4e72d213bc9bc', '4a6bb5a539ec498e2bf5117910af08e4', '가좌로5길 5', '606호', 126.9219362, 37.5852567, '01056511996', 0, '2019-11-18 15:07:48', 0);
INSERT INTO `address` VALUES ('c95ffd410cf167b66b1b4c5b87d2dffc', '06f9142749ab69be266e081e536f985e', '서울시 마포구 서교동 463-26', '302호', 126.9185779, 37.5547485, '01029972828', 1, '2019-12-01 15:15:25', 0);
INSERT INTO `address` VALUES ('de40918ca9196fe12c34fd9aa8815ebe', '6157721ea097834ca8502a707e518dcc', '신촌로 150 포스빌 701', '放门口就行', NULL, NULL, '01056511996', 1, '2019-10-20 01:34:10', 0);
INSERT INTO `address` VALUES ('e06517213df695520227bea95cabea43', '8622f22a2277fa231b25bc7dd8a503f3', '연남동 260-36', '202호', 126.9248516, 37.5611547, '01057868002', 1, '2019-12-06 16:43:07', 0);
INSERT INTO `address` VALUES ('e316f5803f9eaaeefcc33c31595cbb39', 'bc469f79a6e9800ccdc15880f07f3291', '마포구 신촌로 150', '신촌포스빌1413', 126.9419671, 37.5562134, '01096349127', 1, '2019-11-26 20:20:30', 0);
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
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `delicious` int(1) UNSIGNED NULL DEFAULT 0,
  `express` int(1) UNSIGNED NULL DEFAULT 0,
  `service` int(1) UNSIGNED NULL DEFAULT 0,
  `comment` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('0d82221d912c781da32db3a44f2bd071', '5c4f0b3d173724428b224b0d874f2a74', 'cc68f27ab724e3383b868cb3048f7eb5', 5, 5, 5, '', '2019-12-03 13:10:16', 0);
INSERT INTO `comment` VALUES ('13be0793cadcfe4abea2ec3781ac3b4f', 'e187c6afd9ae890c0d81583848159748', 'ce0590cd24490058ae94d5e1aaa5623a', 5, 5, 5, '好吃呜呜呜呜呜 好想念的味道～不过份量多了很多', '2019-12-04 17:19:05', 0);
INSERT INTO `comment` VALUES ('2244f13222fe5dc7ff9187ffb234ce32', 'e187c6afd9ae890c0d81583848159748', '6c72b62f86b6089d5495d981c59fd652', 5, 5, 5, '', '2019-12-11 21:15:31', 0);
INSERT INTO `comment` VALUES ('24a2c8226805ef81153bb08016b33751', '5c4f0b3d173724428b224b0d874f2a74', 'cc11abe7a5f57fe43fd9a6e191381fb1', 5, 5, 5, '', '2019-11-25 16:42:11', 0);
INSERT INTO `comment` VALUES ('2cbd31f85de680e974e951bab2a5db56', '4a6bb5a539ec498e2bf5117910af08e4', 'f29ed114208ed87a1d945cfa28f57ce0', 2, 3, 4, '', '2019-11-19 18:35:46', 0);
INSERT INTO `comment` VALUES ('618055f1ae7ffb8be5b0533b814c9f11', 'e187c6afd9ae890c0d81583848159748', '3c80059fe9605468211d5cea533c92c4', 5, 5, 5, '', '2019-12-11 21:15:25', 0);
INSERT INTO `comment` VALUES ('799073b43cf1c1fd252b48471666c4a7', '5c4f0b3d173724428b224b0d874f2a74', 'e713d24bda9cd19f3efd9c0de58b0583', 5, 5, 5, '', '2019-12-01 11:54:35', 0);
INSERT INTO `comment` VALUES ('8a497ae6c179bf6982894879176c5586', 'e187c6afd9ae890c0d81583848159748', 'bf1c5da99373728a0f63b68312328791', 5, 5, 5, '', '2019-12-14 21:39:48', 0);
INSERT INTO `comment` VALUES ('8b5ee4e06fa79bd94571c437472806e0', '4a6bb5a539ec498e2bf5117910af08e4', 'fe58c27e3ae9ee88c8b15baf7eebffb2', 4, 4, 5, '啊实打实打算', '2019-11-19 18:48:36', 0);
INSERT INTO `comment` VALUES ('8b8e4ff4586223b1a943bdda3ba85a79', '4a6bb5a539ec498e2bf5117910af08e4', '9340e6ed076df362deeb758657794214', 3, 3, 3, '', '2019-11-19 18:48:27', 0);
INSERT INTO `comment` VALUES ('b399df64d6053f243600f483d3845abb', '5c4f0b3d173724428b224b0d874f2a74', '71a1f3b3aa844b8771040de26878f4ee', 5, 5, 5, '', '2019-12-03 13:10:19', 0);
INSERT INTO `comment` VALUES ('d98f9a68a6fa56821c06cf02feb86943', 'e187c6afd9ae890c0d81583848159748', 'f0cee1c99ead5eff33bc818a3505adff', 5, 5, 5, '', '2019-12-13 11:11:34', 0);
INSERT INTO `comment` VALUES ('e7897f5c97a343ad596fa7072a3cdc17', 'e187c6afd9ae890c0d81583848159748', 'ecb9c9845f919ea2895c4f0232554c77', 5, 5, 5, '', '2019-12-07 16:49:27', 0);
INSERT INTO `comment` VALUES ('ea00f4f81ff47cd64b66f21334bdd3f6', '4a6bb5a539ec498e2bf5117910af08e4', 'e74d39d4b5c6793a2ce7654e2f3c2ae9', 1, 1, 2, '', '2019-11-19 18:48:02', 0);

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
INSERT INTO `goods` VALUES ('05444437734b79d597b8fff2c8fecd21', '麻辣烫/香锅 +牛肉卷', '', '/upload/images/638d1959b8bf4bd5a5486e2c2a898c14.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', '', 3000, 0, 10, 1, '2019-10-07 17:27:46', '2019-12-22 06:00:16', 2, 0);
INSERT INTO `goods` VALUES ('05c71e0ca83929eb3577ccc60c55cc33', '素三丝', '人气凉菜，好看又好吃～', '/upload/images/221f760731484f33adf05892709eee00.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 7000, 0, 10, 1, '2019-10-07 16:25:39', '2019-11-23 01:14:29', 3, 0);
INSERT INTO `goods` VALUES ('063919d67bae60f500d48e18c30a422c', '酸菜炒粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:37:25', '2019-10-07 16:37:25', 0, 0);
INSERT INTO `goods` VALUES ('09ca2fca09f81554ef2947f3775dce3f', '尖椒鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:15', '2019-10-07 17:02:15', 0, 0);
INSERT INTO `goods` VALUES ('0a9eca37dd94af1a19c8f2eae7549817', '酸辣汤', NULL, '/upload/images/de4aa2d039f949a5a237346dc880e548.jpg', '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:29:48', '2019-11-23 01:40:13', 1, 0);
INSERT INTO `goods` VALUES ('0c92136340cb0e43237b09d00762225f', '牛肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:48', '2019-10-07 17:24:48', 0, 0);
INSERT INTO `goods` VALUES ('0cc106a304191a1dc1e27523b93b92e9', '番茄蛋汤', NULL, NULL, '0c309767046ac9952bda4ea5dee37770', NULL, 6000, 0, 10, 1, '2019-10-07 17:30:15', '2019-10-07 17:30:15', 0, 0);
INSERT INTO `goods` VALUES ('0f0c98951bcf7f5b5128dac43b62dfea', '可乐瓶装', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', '', 2500, 0, 10, 1, '2019-11-23 01:43:58', '2019-11-23 01:43:58', 0, 0);
INSERT INTO `goods` VALUES ('11fdaa27de2eea12dde6a20e063e6c5d', '凉拌牛肚', '麻辣劲道的凉拌菜', '/upload/images/6bb51e05543f423198474eff11523d00.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 12000, 0, 10, 1, '2019-10-07 16:26:32', '2019-11-23 01:15:08', 3, 0);
INSERT INTO `goods` VALUES ('13daa7235fc8c0ff68eb503223fe743f', '冰红茶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:34:43', '2019-10-07 17:34:43', 0, 0);
INSERT INTO `goods` VALUES ('152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 1000, 0, 10, 1, '2019-10-07 17:18:44', '2019-10-07 17:18:44', 0, 0);
INSERT INTO `goods` VALUES ('1616e0e42ceb30d70ae916909cfe365e', '麻辣烫/香锅 +虾仁 ', '', '/upload/images/8bab7ebe61204cefb3af7bbd6ec228f5.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', '', 3000, 0, 10, 1, '2019-10-07 17:28:53', '2019-12-22 06:00:22', 3, 0);
INSERT INTO `goods` VALUES ('170d701974dafba665b703d25c9958a2', '扬州炒饭', NULL, '/upload/images/9efcc63c53ef4abdb80d98ba9b6808d4.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:07', '2019-11-23 01:36:05', 1, 0);
INSERT INTO `goods` VALUES ('172794967e828961791eebf401ff03f8', '干煸大虾', '', '/upload/images/50c2a611d2694cbeb6c99f81715bd293.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 12000, 0, 10, 1, '2019-10-07 17:08:09', '2019-11-25 11:11:07', 3, 0);
INSERT INTO `goods` VALUES ('18fbd399b81301c6128bd92548d7a349', '蚂蚁上树', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 7000, 0, 10, 1, '2019-10-07 16:41:25', '2019-10-07 16:41:25', 0, 0);
INSERT INTO `goods` VALUES ('1aa39f6d2967b8f3eb1d6820cbc7e2b8', '老干妈炒饭', NULL, '/upload/images/d2404781466a408593a8c260d3fe8240.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:28', '2019-11-23 01:36:14', 1, 0);
INSERT INTO `goods` VALUES ('1ad51cb674616fad33ee5ba37415304e', '香辣小排', NULL, '/upload/images/1d78d934edec49b9a239d0db9b0222ef.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:14', '2019-11-23 01:53:58', 2, 0);
INSERT INTO `goods` VALUES ('1b81de0fcc1c717da3495d9f00566293', '馄饨', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:45', '2019-10-07 17:19:45', 0, 0);
INSERT INTO `goods` VALUES ('1c2ff79b530aab6acad1148a2f27779b', '香辣鸡翅', NULL, '/upload/images/1f9d5a83a67c4f4099afca2b890ee2d1.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:57:46', '2019-11-23 01:26:53', 2, 0);
INSERT INTO `goods` VALUES ('1e8651ab5598730cd0275d7fca986e26', '辣子鸡', NULL, '/upload/images/92c22c045a944041b6e18d7241e7a8c6.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:58', '2019-11-23 01:25:47', 2, 0);
INSERT INTO `goods` VALUES ('1fa5e874f2b4562e9b3c03235c3c13b4', '虾仁青菜', '这是一道人气菜～', '/upload/images/0548e19c3ea54e40a46d79bcf8b14563.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:39:02', '2019-11-23 01:18:38', 3, 0);
INSERT INTO `goods` VALUES ('212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', '肥牛+鱼丸+火腿+干豆腐+腐竹+土豆+木耳+白菜+豆芽', '/upload/images/0b7de416a4664e1bb6af94d35f237f1b.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', '', 12000, 0, 10, 1, '2019-10-07 17:26:13', '2019-12-08 20:05:33', 3, 0);
INSERT INTO `goods` VALUES ('22da4b82121d50f90ddaff584859b969', '老干妈鸡丁', NULL, '/upload/images/cc675d2488aa4a03b2200d540fe0c84d.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:43:41', '2019-11-23 01:50:34', 2, 0);
INSERT INTO `goods` VALUES ('28d2ba9f82f275e5121820008c2e8a24', '土豆肉丝', '土豆搭配肉丝，有荤有素～', '', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 8000, 0, 10, 1, '2019-10-07 16:41:44', '2019-10-18 15:48:43', 1, 0);
INSERT INTO `goods` VALUES ('2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '爱吃皮蛋和日本豆腐的不容错过～', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:22:52', '2019-11-23 01:14:06', 7, 0);
INSERT INTO `goods` VALUES ('2e2367630e6fbd6adffcc51d4b7a3806', '爆炒肝尖', NULL, '/upload/images/59901980362445768543905e0e85a0ef.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:48:07', '2019-11-23 01:23:42', 2, 0);
INSERT INTO `goods` VALUES ('309fbad34a43306e3c45e8cfd7d005b0', '羊肉米线', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:31', '2019-10-07 17:24:31', 0, 0);
INSERT INTO `goods` VALUES ('30b7ed3ce157e38e67a1ebd3bcff6c45', '三鲜汤', NULL, '/upload/images/ef39e0e6b2814e26ad4b9ca980d6e925.jpg', '0c309767046ac9952bda4ea5dee37770', NULL, 10000, 0, 10, 1, '2019-10-07 17:33:26', '2019-11-23 01:41:17', 1, 0);
INSERT INTO `goods` VALUES ('315ad4ec34ca27f6f5339ee5c193d8b4', '黄瓜拌牛肉', '脆脆的黄瓜配上劲道的牛腩肉，绝配哦', '', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 12000, 0, 10, 1, '2019-10-07 16:27:17', '2019-10-18 15:28:46', 1, 0);
INSERT INTO `goods` VALUES ('31a06a0038b97c891abe609b29c0eee8', '锅包肉', NULL, '/upload/images/4ab979ffb7704eaabc3812949f4ac3a6.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:07:34', '2019-11-23 01:30:56', 1, 0);
INSERT INTO `goods` VALUES ('31e077c41e3d92cb099dd4cebb4fb11b', '湖南小炒肉', NULL, '/upload/images/45a47823262345af88643505e6f6d85e.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:34', '2019-11-23 01:24:35', 2, 0);
INSERT INTO `goods` VALUES ('3291e2ad095c3354b82e33e9dbb4b7ad', '烧溜豆腐', '营养丰富的家常菜～', '/upload/images/f656ab969be54c45b30fcab4cc9ab340.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 6000, 0, 10, 1, '2019-10-07 16:29:16', '2019-12-08 15:23:29', 2, 0);
INSERT INTO `goods` VALUES ('333a1a2f0f85b45aae879f31f2f975ce', '孜然里脊', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:21', '2019-10-07 16:53:21', 0, 0);
INSERT INTO `goods` VALUES ('33c0fa961f3598d9791a53089c21cdb5', '川香烤鱼(大份)', '2条鲤鱼，每条鱼都在一斤左右,100%满足你的味蕾', '/upload/images/b7b94af6d9a54108a777237b02f1ff55.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 32000, 0, 10, 1, '2019-12-08 15:08:18', '2019-12-08 15:16:44', 3, 0);
INSERT INTO `goods` VALUES ('3489f48cb26c95750813b55f0f54d0a8', '小鸡炖蘑菇', '', '/upload/images/3eae75f6bbc14db8939e52f126f42aa8.jpg', '1f1680134fc00750c5f432faa83644d3', ',热门', 11000, 0, 10, 1, '2019-10-07 17:13:50', '2019-11-25 11:09:33', 3, 0);
INSERT INTO `goods` VALUES ('34d64d55f6ddc14f8cda410d527691cc', '香辣肉丝', NULL, '/upload/images/653ddac4283a4f1c9ee6dc796bc5747a.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:21', '2019-11-23 01:25:35', 2, 0);
INSERT INTO `goods` VALUES ('34e4acd099e554d58eb9e5c1935326c9', '冰糖雪梨', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 2500, 0, 10, 1, '2019-10-07 17:35:00', '2019-10-07 17:35:00', 0, 0);
INSERT INTO `goods` VALUES ('372f4db201871f20e81fa214dca621c5', '粉条炖牛腩', NULL, '/upload/images/2599b968c23342539354b716ffb8cdc4.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:03', '2019-11-23 01:55:55', 2, 0);
INSERT INTO `goods` VALUES ('388d8774dc686d87f6656b9104c4c999', '清炒腐竹', '腐竹清炒，别具风味～', '/upload/images/eacc8bca2849431c9360bc025d8838ee.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:56', '2019-11-23 01:49:23', 3, 0);
INSERT INTO `goods` VALUES ('3be786d311bea4d1b6be7a1fc8fd498a', '水饺', NULL, '/upload/images/54194fe75f45457894179de26fc0b3ba.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:19:25', '2019-11-23 01:35:52', 2, 0);
INSERT INTO `goods` VALUES ('3cc50f52f9c51061b2f69d7535ee1a2a', '地三鲜', NULL, '/upload/images/87d0b06b87d942f0a28d282a961420fd.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:40', '2019-11-23 01:18:02', 2, 0);
INSERT INTO `goods` VALUES ('3db0f8f9ce492d7149744ce7a2a3fa19', '酱油炒饭', NULL, '/upload/images/e8b9c94a23a34f9c9925bf773804b971.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:20:48', '2019-11-23 01:36:23', 1, 0);
INSERT INTO `goods` VALUES ('3e416cfb3cdff1547842d4981afe818d', '鱼香茄子', '茄子的人气，不用我多说哦哈哈', '/upload/images/f7ccb2c2499445cf95aebaf1eee23e1e.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:52', '2019-11-23 01:17:46', 3, 0);
INSERT INTO `goods` VALUES ('3e9ad83124e30144d65f318d2109732c', '肉圆粉丝汤', '', '/upload/images/7fc0ea6c699843f2a74d80ec263f97d9.jpeg', '0c309767046ac9952bda4ea5dee37770', '', 9000, 0, 10, 1, '2019-10-07 17:33:06', '2019-11-23 01:57:07', 3, 0);
INSERT INTO `goods` VALUES ('434db0970f06d995764612c4a85c72b2', '爆炒腰花', NULL, '/upload/images/925f67a097ad4ea9a21a70ce09002ff1.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:50:22', '2019-11-23 01:24:08', 2, 0);
INSERT INTO `goods` VALUES ('43eadde1ed3ea445a950676353ef7853', '红烧丸子', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:57:00', '2019-11-23 01:26:15', 0, 1);
INSERT INTO `goods` VALUES ('470a11753727382cca018843c7a5e49c', '凉拌腐竹', '清爽的腐竹凉拌菜', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:23:32', '2019-11-23 01:47:55', 3, 0);
INSERT INTO `goods` VALUES ('49b46e756cfb5a576b853f8ee871f607', '虾仁玉米', '人气美食，颜值第一哈哈', '/upload/images/979e51cc30914e8289d2e2bd90c433b7.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:40:58', '2019-11-23 01:19:28', 3, 0);
INSERT INTO `goods` VALUES ('5384a3538ae8fd34593be6209f0380b5', '尖椒炒鸡蛋', '这个是真的下饭神器', '/upload/images/99ff9d6fb75d45f38316175f5a138ea5.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:39:53', '2019-11-23 01:19:11', 3, 0);
INSERT INTO `goods` VALUES ('544d84af8501517caaa24eeb1593eced', '孜然羊肉', NULL, '/upload/images/84c467e4931a4c328869088a719ba5f4.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:57', '2019-11-23 01:27:32', 1, 0);
INSERT INTO `goods` VALUES ('54a33ae4ef409d42dd196a26f30d2c0f', '可乐罐装', '', '/upload/images/a3b4a6d001a54891a0ab4db2e43b1dbe.jpg', '1447c360419f319bcd38e9ef043f07b8', '', 2000, 0, 10, 1, '2019-10-07 17:34:04', '2019-11-23 01:43:18', 2, 0);
INSERT INTO `goods` VALUES ('551bce6cad97153d9220cfdcaba51325', '口水鸡', '口水鸡，名气远扬～', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 9000, 0, 10, 1, '2019-10-07 16:26:04', '2019-11-23 01:14:54', 3, 0);
INSERT INTO `goods` VALUES ('55485bf569e0aac16b0770d2a6f9b327', '肉丝炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:01', '2019-10-07 17:23:01', 0, 0);
INSERT INTO `goods` VALUES ('5c09ec5d8d6c38ea729908e38fd5f37b', '尖椒火腿', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:47:10', '2019-10-07 16:47:10', 0, 0);
INSERT INTO `goods` VALUES ('5d4a3d59fbc7167e648f57c95c37e01e', '土豆红烧肉', '', '/upload/images/ef5768f2d9a344fd9494fa0fb1460653.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 11000, 0, 10, 1, '2019-10-07 17:03:01', '2019-11-25 11:11:26', 3, 0);
INSERT INTO `goods` VALUES ('5f361deb700360d98452d5b9632a40ab', '番茄炖牛腩', '', '/upload/images/7bf0f12d34144661937f5e55562b5d51.jpeg', '1f1680134fc00750c5f432faa83644d3', ',热门', 12000, 0, 10, 1, '2019-10-07 17:16:32', '2019-11-25 11:09:49', 3, 0);
INSERT INTO `goods` VALUES ('62eaf34022a211ab237d4f45d12bc175', '腐竹红烧肉', '', '/upload/images/af1e6bd56e0e499da7065390efc7828c.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 11000, 0, 10, 1, '2019-10-07 17:03:24', '2019-11-25 11:11:30', 3, 0);
INSERT INTO `goods` VALUES ('6441ac52955d6d6a59441879a7bb9fa9', '阿萨姆奶茶', NULL, '/upload/images/81b3223ec9f34d7c8c0ddf1973cf7351.jpg', '1447c360419f319bcd38e9ef043f07b8', '', 3000, 0, 10, 1, '2019-11-23 01:42:21', '2019-11-23 01:42:36', 1, 0);
INSERT INTO `goods` VALUES ('650632fb49a091669f41fbfaaf2d53fb', '糖醋里脊', NULL, '/upload/images/665992d4c4814ee2bffb43b39e57b0f4.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:53:39', '2019-11-23 01:51:43', 2, 0);
INSERT INTO `goods` VALUES ('652a6464943a81c0f5fdfd06e4affd0e', '川香烤鱼', '川香美味的烤鱼，一条鱼1斤左右！好吃过瘾', '/upload/images/1a013c1bc8ed4de8a326c84b1b4a7bc6.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 18000, 0, 10, 1, '2019-12-08 15:06:49', '2019-12-08 15:16:33', 3, 0);
INSERT INTO `goods` VALUES ('67a7987dcbafd64a946e9ed70903bba7', '萝卜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:55', '2019-10-07 17:12:55', 0, 0);
INSERT INTO `goods` VALUES ('67e6c3d6f4142e4df205e0d5b917acaa', '水煮鱼', '川味的酸菜鱼,绝对满足你^^', '/upload/images/22da6d92c1c34ce8ad5b441714aa0b7a.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 15000, 0, 10, 1, '2019-12-08 15:19:30', '2019-12-08 15:21:43', 3, 0);
INSERT INTO `goods` VALUES ('6e36a9126991fefc401d3799a923ca29', '酸辣土豆丝', '酸辣口感，停不下来～', '/upload/images/3190a579573c46c6bb397c2243f27619.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:29:46', '2019-11-23 01:15:55', 3, 0);
INSERT INTO `goods` VALUES ('6fb3bb8dd013108c39463f4924027bcb', '青椒炒鸡蛋', NULL, '/upload/images/6388954e1a63499d94843c6e9ffb8000.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:40:16', '2019-12-08 15:25:36', 1, 0);
INSERT INTO `goods` VALUES ('71928e9de8afc4b91b3dc1c76cba80fb', '椰汁', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:48', '2019-10-07 17:35:48', 0, 0);
INSERT INTO `goods` VALUES ('72e0fc62ce10c04e4c01afae5f94a217', '番茄鸡蛋', NULL, '/upload/images/39f81bfd6b524078ae4c679a88ff6819.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:34:15', '2019-11-23 01:49:15', 2, 0);
INSERT INTO `goods` VALUES ('761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '美味好吃的蒜蓉黄瓜!', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', ',热卖', 6000, 0, 10, 1, '2019-09-22 04:06:33', '2019-11-25 11:09:15', 16, 0);
INSERT INTO `goods` VALUES ('77769b218df210092e5467134b774646', '猪肉炖粉条', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:12:03', '2019-10-07 17:12:03', 0, 0);
INSERT INTO `goods` VALUES ('7a85eff463de0c1eeb836cccefb76a6b', '黄瓜炒鸡蛋', '黄瓜炒蛋，简单美味～', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:31:33', '2019-10-18 15:32:16', 1, 0);
INSERT INTO `goods` VALUES ('7c066108cc4c0877750d163c32b3ef22', '青椒土豆丝', '清香口感～', '/upload/images/a0fd056983ed423bbb1ec07822f40bdf.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 8000, 0, 10, 1, '2019-10-07 16:30:14', '2019-11-23 01:16:12', 3, 0);
INSERT INTO `goods` VALUES ('7c6d50fb3c0b6dbf8cd52b607a43d958', '茶派', NULL, '/upload/images/b7c16a7def5d410a95d6a0056f05d73a.jpg', '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:36:06', '2019-11-23 01:41:46', 1, 0);
INSERT INTO `goods` VALUES ('7f1eeea4a086244907da5e8cfb303cb8', '皮蛋瘦肉粥', NULL, '/upload/images/e3a84e8fbfcb45a29f3f887c8433a695.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:38', '2019-11-23 01:37:05', 2, 0);
INSERT INTO `goods` VALUES ('7f460dc38492286587549276aaa6e552', '花甲粉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:25:15', '2019-11-23 01:56:22', 0, 1);
INSERT INTO `goods` VALUES ('8028633e923bba71a540559b404e960f', '香菇肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:54', '2019-10-07 16:49:54', 0, 0);
INSERT INTO `goods` VALUES ('80d1d16712ee9988341ccc5602f10292', '酸菜白肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:56', '2019-10-07 17:10:56', 0, 0);
INSERT INTO `goods` VALUES ('814865ead95fc474425b65a56940f3c3', '尖椒干豆腐', '这个口感真的刺激，贼下饭', '/upload/images/20a578a10775429ca8c08647643e69a9.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:31:07', '2019-11-23 01:48:45', 3, 0);
INSERT INTO `goods` VALUES ('814976d852be95b06660e78f107bf031', '旺仔牛奶', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', NULL, 3000, 0, 10, 1, '2019-10-07 17:35:29', '2019-10-07 17:35:29', 0, 0);
INSERT INTO `goods` VALUES ('835a89bc3f317a32a6b92e7397b9da33', '茄条肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:16', '2019-10-07 16:45:16', 0, 0);
INSERT INTO `goods` VALUES ('84a646a8629a3c7585035b776153b98d', '红烧鲤鱼', '一整条一斤左右的鲤鱼哦! 再也不怕在韩国吃不到鱼了^^', '/upload/images/4c7493c854bd4157a384ae6c6562c974.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 15000, 0, 10, 1, '2019-12-08 15:18:26', '2019-12-08 15:20:51', 1, 0);
INSERT INTO `goods` VALUES ('85b4cbb5f09b087c27a06e5527ebe039', '青椒肉丝', NULL, '/upload/images/012407466ef5498d9f8a810ceabf921b.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:50:46', '2019-11-23 01:24:20', 2, 0);
INSERT INTO `goods` VALUES ('861baf559ae479a7faadf125d2842fc2', '毛血旺', '', '/upload/images/6115d8e486864d0ebb7ab39a90c15b46.jpg', '1f1680134fc00750c5f432faa83644d3', ',热门', 12000, 0, 10, 1, '2019-10-07 17:18:21', '2019-11-25 11:10:05', 3, 0);
INSERT INTO `goods` VALUES ('885e94d1daf481e72d633dfc7ec5462b', '辣炒鸡心', NULL, '/upload/images/20c36395769a4006afe89af6e84230de.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:29', '2019-11-23 01:23:04', 2, 1);
INSERT INTO `goods` VALUES ('8c106901edec65a169aa7b84aa307ed6', '干煸牛腿肉', '', '', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 11000, 0, 10, 1, '2019-10-07 16:57:23', '2019-11-25 15:46:52', 1, 1);
INSERT INTO `goods` VALUES ('8ef386c437d239e990177089afda080c', '香辣猪蹄', NULL, '/upload/images/c29faae3c53d4a7f96e894a241586367.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:50', '2019-11-23 01:30:14', 2, 0);
INSERT INTO `goods` VALUES ('8f3883855e6321762c324a8825312c59', '牛肉蛋炒饭', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:23:39', '2019-10-07 17:23:39', 0, 0);
INSERT INTO `goods` VALUES ('956176f39dfa2222d6a84aedc46eefd1', '三鲜疙瘩汤', NULL, NULL, 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 8000, 0, 10, 1, '2019-10-07 17:22:01', '2019-10-07 17:22:01', 0, 0);
INSERT INTO `goods` VALUES ('959be79aec14a643c6bab1d08601866e', '四川回锅肉', NULL, '/upload/images/fd5d2dd62ee14b9d8b6646e68b3164b4.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:52:00', '2019-11-23 01:25:18', 2, 0);
INSERT INTO `goods` VALUES ('96d06c954a6f4b40cf8c995a0cbfb0de', '麻辣烫/香锅 +西蓝花', '', '/upload/images/1f38244e2fa146528adfd61b2abd0bcf.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', '', 2000, 0, 10, 1, '2019-10-07 17:29:24', '2019-12-22 06:00:28', 2, 0);
INSERT INTO `goods` VALUES ('9ac7b907fe66e1879ab719d4ddcadf19', '油焖大虾', '', '/upload/images/872afce6d6784f6b9ad64ce5ba680ec5.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 12000, 0, 10, 1, '2019-10-07 17:08:33', '2019-11-25 11:11:14', 3, 0);
INSERT INTO `goods` VALUES ('9cba928b7b27de0456a74e966796e205', '黄菜烧牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:11:36', '2019-10-07 17:11:36', 0, 0);
INSERT INTO `goods` VALUES ('9cd096d249efc6cfc37f4522a2a072c0', '辣炒花甲', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:28', '2019-11-23 01:53:18', 0, 1);
INSERT INTO `goods` VALUES ('9daf4687b855ed61ba74f8115ff792b5', '瓶装雪碧', NULL, NULL, '1447c360419f319bcd38e9ef043f07b8', '', 2500, 0, 10, 1, '2019-11-23 01:44:20', '2019-11-23 01:44:20', 0, 0);
INSERT INTO `goods` VALUES ('9f0bb6f711f618624a94a3a43909e7bf', '香菇青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:38:31', '2019-10-07 16:38:31', 0, 0);
INSERT INTO `goods` VALUES ('9fd1e6b7a4cb343a1826ef4f947f7907', '酸辣粉', NULL, '/upload/images/b073226595064348a82bd73e49433e00.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 6000, 0, 10, 1, '2019-10-07 17:21:04', '2019-11-23 01:36:37', 2, 0);
INSERT INTO `goods` VALUES ('a56e4dda92915cd75be0a32fdc4be10a', '孜然肉片', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:45:46', '2019-10-07 16:45:46', 0, 0);
INSERT INTO `goods` VALUES ('a5aab1591e53f67d13cf9a683114842a', '豆芽粉丝', '豆芽粉丝拌一拌，也是简单的美味～', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 6000, 0, 10, 1, '2019-10-07 16:27:47', '2019-10-18 15:29:17', 1, 0);
INSERT INTO `goods` VALUES ('a6d83178e10446a2f29a5c5c304462e8', '麻辣烫', NULL, '/upload/images/616a1fd0f6354687a92da64743ebf2fa.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:25:54', '2019-11-23 01:38:54', 2, 0);
INSERT INTO `goods` VALUES ('a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', NULL, '/upload/images/6fc1f856876344b9812827687ff74d6f.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 9000, 0, 10, 1, '2019-10-07 17:24:00', '2019-11-23 01:39:56', 2, 0);
INSERT INTO `goods` VALUES ('a9845eb443b77479459a765f3038ebfc', '水煮牛肉', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:14:53', '2019-10-07 17:14:53', 0, 0);
INSERT INTO `goods` VALUES ('ab4b0c39cc46b53deb0498dd4e6599d2', '皮蛋豆腐羹', NULL, '/upload/images/6d3cb3ccd71f4977acd0e9a3a84246d0.jpg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:32:41', '2019-11-23 01:40:42', 2, 0);
INSERT INTO `goods` VALUES ('abe3a6acc45ecc03f3c8cafd74251f9f', '麻辣牛肚 ', NULL, '/upload/images/d7b865dd34e24232bfd4756bbf044a9f.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:05:38', '2019-11-23 01:29:19', 1, 0);
INSERT INTO `goods` VALUES ('ac830dbc81a0a0e78a1b77e0d6d64921', '手撕包菜', '名气远扬的干锅菜～', '/upload/images/00b7acc090b24fc388c5652c8ec9399f.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:30:41', '2019-11-23 01:16:27', 3, 0);
INSERT INTO `goods` VALUES ('aced92326fe9e237c264ad821888107e', '尖椒鸡', '', '/upload/images/46df93233edc426e93184e66c63c2b12.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 11000, 0, 10, 1, '2019-10-07 17:04:04', '2019-11-25 11:10:44', 3, 0);
INSERT INTO `goods` VALUES ('ad36856f9242f438d0fabaf24e479c2d', '银牙肉丝', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:43:15', '2019-10-07 16:43:15', 0, 0);
INSERT INTO `goods` VALUES ('ade51e3b9e880abc00cbcc369890322d', '孜然心管', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:06:08', '2019-11-23 01:53:22', 0, 1);
INSERT INTO `goods` VALUES ('b0cbede48db1b84041ea6ab32f6a51ec', '麻婆豆腐', '麻辣鲜香～', '/upload/images/d4ad18c161e94e05be5bfe8ff831b198.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:26', '2019-11-23 01:18:17', 3, 0);
INSERT INTO `goods` VALUES ('b0fda02a51cac7a08c76b9dbadbba693', '酸菜鱼', '川味的酸菜鱼,绝对满足你^^', '/upload/images/150eadbcf4384d37a6c8cde2a1ece502.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 15000, 0, 10, 1, '2019-12-08 15:19:16', '2019-12-08 15:21:31', 2, 0);
INSERT INTO `goods` VALUES ('b132f10d83515369a9e753fde3d39257', '宫保鸡丁', NULL, '/upload/images/7e460e85ddc147fd841e7f501ccad372.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:05', '2019-11-23 01:22:02', 2, 0);
INSERT INTO `goods` VALUES ('b1427e2373345a6c954d3a8c3de152d3', '酸菜炖排骨', NULL, NULL, '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:09:59', '2019-10-07 17:09:59', 0, 0);
INSERT INTO `goods` VALUES ('b2776fe0ef5d43dfa4228dd51ceef530', '肥肠豆腐煲', NULL, '/upload/images/66701f3de17e410780d1ff68f5305a83.jpg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:17:56', '2019-11-23 01:35:10', 2, 0);
INSERT INTO `goods` VALUES ('b27935ecc22c836f6f390883b47671b5', '韭菜鸡蛋', NULL, '/upload/images/9c377c15996b43ebb814cd23f46fe176.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:47', '2019-11-23 01:49:04', 2, 0);
INSERT INTO `goods` VALUES ('b6bdb4d57bd008b200c713b361a7ef75', '孜然牛肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:39', '2019-10-07 16:59:39', 0, 0);
INSERT INTO `goods` VALUES ('b7d74a6ce8ca9b7a4b02cd619f6ad98a', '辣炒牛肉', NULL, '/upload/images/c31ed0cb0a7c4af78409f2bbcb364836.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:00:23', '2019-11-23 01:52:42', 2, 0);
INSERT INTO `goods` VALUES ('bb08daaca3ff7e0cade0919a0cf7c544', '三鲜丸子', NULL, '/upload/images/37d064dcf3fe49b190637415121395f9.jpg', '1f1680134fc00750c5f432faa83644d3', NULL, 10000, 0, 10, 1, '2019-10-07 17:10:27', '2019-11-23 01:31:55', 2, 0);
INSERT INTO `goods` VALUES ('bee2b3fec6a7a54170f5c6364446a676', '可乐鸡翅', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:59:09', '2019-10-07 16:59:09', 0, 0);
INSERT INTO `goods` VALUES ('beeabd2a0a1a967266a428e382709e4f', '油焖茄子', '茄子的人气，不用我多说哦哈哈', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:30', '2019-10-18 15:33:15', 1, 0);
INSERT INTO `goods` VALUES ('bef58e4b36ed0f79837696659ca7a1a6', '木须肉', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:49:34', '2019-10-07 16:49:34', 0, 0);
INSERT INTO `goods` VALUES ('c3ca238fa4cd5eb8b742ef2823e72d00', '豆皮烧牛肉', NULL, '/upload/images/aa3b6c69f79b4ee99deb15499cd6fbfc.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:02:42', '2019-11-23 01:53:08', 2, 0);
INSERT INTO `goods` VALUES ('c3f795510d0bdcd1d7cfd315878ac412', '外婆牛肉丁', '10000', '/upload/images/59685764c47147d19dfb64788a91e3c9.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 17:04:52', '2019-11-23 01:29:03', 2, 0);
INSERT INTO `goods` VALUES ('c43e80c97efc78363090815ac501d7b1', '回锅牛肉', NULL, '/upload/images/c3be54cc8e6c4c13b255db31a1399f51.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:56:35', '2019-11-23 01:52:08', 2, 0);
INSERT INTO `goods` VALUES ('c506378d3a7faafd6d719d666f409111', '辣炒鸡胗', NULL, '/upload/images/9afe5e3fa3534ca38ef3f2e2e9901d86.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:44:47', '2019-11-23 01:22:47', 2, 0);
INSERT INTO `goods` VALUES ('c54e7030f3c2f78ed10ed989ff4c2829', '蒜蓉青菜', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:39:32', '2019-10-07 16:39:32', 0, 0);
INSERT INTO `goods` VALUES ('c5db94a3b4b96c10471ed5cb9939668b', '沸腾牛肚', NULL, '/upload/images/44765131598c4a029d19b31c591803b6.jpg', '1f1680134fc00750c5f432faa83644d3', NULL, 12000, 0, 10, 1, '2019-10-07 17:16:54', '2019-11-23 01:34:43', 2, 0);
INSERT INTO `goods` VALUES ('c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', '经典家常菜～', '/upload/images/cbff583b479c474eb270732abdd93566.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 8000, 0, 10, 1, '2019-10-07 16:42:00', '2019-11-23 01:20:23', 3, 0);
INSERT INTO `goods` VALUES ('c88499d0ad3d4f3d5a214f87617b582f', '水煮肉片', NULL, '/upload/images/f6cce8c6dc174bafa1458c82d16f26c7.jpg', '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:14:12', '2019-11-23 01:33:01', 2, 0);
INSERT INTO `goods` VALUES ('ca369ca09e5d4bb8f489aff2e73fdf6c', '芹菜炒肉', NULL, '/upload/images/cf23fad32ced41d98ecc987205c69d08.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:56', '2019-11-23 01:21:48', 2, 0);
INSERT INTO `goods` VALUES ('cd69a09bd278dc062392d62407c525b0', '肉末茄子', '茄子的人气，不用我多说哦哈哈', '', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:32:08', '2019-10-18 15:33:05', 1, 0);
INSERT INTO `goods` VALUES ('ce08cb7a2bb750b17ae398a8798b2b76', '油炸小花卷', NULL, '/upload/images/32935bc1cff749c28b4a6277e4487a4b.jpg', 'e5f6c1eb9aeff5675d69ee33b9a2c946', NULL, 3000, 0, 10, 1, '2019-10-07 17:19:08', '2019-11-23 01:35:37', 2, 0);
INSERT INTO `goods` VALUES ('cea32d01e13c056a448f4b2d8bcd12b9', '酸汤肥牛', '', '/upload/images/01d3cc59dd7d4fffb9ad54dd9cef148a.jpg', '1f1680134fc00750c5f432faa83644d3', ',热门', 12000, 0, 10, 1, '2019-10-07 17:15:19', '2019-11-25 11:09:41', 3, 0);
INSERT INTO `goods` VALUES ('cea6ec8aad7077e1bcd23ba1abe0f1f8', '白菜豆腐汤', NULL, '/upload/images/d0af02accfd746c6848be92472e7cc5a.jpeg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:30:35', '2019-11-23 01:56:42', 2, 0);
INSERT INTO `goods` VALUES ('cefa589d9c3eb87470427cb022f28367', '糖醋鲤鱼', '一整条一斤左右的鲤鱼哦! 再也不怕在韩国吃不到鱼了^^', '/upload/images/6ba3687640aa45c28ce76535283f0ec4.jpg', '1f1680134fc00750c5f432faa83644d3', '新品', 15000, 0, 10, 1, '2019-12-08 15:18:44', '2019-12-17 17:17:27', 2, 0);
INSERT INTO `goods` VALUES ('cf8fd3a6e3ea714f998a7423f00bdd18', '香辣肥肠', NULL, '/upload/images/d95eff32f021472dabe69eab97723de4.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 12000, 0, 10, 1, '2019-10-07 17:09:04', '2019-11-23 01:31:33', 2, 0);
INSERT INTO `goods` VALUES ('d2991ea4c0dc65764d934188f1c441c5', '辣炒羊肉', NULL, '/upload/images/597f3fcb5f3244ba8799ca5bf7842d90.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:30', '2019-11-23 01:52:57', 2, 0);
INSERT INTO `goods` VALUES ('d52a110f824dcb18cb3b420b6039e9a0', '土豆炖排骨', NULL, '/upload/images/9f7a5d0e9bd54a1591ad8fb39bd41b51.jpeg', '1f1680134fc00750c5f432faa83644d3', NULL, 11000, 0, 10, 1, '2019-10-07 17:13:27', '2019-11-23 01:55:39', 2, 0);
INSERT INTO `goods` VALUES ('d7afc24ccd36da2139000aab9c78a0ae', '千叶红烧肉', '', '/upload/images/c103d7beb2834808b1eabf97cdd96134.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', ',热门', 11000, 0, 10, 1, '2019-10-07 17:03:44', '2019-11-25 11:11:34', 3, 0);
INSERT INTO `goods` VALUES ('d88891e17c565d017df0415bbce0b9dc', '蒜蓉西蓝花', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 8000, 0, 10, 1, '2019-10-07 16:36:18', '2019-10-07 16:36:18', 0, 0);
INSERT INTO `goods` VALUES ('da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', '家常凉拌菜酸甜口味', '/upload/images/5f09c42f582e4ac58345fb7b9c36de24.jpeg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 6000, 0, 10, 1, '2019-10-07 16:24:38', '2019-11-20 21:17:48', 6, 0);
INSERT INTO `goods` VALUES ('db90ea94e33bc790bc682d320c808d21', '玉米蛋花汤', NULL, '/upload/images/b15b24b49c6949af80b1d02a8edd741d.jpg', '0c309767046ac9952bda4ea5dee37770', NULL, 7000, 0, 10, 1, '2019-10-07 17:31:02', '2019-11-23 01:40:27', 2, 0);
INSERT INTO `goods` VALUES ('dcbc5470125f3ead0db55eb26a61376e', '金针菇粉丝', NULL, NULL, 'a8f1b3bae4c02e3208aa108603bdb3eb', NULL, 7000, 0, 10, 1, '2019-10-07 16:33:16', '2019-10-07 16:33:16', 0, 0);
INSERT INTO `goods` VALUES ('e10081ad2fe80dd437c67e6ece595b14', '雪碧罐装', '', '', '1447c360419f319bcd38e9ef043f07b8', '', 2000, 0, 10, 1, '2019-10-07 17:34:22', '2019-11-23 01:43:31', 1, 0);
INSERT INTO `goods` VALUES ('e35b2b982530f091016cb1a57f4c2adb', '尖椒肉丝', NULL, '/upload/images/c49c014c7f5e4fe39f6e6781ca2b32dc.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 10000, 0, 10, 1, '2019-10-07 16:51:11', '2019-10-18 02:36:59', 1, 0);
INSERT INTO `goods` VALUES ('e3fe4e074269e2d6ece45d49bc5633d4', '尖椒牛肚', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', '', 12000, 0, 10, 1, '2019-11-23 01:54:46', '2019-11-23 01:54:46', 0, 0);
INSERT INTO `goods` VALUES ('e5a7e0a001111392d98d25b5e8fa030f', '红烧鸡翅', NULL, '/upload/images/1ec57bff7f484c2294d377a4090ce7ea.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 16:58:51', '2019-11-23 01:27:14', 2, 0);
INSERT INTO `goods` VALUES ('e8b9e920786822151845d68655dbe130', '肉末蒸蛋', NULL, '/upload/images/cf069a97f1b44d98838b61d51e08b90e.jpeg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 8000, 0, 10, 1, '2019-10-07 16:42:29', '2019-11-23 01:49:51', 2, 0);
INSERT INTO `goods` VALUES ('ebff38868d14209e9c3ecfb13120566d', '干煸鱿鱼', NULL, NULL, 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 11000, 0, 10, 1, '2019-10-07 17:01:49', '2019-10-07 17:01:49', 0, 0);
INSERT INTO `goods` VALUES ('ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', '土豆丝的凉拌菜～', '/upload/images/603a255388d64bd2ac714c60ebc04a75.jpg', '8410fe3eac3dd72c7b0aeb4f24cc05a8', '', 7000, 0, 10, 1, '2019-10-07 16:25:14', '2019-11-23 01:14:19', 4, 0);
INSERT INTO `goods` VALUES ('f30d51dc2706e36f6869bc4d711c1493', '孜然小土豆', '孜然与土豆在一起的香味~值得一试！！', '/upload/images/72f35c2bd5ef4cee97c899c2481958c7.jpg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '新品,热门', 8000, 0, 10, 1, '2019-12-08 15:22:50', '2019-12-08 15:25:06', 1, 0);
INSERT INTO `goods` VALUES ('f8a262b82cf30521999f75053ef5dc24', '干煸土豆片', '光看图片就已经很有食欲啦', '/upload/images/16282f52a021475e89ea2586b1b087f5.jpeg', 'a8f1b3bae4c02e3208aa108603bdb3eb', '', 7000, 0, 10, 1, '2019-10-07 16:35:03', '2019-11-23 01:06:30', 3, 0);
INSERT INTO `goods` VALUES ('f8cf2f6fb3b6af7f9b9417fe8b1f33f2', '素三鲜', NULL, '/upload/images/f1c15ecb94fc45ada73a3455754b28d3.jpg', '1f1680134fc00750c5f432faa83644d3', NULL, 9000, 0, 10, 1, '2019-10-07 17:09:28', '2019-11-23 01:31:44', 1, 0);
INSERT INTO `goods` VALUES ('f9896e09387a6b91abca11146fe655a6', '沸腾牛五花', '', '/upload/images/be703e43142c4aec9b5885800cc6febd.jpg', '1f1680134fc00750c5f432faa83644d3', ',热门', 12000, 0, 10, 1, '2019-10-07 17:17:17', '2019-11-25 11:09:58', 3, 0);
INSERT INTO `goods` VALUES ('facffddf7c0cf642c8498f139b7f3315', '蒜薹炒肉', NULL, '/upload/images/ad29f51263ce46f688275054a63da3c8.jpg', 'c7ae0008e4c4b0b7f6cf5d270fa36e06', NULL, 9000, 0, 10, 1, '2019-10-07 16:46:32', '2019-11-23 01:23:27', 2, 0);

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
INSERT INTO `order` VALUES ('00822a69cc5b0b0b09d3085c87930664', 2, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 0, '2019-12-07 14:36:43', '2019-12-07 14:55:52', 4, 0);
INSERT INTO `order` VALUES ('0b10b2674464e7fe847419a098510844', 2, '6e3237cabde082a5910e799beabb1b92', 'ab5b8b82845168248a77cb3b4a7030f8', 2, 0, 0, 11000, 11000, 2, '', 0, 0, 0, '2019-11-26 17:05:50', '2019-11-26 20:45:20', 0, 1);
INSERT INTO `order` VALUES ('0b9c2cbeed603ea2db4caff0df3f7d28', 3, 'f955ba46c7700e218af38e8e5ef305ec', '14be9972393b3482b9a26694ae0333ca', 1, 0, 0, 10000, 10000, 2, '', 1, 3, 0, '2019-12-08 21:04:16', '2019-12-08 21:15:22', 4, 0);
INSERT INTO `order` VALUES ('0d8e0bb9f450aecab1c0f042cbeca0da', 4, '6e3237cabde082a5910e799beabb1b92', 'ab5b8b82845168248a77cb3b4a7030f8', 2, 0, 0, 11000, 11000, 3, '', 0, 5, 0, '2019-11-26 17:11:18', '2019-11-26 20:45:22', 1, 1);
INSERT INTO `order` VALUES ('1333c72c0ba69d238455459091896fa9', 3, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 50000, 50000, 2, '', 0, 0, 0, '2019-12-22 16:50:04', '2019-12-22 16:50:28', 0, 1);
INSERT INTO `order` VALUES ('14df4d1cd9db27707e8cc3464c1654f7', 6, 'bc469f79a6e9800ccdc15880f07f3291', 'e316f5803f9eaaeefcc33c31595cbb39', 2, 0, 0, 17000, 17000, 4, '加辣', 2, 2, 0, '2019-11-26 20:28:24', '2019-11-26 21:22:22', 2, 0);
INSERT INTO `order` VALUES ('1b0072224c719948b6ef52242ff0cbe8', 1, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 0, '2019-12-08 12:03:23', '2019-12-08 12:32:37', 4, 0);
INSERT INTO `order` VALUES ('1b9cad67000520e9dc7ee4915b398a23', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 5, 0, 0, 33000, 33000, 3, '', 0, 0, 0, '2019-12-17 16:47:11', '2019-12-17 16:47:25', 0, 1);
INSERT INTO `order` VALUES ('272601d8eb2f7938a74f4ccd11de61e1', 7, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 2, '', 0, 0, 0, '2019-12-22 17:22:46', '2019-12-22 17:37:09', 0, 1);
INSERT INTO `order` VALUES ('2b7f61baf3ee502749a477f467d9f732', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 16, 0, 0, 96000, 96000, 2, '', 0, 0, 0, '2019-12-22 16:48:54', '2019-12-22 16:49:55', 0, 1);
INSERT INTO `order` VALUES ('2d098f3c1c9a4ccbbbfc55a192aff487', 4, '06f9142749ab69be266e081e536f985e', 'c95ffd410cf167b66b1b4c5b87d2dffc', 1, 0, 0, 6000, 6000, 3, '', 0, 0, 0, '2019-12-01 15:16:20', '2019-12-01 15:16:42', 0, 1);
INSERT INTO `order` VALUES ('3618ffa6683ba1323efdcfb83b1e70f9', 1, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 6000, 6000, 2, '', 1, 3, 0, '2019-12-11 11:00:41', '2019-12-11 11:53:52', 4, 0);
INSERT INTO `order` VALUES ('3b3e2f9a9a52d396015692b757fa46a9', 3, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-11 12:36:15', '2019-12-11 12:59:29', 4, 0);
INSERT INTO `order` VALUES ('3b8ecb48625a411022d53448227d753a', 2, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '【这是订餐】多加点黄瓜丝！！！', 1, 1, 0, '2019-12-24 02:17:05', '2019-12-24 02:39:40', 2, 0);
INSERT INTO `order` VALUES ('3c80059fe9605468211d5cea533c92c4', 2, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 5, 0, 0, 31500, 31500, 2, '要两份饭就可以啦', 1, 3, 1, '2019-12-08 18:28:44', '2019-12-11 21:15:25', 5, 0);
INSERT INTO `order` VALUES ('4ca737c10fdd31812d67419919d51ec9', 2, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 1, 0, 0, 11000, 11000, 2, '', 0, 5, 0, '2019-12-03 17:28:05', '2019-12-03 17:43:05', 1, 0);
INSERT INTO `order` VALUES ('52668c37f295340b7c42f7d81cc34e96', 3, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 7000, 7000, 2, '', 1, 3, 0, '2019-12-10 20:01:04', '2019-12-11 12:25:53', 4, 0);
INSERT INTO `order` VALUES ('52773d10a55db67354f9fcee4e35d96d', 8, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 2, '', 0, 5, 0, '2019-12-22 19:29:56', '2019-12-22 19:59:38', 1, 1);
INSERT INTO `order` VALUES ('54bfa9ca16ba68c1bf8f175f9d519fc0', 2, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 6, 0, 0, 41000, 41000, 2, '', 0, 0, 0, '2019-12-22 16:49:24', '2019-12-22 16:49:54', 0, 1);
INSERT INTO `order` VALUES ('599799279161c2a5473b0bdd8797bd0a', 3, '8622f22a2277fa231b25bc7dd8a503f3', 'e06517213df695520227bea95cabea43', 3, 0, 0, 27000, 27000, 2, '', 1, 3, 0, '2019-12-06 16:44:48', '2019-12-07 14:30:41', 4, 0);
INSERT INTO `order` VALUES ('5e9dc42fb2156dc0a07afd3b85be2345', 6, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 2, '', 0, 0, 0, '2019-12-22 17:19:39', '2019-12-22 17:22:08', 0, 1);
INSERT INTO `order` VALUES ('63430834ed4f88c58ddb9425d044ad63', 2, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 0, '2019-12-13 14:35:31', '2019-12-14 22:54:07', 4, 0);
INSERT INTO `order` VALUES ('689d66a69ecd6536d709fc6609615805', 1, '8622f22a2277fa231b25bc7dd8a503f3', 'e06517213df695520227bea95cabea43', 3, 0, 0, 29000, 29000, 2, '两份米饭', 0, 5, 0, '2019-12-15 20:48:44', '2019-12-22 06:22:21', 1, 1);
INSERT INTO `order` VALUES ('68f2a3263d835146e92dd6cf22eac0e7', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 5, 0, 0, 35000, 35000, 2, '', 0, 5, 0, '2019-11-25 12:30:54', '2019-11-25 14:15:54', 1, 1);
INSERT INTO `order` VALUES ('6b617eff49e999e5747cc30ed6b8ed05', 2, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 1, 0, 0, 9000, 9000, 2, '', 0, 5, 0, '2019-12-10 17:38:49', '2019-12-22 06:22:25', 1, 1);
INSERT INTO `order` VALUES ('6c72b62f86b6089d5495d981c59fd652', 3, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 7, 0, 0, 41500, 41500, 2, '', 1, 3, 1, '2019-12-07 16:48:45', '2019-12-11 21:15:31', 5, 0);
INSERT INTO `order` VALUES ('71a1f3b3aa844b8771040de26878f4ee', 1, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 1, '2019-12-01 11:57:18', '2019-12-03 13:10:19', 5, 0);
INSERT INTO `order` VALUES ('71c1592c71deb974741f890eeb34531e', 3, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-03 17:28:25', '2019-12-04 23:06:24', 4, 0);
INSERT INTO `order` VALUES ('7529cc0a19973c4e70f1b41e8ad19a36', 1, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 1, 0, 0, 12000, 12000, 2, '', 1, 3, 0, '2019-11-27 16:47:30', '2019-12-04 16:28:02', 4, 0);
INSERT INTO `order` VALUES ('7dc4ebfe8f237993ff7662305975309c', 1, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 0, '2019-12-02 11:52:11', '2019-12-04 16:27:52', 4, 0);
INSERT INTO `order` VALUES ('7f4f78213a6d89cbcac54e5b260121e0', 2, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 12000, 12000, 2, '', 0, 5, 0, '2019-12-17 17:13:07', '2019-12-22 06:22:15', 1, 1);
INSERT INTO `order` VALUES ('818b42f76d28e4d40eb06a977822a351', 1, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 2, 0, 0, 17000, 17000, 2, '', 1, 3, 0, '2019-12-07 13:51:34', '2019-12-07 14:25:07', 4, 0);
INSERT INTO `order` VALUES ('8563ac0bd053af6f5a7a903c91fa9afc', 3, '06f9142749ab69be266e081e536f985e', 'c95ffd410cf167b66b1b4c5b87d2dffc', 2, 0, 0, 23000, 23000, 2, '', 0, 0, 0, '2019-12-01 15:15:33', '2019-12-01 15:16:09', 0, 1);
INSERT INTO `order` VALUES ('864b04f78f40dbfd63f2aa1e3e6e2e53', 4, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 2, 0, 0, 50000, 50000, 2, '', 0, 5, 0, '2019-12-22 16:50:52', '2019-12-22 17:24:04', 1, 1);
INSERT INTO `order` VALUES ('866fec4f5b8a8c4e2c21afb2bcc4bdf9', 1, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 11000, 11000, 2, '', 1, 3, 0, '2019-12-12 13:41:59', '2019-12-14 22:54:14', 4, 0);
INSERT INTO `order` VALUES ('8cb36471dac4ba4ba90856a384760db0', 5, 'bc469f79a6e9800ccdc15880f07f3291', 'e316f5803f9eaaeefcc33c31595cbb39', 3, 0, 0, 18000, 18000, 4, '', 2, 4, 0, '2019-11-26 20:20:56', '2019-11-26 20:45:25', 4, 1);
INSERT INTO `order` VALUES ('911af0e7e77b7b798a615fbd0ea1539f', 4, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 1, 0, 0, 12000, 12000, 2, '', 1, 3, 0, '2019-12-11 17:16:58', '2019-12-11 17:34:21', 4, 0);
INSERT INTO `order` VALUES ('9286c3538baba117ed2fed46309ccc34', 5, '06f9142749ab69be266e081e536f985e', 'c95ffd410cf167b66b1b4c5b87d2dffc', 1, 0, 0, 12000, 12000, 1, '', 0, 0, 0, '2019-12-01 15:17:06', '2019-12-01 15:17:31', 0, 1);
INSERT INTO `order` VALUES ('95c3f714cba10c0335395489495054b4', 2, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 7000, 7000, 2, '', 1, 2, 0, '2019-12-01 14:12:40', '2019-12-01 14:44:25', 3, 0);
INSERT INTO `order` VALUES ('972b87a88937670bcf2d384052d57a6f', 1, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-03 13:13:19', '2019-12-03 19:55:11', 4, 0);
INSERT INTO `order` VALUES ('9c3d5a56c90579da6f1dd7cf9c29b824', 2, '8622f22a2277fa231b25bc7dd8a503f3', 'e06517213df695520227bea95cabea43', 3, 0, 0, 29000, 29000, 2, '', 1, 3, 0, '2019-12-15 21:48:51', '2019-12-22 06:22:17', 4, 0);
INSERT INTO `order` VALUES ('b282e0cdfe9dd6e88eb73c7f2541e16d', 2, '8622f22a2277fa231b25bc7dd8a503f3', 'e06517213df695520227bea95cabea43', 2, 0, 0, 16000, 16000, 2, '', 1, 3, 0, '2019-12-11 12:21:30', '2019-12-11 12:59:30', 4, 0);
INSERT INTO `order` VALUES ('b2fac7565124ebcb1f294ea7334313e3', 5, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 3, 0, 0, 62000, 62000, 3, '', 0, 0, 0, '2019-12-22 17:11:44', '2019-12-22 17:22:09', 0, 1);
INSERT INTO `order` VALUES ('b79200d15703e7c46776c009c2af9340', 2, 'f3e0280aa31435a3df2c1d717dd671ca', 'ae48fd0d70dad0479689526613320f53', 2, 0, 0, 16000, 16000, 4, '', 2, 3, 0, '2019-12-04 19:58:17', '2019-12-04 23:06:15', 3, 0);
INSERT INTO `order` VALUES ('bf1c5da99373728a0f63b68312328791', 1, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 2, 0, 0, 16000, 16000, 2, '一份饭就可以啦 外卖放门口不用敲门 通知我一下送到了就可以了 谢谢～', 1, 3, 1, '2019-12-13 11:11:09', '2019-12-14 21:39:48', 5, 0);
INSERT INTO `order` VALUES ('c2b5f34ea81b31616cf877a42608b90d', 1, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-06 12:50:41', '2019-12-07 14:30:43', 4, 0);
INSERT INTO `order` VALUES ('cb6daa585e59d28519f7e014e24feafe', 5, 'de4d141b74de1acf2ba111aa3618dd83', '15a1d6c99ba5ff587dac68b47d47b21a', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 0, '2019-12-06 18:02:22', '2019-12-07 14:30:39', 4, 0);
INSERT INTO `order` VALUES ('cc11abe7a5f57fe43fd9a6e191381fb1', 2, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '请多一点黄瓜丝555', 1, 3, 1, '2019-11-25 13:22:36', '2019-11-25 16:42:11', 5, 0);
INSERT INTO `order` VALUES ('cc68f27ab724e3383b868cb3048f7eb5', 6, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 8000, 8000, 2, '', 1, 3, 1, '2019-12-01 17:32:22', '2019-12-03 13:10:16', 5, 0);
INSERT INTO `order` VALUES ('cdfa4cf9a3f111fee8eeaa436e9c0fd2', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 1, 0, 0, 10000, 10000, 4, '', 2, 4, 0, '2019-11-23 19:14:46', '2019-11-24 01:24:10', 4, 1);
INSERT INTO `order` VALUES ('ce0590cd24490058ae94d5e1aaa5623a', 1, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 4, 0, 0, 23000, 23000, 3, '', 1, 3, 1, '2019-12-04 16:11:57', '2019-12-04 17:19:05', 5, 0);
INSERT INTO `order` VALUES ('d9b0ba2c9e91b4f44910f43e337ff3f3', 4, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-07 20:06:24', '2019-12-07 20:32:52', 4, 0);
INSERT INTO `order` VALUES ('dac1c7867fa45f5cf0dbdbf8c72f4562', 3, '6e3237cabde082a5910e799beabb1b92', 'ab5b8b82845168248a77cb3b4a7030f8', 2, 0, 0, 11000, 11000, 2, '', 0, 5, 0, '2019-11-26 17:09:16', '2019-11-26 20:45:21', 1, 1);
INSERT INTO `order` VALUES ('e08bc6b3011a0edf58d4bc234f7830dd', 1, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '', 1, 3, 0, '2019-12-10 11:00:26', '2019-12-10 11:16:46', 4, 0);
INSERT INTO `order` VALUES ('e713d24bda9cd19f3efd9c0de58b0583', 1, '5c4f0b3d173724428b224b0d874f2a74', '8214ced91719e75c9b445b42ec8dbea5', 1, 0, 0, 9000, 9000, 2, '亲 多点黄瓜丝', 1, 3, 1, '2019-11-26 11:06:18', '2019-12-01 11:54:35', 5, 0);
INSERT INTO `order` VALUES ('ecb9c9845f919ea2895c4f0232554c77', 4, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 3, 0, 0, 28000, 28000, 2, '', 1, 3, 1, '2019-12-06 17:38:31', '2019-12-07 16:49:27', 5, 0);
INSERT INTO `order` VALUES ('f0cee1c99ead5eff33bc818a3505adff', 5, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 2, 0, 0, 15000, 15000, 2, '', 1, 3, 1, '2019-12-11 21:14:41', '2019-12-13 11:11:34', 5, 0);
INSERT INTO `order` VALUES ('f4e6f10f3f42f9460882c169c5330caf', 1, 'e187c6afd9ae890c0d81583848159748', '115713a7d2f3ed93a6a69a19beb45cd4', 4, 0, 0, 22500, 22500, 2, '要一份饭就可以啦 放在门口不用敲门 谢谢～', 1, 3, 0, '2019-12-14 21:39:28', '2019-12-14 22:54:03', 4, 0);
INSERT INTO `order` VALUES ('f88c633aa67289dbef8a2c2ff1fa0b97', 1, '4a6bb5a539ec498e2bf5117910af08e4', 'ef80e4fae51464af1b997fb449864d8f', 12, 0, 0, 92000, 92000, 2, '', 0, 0, 0, '2019-12-24 01:17:59', '2019-12-24 01:18:38', 0, 1);
INSERT INTO `order` VALUES ('f92f8ad1e05d5303ebfcc856008262f7', 2, '8622f22a2277fa231b25bc7dd8a503f3', 'e06517213df695520227bea95cabea43', 2, 0, 0, 17000, 17000, 2, '', 0, 5, 0, '2019-12-06 16:43:18', '2019-12-06 16:58:18', 1, 0);
INSERT INTO `order` VALUES ('fb4f8a4073992b517b525943d4b60c93', 3, '2f33af0d978c616a9ad0fbaffe5e7d5e', '60a3810d86f9f62c9905cba271f8f524', 2, 0, 0, 16000, 16000, 2, '', 1, 3, 0, '2019-11-25 15:09:53', '2019-11-25 22:37:23', 4, 0);

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
INSERT INTO `order_item` VALUES ('04e6dcdf8678563b6c819f3b8158fd9f', 'bf1c5da99373728a0f63b68312328791', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('06bec9d9d107b72e13b1a4ead8eb0d6f', '1b9cad67000520e9dc7ee4915b398a23', 'b27935ecc22c836f6f390883b47671b5', '韭菜鸡蛋', '/upload/images/9c377c15996b43ebb814cd23f46fe176.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('071f65a67c235a9e0780c06d6c2b7112', '54bfa9ca16ba68c1bf8f175f9d519fc0', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('0ee1131e52f14042dd83c5e55ec1cd43', '866fec4f5b8a8c4e2c21afb2bcc4bdf9', '3489f48cb26c95750813b55f0f54d0a8', '小鸡炖蘑菇', '/upload/images/3eae75f6bbc14db8939e52f126f42aa8.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('11adc81eeb0276993e77450d217ad0bf', '54bfa9ca16ba68c1bf8f175f9d519fc0', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('14d65e72e3005ff55d07cdbc5783bb58', '95c3f714cba10c0335395489495054b4', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('19ea4fe40e5f0dd7b7ca45ae1967e726', '5e9dc42fb2156dc0a07afd3b85be2345', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('1b35082d414d833c9cf22b2cda99e5fe', '6c72b62f86b6089d5495d981c59fd652', '62eaf34022a211ab237d4f45d12bc175', '腐竹红烧肉', '/upload/images/af1e6bd56e0e499da7065390efc7828c.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('1c5b2289a7652fcbed5c59e9d3aa0d99', '6c72b62f86b6089d5495d981c59fd652', '7c6d50fb3c0b6dbf8cd52b607a43d958', '茶派', '/upload/images/b7c16a7def5d410a95d6a0056f05d73a.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('1cbc2d6f00ffbc9c575175cbc36e6075', '68f2a3263d835146e92dd6cf22eac0e7', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('200e4266f425a1c8207b4550481debc2', '8cb36471dac4ba4ba90856a384760db0', '152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', '', 1000, 1);
INSERT INTO `order_item` VALUES ('20125e6ae11ea988d656e0e50ddb8a29', 'ecb9c9845f919ea2895c4f0232554c77', '212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', '/upload/images/0b7de416a4664e1bb6af94d35f237f1b.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('22d810d7cd4b2ca8e3b191f43fd50248', '0b10b2674464e7fe847419a098510844', 'a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', '/upload/images/6fc1f856876344b9812827687ff74d6f.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('22e79be95b9987db5ab635ce187c16b9', '3b3e2f9a9a52d396015692b757fa46a9', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('23be8ca95e22913c783fee45669e2702', 'dac1c7867fa45f5cf0dbdbf8c72f4562', 'a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', '/upload/images/6fc1f856876344b9812827687ff74d6f.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('26dda0814b5adb019a92ef14fe7bcef6', '71a1f3b3aa844b8771040de26878f4ee', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('27428b05cfdb7d2f0b2e96dfa936ec9a', '7529cc0a19973c4e70f1b41e8ad19a36', '861baf559ae479a7faadf125d2842fc2', '毛血旺', '/upload/images/6115d8e486864d0ebb7ab39a90c15b46.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('27a247cfa9c50de17017ba7c9aaf57a8', 'fb4f8a4073992b517b525943d4b60c93', '6e36a9126991fefc401d3799a923ca29', '酸辣土豆丝', '/upload/images/3190a579573c46c6bb397c2243f27619.jpg', 7000, 1);
INSERT INTO `order_item` VALUES ('280ed2b217a10caa923487fbefa741e4', 'f92f8ad1e05d5303ebfcc856008262f7', '959be79aec14a643c6bab1d08601866e', '四川回锅肉', '/upload/images/fd5d2dd62ee14b9d8b6646e68b3164b4.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('286b8e73c2a6ceed264dacfb4e7df29f', 'ce0590cd24490058ae94d5e1aaa5623a', '152c1cf26dac5cbcad6be1c9ce5b01a4', '米饭', '', 1000, 1);
INSERT INTO `order_item` VALUES ('2c2944ed951e5df9323977f63733008f', '3c80059fe9605468211d5cea533c92c4', '6441ac52955d6d6a59441879a7bb9fa9', '阿萨姆奶茶', '/upload/images/81b3223ec9f34d7c8c0ddf1973cf7351.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('2efd7d0d73b42bb691a6870bfe368435', '7f4f78213a6d89cbcac54e5b260121e0', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('2f4c64b04a69561007a3e265849af777', '9c3d5a56c90579da6f1dd7cf9c29b824', 'd7afc24ccd36da2139000aab9c78a0ae', '千叶红烧肉', '/upload/images/c103d7beb2834808b1eabf97cdd96134.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('2ff5b6a0907ca2d7593b68efebccc0d2', '9c3d5a56c90579da6f1dd7cf9c29b824', 'f9896e09387a6b91abca11146fe655a6', '沸腾牛五花', '/upload/images/be703e43142c4aec9b5885800cc6febd.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('30715ece8aabdca54706ba26eb1b0503', '2d098f3c1c9a4ccbbbfc55a192aff487', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('3537ef3ceca890ddf8b1697445140733', 'b282e0cdfe9dd6e88eb73c7f2541e16d', '7f1eeea4a086244907da5e8cfb303cb8', '皮蛋瘦肉粥', '/upload/images/e3a84e8fbfcb45a29f3f887c8433a695.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('3a53e771e58aadfff51fabe2edf6106c', '3618ffa6683ba1323efdcfb83b1e70f9', '170d701974dafba665b703d25c9958a2', '扬州炒饭', '/upload/images/9efcc63c53ef4abdb80d98ba9b6808d4.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('3a6ce940be769706c686757848730a04', '6c72b62f86b6089d5495d981c59fd652', '0f0c98951bcf7f5b5128dac43b62dfea', '可乐瓶装', '', 2500, 1);
INSERT INTO `order_item` VALUES ('3aeabe9441a2fed0d859176653c74e3b', '689d66a69ecd6536d709fc6609615805', 'f9896e09387a6b91abca11146fe655a6', '沸腾牛五花', '/upload/images/be703e43142c4aec9b5885800cc6febd.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('3e15fbed46a8c46984d2c1c793a0ce1f', '972b87a88937670bcf2d384052d57a6f', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('3e1f1e052a96a8c80d2e1f41fa1fbf49', 'f88c633aa67289dbef8a2c2ff1fa0b97', 'b0fda02a51cac7a08c76b9dbadbba693', '酸菜鱼', '/upload/images/150eadbcf4384d37a6c8cde2a1ece502.jpg', 15000, 1);
INSERT INTO `order_item` VALUES ('3e89bbf761c026aff964a77d325a0689', '00822a69cc5b0b0b09d3085c87930664', 'e8b9e920786822151845d68655dbe130', '肉末蒸蛋', '/upload/images/cf069a97f1b44d98838b61d51e08b90e.jpeg', 8000, 1);
INSERT INTO `order_item` VALUES ('3f2b1fab36658d81f66ca3d7d533d3ff', 'f88c633aa67289dbef8a2c2ff1fa0b97', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', 6000, 2);
INSERT INTO `order_item` VALUES ('41f88777e46b4a5adb2ef875575e0f81', '689d66a69ecd6536d709fc6609615805', '3be786d311bea4d1b6be7a1fc8fd498a', '水饺', '/upload/images/54194fe75f45457894179de26fc0b3ba.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('431396ad0b752d41121ef0e1a79f369f', 'fb4f8a4073992b517b525943d4b60c93', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('44f3c83ebfc6e5a8dc494bf1144afb62', 'f0cee1c99ead5eff33bc818a3505adff', '212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', '/upload/images/0b7de416a4664e1bb6af94d35f237f1b.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('4572f1bf59d2b77fe27e8f64e6df8c2f', 'f88c633aa67289dbef8a2c2ff1fa0b97', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', '/upload/images/603a255388d64bd2ac714c60ebc04a75.jpg', 7000, 2);
INSERT INTO `order_item` VALUES ('464097f5b6e420b991cfdfa3c9cff934', '2b7f61baf3ee502749a477f467d9f732', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('4683f905ecc63a20c88ca550c0b78e45', '6c72b62f86b6089d5495d981c59fd652', '1616e0e42ceb30d70ae916909cfe365e', '麻辣烫（麻辣香锅）加菜+虾仁 ', '/upload/images/8bab7ebe61204cefb3af7bbd6ec228f5.jpg', 2000, 1);
INSERT INTO `order_item` VALUES ('4882b79abd51a88669dfb50cea9ce633', '68f2a3263d835146e92dd6cf22eac0e7', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', '/upload/images/5f09c42f582e4ac58345fb7b9c36de24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('49b8227e8b21a9feb527c621cc4f001f', '52773d10a55db67354f9fcee4e35d96d', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('49d5450575a0acb0d7e90fd2a8270bef', '8563ac0bd053af6f5a7a903c91fa9afc', '5f361deb700360d98452d5b9632a40ab', '番茄炖牛腩', '/upload/images/7bf0f12d34144661937f5e55562b5d51.jpeg', 12000, 1);
INSERT INTO `order_item` VALUES ('4bee115872acb76d7b7eb5ee3b9a3e2a', '7dc4ebfe8f237993ff7662305975309c', 'c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', '/upload/images/cbff583b479c474eb270732abdd93566.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('4bfa48444e85ddcf9fe00f8e11597fee', '14df4d1cd9db27707e8cc3464c1654f7', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('51bc826dbfdee2653d94384fb8080165', 'cdfa4cf9a3f111fee8eeaa436e9c0fd2', 'bb08daaca3ff7e0cade0919a0cf7c544', '三鲜丸子', '/upload/images/37d064dcf3fe49b190637415121395f9.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('51fb9bd06717d27e5eed4d7891c8d7b0', '8cb36471dac4ba4ba90856a384760db0', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('52b1bb9bf0ef33c42eedd7c7b758e4b7', 'f92f8ad1e05d5303ebfcc856008262f7', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('5321b29bb07421546f3dfdad1704fd0c', 'cc68f27ab724e3383b868cb3048f7eb5', '8f3883855e6321762c324a8825312c59', '牛肉蛋炒饭', '', 8000, 1);
INSERT INTO `order_item` VALUES ('59aabe404f313d4839e8f886d446aceb', 'b79200d15703e7c46776c009c2af9340', 'a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', '/upload/images/6fc1f856876344b9812827687ff74d6f.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('5a502c3ee5c23048a69484acaf2d19b9', '1333c72c0ba69d238455459091896fa9', '33c0fa961f3598d9791a53089c21cdb5', '川香烤鱼(大份)', '/upload/images/b7b94af6d9a54108a777237b02f1ff55.jpg', 32000, 1);
INSERT INTO `order_item` VALUES ('5a61e6c0f3d613f268cf475a87b5e987', 'c2b5f34ea81b31616cf877a42608b90d', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('5fe01fd77f25db58e1c473542a95a070', '1b9cad67000520e9dc7ee4915b398a23', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('6034ab34fa17cee9ec0379927e3ec862', '3c80059fe9605468211d5cea533c92c4', '1616e0e42ceb30d70ae916909cfe365e', '麻辣烫（麻辣香锅）加菜+虾仁 ', '/upload/images/8bab7ebe61204cefb3af7bbd6ec228f5.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('61983898df41e289fd00601e903af905', '54bfa9ca16ba68c1bf8f175f9d519fc0', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', '/upload/images/5f09c42f582e4ac58345fb7b9c36de24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('62faa7114dc65347145a5e14f437250e', 'ce0590cd24490058ae94d5e1aaa5623a', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('63e7400a76ce07ba52d03c29e5d83f10', '599799279161c2a5473b0bdd8797bd0a', '650632fb49a091669f41fbfaaf2d53fb', '糖醋里脊', '/upload/images/665992d4c4814ee2bffb43b39e57b0f4.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('641bf9bbd908565a2b278328cf0df599', 'f0cee1c99ead5eff33bc818a3505adff', '1616e0e42ceb30d70ae916909cfe365e', '麻辣烫（麻辣香锅）加菜+虾仁 ', '/upload/images/8bab7ebe61204cefb3af7bbd6ec228f5.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('643e1cd68c5016a35c95529dffd48ea5', '68f2a3263d835146e92dd6cf22eac0e7', '5d4a3d59fbc7167e648f57c95c37e01e', '土豆红烧肉', '/upload/images/ef5768f2d9a344fd9494fa0fb1460653.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('677301a696d688b1f974d59496cc1c82', 'b282e0cdfe9dd6e88eb73c7f2541e16d', 'c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', '/upload/images/cbff583b479c474eb270732abdd93566.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('699e9a28453ed1f46bdd403876dbeb36', '52668c37f295340b7c42f7d81cc34e96', 'cea6ec8aad7077e1bcd23ba1abe0f1f8', '白菜豆腐汤', '/upload/images/d0af02accfd746c6848be92472e7cc5a.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('6b7681d82b2f9b806101c70def8add5a', '5e9dc42fb2156dc0a07afd3b85be2345', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('6cb5d8154193bf2a906a108d30c88470', 'b2fac7565124ebcb1f294ea7334313e3', '84a646a8629a3c7585035b776153b98d', '红烧鲤鱼', '/upload/images/4c7493c854bd4157a384ae6c6562c974.jpg', 15000, 1);
INSERT INTO `order_item` VALUES ('6ce497696c299bea728e78b9c0e1fab0', 'ce0590cd24490058ae94d5e1aaa5623a', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('6e3e800f5e36bad1b0c4d0d3c45c621f', '1b9cad67000520e9dc7ee4915b398a23', '3cc50f52f9c51061b2f69d7535ee1a2a', '地三鲜', '/upload/images/87d0b06b87d942f0a28d282a961420fd.jpg', 7000, 1);
INSERT INTO `order_item` VALUES ('70c3eb4a29a6bc5283791dfc476b152d', '4ca737c10fdd31812d67419919d51ec9', 'abe3a6acc45ecc03f3c8cafd74251f9f', '麻辣牛肚 ', '/upload/images/d7b865dd34e24232bfd4756bbf044a9f.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('70f7a47060cb09bd92a6421e66650307', '54bfa9ca16ba68c1bf8f175f9d519fc0', '05c71e0ca83929eb3577ccc60c55cc33', '素三丝', '/upload/images/221f760731484f33adf05892709eee00.jpg', 7000, 1);
INSERT INTO `order_item` VALUES ('745e96279bf0a897851a92a84bb4dc05', '0b9c2cbeed603ea2db4caff0df3f7d28', '9cba928b7b27de0456a74e966796e205', '黄菜烧牛肉', '', 10000, 1);
INSERT INTO `order_item` VALUES ('798a4b97cb95f1c78e1c0cd162b762d6', '3c80059fe9605468211d5cea533c92c4', '212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', '/upload/images/0b7de416a4664e1bb6af94d35f237f1b.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('7fc7c62ce7f51ee3f64b32c7f4b03ba9', 'd9b0ba2c9e91b4f44910f43e337ff3f3', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('851740399ad1ec824ec283be8c4e6ab2', 'dac1c7867fa45f5cf0dbdbf8c72f4562', '54a33ae4ef409d42dd196a26f30d2c0f', '可乐罐装', '/upload/images/a3b4a6d001a54891a0ab4db2e43b1dbe.jpg', 2000, 1);
INSERT INTO `order_item` VALUES ('864c2a5c57fd5adef14a87f00de739e2', '6c72b62f86b6089d5495d981c59fd652', '05444437734b79d597b8fff2c8fecd21', '麻辣烫（麻辣香锅）加菜+牛肉卷', '/upload/images/638d1959b8bf4bd5a5486e2c2a898c14.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('895942e770c35367dc89c14506620cc8', '2b7f61baf3ee502749a477f467d9f732', 'a5aab1591e53f67d13cf9a683114842a', '豆芽粉丝', '', 6000, 3);
INSERT INTO `order_item` VALUES ('8d101791193d6b8f3864d697c0327fe4', 'f88c633aa67289dbef8a2c2ff1fa0b97', '67e6c3d6f4142e4df205e0d5b917acaa', '水煮鱼', '/upload/images/22da6d92c1c34ce8ad5b441714aa0b7a.jpg', 15000, 1);
INSERT INTO `order_item` VALUES ('8de098969938e565aef9556c1c7f5b7f', '818b42f76d28e4d40eb06a977822a351', '650632fb49a091669f41fbfaaf2d53fb', '糖醋里脊', '/upload/images/665992d4c4814ee2bffb43b39e57b0f4.jpeg', 10000, 1);
INSERT INTO `order_item` VALUES ('93024194d5a6e6dd5729a72f33d95f71', 'f88c633aa67289dbef8a2c2ff1fa0b97', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('934d39d0056a8fe5ce85984f92747077', 'b2fac7565124ebcb1f294ea7334313e3', '33c0fa961f3598d9791a53089c21cdb5', '川香烤鱼(大份)', '/upload/images/b7b94af6d9a54108a777237b02f1ff55.jpg', 32000, 1);
INSERT INTO `order_item` VALUES ('9793874fa608b15adb915f0c741ec5ea', 'cb6daa585e59d28519f7e014e24feafe', '7c066108cc4c0877750d163c32b3ef22', '青椒土豆丝', '/upload/images/a0fd056983ed423bbb1ec07822f40bdf.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('97a65a894cb0eb4db97da63470c9e7e4', '54bfa9ca16ba68c1bf8f175f9d519fc0', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('9a19f40a250c6ca656e6252eb0ed884a', '3c80059fe9605468211d5cea533c92c4', 'c88499d0ad3d4f3d5a214f87617b582f', '水煮肉片', '/upload/images/f6cce8c6dc174bafa1458c82d16f26c7.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('9db92469236d570a1a12671ea99e367a', '14df4d1cd9db27707e8cc3464c1654f7', '959be79aec14a643c6bab1d08601866e', '四川回锅肉', '/upload/images/fd5d2dd62ee14b9d8b6646e68b3164b4.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('9eab5c9d5a977dcc34612b5267917055', '599799279161c2a5473b0bdd8797bd0a', '959be79aec14a643c6bab1d08601866e', '四川回锅肉', '/upload/images/fd5d2dd62ee14b9d8b6646e68b3164b4.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('a2c56abd99f8a42e36be56409fac47e3', '6c72b62f86b6089d5495d981c59fd652', '212d9939e2a3414d63f78e3c8bebb7d4', '麻辣香锅', '/upload/images/0b7de416a4664e1bb6af94d35f237f1b.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('a646eae020d1e260fd388a107fb889ff', '272601d8eb2f7938a74f4ccd11de61e1', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('a9d904dbc7412e03fcb6f64861c8530a', '8cb36471dac4ba4ba90856a384760db0', '959be79aec14a643c6bab1d08601866e', '四川回锅肉', '/upload/images/fd5d2dd62ee14b9d8b6646e68b3164b4.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('abaf2722b64f360a11f182808e22b9a8', '0d8e0bb9f450aecab1c0f042cbeca0da', 'a6f5b086db3c55c4e4e0ad483249c23b', '牛肉面', '/upload/images/6fc1f856876344b9812827687ff74d6f.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('ac597e6b76d744ee129ed5a2d42a7168', '9286c3538baba117ed2fed46309ccc34', 'cea32d01e13c056a448f4b2d8bcd12b9', '酸汤肥牛', '/upload/images/01d3cc59dd7d4fffb9ad54dd9cef148a.jpg', 12000, 1);
INSERT INTO `order_item` VALUES ('ac8b967910666ed6da3167c61185d4c2', '1b9cad67000520e9dc7ee4915b398a23', '72e0fc62ce10c04e4c01afae5f94a217', '番茄鸡蛋', '/upload/images/39f81bfd6b524078ae4c679a88ff6819.jpeg', 7000, 1);
INSERT INTO `order_item` VALUES ('acfe68304492350319edcebd90f52db2', '818b42f76d28e4d40eb06a977822a351', '3e416cfb3cdff1547842d4981afe818d', '鱼香茄子', '/upload/images/f7ccb2c2499445cf95aebaf1eee23e1e.jpg', 7000, 1);
INSERT INTO `order_item` VALUES ('b05f4701961adf6febfcf2a8b8195397', 'b2fac7565124ebcb1f294ea7334313e3', 'cefa589d9c3eb87470427cb022f28367', '糖醋鲤鱼', '/upload/images/6ba3687640aa45c28ce76535283f0ec4.jpg', 15000, 1);
INSERT INTO `order_item` VALUES ('b3c77fcf83d7ec3a84ba8aea9daa0791', '0b10b2674464e7fe847419a098510844', '54a33ae4ef409d42dd196a26f30d2c0f', '可乐罐装', '/upload/images/a3b4a6d001a54891a0ab4db2e43b1dbe.jpg', 2000, 1);
INSERT INTO `order_item` VALUES ('b440885255bd6db1dd753cc112fa9e88', '3b8ecb48625a411022d53448227d753a', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('b51f8d29c1c2dc900b0b6d264aededbf', 'f4e6f10f3f42f9460882c169c5330caf', '85b4cbb5f09b087c27a06e5527ebe039', '青椒肉丝', '/upload/images/012407466ef5498d9f8a810ceabf921b.jpg', 10000, 1);
INSERT INTO `order_item` VALUES ('b5aa019fdfca1d9f22ac18e7d1c1bd88', 'f4e6f10f3f42f9460882c169c5330caf', '7c6d50fb3c0b6dbf8cd52b607a43d958', '茶派', '/upload/images/b7c16a7def5d410a95d6a0056f05d73a.jpg', 3000, 1);
INSERT INTO `order_item` VALUES ('b5acba22e3b2a720e1a0d11c15d244d4', '68f2a3263d835146e92dd6cf22eac0e7', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('b901b86f5a69f678bdc1abf3adee9b14', '6c72b62f86b6089d5495d981c59fd652', 'c737174b97812ffec5c54f2a6461bf80', '鱼香肉丝', '/upload/images/cbff583b479c474eb270732abdd93566.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('bb479d67ae8c8ac118715b77434a7cd2', 'f88c633aa67289dbef8a2c2ff1fa0b97', 'da70b2f737737e8de82a8a5c108f6e55', '糖拌西红柿', '/upload/images/5f09c42f582e4ac58345fb7b9c36de24.jpeg', 6000, 5);
INSERT INTO `order_item` VALUES ('bbd3ca746fc617361547c12521b21e5e', '9c3d5a56c90579da6f1dd7cf9c29b824', '3be786d311bea4d1b6be7a1fc8fd498a', '水饺', '/upload/images/54194fe75f45457894179de26fc0b3ba.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('bc7feced2a8726c3de94484e2b17812b', '6b617eff49e999e5747cc30ed6b8ed05', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('be6e95c71904586f491b18c95f38797b', 'f4e6f10f3f42f9460882c169c5330caf', '13daa7235fc8c0ff68eb503223fe743f', '冰红茶', '', 2500, 1);
INSERT INTO `order_item` VALUES ('c3db8cf56a6667bb27360ad498bb77c5', '68f2a3263d835146e92dd6cf22eac0e7', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('c642e759d3138f156ea196c85f2f72a9', '599799279161c2a5473b0bdd8797bd0a', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('ce2c166f6eb854bbe1ebf4cdc15d6ca7', 'ecb9c9845f919ea2895c4f0232554c77', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('cf9b7e813dae84e444713251b94bdf0f', '8563ac0bd053af6f5a7a903c91fa9afc', '5d4a3d59fbc7167e648f57c95c37e01e', '土豆红烧肉', '/upload/images/ef5768f2d9a344fd9494fa0fb1460653.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('d053f187339a522f28cf8692bda53bfe', '2b7f61baf3ee502749a477f467d9f732', '3291e2ad095c3354b82e33e9dbb4b7ad', '烧溜豆腐', '/upload/images/f656ab969be54c45b30fcab4cc9ab340.jpg', 6000, 4);
INSERT INTO `order_item` VALUES ('d0d25d708d911d872a1b718b6b2a0fdc', 'cc11abe7a5f57fe43fd9a6e191381fb1', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('d106631cd04ba7f4f01a516cabc168b2', 'ce0590cd24490058ae94d5e1aaa5623a', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('d13f735ac82a83a30f43f981ea809d3a', 'b79200d15703e7c46776c009c2af9340', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('d1a9474456665a065ec8a8d8166591ee', '3c80059fe9605468211d5cea533c92c4', '9daf4687b855ed61ba74f8115ff792b5', '瓶装雪碧', '', 2500, 1);
INSERT INTO `order_item` VALUES ('da216ee4e3a7aaeb783a1217c8dd0ebe', '7f4f78213a6d89cbcac54e5b260121e0', '2d912f7f177635c9cb0e6a28e5e4439e', '松花蛋豆腐', '/upload/images/29667a1b3744476fbb647c07165c8e6b.jpg', 6000, 1);
INSERT INTO `order_item` VALUES ('db425e1919e1299bf0a7af91a2eb13e4', '2b7f61baf3ee502749a477f467d9f732', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 8);
INSERT INTO `order_item` VALUES ('dbc11de60606412af81af50102b75478', '54bfa9ca16ba68c1bf8f175f9d519fc0', 'ec3524e703e115ef0d3f8e07340d218f', '炝拌土豆丝', '/upload/images/603a255388d64bd2ac714c60ebc04a75.jpg', 7000, 1);
INSERT INTO `order_item` VALUES ('dcc3d7a113886683e0d060729302ddce', 'f4e6f10f3f42f9460882c169c5330caf', 'cd69a09bd278dc062392d62407c525b0', '肉末茄子', '', 7000, 1);
INSERT INTO `order_item` VALUES ('dda61c880e6e47a5a114693482017be6', '1333c72c0ba69d238455459091896fa9', '652a6464943a81c0f5fdfd06e4affd0e', '川香烤鱼', '/upload/images/1a013c1bc8ed4de8a326c84b1b4a7bc6.jpg', 18000, 1);
INSERT INTO `order_item` VALUES ('de00885fb531105404b389a9655fc316', '911af0e7e77b7b798a615fbd0ea1539f', 'a9845eb443b77479459a765f3038ebfc', '水煮牛肉', '', 12000, 1);
INSERT INTO `order_item` VALUES ('e15686414be21dc4d16d358f605de3e0', '864b04f78f40dbfd63f2aa1e3e6e2e53', '33c0fa961f3598d9791a53089c21cdb5', '川香烤鱼(大份)', '/upload/images/b7b94af6d9a54108a777237b02f1ff55.jpg', 32000, 1);
INSERT INTO `order_item` VALUES ('e45ff30123834e686116ce0ed2f56201', 'bf1c5da99373728a0f63b68312328791', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('e6ffd86e3f8f37481b51eeab7500e13a', 'ecb9c9845f919ea2895c4f0232554c77', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('ea6c8a575724e3feda4419ca8c283282', '689d66a69ecd6536d709fc6609615805', 'd7afc24ccd36da2139000aab9c78a0ae', '千叶红烧肉', '/upload/images/c103d7beb2834808b1eabf97cdd96134.jpg', 11000, 1);
INSERT INTO `order_item` VALUES ('eecf2f203658980cf716175c971bafd3', '71c1592c71deb974741f890eeb34531e', 'facffddf7c0cf642c8498f139b7f3315', '蒜薹炒肉', '/upload/images/ad29f51263ce46f688275054a63da3c8.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('f1440a496433e32d5c7d92247a9d681b', '63430834ed4f88c58ddb9425d044ad63', '7f1eeea4a086244907da5e8cfb303cb8', '皮蛋瘦肉粥', '/upload/images/e3a84e8fbfcb45a29f3f887c8433a695.jpg', 8000, 1);
INSERT INTO `order_item` VALUES ('f24853ca5d6651d40a5b79437f4a1bed', 'e713d24bda9cd19f3efd9c0de58b0583', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('f44165739677e6f95df5680576135ee8', '1b9cad67000520e9dc7ee4915b398a23', '470a11753727382cca018843c7a5e49c', '凉拌腐竹', '/upload/images/055af92c9ff64f8f8d25e42f2644fc24.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('f82445960d8dde812a3d3cf3e2cffb31', '272601d8eb2f7938a74f4ccd11de61e1', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('f9138de7207c4729bdaf919e7907a3ab', '864b04f78f40dbfd63f2aa1e3e6e2e53', '652a6464943a81c0f5fdfd06e4affd0e', '川香烤鱼', '/upload/images/1a013c1bc8ed4de8a326c84b1b4a7bc6.jpg', 18000, 1);
INSERT INTO `order_item` VALUES ('f91cc5832dfa2265ffbef019a9ef1398', '0d8e0bb9f450aecab1c0f042cbeca0da', '54a33ae4ef409d42dd196a26f30d2c0f', '可乐罐装', '/upload/images/a3b4a6d001a54891a0ab4db2e43b1dbe.jpg', 2000, 1);
INSERT INTO `order_item` VALUES ('fa3c94ccdb708fd8d922a1b12e8079f8', 'e08bc6b3011a0edf58d4bc234f7830dd', '551bce6cad97153d9220cfdcaba51325', '口水鸡', '/upload/images/080a67ed6ac749d385f4c2954fb551a3.jpg', 9000, 1);
INSERT INTO `order_item` VALUES ('fc9bd4b2eb218313bf09239ea2d17928', '52773d10a55db67354f9fcee4e35d96d', '761b1144c87e8be15d8ab3356c71556b', '蒜蓉黄瓜', '/upload/images/73118b1fa1d2493495c28bdce092a8d7.jpeg', 6000, 1);
INSERT INTO `order_item` VALUES ('fe176fa34228fc78ba560ae853bc4e02', '1b0072224c719948b6ef52242ff0cbe8', '49b46e756cfb5a576b853f8ee871f607', '虾仁玉米', '/upload/images/979e51cc30914e8289d2e2bd90c433b7.jpg', 8000, 1);

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
INSERT INTO `setting` VALUES ('d45537897868f12f36558fc24f425453', 'goods_page_tags', 'system', '免费配送,无起送费');
INSERT INTO `setting` VALUES ('389e2080d5fb9069c33638e89cbbdad8', 'store_address_x', 'store', '126.9357696');
INSERT INTO `setting` VALUES ('f4571b0a5786c8bd8e95e48322ccd1c3', 'average_express_time', 'express', '10');
INSERT INTO `setting` VALUES ('457771d67923c363829f127db8f0021b', 'max_express_distance', 'express', '4000');
INSERT INTO `setting` VALUES ('815243cd4f1dd35776b0b6d231519c8f', 'base_express_time', 'express', '25');
INSERT INTO `setting` VALUES ('1c18f17f34d321d5ecf0b04012674597', 'courier_count', 'express', '1');
INSERT INTO `setting` VALUES ('a571deb0a24ba2af5ed4686b64a5f792', 'store_open_date', 'store', '3,6,7,1,4,5');
INSERT INTO `setting` VALUES ('2d5050c2cde8ddc6e93bba51f822bb18', 'store_address_y', 'store', '37.5586305');
INSERT INTO `setting` VALUES ('697c4cc50394e24ef88c52e6c7cc4778', 'store_address', 'store', '창천동 52-31');
INSERT INTO `setting` VALUES ('d898a0d388c7ec6fe9567f035631ea7e', 'store_close_time', 'store', '2019-11-15T13:10:00.000Z');
INSERT INTO `setting` VALUES ('7a47a582b317f77a9c391dcf0c144a8e', 'store_open_time', 'store', '2019-11-14T15:00:01.000Z');

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
INSERT INTO `user` VALUES ('06f9142749ab69be266e081e536f985e', NULL, '小恐龙', 0, 'o9UA_5Sjl9ocBp4UAPC_T3S6PcYk', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLV1oDkdJV22RRx5AGFj1Hdu5veK5CD0pS04ibaf7FjYib8VGODWBDxaLmGKhjw2RN9aZtjHM8WJRaA/132', NULL, 1, 1, '2019-12-23 10:31:28', '2019-12-01 15:10:33', '2019-12-23 10:31:29', 4, 0, NULL);
INSERT INTO `user` VALUES ('1662483342a62408e036fe8f0cc264a5', NULL, NULL, 0, 'o9UA_5elCBkHgT1C6OAPG0L-Ix04', NULL, NULL, 0, 1, '2019-12-07 15:37:53', '2019-12-07 15:37:37', '2019-12-07 15:37:52', 1, 0, NULL);
INSERT INTO `user` VALUES ('1d14c476a0aad1047ce9506aacadf5bb', NULL, 'S-36ae', 0, 'o9UA_5clW_0iShh2q-Ard48bdVxA', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI6ItSlxNUIOLWdDIttPQoYg44nGIZib0CmibiaWDib617pQRbIJZbic7HB9TVzWkqFaIaaYoQuyaFhkOQ/132', NULL, 2, 1, '2019-12-18 14:31:49', '2019-12-18 14:31:41', '2019-12-18 14:31:54', 2, 0, NULL);
INSERT INTO `user` VALUES ('1ec05cf9d97a71c84b472472ed3e602b', NULL, NULL, 0, 'o9UA_5UxzruKl29wMfLTdsz2A1Is', NULL, NULL, 0, 1, '2019-12-22 02:06:48', '2019-11-25 11:15:31', '2019-12-22 02:06:48', 4, 0, NULL);
INSERT INTO `user` VALUES ('2394589754f574865cde521525d99ca9', NULL, 'zengzeng', 0, 'o9UA_5TDTkQWf_FL-Qk3xKk8FS5A', 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELquHETEnaZ4paO7jh7XKia7pLqm6fL1bhVdIzxqeu1kK3axPqYSaVXmQyTI9kqXLE8ibDYwwOn8tOg/132', NULL, 0, 1, '2019-12-17 15:23:26', '2019-11-25 16:55:06', '2019-12-17 15:23:26', 8, 0, NULL);
INSERT INTO `user` VALUES ('2f33af0d978c616a9ad0fbaffe5e7d5e', NULL, 'ྀི', 0, 'o9UA_5XESIn3x3Qz-kYz_fvrOI4w', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJZicRP0FZ78kV8u6fKBmHUjHQRugYwviaCno5mz64XvGvOYwqeeV5KLHbYw8UcHTLIxS5ZLEwPTzxQ/132', NULL, 2, 1, '2019-12-10 17:36:17', '2019-11-25 11:14:07', '2019-12-10 17:36:18', 6, 0, NULL);
INSERT INTO `user` VALUES ('442faaa72bf702b2f1e108c830ec558b', NULL, 'A-川香苑(新村店)', 0, 'o9UA_5dmfJvck2wZPswk0FuaY4E4', 'https://wx.qlogo.cn/mmopen/vi_32/IQhia6bnF9m3VcqIxJRIr1wp8RsMpgibtbibLNwW8tNtmqGbgbql7fibrmpiaj6wk2B0Via7icr62UASR7ib7dXB2oEOGA/132', NULL, 1, 1, '2019-12-23 15:27:23', '2019-11-24 19:30:44', '2019-12-23 15:27:23', 42, 0, NULL);
INSERT INTO `user` VALUES ('49268c005a631e2d77b7b90a206fe0eb', 'test', NULL, 0, NULL, 'http://www.baidu.com', NULL, 0, 1, NULL, '2019-10-06 03:34:32', '2019-11-15 02:03:16', 0, 1, NULL);
INSERT INTO `user` VALUES ('4a6bb5a539ec498e2bf5117910af08e4', NULL, 'Jeremy.', 0, 'o9UA_5abDk-kn7KSaMAoriIlvg6c', 'https://wx.qlogo.cn/mmopen/vi_32/UL02ia2qHNyA6UvWNf2Yia5KMOxAh4Kp6icf2ibSOWMiaP8iadSoGgEdE5vbDENG2GVCu97ics161tgrl2cAoGBmX4acg/132', NULL, 1, 1, '2019-12-14 12:31:59', '2019-10-14 23:39:12', '2019-12-14 12:32:00', 95, 0, NULL);
INSERT INTO `user` VALUES ('50808dec1eb8efb2f98e147d9fadb85d', NULL, NULL, 0, 'o9UA_5XYFSr3ZLrlho1PrK0NOeKQ', NULL, NULL, 0, 1, '2019-11-25 15:39:36', '2019-11-25 15:39:28', '2019-11-25 15:39:35', 1, 0, NULL);
INSERT INTO `user` VALUES ('51078b98cd5e1d3619e7bf2057fddfd1', NULL, NULL, 0, 'o9UA_5SQADmgF9mO-0h7Gzl_clAI', NULL, NULL, 0, 1, '2019-12-04 16:26:57', '2019-11-25 15:44:26', '2019-12-04 16:26:57', 2, 0, NULL);
INSERT INTO `user` VALUES ('5c4f0b3d173724428b224b0d874f2a74', NULL, '钟종维유翰한', 0, 'o9UA_5UFRtpiqnLprLecAzknYCnA', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIQNmXcDC2icepOCVWbricJacVK0x7jVIZoZwhGhhO6tJA5H9GUic0vEOvN27icuQ48aZJ6EIm0fJOAmQ/132', NULL, 1, 1, '2019-12-22 12:32:15', '2019-11-25 13:16:38', '2019-12-22 12:32:15', 8, 0, NULL);
INSERT INTO `user` VALUES ('6157721ea097834ca8502a707e518dcc', NULL, '感觉才才萌萌哒', 0, 'o9UA_5TRxLFe0PnsTbeutriJ569M', 'https://wx.qlogo.cn/mmopen/vi_32/p4rFoMFGBKZTIB9ibbM9Yo3RLTg8rDgfOiaavDHKuNjtNaN3AKzHWrwagmYmoBqF3pSD520TDFFkXVFhRo1cSgbQ/132', NULL, 2, 1, '2019-10-29 16:29:28', '2019-10-18 02:07:04', '2019-10-29 16:29:28', 4, 0, NULL);
INSERT INTO `user` VALUES ('6e3237cabde082a5910e799beabb1b92', NULL, 'JOKER', 0, 'o9UA_5W0vHfTzYZO5hlhSrx3sLUA', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJuajrV3CBmZYT31He6FjhZbJopoNiafxfuCJ9kUibqRStevmaVlSJVx2HibsgIeS0FcxXf5uciacXk6Q/132', NULL, 2, 1, '2019-11-25 11:17:57', '2019-11-25 11:17:32', '2019-11-25 11:18:33', 2, 0, NULL);
INSERT INTO `user` VALUES ('8622f22a2277fa231b25bc7dd8a503f3', NULL, NULL, 0, 'o9UA_5eDDl0SF5_Ukh5Lyyo8ulzs', NULL, NULL, 0, 1, '2019-12-21 16:31:56', '2019-12-06 16:39:44', '2019-12-21 16:31:55', 4, 0, NULL);
INSERT INTO `user` VALUES ('b30bf4d40012872e1a1b77e9ef2c86c0', NULL, '司马静', 0, 'o9UA_5SXyrh3p4ivHuxs_Q4EuwGQ', 'https://wx.qlogo.cn/mmopen/vi_32/EwB74dR4cgokXEFThO90sibibeSSQcXIPGyxRGdW8ly3Eqv6TvxECqzdeyt99fEKOqlL7hkmBNE3jxrkvBmhiaxVQ/132', NULL, 2, 1, '2019-12-22 09:39:59', '2019-12-18 23:59:18', '2019-12-22 09:39:59', 4, 0, NULL);
INSERT INTO `user` VALUES ('bc469f79a6e9800ccdc15880f07f3291', NULL, NULL, 0, 'o9UA_5REB4julZOp1mK74VWHuOso', NULL, NULL, 0, 1, '2019-11-26 20:14:28', '2019-11-26 20:14:21', '2019-11-26 20:14:28', 1, 0, NULL);
INSERT INTO `user` VALUES ('dc374a1ddd7a1d7ab8a2fbf6b6a55b44', NULL, NULL, 0, 'o9UA_5ajp7yGtVs76vQF_aVkBzMg', NULL, NULL, 0, 1, '2019-12-08 08:15:25', '2019-11-26 11:32:55', '2019-12-08 08:15:24', 1, 0, NULL);
INSERT INTO `user` VALUES ('de4d141b74de1acf2ba111aa3618dd83', NULL, NULL, 0, 'o9UA_5QC-lQSoBD4kgFiMK6z7LXE', NULL, NULL, 0, 1, '2019-12-06 17:55:58', '2019-11-25 13:24:22', '2019-12-06 17:55:58', 3, 0, NULL);
INSERT INTO `user` VALUES ('e187c6afd9ae890c0d81583848159748', NULL, 'ㅇ', 0, 'o9UA_5blcG04JkesYeTQkIsrRSvw', 'https://wx.qlogo.cn/mmopen/vi_32/kfX48rv1hTvHCicibTykTyN1AxRAL760T0KrFZslaDp2umFic0mXcA1rQ3J7icpOUUMt5bb0Z7Acic13AWB7XSrKBNg/132', NULL, 2, 1, '2019-12-19 08:24:00', '2019-12-04 04:55:31', '2019-12-19 08:24:00', 8, 0, NULL);
INSERT INTO `user` VALUES ('f3e0280aa31435a3df2c1d717dd671ca', NULL, '芒果果多肉🥭🥭', 0, 'o9UA_5cV9nkiVbt1XmcvlR36_bA4', 'https://wx.qlogo.cn/mmopen/vi_32/nzrxub4gHlCVQ6uw4kicSdPW83CkrX2lbM1lFTDgJUico1eSEwwnWicS1EmicPJfccU27d2Flicx9mAhgxTZzR8so8A/132', NULL, 2, 1, '2019-12-14 11:04:39', '2019-11-25 12:16:49', '2019-12-14 11:04:40', 7, 0, NULL);
INSERT INTO `user` VALUES ('f955ba46c7700e218af38e8e5ef305ec', NULL, NULL, 0, 'o9UA_5QQpnR9fj9HcYKyfXmzCbAM', NULL, NULL, 0, 1, '2019-12-08 21:01:12', '2019-11-25 14:02:50', '2019-12-08 21:01:12', 2, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
