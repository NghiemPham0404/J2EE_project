-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for j2ee_project
CREATE DATABASE IF NOT EXISTS `j2ee_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `j2ee_project`;

-- Dumping structure for table j2ee_project.account
CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.account: ~4 rows (approximately)
INSERT INTO `account` (`id`, `name`, `birth_date`, `email`, `username`, `password`, `role_id`, `active`) VALUES
	(1, 'Admin', '2000-10-14', 'admin@gmail.com', 'admin', '$2a$10$MWTLsoZwIGxZbNXxyanJG.11bVqMqFSaOCzTqLpeNjcVoadU/ICDa', 1, 1),
	(2, 'Nguyen Van Anh', '2003-08-13', 'nguyenvana@gmail.com', 'nguyena', '$2a$10$K09xFUB8MwzNRT41BiJane7hY1VYEiab.p6j513GDU3hB9JN/GKua', 2, 1),
	(3, 'Tran Van Bình', '2001-12-11', 'tranbinh@gmail.com', 'tranbinh', '$2a$10$K09xFUB8MwzNRT41BiJane7hY1VYEiab.p6j513GDU3hB9JN/GKua', 3, 1),
	(4, 'Nguyen Van Anh', '2003-08-11', 'nguyenvana@gmail.com', 'nguyena', '$2a$10$/cP5WZeOU3Fw7Ut8.IHIS.uvXc37cS7QI09fo2EHv2Cpp./.APob.', 1, 1);

-- Dumping structure for table j2ee_project.action
CREATE TABLE IF NOT EXISTS `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.action: ~3 rows (approximately)
INSERT INTO `action` (`id`, `name`) VALUES
	(1, 'Account Management'),
	(2, 'Charity Event Management'),
	(3, 'Post Management'),
	(4, 'Accounting');

-- Dumping structure for table j2ee_project.charity_event
CREATE TABLE IF NOT EXISTS `charity_event` (
  `id` char(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `goal_amount` decimal(10,2) DEFAULT NULL,
  `is_disbursed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.charity_event: ~2 rows (approximately)
INSERT INTO `charity_event` (`id`, `name`, `description`, `start_time`, `end_time`, `goal_amount`, `is_disbursed`) VALUES
	('34323233-6233-3764-2d62-6438322d3466', 'Gây quỹ trao tặng cơ hội phẫu thuật cho 20 em bé hở môi, hàm ếch (Tháng 10/2024)', 'Năm 2024 đánh dấu năm thứ 35 Operation Smile hoạt động tại Việt Nam, hãy cùng chúng tôi tiếp sức cho 20 hoàn cảnh khó khăn ở các địa phương vùng cao phía Bắc có cơ hội tìm lại nụ cười cho con em mình.', '2024-10-01 12:45:17', '2024-11-01 12:45:18', 30000.00, 0),
	('37313236-6663-3238-2d64-3633322d3461', 'Chung tay giúp đỡ các tỉnh miền Trung bị thiệt hại do mưa lũ.', 'Nhằm chia sẻ những khó khăn mà đồng bào miền Trung đang phải gánh chịu, được sự đồng ý của Thường trực Tỉnh ủy, Ủy ban Mặt trận Tổ quốc Việt Nam tỉnh đã kêu gọi “Toàn dân tham gia ủng hộ miền Trung bị thiệt hại do mưa lũ”.', '2024-09-01 00:00:00', '2024-09-30 23:59:59', 30000.00, 0);

-- Dumping structure for table j2ee_project.post
CREATE TABLE IF NOT EXISTS `post` (
  `id` char(36) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `body` text DEFAULT NULL,
  `time_post` datetime DEFAULT NULL,
  `approved` datetime DEFAULT NULL,
  `thumb_img` varchar(255) DEFAULT NULL,
  `ce_id` char(36) DEFAULT NULL,
  `ac_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ce_id` (`ce_id`),
  KEY `ac_id` (`ac_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`ce_id`) REFERENCES `charity_event` (`id`),
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`ac_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.post: ~3 rows (approximately)
INSERT INTO `post` (`id`, `title`, `body`, `time_post`, `approved`, `thumb_img`, `ce_id`, `ac_id`) VALUES
	('6eb66d3f-48bb-4d76-8eae-993d5a2d10b0', 'Ủng hộ 2', 'Ủng hộ', '2024-11-05 22:36:21', '2024-11-05 22:36:20', 'sfsdf', '37313236-6663-3238-2d64-3633322d3461', 1),
	('c1f2048b-5891-46c2-ba19-47a872afaf15', 'Ủng hộ 1', 'Ủng hộ', '2024-11-05 22:36:21', '2024-11-05 22:36:20', 'sfsdf', '37313236-6663-3238-2d64-3633322d3461', 1),
	('eed94c01-fdc2-4057-8de1-c2f55eb2055a', 'Ủng hộ 1', 'Ủng hộ', '2024-11-05 22:36:21', '2024-11-05 22:36:20', 'sfsdf', '37313236-6663-3238-2d64-3633322d3461', 1),
	('fef8e348-46af-4b2a-87d4-af803ba0c952', 'string', 'string', '2024-11-22 15:26:19', '2024-11-22 15:26:19', 'string', '37313236-6663-3238-2d64-3633322d3461', 1);

-- Dumping structure for table j2ee_project.post_view
CREATE TABLE IF NOT EXISTS `post_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `post_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `FK_post_view_post` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.post_view: ~0 rows (approximately)
INSERT INTO `post_view` (`id`, `time`, `post_id`) VALUES
	(1, '2024-11-22 14:37:55', 'c1f2048b-5891-46c2-ba19-47a872afaf15');

-- Dumping structure for table j2ee_project.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `name`) VALUES
	(1, 'Adminstrator'),
	(2, 'Collaborator'),
	(3, 'Accountant');

-- Dumping structure for table j2ee_project.role_action
CREATE TABLE IF NOT EXISTS `role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `action_id` int(11) DEFAULT NULL,
  `_create` tinyint(1) unsigned zerofill NOT NULL DEFAULT 0,
  `_update` tinyint(1) unsigned zerofill NOT NULL DEFAULT 0,
  `_delete` tinyint(1) unsigned zerofill NOT NULL DEFAULT 0,
  `_read` tinyint(1) unsigned zerofill NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id_action_id` (`role_id`,`action_id`),
  KEY `action_id` (`action_id`),
  CONSTRAINT `role_action_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_action_ibfk_2` FOREIGN KEY (`action_id`) REFERENCES `action` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.role_action: ~8 rows (approximately)
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `_create`, `_update`, `_delete`, `_read`) VALUES
	(1, 1, 1, 1, 1, 1, 1),
	(2, 1, 2, 1, 1, 1, 1),
	(3, 1, 3, 1, 1, 1, 1),
	(4, 1, 4, 0, 0, 0, 1),
	(5, 2, 2, 1, 1, 1, 1),
	(6, 2, 3, 1, 1, 1, 1),
	(7, 3, 4, 0, 1, 0, 1),
	(8, 3, 2, 0, 0, 0, 1);

-- Dumping structure for table j2ee_project.transfer_session
CREATE TABLE IF NOT EXISTS `transfer_session` (
  `id` char(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `description` varchar(280) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `ce_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ce_id` (`ce_id`),
  CONSTRAINT `transfer_session_ibfk_1` FOREIGN KEY (`ce_id`) REFERENCES `charity_event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table j2ee_project.transfer_session: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
