CREATE TABLE `v1_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `user_name` varchar(64) NOT NULL DEFAULT '',
  `lv` int(11) NOT NULL DEFAULT '1',
  `exp` int(11) NOT NULL DEFAULT '0',
  `remark` varchar(64) NOT NULL DEFAULT '',
  `vip` tinyint(4) NOT NULL DEFAULT '0',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_users_user_id` (`user_id`)
);

CREATE TABLE `v1_user_vehicles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `vehicle` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_v1_user_vehicles_user_id` (`user_id`)
);

CREATE TABLE `v1_user_rubbishes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `type_id` smallint(6) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_user_rubbishes_user_id_type_id` (`user_id`,`type_id`)
);

CREATE TABLE `v1_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_accounts_user_id` (`user_id`)
);

CREATE TABLE `v1_cheating_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `speed_up_rate` decimal(15,2) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_v1_cheating_users_user_id` (`user_id`)
);

CREATE TABLE `v1_goods_payment_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `goods_key` varchar(64) NOT NULL,
  `amount` int(11) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_v1_goods_payment_records_user_id` (`user_id`)
);

CREATE TABLE `v1_rubbish_recycle_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `rubbish_count` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_v1_rubbish_recycle_records_user_id` (`user_id`)
);

CREATE TABLE `v1_recharge_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `amount` int(11) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_v1_recharge_records_user_id` (`user_id`)
);

CREATE TABLE `v1_user_register_infos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `gender` varchar(16) NOT NULL,
  `qq` varchar(32) NOT NULL,
  `email` varchar(64) NOT NULL,
  `introduction` varchar(256) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_user_register_infos_login_name` (`login_name`),
  UNIQUE KEY `idx_v1_user_register_infos_user_id` (`user_id`)
);

CREATE TABLE `v1_promotion_link_stats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `promotion_count` bigint(20) NOT NULL DEFAULT '0',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
);

CREATE TABLE `v1_user_pets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pet_id` varchar(64) NOT NULL,
  `pet_type` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_user_pets_pet_id` (`pet_id`),
  KEY `idx_v1_user_pets_user_id` (`user_id`)
);

CREATE TABLE `v1_user_equipments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `equipment_type` varchar(32) NOT NULL,
  `equipment_key` varchar(64) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_v1_user_equipments_user_id` (`user_id`)
);

CREATE TABLE `v1_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `equipment_type` varchar(32) NOT NULL,
  `key` varchar(64) NOT NULL,
  `price` bigint(20) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_goods_key` (`key`),
  KEY `idx_v1_goods_equipment_type` (`equipment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;