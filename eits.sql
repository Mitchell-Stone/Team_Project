-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2018 at 02:27 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` int(11) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`) VALUES
(1, 'Mitch', 'Stone', 'mitchStone@email.com', 'seasame', '0404040404');

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE `assessment` (
  `assessmentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `grade` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `date submitted` date DEFAULT NULL,
  `passorfail` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`assessmentID`, `courseID`, `title`, `description`, `grade`, `date`, `date submitted`, `passorfail`) VALUES
(1, 1, 'History Of Pepe', 'Research the history of the pepe meme.', NULL, '2018-05-31', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `date` date NOT NULL,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`date`, `studentID`, `courseID`) VALUES
('2018-05-15', 2, 1);

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
  `phoneNymber` int(11) NOT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `image` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caseworker`
--

INSERT INTO `caseworker` (`employeeID`, `firstName`, `lastName`, `email`, `password`, `specialty`, `phoneNymber`, `bio`, `image`) VALUES
(1, 'Dave', 'Hunt', 'david.hunt@hotmail.com', 'seasame', 'Stuff', 404040404, '2', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `courseID` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `industry` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `numberOfHours` int(11) NOT NULL,
  `finishingDegree` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseID`, `name`, `industry`, `location`, `numberOfHours`, `finishingDegree`) VALUES
(1, 'Meme History', 'Memes', 'Meme Campus', 69, 420);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `studentID` int(11) NOT NULL,
  `courseID` int(11) DEFAULT NULL,
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

INSERT INTO `student` (`studentID`, `courseID`, `employeeID`, `firstName`, `lastName`, `email`, `password`, `industryPreference`, `visitCount`, `averageGrade`, `assessmentsPassed`) VALUES
(1, 1, 1, 'Jake', 'Smith', 'jakesmithlive.com', 'seasame', NULL, NULL, NULL, NULL),
(2, 1, 1, 'Matteo', 'Baldini', 'Matteo@live.com', 'seasame', NULL, NULL, NULL, NULL),
(3, 1, 1, 'Dion', 'Bird', 'DionBird@live.com', 'seasame', NULL, NULL, NULL, NULL),
(4, NULL, NULL, 'ggg', 'ggg', 'ggg', 'Vpx/C0HOlklgKgIYzQLtCwo9kxMDKUUcx4K339p5znE=', NULL, NULL, NULL, NULL),
(5, NULL, NULL, 'ooo', 'ooo', 'ooo', 'GtJdAAJpDcAuJwiil9jJ3x8WDTdvZjMJzCYcfJITZ+c=', NULL, NULL, NULL, NULL),
(6, NULL, NULL, 'www', 'www', 'www', 'fC7NB/FVZIQx4PlLiSR9cTxXhuHnPpU/L+fso5U0zW0=', NULL, NULL, NULL, NULL),
(7, NULL, NULL, 'qwe', 'qwe', 'qwe', 'SJzV28cIx+VB3k182Rzm0PFhNXO3/FtA05Qsy5VVzzU=', NULL, NULL, NULL, NULL),
(9, NULL, NULL, 'aaa', 'aaa', 'aaa', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `assessment`
--
ALTER TABLE `assessment`
  ADD PRIMARY KEY (`assessmentID`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`date`),
  ADD KEY `studentID` (`studentID`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `caseworker`
--
ALTER TABLE `caseworker`
  ADD PRIMARY KEY (`employeeID`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`courseID`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`studentID`),
  ADD KEY `courseID` (`courseID`),
  ADD KEY `courseID_2` (`courseID`),
  ADD KEY `employee` (`employeeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `assessment`
--
ALTER TABLE `assessment`
  MODIFY `assessmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `caseworker`
--
ALTER TABLE `caseworker`
  MODIFY `employeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `assessment`
--
ALTER TABLE `assessment`
  ADD CONSTRAINT `assessment_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`);

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`),
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `courses` (`courseID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
