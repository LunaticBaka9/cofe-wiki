/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3306
 Source Schema         : maid-coffee

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 07/01/2026 20:08:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop`  (
  `shopId` int(11) NOT NULL AUTO_INCREMENT,
  `shopName` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `slogn` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `openTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `shopusing` tinyint(4) NULL DEFAULT 1,
  `coverPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT 'api/img/shop/cover/NoImage.png',
  `shopType` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`shopId`) USING BTREE,
  UNIQUE INDEX `shopName_index`(`shopName`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 32009 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES (32005, '不良メイド喫茶・Bar黒月柏店', '世界の終末期に不良たちが集う酒場！ゴシックなメイドがお給仕する柏のコンカフェ！', 90, '04-7110-3155', '千葉県柏市柏3丁目6-16 磯野ビル4F', '周二〜周四 19点〜23点/周五・周六 19点〜4点30分', '千葉県', 1, 'api/img/shop/cover/shop_image_32005_240x240.jpg', '咖啡厅BAR （con cafe）', '2026-01-03 17:01:28');
INSERT INTO `shop` VALUES (15242, '不良メイド喫茶・Bar黒月横浜本店', '世界の終末期に不良たちが集う酒場！ゴシックな特攻服メイドがお給仕する横浜のコンカフェ！', 150, '045-306-9343', '神奈川県横浜市中区相生町5-79-3 ベルビル馬車道4F', '平日 19:00 ~ 23:00(L.O 22:30) 周五 19:00 ~ 25:00(L.O 24:30) 周六、节假日前 18:00〜25:00(L.O 24:30) 周日、节假日 18:00〜23:00(L.O 22:30)', '神奈川県', 1, 'api/img/shop/cover/shop_image_15242_240x240.jpg', '咖啡厅BAR （con cafe）', '2025-12-16 18:16:45');
INSERT INTO `shop` VALUES (12522, 'アンネット', '\r\n\r\n大阪日本橋で唯一のカントリーがコンセプト', 135, '08061773652', ' 	\r\n大阪府大阪市浪速区日本橋4-13-15', '2023年7月开放 平日17-22 周六日假期12-22', '大阪府', 1, 'api/img/shop/cover/shop_image_12522_240x240.jpg', '咖啡厅BAR （con cafe）', NULL);
INSERT INTO `shop` VALUES (12431, '秋葉原☆うさぎと星のコンカフェ LittleStarRabbit', '秋葉原☆お星さまとうさぎがコンセプトの可愛いコンカフェ', 70, '〒101-0021', '東京都東京都千代田区外神田4丁目6-10 1/3レジデンス5F', '月〜木：17:00-23:00 金・祝前日：17:00-翌朝5:00 土：12:00-翌朝5:00 日・祝日：12:00-23:00', '東京都', 1, 'api/img/shop/cover/shop_image_12431_240x240.jpg', '咖啡厅BAR （con cafe）', NULL);
INSERT INTO `shop` VALUES (12424, 'プリティデビルメイト', '在霓虹灯闪烁的店内成为小恶魔吧！', NULL, '0352444799', '東京都千代田区外神田4-6-2いすゞビル2階', NULL, '東京都', 1, 'api/img/shop/cover/shop_image_12424_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (32007, '雾雨咖啡店', '东方project主题咖啡店', NULL, NULL, '上海市黄浦区云南北路18号', '8:30-17:30', '上海市', 1, 'api/img/shop/cover/shop_image_2164_240x240.jpg', '精品咖啡店', NULL);
INSERT INTO `shop` VALUES (8000, 'アニソン酒場 宝包-PAOPAO-', '说吧，唱吧，一醉方休！', 117, '000', '東京都中央区銀座7-8-19 2階C室', '营业时间周一：18:00 ～ 23:00周五：23:00 ～ 次日 5:00周六：23:00 ～ 次日 5:00', '東京都', 1, 'api/img/shop/cover/shop_image_8000_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7823, 'アミューズメントポーカー・カジノ　VALUE(バリュー）', '这是一家与川崎站地下街直通的全新娱乐型赌场店铺！', NULL, '070-8333-3703', '神奈川県川崎市川崎区駅前本町１－１ 三協ビル4階', '', '神奈川', 1, 'api/img/shop/cover/shop_image_7823_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7804, 'melfi', '这里是银座的Long Maid（长时陪伴女仆）专属女仆酒吧——melfi（梅尔菲），为您倾心打造。', 117, '000', '東京都中央区銀座7-8-19 2階C室', '平日（周一至周五）：17:00 ～ 22:00（※ 周一固定公休）周末及节假日：15:00 ～ 22:00', '東京都', 1, 'api/img/shop/cover/shop_image_7804_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7795, 'がさいれ！', '新店开业！位于歌舞伎町，以警察为主题的咖啡厅＆酒吧！', NULL, '070-8333-3703', '神奈川県川崎市川崎区駅前本町１－１ 三協ビル4階', '常规营业：每日 18:00 ～ 次日 5:00（※ 周一公休）时短营业期间（根据政府缩短营业时间要求）：平日（周二至周日）：17:00 ～ 20:00周末及节假日：15:00 ～ 20:00', '神奈川', 1, 'api/img/shop/cover/shop_image_7795_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7762, 'cafe&bar Funcy', '超过15种服装任你挑选，随心所欲更换装扮！', 141, '050-31770-2336', '大阪府大阪市浪速区日本橋4-8-17 KIKビル1階', '平日（周一至周五）：18:00 ～ 23:00周末及节假日：15:00 ～ 23:00', '大阪府', 1, 'api/img/shop/cover/shop_image_7762_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7660, 'SleepingForest（スリーピング フォレスト）', '这是一家以“沉睡的森林”为主题的特色咖啡馆。', 141, '080-9709-1717', '大阪府大阪府大阪市浪速区難波中２丁目６−２１', '', '大阪府', 1, 'api/img/shop/cover/shop_image_2164_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (7400, 'mirai connect (ミラコネ)', '这是一家以“VOCALOID（虚拟歌姬）与未来”为主题的女仆咖啡厅＆酒吧！', 70, '0924065933', '福岡県福岡市中央区天神３丁目１４－２', '', '福岡県', 1, 'api/img/shop/cover/shop_image_2164_240x240.jpg', '咖啡厅BAR', NULL);
INSERT INTO `shop` VALUES (100, 'test', 'test', 0, '12312312312312', '321312312312', 'sdfafadsfasdfasdf', '上海', 1, 'api/img/shop/cover/shop_image_2164_240x240.jpg', NULL, '2026-01-04 12:39:54');
INSERT INTO `shop` VALUES (32008, 'asdfasdf', NULL, 90, NULL, 'sadfasdfasdf', NULL, 'asdfsadf', 0, 'api/img/shop/cover/shop_image_2164_240x240.jpg', NULL, '2026-01-02 17:20:47');

-- ----------------------------
-- Table structure for shopdetails
-- ----------------------------
DROP TABLE IF EXISTS `shopdetails`;
CREATE TABLE `shopdetails`  (
  `intro` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `shopId` int(11) NOT NULL,
  `obs1` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `obs2` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `obs3` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `offical` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `twitter` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `facebook` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `menu` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `mon` int(4) NULL DEFAULT NULL,
  `tues` int(4) NULL DEFAULT NULL,
  `wed` int(4) NULL DEFAULT NULL,
  `thur` int(4) NULL DEFAULT NULL,
  `fri` int(4) NULL DEFAULT NULL,
  `sat` int(4) NULL DEFAULT NULL,
  `sun` int(4) NULL DEFAULT NULL,
  `pmenu` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL,
  `shopImg` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`shopId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shopdetails
