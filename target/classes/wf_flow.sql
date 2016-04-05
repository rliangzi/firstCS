/*
Navicat MySQL Data Transfer

Source Server         : shosho
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : wf_flow

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-02-15 16:37:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `groupId` varchar(50) NOT NULL DEFAULT '',
  `groupName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of tb_group
-- ----------------------------
INSERT INTO `tb_group` VALUES ('dept', '部门经理');
INSERT INTO `tb_group` VALUES ('financial', '财务部');
INSERT INTO `tb_group` VALUES ('general', '总经理');
INSERT INTO `tb_group` VALUES ('hr', '人事经理');
INSERT INTO `tb_group` VALUES ('team', '组长');

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `orderId` varchar(255) NOT NULL COMMENT '工单编号',
  `orderName` varchar(50) DEFAULT NULL COMMENT '工单名称',
  `processInstId` varchar(255) DEFAULT NULL COMMENT '流程实例id',
  `nextActivityId` varchar(255) DEFAULT NULL COMMENT '状态编码',
  `nextActivityName` varchar(255) DEFAULT NULL COMMENT '状态名称',
  `status` varchar(255) DEFAULT NULL,
  `createUserId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userId` varchar(50) NOT NULL DEFAULT '' COMMENT '用户帐号',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `userPass` varchar(100) DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', '管理员', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('dept1', '部门经理1', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('dept2', '部门经理2', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('financial1', '财务部1', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('financial2', '财务部2', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('general', '总经理', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('general1', '总经理1', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('hr1', '人事经理1', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('hr2', '人事经理2', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('team1', '组长1', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('team2', '组长2', 'D2C33DD52212A89DA41C19F32AA4D9AD');
INSERT INTO `tb_user` VALUES ('team3', '组长3', 'D2C33DD52212A89DA41C19F32AA4D9AD');

-- ----------------------------
-- Table structure for `tb_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_group`;
CREATE TABLE `tb_user_group` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) DEFAULT NULL COMMENT '用户id',
  `groupId` varchar(50) DEFAULT NULL COMMENT '组织机构id',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户组长机构表';

-- ----------------------------
-- Records of tb_user_group
-- ----------------------------
INSERT INTO `tb_user_group` VALUES ('1', 'dept1', 'dept');
INSERT INTO `tb_user_group` VALUES ('2', 'dept2', 'dept');
INSERT INTO `tb_user_group` VALUES ('3', 'financial1', 'financial');
INSERT INTO `tb_user_group` VALUES ('4', 'financial2', 'financial');
INSERT INTO `tb_user_group` VALUES ('5', 'general', 'general1');
INSERT INTO `tb_user_group` VALUES ('6', 'hr1', 'hr');
INSERT INTO `tb_user_group` VALUES ('7', 'hr2', 'hr');
INSERT INTO `tb_user_group` VALUES ('8', 'team1', 'team');
INSERT INTO `tb_user_group` VALUES ('9', 'team2', 'team');

-- ----------------------------
-- Table structure for `tb_wf_task`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wf_task`;
CREATE TABLE `tb_wf_task` (
  `id_` int(255) NOT NULL AUTO_INCREMENT COMMENT '工单编号',
  `processDefinitionId` varchar(255) DEFAULT NULL,
  `processInstId` varchar(255) DEFAULT NULL COMMENT '流程实例id',
  `taskId` varchar(255) DEFAULT NULL COMMENT '任务id',
  `person` varchar(255) DEFAULT NULL COMMENT '处理人',
  `currGroup` varchar(255) DEFAULT NULL COMMENT '处理组',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_wf_task
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_wf_task_config`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wf_task_config`;
CREATE TABLE `tb_wf_task_config` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `processDefinitionId` varchar(255) DEFAULT NULL,
  `activityId` varchar(255) DEFAULT NULL,
  `paramType` varchar(255) DEFAULT NULL,
  `paramKey` varchar(255) DEFAULT NULL,
  `paramValue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wf_task_config
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_wf_task_history`
-- ----------------------------
DROP TABLE IF EXISTS `tb_wf_task_history`;
CREATE TABLE `tb_wf_task_history` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `processDefinitionId` varchar(255) DEFAULT NULL,
  `processInstId` varchar(255) DEFAULT NULL,
  `lastTaskId` varchar(255) DEFAULT NULL,
  `taskId` varchar(255) DEFAULT NULL,
  `businessKey` varchar(255) DEFAULT NULL,
  `activityId` varchar(255) DEFAULT NULL,
  `activityName` varchar(255) DEFAULT NULL,
  `person` varchar(255) DEFAULT NULL,
  `currGroup` varchar(255) DEFAULT NULL,
  `nextActivityId` varchar(255) DEFAULT NULL,
  `nextActivityName` varchar(255) DEFAULT NULL,
  `dealPerson` varchar(255) DEFAULT NULL,
  `dealGroup` varchar(255) DEFAULT NULL,
  `operCode` varchar(255) DEFAULT NULL,
  `operName` varchar(255) DEFAULT NULL,
  `operDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_wf_task_history
-- ----------------------------
