-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2020-06-29 09:09:36
-- 服务器版本： 5.6.47-log
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- 表的结构 `doctor`
--

CREATE TABLE `doctor` (
  `did` int(11) NOT NULL COMMENT '医生id',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `cardno` varchar(100) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `department` int(11) DEFAULT NULL COMMENT '科室',
  `education` int(11) DEFAULT NULL COMMENT '学历',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `doctor`
--

INSERT INTO `doctor` (`did`, `name`, `cardno`, `phone`, `sex`, `age`, `birthday`, `email`, `department`, `education`, `remark`) VALUES
(1, '小北', '220524195008029876', '18829458713', 0, 22, '2020-06-01 16:28:12', 'huatuo@163.com', 2, 3, '妙手回春'),
(2, '扁鹊', '220524196808020000', '17600998877', 0, 60, '1968-08-02 14:21:12', 'bianque@163.com', 2, 3, '赛华佗'),
(3, '喜来乐', '220524197312209898', '18600986677', 1, 40, '1973-12-20 14:21:48', 'xilaile@163.com', 3, 2, '小扁鹊'),
(4, '李时珍', '220524199108024545', '15666773344', 0, 30, '1991-08-02 14:21:48', 'lishizhen@163.com', 5, 3, '尝百草'),
(5, '端木蓉', '220524196801023322', '18347466133', 1, 28, '1968-01-02 14:21:48', 'duanmurong@163.com', 2, 3, '三不救'),
(6, '杨永信', '220524199508024567', '15500999900', 1, 48, '1995-08-02 14:57:50', 'xin@163.com', 1, 2, '专门治疗网瘾'),
(8, '小甜甜', '220524190001101223', '13445268390', 1, 31, '1900-01-10 00:00:00', 'xilaile@163.com', 5, 2, '祖传治牙'),
(11, 'yang', '220524197008029876', '17867345987', 0, 50, '1970-08-02 12:03:15', 'yang@qq.com', 1, 2, '专家级别');

-- --------------------------------------------------------

--
-- 表的结构 `medicine`
--

CREATE TABLE `medicine` (
  `mid` varchar(100) NOT NULL COMMENT '药品编号',
  `picture` varchar(100) DEFAULT NULL COMMENT '图片',
  `inPrice` double DEFAULT NULL COMMENT '进价',
  `salPrice` double DEFAULT NULL COMMENT '售价',
  `name` varchar(50) DEFAULT NULL COMMENT '药品名称',
  `type` int(11) DEFAULT NULL COMMENT '药品类型',
  `descs` varchar(200) DEFAULT NULL COMMENT '简单描述',
  `qualityDate` int(11) DEFAULT NULL COMMENT '保质期',
  `description` varchar(500) DEFAULT NULL COMMENT '详细描述',
  `produceFirm` varchar(80) DEFAULT NULL COMMENT '生产厂商',
  `readme` varchar(300) DEFAULT NULL COMMENT '服用说明',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `medicine`
--

INSERT INTO `medicine` (`mid`, `picture`, `inPrice`, `salPrice`, `name`, `type`, `descs`, `qualityDate`, `description`, `produceFirm`, `readme`, `remark`) VALUES
('cat-001', '520de947-c742-41d4-8142-0c0cae63ab3d3.jpg', 100, 101, '猫薄荷', 1, '救猫', 36, '==', '中公猫厂', '吸服', '=='),
('changde-1100', 'afdddd94-7e80-48a3-8b35-f8ea939186ed.jpg', 12, 11, '马来酸非尼拉敏盐酸萘甲唑啉滴眼液', 2, '非常好', 36, '不错', '不', '一天3次，一次2滴', '=='),
('CYN-0011', '173823e3-28c5-400d-a17a-a7ebcdb3ad14.jpg', 25, 30, '肠炎宁', 3, '治疗胃病', 36, '非常实用', '医药公司', '一日3次，每次一片', '-----'),
('DCY-001', '80325b28-b070-4653-82d2-90932cf433e8.jpg', 1, 10, '打虫药', 1, '打虫药', 36, '甜滋滋', '打虫药', '打虫药', '打虫药'),
('DJ-74110', '3a6dc695-65b1-4f14-8da9-c942cc9f98e4.jpg', 1000, 100000, '精神病', 3, '治理精神病', 36, '专治精神病', '未知', '未知', '谁用谁知道'),
('GMLKL', '938abb13-2c13-4d33-8767-f70717b1708d.jpg', 100, 10, '感冒灵颗粒', 1, '治感冒', 1, '治感冒', '小儿', '1天一盒', '包治百病'),
('GM_0938', 'd8816b11-1ccf-40aa-bfe1-2fa8f91681fc.jpg', 5, 20, '感冒灵·', 1, '治疗感冒', 36, '治疗感冒', '1', '1', '1'),
('GS_921', '009e9167-2e5d-47bd-82ef-e3028aa7aef21.jpg', 23, 56, '感康', 2, '专治感冒', 36, '专治感冒，一盒见效', '北京中药厂', '一天3次，一次2片', '专治感冒，一盒见效'),
('H-2345', '1d406b70-85d5-4460-b1b8-2d31c6ce8fc2.jpg', 15, 35, '999感冒灵颗粒', 3, '感冒必备', 36, '感冒必备', '13245678', '口服', '####'),
('hdh1254', '39c7d56c-f7a3-4251-95f7-dc6bd49c48e5.jpg', 30, 50, '鹤顶红', 1, '好用啊', 36, '一点就好用', '奸商', '口服', '别不小心自己喝了'),
('HH-001', '25f703a4-d733-41e0-b53f-d679e9bd69a0.jpg', 100, 10000, '后悔药', 2, '后悔药', 36, '后悔药', '后悔药', '沸水送服', '后悔药'),
('HHY-001', 'b188792d-9787-4d3b-be52-f83e63ca7407.jpg', 25, 100, '红花油', 2, '包治百病！', 36, '好', '好', '直接喝！', '好'),
('HZ-16326', '02b7b77d-d3d1-4c58-9e83-fb26ef6e2da2.jpg', 2, 5, '三黄片', 1, '清热解毒', 36, '具有清热解毒，泻火通便功效。主治三焦热盛，目赤肿痛，口鼻生疮，咽喉肿痛，心烦口渴。', '亚宝药业', '1日2次', '无'),
('jbsw', 'aa2b3702-94a8-44e9-9a0e-d600fd46bb4b.jpg', 999, 99990, '九步蛇丸', 2, '没有描述', 999, '没有描述', '没有厂商', '随便服用', '没有备注'),
('jj_002', 'cc9e5aa4-febb-404e-a834-aed4f5917293.jpg', 9.9, 19, '九九感冒灵', 1, '治感冒', 36, '疗效', '九九制药', '本品解热镇痛。用于感冒引起的头痛，发热等', '本品为浅棕色至深棕色的颗粒，味甜、微苦。每盒含9包，每包袋装10g。解热镇痛功效，用于因感冒引起的头痛，发热，鼻塞，流涕，咽痛等症状。'),
('ML-001', '25ad4300-8c77-420a-81d0-b0dd400f840e.jpg', 80000, 100000, '爱的魔力药水', 1, '转圈圈', 100, '爱的魔力转圈圈', '爱情', '口服', '转圈圈'),
('No.00001', 'f8658818-341f-4a02-8862-eceb9e67efe5.jpg', 10000, 10000, '还魂丹', 1, '一颗极乐,两颗升天', 1, '灵隐寺', '灵隐寺', '一口吞服', ''),
('No1', 'f11edb5f-a3be-4477-9a66-cf8400062a3e.jpg', 10000, 10000000, '天山雪莲', 2, '老好呲了', 36, '--', '-', '-', '-'),
('No2', 'b25dc979-8b7a-4469-83e6-eccee5b96c1c.jpg', 10002020, 120301230120, 'nice棒棒鼓励片', 1, '老好呲了', 36, '-', '-', '-', '-'),
('No3', '6bde8c74-1736-4108-92ce-a72f9b768d30.jpg', 123123, 123123412312, '肛泰', 1, '-', 36, '-', '-', '-', '-'),
('pc9988', 'f66331a3-bf95-4e35-9d26-d300a8e8b3d5.jpg', 15, 150, '万通筋骨片', 1, '一吃就好', 36, '谁用谁知道', '三九制药', '别忘了吃', '忌酒'),
('QXTYS-110', 'a9c41431-78a0-4146-9eab-25d11695b003.jpg', 10000, 99999, '强效太阳水', 2, '喝一瓶想两瓶', 36, '牛逼', '盛大或者？？？', '外服内服', '666'),
('SKKFF-1-23213', '3bdb01fe-76dd-4a64-a2fa-cb7ea662a79e.jpg', 20, 40, '复方炔诺酮糖衣片', 3, '女人掌握核心科技', 36, '避免意外怀孕', '不知道', '口服', '自己理解'),
('SS-001', '4661e4bd-f27e-4a0a-959a-0d8ff4456a4a.jpg', 111, 10000, '烧烧果实', 1, '烧烧果实', 36, '烧烧果实', '烧烧果实', '烧烧果实', '烧烧果实'),
('STDYW-01', '61173dbe-8214-4c2b-8dc5-91776b994cc6.jpeg', 20, 200, '伸腿瞪眼丸', 2, '很好吃', 36, '吃完就伸腿', '伸腿公司', '生吞', '嘎嘎嘎'),
('SXRJS-001', '790c5e35-78c9-44bb-aeb3-9d7d446304b5.jpg', 34, 430, '黑玉断骨膏', 1, '又香又好吃', 36, '香的一批，又好看又好吃', '孙伟志牌烤肉店', '香的一批，又好看又好吃', '孙伟志牌烤肉香的一批，又好看又好吃'),
('WTJGT-123', '07aa2375-32b9-404f-bbe6-8157745cf340.jpg', 45, 100, '万通胫骨贴', 2, '贴上暖暖的', 36, '直接贴', 'baidu', '直接贴', '效果666'),
('YKN-008', 'c9bd59d3-f0ce-49f1-a1d0-97fced715a68.png', 88, 188, '炎可宁片', 3, '就是牛!', 12, '一粒就见效!', '吉尔吉药业', '使劲吃!', '------'),
('YNBY-001', '559ab16f-82c5-4a1e-8b8e-1f73e0045aea.jpg', 10, 30, '云南白药', 3, '神药', 36, '具特异性香气，味略感清凉，并有麻舌感。', '云南', '口服', '。。。'),
('YSG--110', 'b0af939f-966c-47d4-b566-cafd844733a7.gif', 20, 30000, '药水哥', 3, '解心宽', 36, '解懒解心宽', '--', '==', '==='),
('YYHHS', '02c1c87e-cfcc-4398-ad2b-4a85e28bd7c4.jpg', 250, 500, '阴阳合欢散', 2, '很嗨皮', 50, '嗨皮的很', '自制', '直接服用', '嗨到飞起'),
('ZH-0000001', '93e5753e-6f92-43f0-aa81-c77142857181.jpg', 10000, 200000, '绮罗郁金香', 2, '规避任何毒物', 33333, '不知道', '斗罗大陆', '携带身边', '不要食用'),
('zzw-001', 'd1ce6850-229e-4995-b96b-e5991989a0e2.jpg', 2, 20000, '智障丸', 2, '治智障神药', 36, '一次一片，立刻见效', '智障厂', '==', '==');

-- --------------------------------------------------------

--
-- 表的结构 `register`
--

CREATE TABLE `register` (
  `rid` varchar(10) NOT NULL COMMENT '病人编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `idCard` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `siNumber` varchar(18) DEFAULT NULL COMMENT '社保卡号',
  `registerMoney` double DEFAULT NULL COMMENT '挂号费',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `isPay` int(11) DEFAULT NULL COMMENT '是否自费，0-否，1-是',
  `sex` int(11) DEFAULT NULL COMMENT '性别：0-男，1-女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `consultation` int(11) DEFAULT NULL COMMENT '初复诊，0-出诊，1-复诊',
  `department` int(11) DEFAULT NULL COMMENT '科室，1-急诊，2-儿科，3-妇科，4-皮肤科，5-内分泌科，6-牙科',
  `did` int(11) DEFAULT NULL COMMENT '医生编号，与doctor表关联',
  `status` int(11) DEFAULT NULL COMMENT '病人当前状态，1-挂号，2-已住院，3-已出院，4-已结算',
  `registerDate` datetime DEFAULT NULL COMMENT '挂号日期',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `register`
--

INSERT INTO `register` (`rid`, `name`, `idCard`, `siNumber`, `registerMoney`, `phone`, `isPay`, `sex`, `age`, `consultation`, `department`, `did`, `status`, `registerDate`, `remark`) VALUES
('1004', '贞子', '210456195609052356', '156777', 20, '102945732423', 0, 0, 18, 1, 2, 1, 1, '2019-08-07 16:11:14', '====='),
('1005', '牛魔王', '150456195609056689', '178009', 50, '102945732423', 0, 1, 50, 0, 5, 4, 1, '2019-08-08 14:10:06', '别有用心'),
('1006', '张三2', '230990186609098876', '120987', 5, '17809876789', 0, 1, 34, 1, 2, 5, 1, '2020-02-11 08:12:38', '这么大年龄怎么看儿科'),
('1007', '李四', '110890199008082999', '120987', 5, '17809876789', 0, 0, 34, 0, 2, 4, 1, '2020-02-11 10:28:39', '节'),
('1008', '王五', '220987673546780987', '120987', 5, '13909876453', 0, 1, 56, 1, 1, 11, 1, '2020-02-11 10:29:31', '节能');

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE `users` (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT '1' COMMENT '状态1为可用，0表示禁用',
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `modifytime` datetime DEFAULT NULL COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`uid`, `name`, `email`, `status`, `username`, `password`, `modifytime`) VALUES
(1, '张三', 'zhangsan@163.com', 1, 'zhangsan', '123', '2020-02-07 13:10:08'),
(2, '李四', 'lisi@163.com', 1, 'lisi', '123456', '2019-02-04 00:00:00'),
(3, '王五', 'wangwu@163.com', 1, 'wangwu', '000', '2018-04-23 00:00:00'),
(4, '李红', 'lihong@163.com', 1, 'lihong', '111', '2020-02-09 04:18:08'),
(5, '李明', 'ilmkng@163.com', 1, 'liming', '222', '2020-02-11 10:45:49'),
(6, 'abc', 'abc@162.com', 1, 'abc', '111', '2020-02-11 10:52:20'),
(8, '123', '123@123.com', 1, '123', '�7憒瘌庍邊 (v�', '2020-06-28 09:36:30'),
(9, 'we', 'chen@qq.com', 1, 'chen', '�\n�9I篩V郬��>', '2020-06-28 11:19:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`did`);

--
-- Indexes for table `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`mid`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`rid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `doctor`
--
ALTER TABLE `doctor`
  MODIFY `did` int(11) NOT NULL AUTO_INCREMENT COMMENT '医生id', AUTO_INCREMENT=12;

--
-- 使用表AUTO_INCREMENT `users`
--
ALTER TABLE `users`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id', AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
