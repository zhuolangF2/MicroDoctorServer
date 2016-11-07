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
  `dateTime` timestamp NULL DEFAULT NULL COMMENT '预约时间',
  `diagnose` text CHARACTER SET utf8 COMMENT '医生诊断',
  `dstar` double DEFAULT NULL COMMENT '评论（星号）',
  `dNumber` int(11) DEFAULT NULL COMMENT '字段排号（预约医师的序号）',
  PRIMARY KEY (`id`),
  KEY `patientId` (`patientId`),
  KEY `doctorId` (`doctorId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctor_tab` (`doctorId`),
  CONSTRAINT `patientId` FOREIGN KEY (`patientId`) REFERENCES `user_tab` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `appointment_tab` */

insert  into `appointment_tab`(`id`,`patientId`,`doctorId`,`seeTime`,`disease`,`dateTime`,`diagnose`,`dstar`,`dNumber`) values (2,39,42,'2016-10-18','皮肤发痒,更新','2016-10-18 17:26:48','神经病啊你没得救啦',3,1),(4,47,42,'2016-10-29','皮肤发痒','2016-10-29 22:30:29','皮肤炎症',3,0),(5,40,42,'2016-10-20','喉咙发炎,喉咙痛','2016-10-20 17:26:48','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',5,3),(6,41,42,'2016-10-20','喉咙发炎,喉咙痛','2016-10-20 17:26:48','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',5,4),(7,42,42,'2016-10-29','喉咙发炎,喉咙痛','2016-10-29 17:26:48','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',5,5),(8,44,42,'2016-10-29','喉咙发炎,喉咙痛','2016-10-29 17:26:48','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',5,6),(9,45,42,'2016-10-29','喉咙发炎,喉咙痛','2016-10-29 17:48:40','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',5,7),(10,51,57,'2016-10-29','喉咙发炎,喉咙痛','2016-10-29 21:36:35','感冒咳嗽，按每日三次疗程服用999即可，直到好转为止',0,0),(11,52,57,'2016-10-29','喉咙发炎,喉咙痛','2016-10-31 16:15:59',NULL,0,0),(12,53,57,'2016-10-29','喉咙发炎,喉咙痛','2016-10-31 16:21:37',NULL,0,1),(13,54,57,'2016-10-29','喉咙发炎,喉咙痛','2016-10-31 16:22:15',NULL,0,2),(14,70,42,'2016-11-06','感冒','2016-11-06 17:15:45',NULL,0,1),(15,74,46,'2016-11-07','感冒','2016-11-06 17:38:46',NULL,0,1),(24,48,42,'2016-11-09','腿疼','2016-11-07 17:46:04',NULL,0,1),(25,75,43,'2016-12-07','头晕','2016-11-07 22:14:55',NULL,0,1);

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

insert  into `discuss_tab`(`id`,`sendId`,`observerId`,`dcontent`,`dtime`) values (12,8,39,'医生很专业很有耐心，在医生的指导下我很快就治好我身体上的病了','2016-10-20 00:00:00'),(13,9,39,'医生很专业很有耐心，在医生的指导下我很快就治好我身体上的病了','2016-10-20 00:00:00'),(14,10,39,'医生很专业很有耐心，在医生的指导下我很快就治好我身体上的病了','2016-10-20 18:23:36'),(15,11,56,'医生很专业很有耐心，在医生的指导下我很快就治好我身体上的病了','2016-10-31 23:58:06');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `doctor_tab` */

insert  into `doctor_tab`(`id`,`doctorId`,`hospital`,`office`,`amount`) values (7,42,'江门人民医院','儿科办公室二楼骨科办公室',23),(8,43,'廉江市第一人民医院','二楼骨科办公室',34),(9,44,'廉江市第一人民医院','二楼骨科办公室',500),(10,45,'廉江市第一人民医院','二楼骨科办公室',240),(11,46,'高州市第一人民医院','二楼骨科办公室',50),(12,47,'廉江市第一人民医院','二楼骨科办公室',0),(13,70,'廉江市第一人民医院','二楼骨科办公室',0),(14,71,'廉江市第一人民医院','二楼骨科办公室',0),(15,72,'廉江市第一人民医院','二楼骨科办公室',23),(16,74,'廉江市第一人民医院','二楼骨科办公室',0),(17,75,'广东省江门市蓬江区第一人民医院','二楼骨科办公室',0),(18,76,'北京大学附属医院','二楼骨科办公室',0);

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `likes_tab` */

insert  into `likes_tab`(`id`,`sendId`,`likesId`,`likesTime`) values (14,9,41,'2016-10-22 16:43:52'),(15,10,42,'2016-10-22 16:44:17'),(18,8,44,'2016-10-23 21:20:43');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `send_tab` */

insert  into `send_tab`(`sendId`,`userId`,`sendContent`,`sendTime`) values (8,39,'发送信息','2016-10-22 15:57:29'),(9,39,'发送信息','2016-10-22 15:57:29'),(10,41,'发送信息','2016-10-22 15:57:29'),(11,42,'这个内容是评论信息','2016-10-31 22:54:39');

/*Table structure for table `user_tab` */

DROP TABLE IF EXISTS `user_tab`;

CREATE TABLE `user_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动编号,自增长(用户ID)',
  `nickname` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `name` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别:0表示男，1表示女',
  `phone` varchar(225) NOT NULL COMMENT '联系方式(登录账号)',
  `address` varchar(225) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系地址',
  `signature` text CHARACTER SET utf8 COMMENT '个性签名',
  `introduction` text CHARACTER SET utf8 COMMENT '个人简介（医师擅长、病人病历）',
  `type` int(11) DEFAULT NULL COMMENT '用户类型（0表示病人，1表示医师）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneunique` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=latin1;

/*Data for the table `user_tab` */

insert  into `user_tab`(`id`,`nickname`,`password`,`name`,`age`,`gender`,`phone`,`address`,`signature`,`introduction`,`type`) values (39,'阿福','123456','吴乃福',18,0,'18925060991','廉江','道不同，不相为谋','我是普通用户',0),(41,'阿明','123456','张小明',18,0,'18925060992','廉江','道不同，不相为谋','我是普通用户',0),(42,'阿庭','123456','刘金庭',18,0,'18925060993','廉江','道不同，不相为谋','我是医师用户',1),(43,'涛仔','123456','罗文涛',18,0,'18925060994','廉江','道不同，不相为谋','我是医师用户，擅长骨科，有需要预约我',1),(44,'小开','123456','张开',18,0,'18925060995','廉江','道不同，不相为谋','我是医师用户，擅长骨科，有需要预约我',1),(45,'夜雨','123456','罗红雨',18,0,'18925060996','廉江','道不同，不相为谋','我是医师用户，擅长骨科，有需要预约我',1),(46,'千哥','123456','毛柏千',20,0,'18925060997','湛江','面对现实，忠于梦想','我是医师用户，擅长骨科，有需要预约我',1),(47,'益达','123456','林胜达',2,1,'18925060998','湛江','面对现实，忠于梦想','我是医师用户，擅长骨科，有需要预约我',1),(48,'Tom','1234567','黄益坚',18,0,'18888888888','廉江','道不同，不相为谋','我是普通用户我是普通用户',0),(49,'Tom','123456','李涉 ',18,0,'18925060900','廉江','道不同，不相为谋','我是普通用户',0),(50,'七哥','123445','赵七',2,1,'18925060910','湛江','面对现实，忠于梦想','我是普通用户',0),(51,'浩浩','123456','邓浩波',2,1,'18925060911','湛江','面对现实，忠于梦想','我是普通用户',0),(52,'海','123456','陈华海',18,0,'18925060912','廉江','道不同，不相为谋','我是普通用户',0),(53,'亮剑','123456','刘良剑',18,0,'18925060913','廉江','道不同，不相为谋','我是普通用户',0),(54,'聪明绝顶','123456','徐卓聪',2,1,'18925060914','湛江','面对现实，忠于梦想','我是普通用户',0),(55,'huang阿贵','123456','黄宗贵',18,0,'18925060915','廉江','道不同，不相为谋','我是普通用户',0),(56,'阅历','123456','刘悦可',2,1,'18925060916','湛江','面对现实，忠于梦想','我是普通用户',0),(57,'zhang善思','123456','张善思',2,1,'18925060917','湛江','面对现实，忠于梦想','我是普通用户',0),(58,'meng','123456','李梦竹',21,1,'18925060918','阳江','阳江的道路不是那么好走的','我是普通用户',0),(64,'ning','123456','张清宁',20,0,'18219111685','广东省江门市五邑大学',NULL,NULL,0),(67,'未填写','123456','李珑玲',0,1,'12222222223','未填写','未填写','我是普通用户',0),(68,'未填写','123456','未填写',0,1,'1233544','未填写','未填写','我是普通用户',0),(70,'夜雨','123456','林诗桃',21,0,'18219111625','湛江','阳光总在风雨后','我是医师用户，擅长骨科，有需要预约我',1),(71,'杰字当头','123456','林文杰',15,0,'13531097736','未填写','未填写','我是医师用户，擅长骨科，有需要预约我',1),(72,'未填写','123456','黄杰宇',21,0,'18219111627','未填写','未填写','我是医师用户，擅长骨科，有需要预约我',1),(74,'未填写','123456','张得伟',0,0,'18219111666','未填写','个性签名','我是医师用户，擅长骨科，有需要预约我',1),(75,'阿三','123456','张三',18,0,'18888888881','广东省江门市蓬江区','我就是我，不一样的烟火','我是医师用户，擅长骨科，有需要预约我',1),(76,'七仔','123456','刘七',18,1,'18888888883','北京市天安门','阳光总在风雨后','我是医师用户，擅长骨科，有需要预约我',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
