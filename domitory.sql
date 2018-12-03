/*
SQLyog v10.2 
MySQL - 5.5.47 : Database - domitory
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`domitory` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `domitory`;

/*Table structure for table `dorm` */

DROP TABLE IF EXISTS `dorm`;

CREATE TABLE `dorm` (
  `dorm_id` char(10) DEFAULT NULL COMMENT '宿舍号',
  `bed_num` int(2) DEFAULT NULL COMMENT '床位',
  `people_num` int(2) DEFAULT NULL COMMENT '人数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dorm` */

insert  into `dorm`(`dorm_id`,`bed_num`,`people_num`) values 
	('62321',4,4),
	('61321',4,4);

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `grade_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dorm_id` char(10) NOT NULL COMMENT '宿舍号',
  `discipline` int(3) NOT NULL COMMENT '纪律分',
  `checks` int(3) NOT NULL COMMENT '考勤分',
  `health` int(3) NOT NULL COMMENT '卫生分',
  `grade` int(3) NOT NULL COMMENT '总分',
  `dates` date NOT NULL COMMENT '评分时间',
  `managers_id` int(11) NOT NULL COMMENT '评分人',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`grade_id`,`dorm_id`,
					`discipline`,`checks`,`health`,`grade`,`dates`,`managers_id`) 
values (1,'61321',2,2,3,7,'2016-9-1',100001),
(2,'61321',3,3,3,9,'2016-9-1',100001);

/*Table structure for table `lived` */

DROP TABLE IF EXISTS `lived`;

CREATE TABLE `lived` (
  `student_id` int(11) DEFAULT NULL COMMENT '学号',
  `dorm_id` char(10) DEFAULT NULL COMMENT '宿舍号',
  `bed_id` int(1) DEFAULT NULL COMMENT '床号',
  `livingdate` date DEFAULT NULL COMMENT '入住时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lived` */

insert  into `lived`(`student_id`,`dorm_id`,`bed_id`,`livingdate`) values 
	(1652200204,'61321',1,'2016-09-01'),
	(1652200205,'61321',2,'2016-09-01');

/*Table structure for table `managers` */

DROP TABLE IF EXISTS `managers`;

CREATE TABLE `managers` (
  `manager_name` char(20) NOT NULL COMMENT '姓名',
  `manager_id` int(11) NOT NULL COMMENT '员工号',
  `contact` char(11) NOT NULL COMMENT '联系方式',
  PRIMARY KEY (`manager_id`),
  UNIQUE KEY `UNIQUE` (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `managers` */

insert  into `managers`(`manager_name`,`manager_id`,`contact`) values ('张三',100001,'15547125811'),
	('李四',100002,'15798763214'),
	('王五',100003,'13527899874');

/*Table structure for table `register` */

DROP TABLE IF EXISTS `register`;

CREATE TABLE `register` (
  `identity` int(1) NOT NULL COMMENT '身份(0为管理员，1为宿管，2为学生)',
  `account` int(15) NOT NULL COMMENT '账号',
  `password` char(12) NOT NULL DEFAULT '000' COMMENT '密码',
  PRIMARY KEY (`account`),
  UNIQUE KEY `UNIQUE` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `register` */

insert  into `register`(`identity`,`account`,`password`) values (0,1,'1'),
	(1,3,'222'),
	(1,100001,'000'),
	(1,100002,'000'),
	(1,100003,'000'),
	(0,666666,'000'),
	(0,888888,'000'),
	(0,999999,'000'),
	(2,1652200204,'000000'),
	(2,1652200205,'000');

/*Table structure for table `students` */

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (
  `name` char(10) NOT NULL COMMENT '姓名',
  `sex` char(2) NOT NULL COMMENT '性别',
  `birthday` bigint(15) NOT NULL COMMENT '出生日期',
  `addresss` char(20) NOT NULL COMMENT '地址',
  `contact` char(11) NOT NULL COMMENT '联系方式',
  `student_id` int(9) NOT NULL COMMENT '学号',
  `college` char(20) NOT NULL COMMENT '学院',
  `major` char(20) NOT NULL COMMENT '专业',
  `classes` char(10) NOT NULL COMMENT '班级',
  `dorm_id` char(10) NOT NULL COMMENT '宿舍楼号',
  `bed_id` int(1) NOT NULL COMMENT '床号',
  `status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `UNIQUE` (`contact`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `students` */

insert  into `students`(`name`,`sex`,`birthday`,`addresss`,`contact`,`student_id`,`college`,`major`,`classes`,`dorm_id`,`bed_id`,`status`) values 
	('李毅','男',925488000,'郑州','13838381438',1652200204,'信息工程系','软件工程','软4','61321',1,0),
	('王丽','女',767761871,'北京','13511111111',1652200205,'信息工程学院','计算机科学与技术','计科131','61321',2,0);
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
