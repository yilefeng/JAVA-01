DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `product_id` bigint(20) unsigned NOT NULL COMMENT '关联商品id',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户表id',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '购买数量',
  `unit_price` INT NOT NULL DEFAULT '0' COMMENT '单价,单位分',
  `real_price` INT NOT NULL DEFAULT '0' COMMENT '实际支付价，单位分',
  `discount_price` INT NOT NULL DEFAULT '0' COMMENT '让利价格，单位分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `product_code` varchar(32) NOT NULL DEFAULT '' COMMENT '商品code',
  `product_name` varchar(50) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_price` INT NOT NULL DEFAULT '0' COMMENT '商品价格，单位分',
  `short_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '简要描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_idx_product_code` (`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `real_name` varchar(30) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(40) NOT NULL DEFAULT '' COMMENT '邮箱',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_idx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';