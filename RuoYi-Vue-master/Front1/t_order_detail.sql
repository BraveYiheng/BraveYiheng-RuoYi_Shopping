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

 Date: 14/05/2025 15:35:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail`  (
  `order_detail_id` int NOT NULL AUTO_INCREMENT COMMENT '订单详情的唯一标识，自增',
  `order_product_id` int NULL DEFAULT NULL COMMENT '关联商品表的外键',
  `order_product_count` int NULL DEFAULT NULL COMMENT '订单中商品的数量',
  `order_id` int NULL DEFAULT NULL COMMENT '关联订单表的外键',
  PRIMARY KEY (`order_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '存储订单详细信息的表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------
INSERT INTO `t_order_detail` VALUES (1, 1, 2, 1);
INSERT INTO `t_order_detail` VALUES (2, 2, 3, 2);
INSERT INTO `t_order_detail` VALUES (3, 3, 1, 4);
INSERT INTO `t_order_detail` VALUES (4, 4, 2, 4);
INSERT INTO `t_order_detail` VALUES (9, 9, 2, 9);

SET FOREIGN_KEY_CHECKS = 1;
