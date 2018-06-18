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
  `phoneNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `firstName`, `lastName`, `email`, `password`, `phoneNumber`) VALUES
(1, 'Mitch', 'Stone', 'mitchStone@email.com', 'seasame', '0404040404'),
(2, 'aa', 'aa', 'aa@aa.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', '0606066060'),
(4, 'Gaben', 'Newell', 'lordgaben@steam.money', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL),
(5, 'Bill', 'Gates', 'billgates@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', '01010101');

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE `assessment` (
  `assessmentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assessment`
--

INSERT INTO `assessment` (`assessmentID`, `courseID`, `title`, `description`, `date`) VALUES
(1, 1, 'History Of Pepe', 'Research the history of the pepe meme.', '2018-05-31'),
(2, 1, 'Classic Memes', 'Research the History of the most classic and iconic memes.', '2018-06-23'),
(3, 1, 'Foreign Memes 1 - Italian memes', 'Research the most popular memes used in the ex-Roman Empire.', '2018-06-30'),
(4, 2, 'HTML5', 'Do Hello world in HTMl5.', '2018-06-30'),
(5, 2, 'CSS3', 'Style Hello World In CSS3.', '2018-06-22'),
(6, 3, 'Java', 'Learn Java.', '2018-06-20'),
(7, 3, 'C#', 'Learn C#.', '2018-06-15'),
(8, 4, 'EITS Learning Management App.', 'With the help of your jolly goodfellows build the EITS learning management app according to the project manager''s specs.', '2018-06-18'),
(9, 4, 'Document EITS', 'Draft a viable project to develop a complete product.', '2018-06-14'),
(10, 5, 'Attempt to learn Sourcetree', 'In case of failure refer to Mr. Stone.', '2018-06-29'),
(11, 4, 'MS Projects', 'Use MS Projects to create a viable project.', '2018-06-22'),
(12, 6, 'CPR Practice1', 'Do the CPR.', '2018-06-21'),
(13, 6, 'CPR Practice 2', 'Do more CPR.', '2018-06-16'),
(14, 7, 'Anatomy 1 ', 'Learn about your insides.', '2018-06-16'),
(15, 7, 'Anatomy 2', 'Learn about your mate''s insides.', '2018-06-22'),
(16, 7, 'Anatomy 3 ', 'Learn about your mate''s cat''s insides. He will not be pleased.', '2018-06-21'),
(17, 8, 'Emergency Vehicle equipment', 'Learn about the emergency vehicle equipment.', '2018-06-29'),
(18, 8, 'Speed driving course', 'Try to save people while driving like a nutcase.', '2018-06-30'),
(19, 9, 'hand washing', 'That''s about it.', '2018-06-22'),
(20, 9, 'Surgeon Hand Washing', 'A bit more.', '2018-06-21'),
(21, 10, 'Word and PP', 'MS Word and Power point.', '2018-06-15'),
(22, 10, 'Excell ', 'Like MySQL but green.', '2018-06-15'),
(23, 11, 'MS Projects', 'Learn how to use Projects.', '2018-06-16'),
(24, 12, 'MS Others', 'Learn about lesser known MS Office programs, also become pro at Excell and PP animations. ', '2018-06-15'),
(25, 13, 'Emergency Management Operations', 'Learn how to save all the people at the same time while sitting at your desk.', '2018-06-15'),
(26, 14, 'Photoshop', 'Learn photoshop', '2018-06-22'),
(27, 15, 'Illustrator', 'Learn Illustrator.', '2018-06-22'),
(28, 16, 'Learn lightroom', 'Learn that and all the other Adobe software.', '2018-06-22'),
(29, 17, 'Model making 1', 'Build a tiny house.', '2018-06-21'),
(30, 18, 'Model making 2', 'Build more tiny houses and call it a tiny city. Filled with tiny people and a tiny park.', '2018-06-22'),
(31, 19, 'Visual Arts 1', 'Learn about visual arts.', '2018-06-21'),
(32, 19, 'Performing Arts', 'Learn about performing arts.', '2018-06-22');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `date` date NOT NULL,
  `studentID` int(11) NOT NULL,
  `courseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `phoneNumber` varchar(30) DEFAULT NULL,
  `bio` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `caseworker`
--

INSERT INTO `caseworker` (`employeeID`, `firstName`, `lastName`, `email`, `password`, `specialty`, `phoneNumber`, `bio`) VALUES
(1, 'Dave', 'Hunt', 'david.hunt@hotmail.com', 'seasame', 'Stuff', '404040404', '2'),
(2, 'j', 'j', 'j', 'GJ9AA0vnoZnx+piRZo7jq2BJ+C04xovnD1luqy4YV7c=', NULL, NULL, NULL),
(3, 'Lamp', 'Blanket', 'lampblanket@email.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL),
(4, 'Jar', 'O''Mayo', 'jaromayo@email.mayo', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL),
(5, 'Neill', 'Blomkamp', 'district9@prawn.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', 'IT', '909090', 'Cool guy'),
(6, 'My', 'Smith', 'mysql@oracle.com', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', 'IT', '23232323', 'Cool');

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
(1, 'Meme History', 'Memes', 'Meme Campus', 69, 420),
(2, 'Internet Programming 1', 'IT', 'Coomera', 50, 1),
(3, 'Object Oriented Programming 1', 'IT', 'Coomera', 100, 1),
(4, 'Project Management 1', 'Management', 'Coomera', 100, 1),
(5, 'Basics of Sourcetree', 'Pain', 'Hell', 250, 1),
(6, 'CPR 1', 'Health', 'Coomera', 30, 1),
(7, 'Basic Anatomy 1', 'Health', 'Coomera', 100, 1),
(8, 'Ambulance Operations', 'Health', 'Southport', 200, 1),
(9, 'Sanitation', 'Health', 'Coomera', 20, 1),
(10, 'Basic MS Office', 'IT', 'Coomera', 100, 1),
(11, 'Intermediate MS Office', 'IT', 'Coomera', 200, 1),
(12, 'Advanced MS Office', 'IT', 'Coomera', 250, 1),
(13, 'Ambulance Dispatch', 'Health', 'Southport', 200, 1),
(14, 'Adobe Suite Basics', 'IT', 'Coomera', 100, 1),
(15, 'Adobe Suite Intermediate', 'IT', 'Coomera', 250, 1),
(16, 'Adobe Suite Advanced', 'IT', 'Coomera', 420, 1),
(17, 'Modelling 1 ', 'Design', 'Coomera', 300, 1),
(18, 'Modelling 2', 'Design', 'Coomera', 400, 1),
(19, 'Art History', 'History', 'Coomera', 100, 1);

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
(23, NULL, NULL, 'Bottle', 'O''Water', 'bottleowater@email.h2o', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(24, 2, 3, 'aaa', 'aaa', 'aaa', 'mDSHbc+wXLFnpcJJU+uljErImxrfV/KPL50JrxB+6PA=', NULL, NULL, NULL, NULL),
(25, 3, 4, 'lll', 'lll', 'lll', 'yqIu9aEp2n8d08ROcy3cnL4UHC9FQoWQ74ot3xJWLVU=', NULL, NULL, NULL, NULL);

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
  ADD KEY `employee` (`employeeID`),
  ADD KEY `diplomaID` (`diplomaID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `assessment`
--
ALTER TABLE `assessment`
  MODIFY `assessmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `caseworker`
--
ALTER TABLE `caseworker`
  MODIFY `employeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `studentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
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
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `caseworker` (`employeeID`),
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`diplomaID`) REFERENCES `diploma` (`diplomaID`) ON DELETE SET NULL ON UPDATE SET NULL;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
