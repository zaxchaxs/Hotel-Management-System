-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 22, 2024 at 12:42 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `management_kas_mahasiswa`
--

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `no_phone` varchar(100) DEFAULT NULL,
  `gender` enum('perempuan','laki-laki') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `name`, `no_phone`, `gender`) VALUES
('2310631170143', 'Najwa Latifah', '081382810290', 'perempuan'),
('2310631170161', 'Mochammad Fadli', '081398743800', 'laki-laki');

-- --------------------------------------------------------

--
-- Table structure for table `report_kas`
--

CREATE TABLE `report_kas` (
  `id` int NOT NULL,
  `nim_mahasiswa` varchar(100) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `income` double DEFAULT NULL,
  `expanse` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` enum('income','expanse') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `report_kas`
--

INSERT INTO `report_kas` (`id`, `nim_mahasiswa`, `tanggal`, `name`, `income`, `expanse`, `description`, `type`) VALUES
(1, '2310631170161', '2024-11-22', 'Mochammad Fadli', 1000000, 0, 'uang masuk', 'income'),
(2, '2310631170143', '2024-11-22', 'Najwa Latifah', 0, 750000, 'uang keluar', 'expanse');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `nim` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` enum('pending','approved') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`nim`, `name`, `username`, `password`, `status`) VALUES
('2310631170143', 'Najwa Latifah', 'najwa', 'password123', 'approved'),
('2310631170161', 'Mochammad Fadli', 'fadli', 'password123', 'approved');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `report_kas`
--
ALTER TABLE `report_kas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `nim_mahasiswa` (`nim_mahasiswa`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`nim`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `report_kas`
--
ALTER TABLE `report_kas`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `report_kas`
--
ALTER TABLE `report_kas`
  ADD CONSTRAINT `report_kas_ibfk_1` FOREIGN KEY (`nim_mahasiswa`) REFERENCES `mahasiswa` (`nim`) ON DELETE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `mahasiswa` (`nim`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
