-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2018 at 11:40 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eits`
--

-- --------------------------------------------------------

--
-- Table structure for table `caseworker`
--

CREATE TABLE `caseworker` (
  `employeeID` int(11) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caseworker`
--

INSERT INTO `caseworker` (`employeeID`, `firstName`, `lastName`, `email`, `password`, `specialty`, `phoneNumber`, `bio`) VALUES
(1, 'Dave', 'Hunt', 'david.hunt@hotmail.com', 'seasame', 'Stuff', 404040404, '2'),
(2, 'j', 'j', 'j', 'GJ9AA0vnoZnx+piRZo7jq2BJ+C04xovnD1luqy4YV7c=', NULL, NULL, NULL),
(3, 'Lamp', 'Blanket', 'lampblanket@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL),
(4, 'Jar', 'O\'Mayo', 'jaromayo@email.mayo', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `diplomaID` int(11) DEFAULT NULL,
  `employeeID` int(11) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `industryPreference` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `averageGrade` int(11) DEFAULT NULL,
  `assessmentsPassed` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `diplomaID`, `employeeID`, `firstName`, `lastName`, `email`, `password`, `industryPreference`, `visitCount`, `averageGrade`, `assessmentsPassed`) VALUES
(2, 1, 1, 'Matteo', 'Baldini', 'Matteo@live.com', 'seasame', NULL, NULL, NULL, NULL),
(3, 1, 1, 'Dion', 'Bird', 'DionBird@live.com', 'seasame', NULL, NULL, NULL, NULL),
(7, 1, 3, 'o', 'o', 'o', 'ZcdMFaaGGHu2u/mVj0lPxrgAaANKZZqa1EmRsIxY8tI=', NULL, NULL, NULL, NULL),
(8, 1, 4, 'ggg', 'ggg', 'ggg', 'Vpx/C0HOlklgKgIYzQLtCwo9kxMDKUUcx4K339p5znE=', NULL, NULL, NULL, NULL),
(9, NULL, NULL, 'Matteo', 'Baldini', 'matteo.baldini@outlook.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(10, NULL, NULL, 'Mitchell', 'Stone', 'mitch.stone@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(11, NULL, NULL, 'Jake', 'Smith', 'jake.smith@email.ru', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(12, NULL, NULL, 'Dion', 'Bird', 'dion.bird@email.jp', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(13, NULL, NULL, 'David', 'Hunt', 'david.hunt@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(14, NULL, NULL, 'Scott', 'Green', 'scott.green@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(15, NULL, NULL, 'Post', 'Malone', 'post.malone@email.420', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(16, NULL, NULL, 'Bob', 'Marley', 'bob.marley@email.ja', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(17, NULL, NULL, 'Ziggy', 'Marley', 'z.marley@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(18, NULL, NULL, 'Harry', 'Potter', 'h.p@email.hogwarts', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(19, NULL, NULL, 'Ronald', 'Weasley', 'r.w@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(20, NULL, NULL, 'Hermione', 'Granger', 'h.g@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(21, NULL, NULL, 'Ronald', 'McDonald', 'r.mcd@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(22, NULL, NULL, 'Mudge', 'TheCat', 'mdgthc@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(23, NULL, NULL, 'Bottle', 'O\'Water', 'bottleowater@email.h2o', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(24, 2, 3, 'aaa', 'aaa', 'aaa', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(25, 3, 4, 'lll', 'lll', 'lll', 'yqIu9aEp2n8d08ROcy3cnL4UHC9FQoWQ74ot3xJWLVU=', NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `caseworker`
--
ALTER TABLE `caseworker`
  ADD PRIMARY KEY (`employeeID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`),
  ADD KEY `employee` (`employeeID`),
  ADD KEY `diplomaID` (`diplomaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `caseworker`
--
ALTER TABLE `caseworker`
  MODIFY `employeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `caseworker` (`employeeID`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`diplomaID`) REFERENCES `diploma` (`diplomaID`) ON DELETE SET NULL ON UPDATE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
