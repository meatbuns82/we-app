/*
 Navicat Premium Data Transfer

 Source Server         : phone-home
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 192.168.233.128:3306
 Source Schema         : family-menu-app

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 26/09/2023 10:48:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cook_detail
-- ----------------------------
DROP TABLE IF EXISTS `cook_detail`;
CREATE TABLE `cook_detail`  (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `cook_name` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                `cook_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜的编码',
                                `main_img_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                `cook_step` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜的步骤',
                                `usage_fees` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '食材用料及份数',
                                `tip` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
                                `create_time` datetime(0) NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cook_detail_img_content
-- ----------------------------
DROP TABLE IF EXISTS `cook_detail_img_content`;
CREATE TABLE `cook_detail_img_content`  (
                                            `id` int(11) NOT NULL AUTO_INCREMENT,
                                            `img_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片的编码',
                                            `img_blob` longblob NULL COMMENT '图片的base64编码',
                                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cook_order
-- ----------------------------
DROP TABLE IF EXISTS `cook_order`;
CREATE TABLE `cook_order`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `cook_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                               `create_time` datetime(0) NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for food_detail_overview
-- ----------------------------
DROP TABLE IF EXISTS `food_detail_overview`;
CREATE TABLE `food_detail_overview`  (
                                         `id` int(11) NOT NULL AUTO_INCREMENT,
                                         `cook_detail_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜名',
                                         `cook_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜的编码',
                                         `cook_ingredient` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜的配料',
                                         `img` longblob NULL COMMENT '菜的缩略图',
                                         `food_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                         `cook_link` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细链接',
                                         `enable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用  0 是，1 否',
                                         `create_time` datetime(0) NULL DEFAULT NULL,
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for food_kind
-- ----------------------------
DROP TABLE IF EXISTS `food_kind`;
CREATE TABLE `food_kind`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT,
                              `food_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食物的名称',
                              `food_kind` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食物的种类',
                              `food_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食物的类型',
                              `food_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '食物的类型编码',
                              `food_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                              `enable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否启用 0 否，1 是',
                              `create_time` datetime(0) NULL DEFAULT NULL,
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
