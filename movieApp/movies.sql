-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2019 at 07:44 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `movies`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Image` varchar(500) NOT NULL,
  `video` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`id`, `Name`, `Description`, `Image`, `video`) VALUES
(1, 'Fast and Furious', 'Hello', 'http://www.fubiz.net/wp-content/uploads/2016/02/mixedblockbusters-2-900x1273.jpg', 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4'),
(2, 'Maze Runner', 'sdf', 'https://cdn.cinematerial.com/p/297x/9ebaqxve/maze-runner-the-death-cure-movie-poster-md.jpg', 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4'),
(3, 'Moon Light', 'sdnv', 'https://cdn.pastemagazine.com/www/system/images/photo_albums/best-movie-posters-2016/large/moonlight-ver2-xlg.jpg', 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4'),
(4, 'Fight Club', 'SDF', 'https://i.pinimg.com/originals/fd/5e/66/fd5e662dce1a3a8cd192a5952fa64f02.jpg', 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4'),
(5, 'Star wars', 'SDKFN', 'https://i.pinimg.com/236x/b2/b8/e5/b2b8e5daddd56ccf4d9846994160e915.jpg', 'http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `email`, `password`) VALUES
(1, 'mina', 'mina', 'mina@yahoo.com', '123'),
(2, 'mina', 'mm', 'm@yahoo.com', '123'),
(3, 'mina', 'asd', '123@yahkk', '123'),
(4, 'Hello', 'hello', 'helo@yahoo.com', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
