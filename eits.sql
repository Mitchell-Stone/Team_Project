-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 04, 2018 at 10:09 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `adminID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`) VALUES
(1, 'Mitch', 'Stone', 'mitchStone@email.com', 'seasame', '0404040404'),
(2, 'aa', 'aa', 'aa@aa.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', '0606066060');

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

DROP TABLE IF EXISTS `assessment`;
CREATE TABLE IF NOT EXISTS `assessment` (
  `assessmentID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`assessmentID`),
  KEY `courseID` (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`assessmentID`, `courseID`, `title`, `description`, `date`) VALUES
(1, 1, 'History Of Pepe', 'Research the history of the pepe meme.', '2018-05-31');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `date` date NOT NULL,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  PRIMARY KEY (`date`),
  KEY `studentID` (`studentID`),
  KEY `courseID` (`courseID`)
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

DROP TABLE IF EXISTS `caseworker`;
CREATE TABLE IF NOT EXISTS `caseworker` (
  `employeeID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `specialty` varchar(255) DEFAULT NULL,
  `phoneNumber` int(11) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL,
  `image` blob,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caseworker`
--

INSERT INTO `caseworker` (`employeeID`, `firstName`, `lastName`, `email`, `password`, `specialty`, `phoneNumber`, `bio`, `image`) VALUES
(1, 'Dave', 'Hunt', 'david.hunt@hotmail.com', 'seasame', 'Stuff', 404040404, '2', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `industry` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `numberOfHours` int(11) NOT NULL,
  `finishingDegree` int(11) NOT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`courseID`, `name`, `industry`, `location`, `numberOfHours`, `finishingDegree`) VALUES
(1, 'Meme History', 'Memes', 'Meme Campus', 69, 420);

-- --------------------------------------------------------

--
-- Table structure for table `diploma`
--

DROP TABLE IF EXISTS `diploma`;
CREATE TABLE IF NOT EXISTS `diploma` (
  `diplomaID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(35) NOT NULL,
  `industry` varchar(35) NOT NULL,
  `location` varchar(35) NOT NULL,
  `degree` varchar(100) NOT NULL,
  PRIMARY KEY (`diplomaID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diploma`
--

INSERT INTO `diploma` (`diplomaID`, `name`, `industry`, `location`, `degree`) VALUES
(1, 'Diploma of Sofware Devoplment', 'IT', 'Coomara', 'Is a good one');

-- --------------------------------------------------------

--
-- Table structure for table `diplomatocourses`
--

DROP TABLE IF EXISTS `diplomatocourses`;
CREATE TABLE IF NOT EXISTS `diplomatocourses` (
  `dipolmaID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  KEY `dipolmaID` (`dipolmaID`),
  KEY `courseID` (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diplomatocourses`
--

INSERT INTO `diplomatocourses` (`dipolmaID`, `courseID`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `studentID` int(11) NOT NULL AUTO_INCREMENT,
  `diplomaID` int(11) DEFAULT NULL,
  `employeeID` int(11) DEFAULT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `industryPreference` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `averageGrade` int(11) DEFAULT NULL,
  `assessmentsPassed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`studentID`),
  KEY `employee` (`employeeID`),
  KEY `diplomaID` (`diplomaID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `diplomaID`, `employeeID`, `firstName`, `lastName`, `email`, `password`, `industryPreference`, `visitCount`, `averageGrade`, `assessmentsPassed`) VALUES
(1, 1, 1, 'Jake', 'Smith', 'jakesmithlive.com', 'seasame', NULL, NULL, NULL, NULL),
(2, 1, 1, 'Matteo', 'Baldini', 'Matteo@live.com', 'seasame', NULL, NULL, NULL, NULL),
(3, 1, 1, 'Dion', 'Bird', 'DionBird@live.com', 'seasame', NULL, NULL, NULL, NULL),
(6, 1, NULL, 'zz', 'zz', 'zz', 'SmC/fUvB5IV0TPfo0IYFJHUvyhzkIzG+fEOf0jBD8VE=', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
CREATE TABLE IF NOT EXISTS `submissions` (
  `assessmentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `studentID` int(11) NOT NULL,
  `grade` int(11) NOT NULL,
  KEY `assessmentID` (`assessmentID`),
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `submissions`
--

INSERT INTO `submissions` (`assessmentID`, `courseID`, `studentID`, `grade`) VALUES
(1, 1, 6, 2);

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
-- Constraints for table `diplomatocourses`
--
ALTER TABLE `diplomatocourses`
  ADD CONSTRAINT `diplomatocourses_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`),
  ADD CONSTRAINT `diplomatocourses_ibfk_2` FOREIGN KEY (`dipolmaID`) REFERENCES `diploma` (`diplomaID`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `caseworker` (`employeeID`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`diplomaID`) REFERENCES `diploma` (`diplomaID`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `submissions`
--
ALTER TABLE `submissions`
  ADD CONSTRAINT `submissions_ibfk_1` FOREIGN KEY (`assessmentID`) REFERENCES `assessment` (`assessmentID`),
  ADD CONSTRAINT `submissions_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`),
  ADD CONSTRAINT `submissions_ibfk_3` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