-- ----------------------------
INSERT INTO `shopdetails` VALUES ('世界末日不良们聚集的酒馆！哥特式女仆服务的柏咖啡厅！\r\n\r\n每年由女仆煮大麦制作的原创手工啤酒大受欢迎！这里提供各种美味和有趣的美食，包括禁止的CBD啤酒！还可以品尝女仆亲手做的料理哦！ 让人联想到世界末日的涂鸦艺术和真实赛博朋克风格的异空间值得一看！\r\n\r\n只充电1100日元 \r\n时间无限制 / 卡拉OK唱到饱 / 有原创鸡尾酒特典 / 卡拉OK点播OK \r\n从JR柏站东口步行5分钟（从上野乘坐电车直通约30分钟）', 32005, ' 可以制作手工啤酒的咖啡厅（con cofe）', '既能唱卡拉ok又能做DJ ! ！', '充满涂鸦艺术的网络空间', 'https://lit.link/kurotsukikswd ', 'https://x.com/kurotsuki_kswd', 'https://www.facebook.com/kurotsukikswd ', '充值1,100日元\r\n\r\n时间无限制/卡拉ok无限畅唱\r\n饮料700日元起，食物600日元起可以订购。\r\n\r\n没有任何额外费用！请每小时至少订购1个订单！可以进行切奇摄影，但这是原创鸡尾酒的特典，详情请咨询演员。', '面向女性/面向男性/团体OK /商品销售/酒精/可刷卡/活动日/每日菜单/可预约/可包场/原创制服/可吸烟/优惠券/热闹/深夜营业/大提琴/现场演出/电源/卡拉ok /', 0, 1, 1, 1, 1, 1, 0, '无限量生啤酒是心脏地带\r\n丰富的威士忌系列\r\n香槟1万日元以下~', '/api/img/shop/shopTitleImage/shop_titleimage1_32005_1240.jpg,/api/img/shop/shopTitleImage/shop_titleimage2_32005_1240.jpg,/api/img/shop/shopTitleImage/shop_titleimage3_32005_1240.jpg');
INSERT INTO `shopdetails` VALUES ('20xx年，毁灭世界的末期\r\n欢迎来到伫立在东方魔窟中的街角酒馆\r\n这里是流浪汉和流氓聚集的不良少年们的聚集地\r\n与朋友见面，喝酒，分享\r\n干杯是我们的魔法语言，那么今晚也开始宴会吧。\r\n', 15242, '穿着特攻服的女仆迎接长发女仆', '免费携带！食物自带&外卖OK', '连LiveHouse和俱乐部都自愧不如的DJ展台', 'https://lit.link/kurotsukibay', 'https://x.com/kurotsuki_bay', 'https://www.facebook.com/kurotsukibay', '充值1,100日元（时间无限制）\r\n收费制\r\n筱木¥800（含税¥880）/60分钟+1饮料\r\n\r\n无限畅饮制\r\n非阿尔¥2,000（含税¥2,200）/60分钟\r\n酒精¥3,000（含税¥3,300）/60分钟\r\n附啤酒¥3,300（含税¥3,630）/60分钟\r\n\r\n携带和外卖OK（不可手工制作）\r\n卡拉ok唱到饱\r\n随便扔飞镖', '面向女性 / 面向男性 / 团体OK / 商品销售 / 积分卡 / 酒精 / 手机电波 / 无线局域网 / 可刷卡支付 / 活动日 / 可预约 / 可包场 / 原创制服 / 可吸烟 / 优惠券 / 热闹 / 深夜营业 / 大提琴 / 电源 / 手机充电服务 / 卡拉ok  / 畅饮菜单 /', 1, 1, 1, 1, 1, 1, 1, NULL, NULL);
INSERT INTO `shopdetails` VALUES ('大阪日本桥唯一以乡村为概念的店\r\n非常适合咖啡初学者^ ^\r\n因为要招募开业工作人员', 12522, '大阪日本桥唯一的乡村概念', '制服5种', NULL, 'https://twitter.com/annette_rr1', 'https://twitter.com/annette_rr1', '', '软饮料\r\n酒精\r\n\r\n只在周末和节假日吐司专门店！', '欢迎回来/午餐套餐/商品销售/酒精/无线网络/每日菜单/悠闲/萌萌蛋包饭/切奇/手机充电服务/无限畅饮菜单/', 1, 1, 1, 1, 1, 1, 1, NULL, NULL);
INSERT INTO `shopdetails` VALUES (NULL, 12431, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `shopdetails` VALUES (NULL, 12424, NULL, NULL, NULL, 'https://prettydevilmate.com', 'https://x.com/prettydevilmate', NULL, NULL, NULL, 1, 1, 1, 1, 1, 1, 1, NULL, NULL);
INSERT INTO `shopdetails` VALUES (NULL, 8000, '卡拉OK畅唱尽享！', '可爱的中式服装', '有很多擅长聊天的女孩子！是一家让人玩得开心的店', NULL, 'https://x.com/anison_paopao', NULL, '畅饮＋卡拉OK无限欢唱（含入场费）1小时：2,500日元延长每30分钟：1,000日元通宵套餐（至清晨）：10,000日元', '酒精饮品 / 热闹氛围 / 深夜营业 / 畅饮菜单 /', 0, 1, 0, 0, 0, 1, 1, '畅饮畅唱最长6小时！通宵套餐仅需10,000日元', NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7823, NULL, NULL, NULL, 'https://asobu.space/', 'https://x.com/kp_value', NULL, NULL, '可与店员一起玩游戏/女性友好/提供积分卡/生日特别优惠/提供酒精饮品/手机信号良好/提供免费无线网络（Wi-Fi）/支持信用卡支付/举办主题活动日/独家原创制服/提供手机充电服务/设有畅饮菜单/', 1, 1, 1, 1, 1, 1, 1, NULL, NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7804, '独家原创的长款女仆制服', '交通便利，步行即可到达银座站、新桥站', '饮品选择丰富，还有女仆亲手制作的餐点', 'http://melfi.tokyo', 'https://x.com/melfi_maid', NULL, '含入场费的畅饮套餐：1小时：主人様 2,500日元／小姐様 2,000日元延长每30分钟：1,000日元3小时畅享套餐：6,000日元提供酒瓶寄存优惠', NULL, 1, 0, 1, 1, 1, 1, 1, '宅邸主厨随心手作料理', NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7795, NULL, NULL, NULL, 'https://asobu.space/', 'https://x.com/kp_value', NULL, '我们提供超值畅饮套餐☆18:00～23:00（另加10%消费税 + 10%服务费）男性：2,500日元／小时女性：1,500日元／小时23:00～次日5:00（另加10%消费税 + 15%服务费）男性：3,000日元／小时女性：1,800日元／小时此外，我们也准备了丰富多样的娱乐项目☆店员饮品：800日元店员敬酒（Shot）：1,500日元拍立得合影（Cheki）：1,000日元酒瓶寄存：5,000日元起普通点歌（卡拉OK）：300日元／首店员合唱（卡拉OK）：800日元／首', '可与店员一起玩游戏/提供拍照服务/适合女性顾客/也欢迎男性顾客/接受团体预约/提供积分卡/供应酒精饮品/免费Wi-Fi（无线网络）/支持信用卡支付/可提前预约/设有吸烟区/氛围热闹（わいわい）/也有安静放松的区域（まったり）/提供男装角色服务/可拍立得（Cheki）合影/提供电源插座', 1, 0, 1, 1, 1, 1, 1, NULL, NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7762, '和可爱的女孩们一起欢闹玩耍', '独一无二的主题概念', '简单易懂的系统，最适合初次体验女仆咖啡馆', 'https://twitter.com/cafebarFuncy', 'https://x.com/cafebarfuncy', NULL, '酒精饮料和无酒精饮料畅饮40分钟，仅需3000日元！', '欢迎光临／欢迎回来！/专为男性顾客打造/提供酒精饮品/支持信用卡支付/热闹欢乐的氛围（わいわい）/可拍立得合影（チェキ）/有偶像风格店员登场/设有畅饮菜单', 1, 1, 1, 1, 1, 1, 1, '', NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7660, '以“沉睡的森林”为主题', '女仆制服灵感源自《玛琳菲森》（Maleficent）', '积极拓展直播等网络内容企划', NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, 1, 1, 1, '', NULL);
INSERT INTO `shopdetails` VALUES (NULL, 7400, '店内播放VOCALOID背景音乐', '充满未来感的店内装潢', NULL, 'https://miraiconnect.business.site/', 'https://x.com/connectmirai', NULL, NULL, '提供积分卡/供应酒精饮品/免费Wi-Fi（无线网络）/支持信用卡支付/定期举办主题活动日（Event Day）/可包场使用/独家原创制服/设有吸烟区/氛围可热闹（わいわい）也可放松（まったり）/营业至深夜/可拍立得合影（チェキ）/设有畅饮菜单', 1, 1, 1, 1, 1, 1, 1, '', NULL);
INSERT INTO `shopdetails` VALUES (NULL, 32007, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1, 1, 1, 1, 1, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `deleteTime` datetime NULL DEFAULT NULL,
  `userusing` tinyint(4) NULL DEFAULT 1,
  `userType` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `username_index`(`username`) USING BTREE,
  UNIQUE INDEX `name_index`(`name`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'editor42', '123456', '海豹四十二', 'user@test.com', '12345678910', '2025-12-04 15:08:00', NULL, 1, 'editor');
INSERT INTO `user` VALUES (2, 'user42', '123456', '用户42', 'user@test.com', '12345678910', '2025-12-05 12:37:39', NULL, 1, 'user');
INSERT INTO `user` VALUES (3, 'user3', '123456', '用户3', 'user@test.com', '12345678910', '2025-12-04 16:06:09', NULL, 1, 'user');
INSERT INTO `user` VALUES (4, 'user4', '123456', '用户4', 'user@test.com', '12345678910', '2025-12-04 16:14:05', NULL, 1, 'user');
INSERT INTO `user` VALUES (5, 'user57', '1234562', '用户5', 'user@test.com', '12345678910', '2025-12-05 01:53:56', NULL, 1, 'user');
INSERT INTO `user` VALUES (6, 'user6', '123456', '用户6', 'user@test.com', '12345678910', '2025-12-05 01:53:59', '2025-12-08 18:32:57', 0, 'user');
INSERT INTO `user` VALUES (7, 'user7', '123456', '用户7', 'user@test.com', '12345678910', '2025-12-05 01:54:01', '2025-12-08 18:32:57', 0, 'user');
INSERT INTO `user` VALUES (10, 'test123', '123', '123', '123', '131313131313', '2025-12-05 17:07:19', NULL, 1, 'user');
INSERT INTO `user` VALUES (8, 'user8', '123123', '用户8', 'editor@test.com', '12345678910', '2025-12-05 01:26:38', NULL, 1, 'editor');
INSERT INTO `user` VALUES (9, 'editor9', '123123', '九', '123123@test.com', '99999999999', '2025-12-05 01:54:46', NULL, 1, 'editor');
INSERT INTO `user` VALUES (11, 'user4221', '123456', '用户4213', 'user@test.com', '12345678911', '2025-12-08 17:52:54', NULL, 0, 'user');
INSERT INTO `user` VALUES (12, '234234234', '234324234', '测试一下字符问题', '234234234324', '23423423', '2025-12-08 17:56:05', NULL, 1, 'user');
INSERT INTO `user` VALUES (13, 'testCealTime', 'testest', 'Test for CreateTime', 'user@test.com', NULL, '2025-12-08 17:58:55', '2025-12-08 18:03:16', 1, 'user');
INSERT INTO `user` VALUES (14, 'register', '123123', 'register', 'test@test.com', '12312312312', '2025-12-11 17:22:11', NULL, 1, 'user');
INSERT INTO `user` VALUES (15, 'test3', '123', 'test3', '123@er.vom', '123', '2025-12-11 17:37:21', NULL, 1, 'user');
INSERT INTO `user` VALUES (16, 'admin', '123456', '管理员1', 'admin@test.com', '12345678910', '2025-12-11 18:48:46', '2026-01-01 15:59:40', 1, 'admin');
INSERT INTO `user` VALUES (17, 'adminTest', '12312322', 'testinsert', '123123', '1231233', '2025-12-11 19:02:48', '2026-01-01 15:59:41', 1, 'admin');
INSERT INTO `user` VALUES (18, 'testdup', 'p', 'testdup', '3123123123', NULL, '2025-12-30 17:11:41', '2026-01-01 15:59:41', 1, 'admin');

-- ----------------------------
-- Table structure for wiki
-- ----------------------------
DROP TABLE IF EXISTS `wiki`;
CREATE TABLE `wiki`  (
  `id` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wiki
-- ----------------------------
INSERT INTO `wiki` VALUES (1, NULL, 'cafeShop', '咖啡厅介绍');

SET FOREIGN_KEY_CHECKS = 1;
