/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : t_activiti

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 24/04/2019 14:20:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for SBA_LEAVE_BILL
-- ----------------------------
DROP TABLE IF EXISTS `SBA_LEAVE_BILL`;
CREATE TABLE `SBA_LEAVE_BILL`  (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(30) NULL DEFAULT NULL,
  `TITLE` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CONTENT` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DAYS` int(11) NULL DEFAULT NULL,
  `STATUS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LEAVE_TIME` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for SBA_USER
-- ----------------------------
DROP TABLE IF EXISTS `SBA_USER`;
CREATE TABLE `SBA_USER`  (
  `ID` bigint(30) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PASSWORD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SUPERIOR_ID` bigint(30) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of SBA_USER
-- ----------------------------
INSERT INTO `SBA_USER` VALUES (1, 'userA', '123', 2);
INSERT INTO `SBA_USER` VALUES (2, 'userB', '123', 3);
INSERT INTO `SBA_USER` VALUES (3, 'userC', '123', NULL);

SET FOREIGN_KEY_CHECKS = 1;
