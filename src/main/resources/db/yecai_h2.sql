DROP TABLE IF EXISTS `v1_users`;
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

DROP TABLE IF EXISTS `v1_user_vehicles`;
CREATE TABLE `v1_user_vehicles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `vehicle` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_v1_user_vehicles_user_id` (`user_id`)
);

DROP TABLE IF EXISTS `v1_accounts`;
CREATE TABLE `v1_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_v1_accounts_user_id` (`user_id`)
);

DROP TABLE IF EXISTS `v1_cheating_users`;
CREATE TABLE `v1_cheating_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) NOT NULL,
  `speed_up_rate` decimal(15,2) NOT NULL,
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);