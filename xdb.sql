/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80026
Source Host           : localhost:3306
Source Database       : xdb

Target Server Type    : MYSQL
Target Server Version : 80026
File Encoding         : 65001

Date: 2023-10-08 13:02:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for x_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_menu`;
CREATE TABLE `x_menu` (
  `menu_id` int NOT NULL AUTO_INCREMENT,
  `component` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `redirect` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `is_leaf` varchar(1) DEFAULT NULL,
  `hidden` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of x_menu
-- ----------------------------
INSERT INTO `x_menu` VALUES ('1', 'Layout', '/sys', '/sys/user', 'sysManage', '系统管理', 'userManage', '0', 'N', '0');
INSERT INTO `x_menu` VALUES ('2', 'sys/user', 'user', null, 'userList', '用户列表', 'user', '1', 'Y', '0');
INSERT INTO `x_menu` VALUES ('3', 'sys/role', 'role', null, 'roleList', '角色列表', 'roleManage', '1', 'Y', '0');
INSERT INTO `x_menu` VALUES ('4', 'Layout', '/test', '/nested/menu1', 'test', '功能测试', 'form', '0', 'N', '0');
INSERT INTO `x_menu` VALUES ('5', 'nested/menu1/index', 'test1', '', 'test1', '测试点一', 'form', '4', 'Y', '0');
INSERT INTO `x_menu` VALUES ('6', 'nested/menu1/menu1-1/index', 'test2', '', 'test2', '测试点二', 'form', '4', 'Y', '0');
INSERT INTO `x_menu` VALUES ('7', 'nested/menu1/menu1-2/index', 'test3', '', 'test3', '测试点三', 'form', '4', 'Y', '0');

-- ----------------------------
-- Table structure for x_role
-- ----------------------------
DROP TABLE IF EXISTS `x_role`;
CREATE TABLE `x_role` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of x_role
-- ----------------------------
INSERT INTO `x_role` VALUES ('1', 'admin', '超级管理员');
INSERT INTO `x_role` VALUES ('2', 'hr', '人事专员');
INSERT INTO `x_role` VALUES ('3', 'normal', '普通员工');

-- ----------------------------
-- Table structure for x_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `x_role_menu`;
CREATE TABLE `x_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `menu_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of x_role_menu
-- ----------------------------
INSERT INTO `x_role_menu` VALUES ('30', '3', '4');
INSERT INTO `x_role_menu` VALUES ('51', '1', '1');
INSERT INTO `x_role_menu` VALUES ('52', '1', '2');
INSERT INTO `x_role_menu` VALUES ('53', '1', '3');
INSERT INTO `x_role_menu` VALUES ('54', '1', '4');
INSERT INTO `x_role_menu` VALUES ('55', '1', '5');
INSERT INTO `x_role_menu` VALUES ('56', '1', '6');
INSERT INTO `x_role_menu` VALUES ('57', '1', '7');

-- ----------------------------
-- Table structure for x_user
-- ----------------------------
DROP TABLE IF EXISTS `x_user`;
CREATE TABLE `x_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `deleted` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of x_user
-- ----------------------------
INSERT INTO `x_user` VALUES ('1', 'admin', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'super@aliyun.com', '18677778888', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('2', 'zhangsan', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'zhangsan@gmail.com', '13966667777', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('3', 'lisi', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'lisi@gmail.com', '13966667778', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('4', 'wangwu', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'wangwu@gmail.com', '13966667772', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('5', 'zhaoer', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'zhaoer@gmail.com', '13966667776', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('6', 'songliu', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'songliu@gmail.com', '13966667771', '1', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '0');
INSERT INTO `x_user` VALUES ('7', 'aaa', '$2a$10$t9ARcLIk7MmMMrJ.Thk5iOh/ifjXsHxir1Tnt9ndpAE3oAiRXGhf6', 'sizhanbo@outlook.com', '18677778888', '1', null, '0');
INSERT INTO `x_user` VALUES ('8', 'bbb', '$2a$10$5Im0e6gdrvbbtVfbhekphe.B7deT.iouiNH8an9ppwRh3fhQNtY4q', 'sizhanbo@outlook.com', '18677778888', '1', null, '0');
INSERT INTO `x_user` VALUES ('9', 'text', '$2a$10$.XRdYEG88bFthu7Vuf7Vm.RKDmKMN5sDnO.9.Q7Y9Z/E4Pv74eSn2', 'sizhanbo@outlook.com', '18677778888', '1', null, '1');

-- ----------------------------
-- Table structure for x_user_role
-- ----------------------------
DROP TABLE IF EXISTS `x_user_role`;
CREATE TABLE `x_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of x_user_role
-- ----------------------------
INSERT INTO `x_user_role` VALUES ('1', '1', '1');
INSERT INTO `x_user_role` VALUES ('9', '8', '3');
