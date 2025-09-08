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

 Date: 14/05/2025 15:37:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_orders
-- ----------------------------
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE `t_orders`  (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单的唯一标识，自增',
  `order_user_id` int NULL DEFAULT NULL COMMENT '关联用户表查（哪个用户的订单）',
  `order_money` decimal(10, 0) NULL DEFAULT NULL COMMENT '订单总金额',
  `order_status` int NULL DEFAULT NULL COMMENT '订单状态 待支付 已支付 已发货 已完成 已退款 已取消',
  `order_add_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间 默认当前时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存储订单信息的表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_orders
-- ----------------------------
INSERT INTO `t_orders` VALUES (1, 1, 60, 5, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (2, 2, 15, 6, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (3, 3, 15, 2, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (4, 4, 160, 2, '2025-05-05 00:00:00');
INSERT INTO `t_orders` VALUES (5, 5, 50, 1, '2025-04-28 00:00:00');
INSERT INTO `t_orders` VALUES (6, 6, 200, 2, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (7, 7, 120, 3, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (8, 8, 300, 0, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (9, 9, 80, 2, '2025-05-09 00:00:00');
INSERT INTO `t_orders` VALUES (10, 10, 10, 1, '2025-05-09 16:02:12');
INSERT INTO `t_orders` VALUES (11, NULL, 1000, 4, '2025-05-11 00:00:00');
INSERT INTO `t_orders` VALUES (12, 2, 11111, 0, '2025-05-11 00:00:00');
INSERT INTO `t_orders` VALUES (13, 4, 10, 0, '2025-05-11 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
