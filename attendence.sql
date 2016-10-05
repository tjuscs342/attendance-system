-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-10-05 06:38:37
-- 服务器版本： 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendence`
--

-- --------------------------------------------------------

--
-- 表的结构 `apply_basic`
--

CREATE TABLE `apply_basic` (
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `application_id` bigint(20) UNSIGNED NOT NULL,
  `apply_date` date NOT NULL,
  `audit_date` date DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `reason` varchar(256) DEFAULT NULL,
  `apply_type` smallint(6) NOT NULL,
  `result` tinyint(4) DEFAULT NULL,
  `operator_id` bigint(20) UNSIGNED DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `operator_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `apply_basic`
--

INSERT INTO `apply_basic` (`user_id`, `application_id`, `apply_date`, `audit_date`, `start_date`, `end_date`, `reason`, `apply_type`, `result`, `operator_id`, `remark`, `operator_name`) VALUES
(116, 2, '2016-09-07', NULL, '2016-09-07', '2016-09-07', 'for a joke', 1, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE `user_info` (
  `user_id` bigint(11) UNSIGNED NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `boss_id` bigint(11) UNSIGNED NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `age` int(11) NOT NULL,
  `child_num` int(11) NOT NULL,
  `user_power` smallint(6) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `sex` tinyint(4) NOT NULL,
  `marry_times` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`user_id`, `user_name`, `password`, `boss_id`, `gmt_create`, `gmt_modified`, `age`, `child_num`, `user_power`, `phone`, `sex`, `marry_times`) VALUES
(116, 'lichen.ll', '123', 123, '2016-08-31 11:02:24', '2016-08-31 11:02:24', 11, 0, 2, '4545454554', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `apply_basic`
--
ALTER TABLE `apply_basic`
  ADD PRIMARY KEY (`application_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `apply_basic`
--
ALTER TABLE `apply_basic`
  MODIFY `application_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `user_info`
--
ALTER TABLE `user_info`
  MODIFY `user_id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
