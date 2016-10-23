/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.32 : Database - micro_doctor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`micro_doctor` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `micro_doctor`;

/*Table structure for table `appointment_tab` */

DROP TABLE IF EXISTS `appointment_tab`;

CREATE TABLE `appointment_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `patientId` int(11) DEFAULT NULL COMMENT '病人ID',
  `doctorId` int(11) DEFAULT NULL COMMENT '医师ID',
  `seeTime` date DEFAULT NULL COMMENT '就诊日期时间',
  `disease` text CHARACTER SET utf8 COMMENT '病症',
  `dateTime` date DEFAULT NULL COMMENT '预约时间',
  `diagnose` text CHARACTER SET utf8 COMMENT '医生诊断',
  `dstar` double DEFAULT NULL COMMENT '评论（星号）',
  PRIMARY KEY (`id`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tab` (`doctorId`),
  CONSTRAINT `patientId` FOREIGN KEY (`patientId`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `appointment_tab` */

insert  into `appointment_tab`(`id`,`patientId`,`doctorId`,`seeTime`,`disease`,`dateTime`,`diagnose`,`dstar`) values (2,38,42,'2016-10-18','皮肤发痒,更新','2016-10-18','皮肤炎症',3),(4,38,42,'2016-10-18','皮肤发痒','2016-10-18','皮肤炎症',3),(5,38,42,'2016-10-20','喉咙发炎,喉咙痛','2016-10-20','感冒咳嗽',5),(6,38,42,'2016-10-20','喉咙发炎,喉咙痛','2016-10-20','感冒咳嗽',5);

/*Table structure for table `discuss_tab` */

DROP TABLE IF EXISTS `discuss_tab`;

CREATE TABLE `discuss_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `sendId` int(11) DEFAULT NULL COMMENT '被评论的内容ID',
  `observerId` int(11) DEFAULT NULL COMMENT '评论者ID',
  `dcontent` text CHARACTER SET utf8 COMMENT '评论内容',
  `dtime` datetime DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`),
  KEY `sendId` (`sendId`),
  KEY `FK_discuss_tab` (`observerId`),
  CONSTRAINT `FK_discuss_tab` FOREIGN KEY (`observerId`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `sendId` FOREIGN KEY (`sendId`) REFERENCES `send_tab` (`sendId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `discuss_tab` */

insert  into `discuss_tab`(`id`,`sendId`,`observerId`,`dcontent`,`dtime`) values (12,8,38,'我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的','2016-10-20 00:00:00'),(13,9,38,'我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的','2016-10-20 00:00:00'),(14,10,38,'我喜欢评论，我最喜欢评论了，这个是我的评论内容，够长了吧，text类型的','2016-10-20 18:23:36');

/*Table structure for table `doctor_tab` */

DROP TABLE IF EXISTS `doctor_tab`;

CREATE TABLE `doctor_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `doctorId` int(11) DEFAULT NULL COMMENT '用户ID（外键关联用户信息表中的id）',
  `hospital` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '所属医院',
  `office` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '科室',
  `amount` int(11) DEFAULT NULL COMMENT '接诊量',
  PRIMARY KEY (`id`),
  KEY `user_id` (`doctorId`),
  CONSTRAINT `user_id` FOREIGN KEY (`doctorId`) REFERENCES `user_tab` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `doctor_tab` */

insert  into `doctor_tab`(`id`,`doctorId`,`hospital`,`office`,`amount`) values (7,42,'江门人民医院','妇科',123456123);

/*Table structure for table `likes_tab` */

DROP TABLE IF EXISTS `likes_tab`;

CREATE TABLE `likes_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长',
  `sendId` int(11) DEFAULT NULL COMMENT '被点赞的内容ID',
  `likesId` int(11) DEFAULT NULL COMMENT '点赞者ID，关联user',
  `likesTime` datetime DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`),
  KEY `likesId` (`sendId`),
  KEY `likeId` (`likesId`),
  CONSTRAINT `likesId` FOREIGN KEY (`likesId`) REFERENCES `user_tab` (`id`),
  CONSTRAINT `like_tab` FOREIGN KEY (`sendId`) REFERENCES `send_tab` (`sendId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `likes_tab` */

insert  into `likes_tab`(`id`,`sendId`,`likesId`,`likesTime`) values (13,8,40,'2016-10-22 16:43:38'),(14,9,41,'2016-10-22 16:43:52'),(15,10,42,'2016-10-22 16:44:17');

/*Table structure for table `send_tab` */

DROP TABLE IF EXISTS `send_tab`;

CREATE TABLE `send_tab` (
  `sendId` int(11) NOT NULL AUTO_INCREMENT COMMENT '帖ID',
  `userId` int(11) DEFAULT NULL COMMENT '发帖人ID',
  `sendContent` text CHARACTER SET utf8 COMMENT '内容文字',
  `sendTime` datetime DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`sendId`),
  KEY `userId` (`userId`),
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `send_tab` */

insert  into `send_tab`(`sendId`,`userId`,`sendContent`,`sendTime`) values (8,39,'发送信息','2016-10-22 15:57:29'),(9,39,'发送信息','2016-10-22 15:57:29'),(10,38,'发送信息','2016-10-22 15:57:29');

/*Table structure for table `user_tab` */

DROP TABLE IF EXISTS `user_tab`;

CREATE TABLE `user_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长(用户ID)',
  `nickname` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `name` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:0表示男，1表示女',
  `phone` varchar(225) DEFAULT NULL COMMENT '联系方式',
  `address` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系地址',
  `signature` text CHARACTER SET utf8 COMMENT '个性签名',
  `introduction` text CHARACTER SET utf8 COMMENT '个人简介（医师擅长、病人病历）',
  `type` int(11) DEFAULT NULL COMMENT '用户类型（0表示病人，1表示医师）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

/*Data for the table `user_tab` */

insert  into `user_tab`(`id`,`nickname`,`password`,`name`,`age`,`gender`,`phone`,`address`,`signature`,`introduction`,`type`) values (38,'nickname','123456','黄宗贵',18,0,'18925060991','廉江','道不同，不相为谋','大家好，我叫吴乃福',0),(39,'nickname','123456','吴乃福',18,0,'18925060991','廉江','道不同，不相为谋','大家好，我叫吴乃福',0),(40,'nickname','123456','吴乃福',18,0,'18925060991','廉江','道不同，不相为谋','大家好，我叫吴乃福',0),(41,'nickname','123456','吴乃福',18,0,'18925060991','廉江','道不同，不相为谋','大家好，我叫吴乃福',0),(42,'nickname','123456','吴乃福',18,0,'18925060991','廉江','道不同，不相为谋','大家好，我叫吴乃福',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
