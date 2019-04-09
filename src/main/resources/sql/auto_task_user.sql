/*
SQLyog Professional v12.3.1 (64 bit)
MySQL - 5.1.73 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `auto_task_user` */

DROP TABLE IF EXISTS `auto_task_user`;

CREATE TABLE `auto_task_user` (
  `atuId` int(11) NOT NULL AUTO_INCREMENT,
  `userCode` varchar(20) NOT NULL,
  `cookie` text NOT NULL,
  `formHash` varchar(20) NOT NULL,
  `bbsName` varchar(30) DEFAULT NULL,
  `taskStatus` int(11) NOT NULL DEFAULT '1',
  `createTime` datetime NOT NULL,
  `stopTime` datetime DEFAULT NULL,
  `ipAddress` varchar(15) DEFAULT NULL,
  `lockStatus` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`atuId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `auto_task_user` */

insert  into `auto_task_user`(`atuId`,`userCode`,`cookie`,`formHash`,`bbsName`,`taskStatus`,`createTime`,`stopTime`,`ipAddress`,`lockStatus`) values 
(1,'ak_107jim','MIUI_2132_nofavfid=1; UM_distinctid=168ef1e859d449-0ed2b9646f07bf-7e2d3214-1fa400-168ef1e859e9d8; CNZZDATA5557939=cnzz_eid%3D231649569-1533532188-null%26ntime%3D1552954821; MIUI_2132_saltkey=R9GgG39e; MIUI_2132_lastvisit=1554187544; MIUI_2132_sendmail=1; __utmt=1; MIUI_2132_s_devices=ZTJhNTlzY0h1cXRZL2tpTTJlNkQxWURGNndpbVpJOUIzcFREbHdSdGEzOE1uU0w2bUFMNHdKTnY3WTlYU1l4L2Z2TTZSQ2xtbERaNUZjU2NUUmhHRDA4; MIUI_2132_ulastactivity=7af6mhnwFBoHHnI%2FSXkgNQ3t0R6KrrEMgl9otK6Q1TaXcqx0fUOuF2E; MIUI_2132_auth=d27dsmQnswnWFBfoZP%2FfaVrC0ulQtidEPAaFwuXEQ62hcCol5Bpd4w; lastLoginTime=473f7g8edfxOEY%2FABKz5b2hmyj4t5Zm49U5A5O%2BV4iiklp1yGTan; MIUI_2132_visitedfid=685D37D486D765D763D705D463D809D39D759; MIUI_2132_viewid=tid_22931932; MIUI_2132_lastact=1554191230%09home.php%09spacecp; CNZZDATA2441309=cnzz_eid%3D1546994839-1533254892-null%26ntime%3D1554187495; CNZZDATA30049650=cnzz_eid%3D1557897407-1533254503-null%26ntime%3D1554187355; __utma=230417408.218873132.1533258190.1553737118.1554191149.61; __utmb=230417408.7.10.1554191149; __utmc=230417408; __utmz=230417408.1553220093.58.52.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1553220093,1553493730,1553737118,1554191147; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554191232; MIUI_2132_smile=3D1','a6b0e598:895e0b6a','抢劫小伙伴',3,'2019-04-02 15:48:15',NULL,'127.0.0.1',0),
(2,'ak_8d9req','1','1','1',3,'2019-04-04 10:16:25',NULL,'127.0.0.1',0),
(3,'ak_m55ngt','UM_distinctid=169bd7b3dd70-023c16c0793aec-650e4228-1fa400-169bd7b3dd86ae; MIUI_2132_saltkey=WAbofQof; MIUI_2132_lastvisit=1553658594; MIUI_2132_auth=74fbflJq6pMYmjIMaaBIEMYSLJb9L9XVuwXgdFySyLHFgfiANlSLibU; __utmc=230417408; MIUI_2132_nofavfid=1; MIUI_2132_smile=3D1; MIUI_2132_visitedfid=812D304; __utma=230417408.413483172.1553662164.1553913740.1554368800.3; __utmz=230417408.1554368800.3.3.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmt=1; MIUI_2132_ulastactivity=a6d26Jx4C47UdI%2BuutwG6P9zbn4t7CL%2FV5DrGNNsf6PEzZeXU8tbw0U; CNZZDATA30049650=cnzz_eid%3D1594753267-1553912755-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554367172; CNZZDATA5557939=cnzz_eid%3D229556702-1553910195-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554367906; MIUI_2132_viewid=tid_22988878; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1553913740,1553915463,1554368800,1554368911; Hm_lvt_4e5bdf78b2b9fcb88736fc67709f2806=1553913740,1553915463,1554368800,1554368911; CNZZDATA2441309=cnzz_eid%3D84996810-1553658657-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554364054; MIUI_2132_sendmail=1; MIUI_2132_checkpm=1; __utmb=230417408.9.10.1554368800; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554369232; Hm_lpvt_4e5bdf78b2b9fcb88736fc67709f2806=1554369233; MIUI_2132_lastact=1554369277%09forum.php%09forumdisplay; MIUI_2132_forum_lastvisit=D_812_1554369277','cf85fc3b:b3cf58fc','skteam',3,'2019-04-04 17:23:59',NULL,'127.0.0.1',0),
(4,'ak_ste9cd','MIUI_2132_saltkey=skukDkMM; MIUI_2132_lastvisit=1554435898; UM_distinctid=169ebd06acf2a2-035daf543d669f-38395d0b-1fa400-169ebd06ad05df; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554439499; __utma=230417408.1391245919.1554439499.1554439499.1554439499.1; __utmc=230417408; __utmz=230417408.1554439499.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmt=1; CNZZDATA2441309=cnzz_eid%3D268310833-1554436328-null%26ntime%3D1554434840; CNZZDATA30049650=cnzz_eid%3D1431552047-1554437695-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554437695; MIUI_2132_sendmail=1; MIUI_2132_ulastactivity=64bb63imyK9VNPaYDDvrvBf0ZX4RpJ93aIroJ5X5bFIGO7rK53pjDsY; MIUI_2132_auth=78aeRuixYA%2FLuq4Wq4rwM%2BHL3ZBbv1C2%2FEBMvenIYWY3jQF6nk5dKMA; lastLoginTime=5769BiPFFSeWCWv7Ctcq2bt%2FQ0Cy0CPjb1%2Fmul1f6oCL0Qmv7N1v; MIUI_2132_noticeTitle=1; MIUI_2132_viewid=tid_22644031; MIUI_2132_smile=3D1; MIUI_2132_checkpm=1; MIUI_2132_visitedfid=3D40; __utmb=230417408.13.10.1554439499; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554439762; MIUI_2132_lastact=1554439762%09forum.php%09ajax','0ae842ac:ca248ea0','skteam',4,'2019-04-05 13:06:30',NULL,'127.0.0.1',0),
(5,'ak_qcx45o','UM_distinctid=169ebb5bbff966-0c4635fc5ae628-4d724416-1fa400-169ebb5bc01c6b; __utma=230417408.273388761.1554437750.1554437750.1554437750.1; __utmz=230417408.1554437750.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); CNZZDATA30049650=cnzz_eid%3D243349824-1554437695-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554437695; CNZZDATA5557939=cnzz_eid%3D443029799-1554434969-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554440369; __utmc=230417408; __utmt=1; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554437750,1554440682,1554441906; CNZZDATA2441309=cnzz_eid%3D1818837383-1554436328-%26ntime%3D1554440240; MIUI_2132_saltkey=oTuqTD8g; MIUI_2132_lastvisit=1554438327; __utmb=230417408.57.10.1554437750; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554441927; MIUI_2132_sendmail=1','aeb5d8f5:5f8d5bea','skteam',4,'2019-04-05 13:29:00',NULL,'127.0.0.1',0),
(6,'ak_2f7hb6','UM_distinctid=169ebb5bbff966-0c4635fc5ae628-4d724416-1fa400-169ebb5bc01c6b; __utma=230417408.273388761.1554437750.1554437750.1554437750.1; __utmz=230417408.1554437750.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); CNZZDATA30049650=cnzz_eid%3D243349824-1554437695-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554437695; CNZZDATA5557939=cnzz_eid%3D443029799-1554434969-http%253A%252F%252Fwww.miui.com%252F%26ntime%3D1554440369; __utmc=230417408; Hm_lvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554437750,1554440682,1554441906; __utmt=1; CNZZDATA2441309=cnzz_eid%3D1818837383-1554436328-%26ntime%3D1554440240; MIUI_2132_saltkey=qMlKkRHf; MIUI_2132_lastvisit=1554439188; MIUI_2132_sendmail=1; MIUI_2132_ulastactivity=6e62ccMSbRljNhXw76CgraDmRuEcy%2FT9%2FiNR0odmUq0VcV7eZjHQT2E; MIUI_2132_auth=671d%2FSWo9oBd1n5VAamS2OQLIEDQHgAxDSUvhTIj2j%2BnWEWMN51h3hs; lastLoginTime=09d81N%2B4cZDjbE7DtrrNJv9UmDPo1oRarARcyO0qzNGX%2FfUivpu%2F; MIUI_2132_noticeTitle=1; MIUI_2132_nofavfid=1; MIUI_2132_forum_lastvisit=D_737_1554442808; MIUI_2132_viewid=tid_23010864; MIUI_2132_smile=3D1; MIUI_2132_home_diymode=1; MIUI_2132_lastact=1554442941%09forum.php%09viewthread; MIUI_2132_visitedfid=812D737; __utmb=230417408.99.10.1554437750; Hm_lpvt_3c5ef0d4b3098aba138e8ff4e86f1329=1554442941','9340d462:264d0439','skteam',3,'2019-04-05 13:45:09',NULL,'127.0.0.1',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;