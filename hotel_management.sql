-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 25, 2024 at 01:56 PM
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
-- Database: `hotel_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int NOT NULL,
  `employee_id` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `employee_id`, `email`, `name`, `phone_number`) VALUES
(1, 1, 'marsha@gmail.com', 'Marsha Aprilia', '081234567890'),
(2, 1, 'alexandra@gmail.com', 'Alexandra Melisa', '081234567891'),
(3, 2, 'kevin@gmail.com', 'Kevin Alfarizki', '081234567892'),
(4, 3, 'melviana@gmail.com', 'Melviana Putri', '081234567893'),
(11, 1, 'aprilia@gmail.com', 'Aprilia', '098132318013'),
(14, 1, 'bayu@gmail.com', 'Bayu', '01231231289'),
(16, 1, 'cici@gmail.com', 'caca', '009979871');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` enum('admin','staff') NOT NULL,
  `status` enum('approved','pending') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `email`, `username`, `name`, `password`, `role`, `status`) VALUES
(1, 'admin@gmail.com', 'admin', 'Admin Irzi', 'rahasia123', 'admin', 'approved'),
(2, 'staffirzi@gmail.com', 'staffirzi', 'Staff Irzi', 'rahasia123', 'staff', 'approved'),
(3, 'staffroby@gmail.com', 'staffroby', 'Staff Roby', 'rahasia123', 'staff', 'approved'),
(4, 'staffbayu@gmail.com', 'staffbayu', 'Staff Bayu', 'rahasia123', 'staff', 'approved'),
(6, 'staffmelisa@gmail.com', 'staffmelisa', 'Staff Melisa', 'rahasia123', 'staff', 'pending'),
(7, 'staffalexandra@gmail.com', 'staffalexandra', 'Staff Alexandra', 'rahasia123', 'staff', 'pending'),
(8, 'staffmarsha@gmail.com', 'staffmarsha', 'Staff Marsha', 'rahasia123', 'staff', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` varchar(50) NOT NULL,
  `amount` double NOT NULL,
  `payment_date` date NOT NULL,
  `payment_method` varchar(100) NOT NULL,
  `status` enum('approved','pending','canceled') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `amount`, `payment_date`, `payment_method`, `status`) VALUES
('1f5ea9fc-6bf8-4841-adb5-5b15cc570b17', 500000, '2024-11-19', 'Mandiri', 'approved'),
('3d679b5e-5299-44be-b742-172609fa59a4', 1500000, '2024-11-19', 'Mandiri', 'approved'),
('3ea60412-8479-4107-896f-f7a9314206fd', 4000000, '2024-11-16', 'Paypal', 'approved'),
('5407077d-b93f-470e-b9b9-7cef3c2c7097', 1000000, '2024-11-19', 'Mandiri', 'approved'),
('6d6a2e8a-cf40-410f-935c-bc02e59dc52e', 500000, '2024-11-16', 'Cash', 'approved'),
('863455d6-3ee0-407e-ba04-b7cacd5c9ab4', 3000000, '2024-11-16', 'Credit Card', 'approved'),
('bce77761-80c9-49eb-ad5f-8f0d9a92f4a8', 1500000, '2023-11-19', 'Mandiri', 'approved'),
('c8227d6d-7f86-48ad-ba75-db2efc366258', 1000000, '2024-11-16', 'Credit Card', 'pending'),
('e9ee4360-7a9a-4ba6-ae80-d988b9fd3dff', 500000, '2024-11-19', 'Mandiri', 'approved'),
('ea11a784-9c1e-4fbb-a778-ebc7994cb981', 1500000, '2024-11-19', 'Mandiri', 'approved'),
('f4585c6b-ae70-4c2a-ab83-51113a0eec00', 1500000, '2024-11-30', 'Mandiri', 'approved');

-- --------------------------------------------------------

--
-- Table structure for table `reserved_room`
--

CREATE TABLE `reserved_room` (
  `id` int NOT NULL,
  `payment_id` varchar(50) DEFAULT NULL,
  `customer_id` int NOT NULL,
  `room_id` varchar(50) NOT NULL,
  `check_in_date` date NOT NULL,
  `check_out_date` date NOT NULL,
  `day_reserved` int NOT NULL,
  `price_total` double NOT NULL,
  `status` enum('reserved','pending','completed') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reserved_room`
--

INSERT INTO `reserved_room` (`id`, `payment_id`, `customer_id`, `room_id`, `check_in_date`, `check_out_date`, `day_reserved`, `price_total`, `status`) VALUES
(1, '3ea60412-8479-4107-896f-f7a9314206fd', 1, 'E001', '2024-11-20', '2024-11-24', 4, 4000000, 'reserved'),
(2, '863455d6-3ee0-407e-ba04-b7cacd5c9ab4', 2, 'E002', '2024-11-22', '2024-11-25', 3, 3000000, 'pending'),
(3, 'c8227d6d-7f86-48ad-ba75-db2efc366258', 3, 'S001', '2024-11-18', '2024-11-23', 2, 1000000, 'completed'),
(4, '6d6a2e8a-cf40-410f-935c-bc02e59dc52e', 4, 'S001', '2024-11-19', '2024-11-20', 1, 500000, 'completed'),
(5, 'f4585c6b-ae70-4c2a-ab83-51113a0eec00', 11, 'S008', '2024-11-19', '2024-11-20', 3, 1500000, 'reserved'),
(8, '3d679b5e-5299-44be-b742-172609fa59a4', 14, 'S007', '2024-11-19', '2024-11-21', 3, 1500000, 'completed'),
(10, '1f5ea9fc-6bf8-4841-adb5-5b15cc570b17', 16, 'S006', '2024-11-19', '2024-11-20', 1, 500000, 'reserved');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `type` enum('standard','exclusive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `name`, `price`, `type`) VALUES
('E001', 'Exclusive Room 1', 1000000, 'exclusive'),
('E002', 'Exclusive Room 2', 1000000, 'exclusive'),
('E003', 'Exclusive Room 3', 1000000, 'exclusive'),
('E004', 'Exclusive Room 4', 1000000, 'exclusive'),
('E005', 'Exclusive Room 5', 1000000, 'exclusive'),
('E006', 'Exclusive Room 6', 1000000, 'exclusive'),
('E007', 'Exclusive Room 7', 1000000, 'exclusive'),
('E008', 'Exclusive Room 8', 1000000, 'exclusive'),
('S001', 'Standard Room 1', 500000, 'standard'),
('S002', 'Standard Room 2', 500000, 'standard'),
('S003', 'Standard Room 3', 500000, 'standard'),
('S004', 'Standard Room 4', 500000, 'standard'),
('S005', 'Standard Room 5', 500000, 'standard'),
('S006', 'Standard Room 6', 500000, 'standard'),
('S007', 'Standard Room 7', 500000, 'standard'),
('S008', 'Standard Room 8', 500000, 'standard');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reserved_room`
--
ALTER TABLE `reserved_room`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `payment_id` (`payment_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `reserved_room`
--
ALTER TABLE `reserved_room`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `reserved_room`
--
ALTER TABLE `reserved_room`
  ADD CONSTRAINT `reserved_room_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserved_room_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserved_room_ibfk_3` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
