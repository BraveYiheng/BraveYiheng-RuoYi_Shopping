/*
 Navicat Premium Dump SQL

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : ruoyi_vue

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 14/05/2025 15:39:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '商品的唯一标识，自增',
  `product_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(10, 0) NOT NULL COMMENT '商品价格',
  `product_count` int NULL DEFAULT NULL COMMENT '商品库存',
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_size` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品规格',
  `product_create_time` datetime NULL DEFAULT NULL COMMENT '生产日期',
  `product_brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品品牌',
  `product_type_id` int NULL DEFAULT NULL COMMENT '商品类别管理类别表的外键',
  `product_package` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品包装',
  `product_sign` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标识',
  `product_sugar` int NULL DEFAULT NULL COMMENT '是否加糖',
  `product_status` int NULL DEFAULT NULL COMMENT '商品的上下架状态',
  `product_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品的详细描述',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存储商品详细信息的表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES (1, 'DHA坚果巧克力', 26, 100, '/profile/upload/2025/05/13/10009_20250513104545A001.jpg', '100g', '2025-01-01 00:00:00', '小鹿蓝蓝', 1, '盒装', '新品', 0, 0, '鹿蓝蓝DHA坚果巧克力礼盒可可脂牛奶饼干儿童零食');
INSERT INTO `t_product` VALUES (2, '百事可乐', 2, 200, '/profile/upload/2025/05/13/10013_20250513113148A022.jpg', '250ml', '2025-02-01 00:00:00', '百事可乐', 2, '罐装', '畅销', 1, 0, '经典碳酸饮料，清爽可口。');
INSERT INTO `t_product` VALUES (3, '云南白药牙膏', 23, 150, '/profile/upload/2025/05/13/10001_20250513113533A023.jpg', '120g', '2025-03-01 00:00:00', '云南白药', 3, '管装', '经典', 0, 0, '云南正品老牌子薄荷型牙膏230g牙龈上火出血清新美白去黄去口臭气');
INSERT INTO `t_product` VALUES (4, '米家吹风机', 306, 80, '/profile/upload/2025/05/13/10001_20250513114232A024.jpg', '常规', '2025-04-01 00:00:00', '小米', 4, '盒装', '优质', 0, 0, '小米官旗米家高速水离子吹风机家用速干润养护发风筒负离子恒温');
INSERT INTO `t_product` VALUES (5, '落肩短袖T恤', 69, 120, '/profile/upload/2025/05/13/10001_20250513114320A025.jpg', '2ML', '2025-05-01 00:00:00', '悦游', 5, '袋装', '时尚', 0, 0, '悦游 睡务局局长 夏季纯棉落肩短袖T恤男卡通橘猫打工男生衣服');
INSERT INTO `t_product` VALUES (9, '积木', 80, 110, 'https://picsum.photos/200/200?random=9', '常规', '2025-09-01 00:00:00', '乐高', 9, '盒装', '益智', 0, 0, '高品质积木，激发创造力和想象力。');
INSERT INTO `t_product` VALUES (11, '快补水', 53, 362, '/profile/upload/2025/05/13/10001_20250513110736A012.jpg', '10.1kg', '2025-04-15 00:00:00', '佳得乐', 2, '瓶装', '电解质饮料', 1, 0, '【王鹤棣同款】佳得乐全新快补水红柚味');
INSERT INTO `t_product` VALUES (12, '盐焗鸡脚筋', 15, 86, '/profile/upload/2025/05/13/10007_20250513105910A008.jpg', '100g', '2025-05-07 00:00:00', '良品铺子', 1, '袋装', '熟食', 1, 0, '\n良品铺子盐焗鸡脚筋柠檬酸辣鸡爪网红爆款卤味熟食即食小吃下酒菜');
INSERT INTO `t_product` VALUES (13, '零食大礼包', 98, 300, '/profile/upload/2025/05/13/10004_20250513105439A005.jpg', '6kg', '2025-04-23 00:00:00', '良品铺子', 1, '盒装', '零食', 1, 0, '良品铺子巨型零食大礼包整箱520情人节送女朋友生日礼物休闲食品');
INSERT INTO `t_product` VALUES (14, '黑葡萄气泡水', 60, 200, '/profile/upload/2025/05/13/10005_20250513111407A016.jpg', '250ml', '2025-04-30 00:00:00', '元气森林', 2, '瓶装', '气泡水', 0, 0, '【肖战同款】元气森林夏黑葡萄味0糖0脂0卡气泡水整箱');
INSERT INTO `t_product` VALUES (15, '荣耀MagicBook Pro 16', 5599, 98, '/profile/upload/2025/05/12/10001_20250512220838A006.jpg', '2.8kg', '2025-04-07 00:00:00', '荣耀', 15, '盒装', '新品', 1, 0, '【政府补贴20%】HONOR/荣耀MagicBook Pro 16 英特尔酷睿Ultra5 16英寸AI PC轻薄游戏本笔记本电脑空间音频');
INSERT INTO `t_product` VALUES (16, '光影精灵10', 4959, 65, '/profile/upload/2025/05/12/10002_20250512223842A001.jpg', '3.4kg', '2025-03-04 00:00:00', '惠普', 15, '盒装', '畅销', 1, 0, '【政府补贴20%】HP惠普光影精灵10可选13代英特尔酷睿i5 RTX4060设计笔记本15.6英寸暗影电脑官方旗舰店1938');
INSERT INTO `t_product` VALUES (17, '拯救者Y7000', 6099, 300, '/profile/upload/2025/05/12/10003_20250512224014A002.jpg', '4.5', '2024-04-10 00:00:00', '联想', 15, '盒装', '畅销', 1, 0, 'Lenovo/联想 拯救者Y7000P i7 14650H/I7 14700H RTX4060 8G独显');
INSERT INTO `t_product` VALUES (18, '猎刃16', 7199, 80, '/profile/upload/2025/05/12/10004_20250512224240A003.jpg', '3.5kg', '2025-01-06 00:00:00', '雷神', 15, '盒装', '畅销', 1, 0, '【国家补贴20%】雷神笔记本电脑猎刃16英特尔酷睿14代i7HX满血RTX4060官方旗舰店便携办公16英寸');
INSERT INTO `t_product` VALUES (19, '拯救者 R7000', 5799, 300, '/profile/upload/2025/05/12/10005_20250512224428A004.jpg', '3.5kg', '2025-03-03 00:00:00', '联想', 15, '盒装', '热卖', 1, 0, '正品Lenovo/联想 拯救者 R7000 游戏本笔记本电脑电竞RTX4060独显');
INSERT INTO `t_product` VALUES (20, '暗影精灵10', 5298, 35, '/profile/upload/2025/05/12/10006_20250512224745A005.jpg', '3.2kg', '2025-04-14 00:00:00', '惠普', 15, '盒装', '新品', 1, 0, 'HP/惠普暗影精灵10 暗夜精灵 光影精灵10/11 14代酷睿RTX4060/4070独显大学生设计剪辑游戏本笔记本电脑');
INSERT INTO `t_product` VALUES (21, '拯救者 Y9000P 2023', 9799, 500, '/profile/upload/2025/05/12/10007_20250512224951A006.jpg', '4.6kg', '2025-04-23 00:00:00', '联想', 15, '盒装', '畅销', 1, 0, 'Lenovo/联想 拯救者 Y9000P 24款16英寸高端电竞屏游戏笔记本电脑');
INSERT INTO `t_product` VALUES (22, '拯救者 Y9000P 2024', 11599, 200, '/profile/upload/2025/05/12/10008_20250512225114A007.jpg', '4.6kg', '2025-05-05 00:00:00', '联想', 15, '盒装', '新品', 1, 0, '\nLenovo/联想 拯救者 Y9000P 2024 14代i9 14900H游戏本笔记本电脑');
INSERT INTO `t_product` VALUES (23, '天选5 Pro', 8199, 179, '/profile/upload/2025/05/12/10009_20250512225301A008.jpg', '4.3kg', '2025-02-10 00:00:00', '华硕', 15, '盒装', '畅销', 1, 0, 'Asus/华硕 天选5 Pro i7-13650HX/RTX4060 电竞游戏本笔记本电脑');
INSERT INTO `t_product` VALUES (24, 'ThinkPad P16v', 12849, 165, '/profile/upload/2025/05/12/10010_20250512225416A009.jpg', '3.8kg', '2025-04-15 00:00:00', '联想', 15, '盒装', '新品', 1, 0, '【旗舰新品】联想ThinkPad P16v 13代酷睿标压16英寸大屏RTX系列4G/6G独显专业图形设计渲染工作站笔记本电脑');
INSERT INTO `t_product` VALUES (25, 'RedmiBook 16', 3299, 30, '/profile/upload/2025/05/12/10011_20250512225604A010.jpg', '3.1kg', '2024-05-14 00:00:00', '红米', 15, '盒装', '高性价比', 1, 0, '【政府补贴立减20%】小米RedmiBook 16 2024英特尔酷睿标压轻薄商务笔记本办公商务电脑');
INSERT INTO `t_product` VALUES (26, '小新Pro16', 4899, 600, '/profile/upload/2025/05/12/10012_20250512225657A011.jpg', '2.8', '2025-02-03 00:00:00', '联想', 15, '盒装', '畅销', 1, 0, '【政府补贴20%】联想小新Pro14/Pro16 旗舰锐龙版标压R7 14/16英寸轻薄本笔记本电脑 学习办公便携笔记本电脑');
INSERT INTO `t_product` VALUES (27, '酸枣仁茯苓糕', 89, 423, '/profile/upload/2025/05/13/10001_20250513104709A002.jpg', '100g', '2025-04-08 00:00:00', '协和京', 1, '盒装', '健康', 0, 0, '酸枣仁茯苓糕安睡晚安膏睡眠零食传统中式糕点健康养生无糖精食品');
INSERT INTO `t_product` VALUES (28, '秋梨糕', 89, 67, '/profile/upload/2025/05/13/10002_20250513104906A003.jpg', '80g', '2025-05-01 00:00:00', '协和京', 1, '盒装', '健康', 0, 0, '秋梨糕枇杷雪梨膏传统中式糕点特色小零食高端无糖精食品健康清肺');
INSERT INTO `t_product` VALUES (29, '每日坚果礼盒', 49, 677, '/profile/upload/2025/05/13/10003_20250513105024A004.jpg', '750g', '2025-04-24 00:00:00', '百草味', 1, '袋装', '零食', 1, 0, '百草味每日坚果礼盒750g/30包孕妇健康零食混合干果仁休闲大礼包');
INSERT INTO `t_product` VALUES (31, '辣卤零食大礼包', 21, 100, '/profile/upload/2025/05/13/10005_20250513105614A006.jpg', '2.5kg', '2025-04-23 00:00:00', '百草味', 1, '袋装', '零食', 1, 0, '【到手65包】百草味辣卤零食大礼包肉脯卤味网红解馋小吃休闲');
INSERT INTO `t_product` VALUES (32, '花椒锅巴', 10, 352, '/profile/upload/2025/05/13/10006_20250513105737A007.jpg', '150g', '2025-05-05 00:00:00', '二阳', 1, '袋装', '零食', 1, 0, '\n二阳花椒锅巴手工零食小吃休闲食品吃货解馋网红爆款年货节办公室');
INSERT INTO `t_product` VALUES (34, '藤椒辣条', 9, 231, '/profile/upload/2025/05/13/10010_20250513110118A010.jpg', '60g', '2025-05-05 00:00:00', '馋小乖', 1, '袋装', '辣条', 1, 0, '【藤椒辣条组合】馋小乖平江特产麻辣小吃解馋零食包爆款年货手工');
INSERT INTO `t_product` VALUES (35, '全麦沙琪玛', 15, 21, '/profile/upload/2025/05/13/10011_20250513110257A011.jpg', '60g', '2025-05-04 00:00:00', '歌本', 1, '盒装', '零食', 0, 0, '哥本优选全麦沙琪玛奶盖萨其马无低减早代餐糕点糖脂休闲零食品卡');
INSERT INTO `t_product` VALUES (37, '冷榨柠檬液', 13, 154, '/profile/upload/2025/05/13/10002_20250513110946A013.jpg', '225ml', '2025-05-06 00:00:00', '胖东来', 2, '盒装', '0脂', 0, 0, '胖东来柠檬浓缩汁冷榨柠檬液0脂肪NFC西梅果汁维c饮料冲剂柠檬汁');
INSERT INTO `t_product` VALUES (38, '冲饮柠檬水', 10, 652, '/profile/upload/2025/05/13/10003_20250513111113A014.jpg', '10g', '2025-04-28 00:00:00', '蜜雪冰城', 2, '条装', '冲饮', 0, 0, '蜜雪冰城冲饮柠檬水浓缩柠檬液独立条装便携携带冲泡果茶冰镇饮料');
INSERT INTO `t_product` VALUES (39, '泰韩国风味果肉果汁饮料', 10, 214, '/profile/upload/2025/05/13/10004_20250513111226A015.jpg', '238ml', '2025-04-27 00:00:00', '吉韩源泰', 2, '罐装', '果汁', 1, 0, '【礼盒装】吉韩源泰韩国风味果肉果汁饮料葡萄汁年货送礼整箱饮品');
INSERT INTO `t_product` VALUES (41, '冰茶', 53, 200, '/profile/upload/2025/05/13/10006_20250513111926A017.jpg', '350ml', '2025-05-06 00:00:00', '元气森林', 2, '瓶装', '畅销', 0, 0, '元气森林冰茶 冰爆柠檬红茶 减糖茶饮料600mL*15瓶 整箱');
INSERT INTO `t_product` VALUES (42, '冰红茶可乐', 52, 21, '/profile/upload/2025/05/13/10007_20250513112112A018.jpg', '600ml', '2025-05-01 00:00:00', '元气森林', 2, '瓶装', '畅销', 1, 0, '元气森林霸气含糖汽水冰红茶可乐600ml*15瓶');
INSERT INTO `t_product` VALUES (43, '苏打水', 36, 312, '/profile/upload/2025/05/13/10008_20250513112300A019.jpg', '220ml', '2025-05-06 00:00:00', '名仁', 2, '瓶装', '畅销', 1, 0, '名仁苏打水饮料弱碱性水孕妇饮用水明仁无汽无糖苏打水整箱24瓶');
INSERT INTO `t_product` VALUES (44, '乌龙茶', 19, 32, '/profile/upload/2025/05/13/10009_20250513112414A020.jpg', '125ml', '2025-05-02 00:00:00', '旺旺', 2, '盒装', '热卖', 0, 0, '旺旺饮料冰红冰绿无糖乌龙茉莉味茶果汁125ml*36小盒饮料整箱批发');
INSERT INTO `t_product` VALUES (45, '电解质冲剂粉', 9, 213, '/profile/upload/2025/05/13/10010_20250513112524A021.jpg', '40g', '2025-05-06 00:00:00', '诺特兰', 2, '条装', '电解质饮品', 1, 0, '\n诺特兰德电解质冲剂粉补电解质水多种维生素牛磺酸运动健身非饮料');

SET FOREIGN_KEY_CHECKS = 1;
