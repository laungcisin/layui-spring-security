/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : imooc-demo

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-12-14 17:59:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `username` varchar(255) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT '' COMMENT '性别',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `regist_time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d2ia11oqhsynodbsi46m80vfc` (`nick_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user', '$2a$10$m1DMDcaTFt5jDqwKEV6xLuSrNsH30FHAR2DQCQt29/Jb3Eh4i0gXi', 'aa123456', 'aa1', 'aa', null, '1', '2017-05-27 15:43:12', '2017年11月21日 下午10时49分59秒');
INSERT INTO `user` VALUES ('2', '2', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('3', '3', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('4', '4', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('5', '5', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('6', '6', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('7', '7', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('8', '8', '', null, '', '', null, '', null, '');
INSERT INTO `user` VALUES ('9', 'test', '', null, '', '', null, '123456', '2017-10-16 18:05:23', '');
