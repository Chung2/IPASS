-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2017 at 06:38 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ipass`
--

-- --------------------------------------------------------

--
-- Table structure for table `resultaat`
--

CREATE TABLE `resultaat` (
  `id_speler` int(11) NOT NULL,
  `id_ronde` int(11) NOT NULL,
  `winnaar` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resultaat`
--

INSERT INTO `resultaat` (`id_speler`, `id_ronde`, `winnaar`) VALUES
(1, 1, 1),
(1, 4, 1),
(2, 1, 1),
(6, 1, 1),
(6, 4, 1),
(7, 1, 1),
(7, 4, 1),
(1, 5, 3),
(2, 5, 3),
(3, 5, 3),
(1, 3, 6),
(2, 3, 6),
(6, 3, 6),
(1, 2, 7),
(3, 2, 7);

-- --------------------------------------------------------

--
-- Table structure for table `ronde`
--

CREATE TABLE `ronde` (
  `id_ronde` int(11) NOT NULL,
  `id_spel` int(11) DEFAULT NULL,
  `tijd` time DEFAULT NULL,
  `notities` text,
  `naam` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ronde`
--

INSERT INTO `ronde` (`id_ronde`, `id_spel`, `tijd`, `notities`, `naam`) VALUES
(1, 2, '13:43:05', 'String', 'ronde1'),
(2, 3, '00:00:06', 'sS', 'ronde2'),
(3, 2, '00:00:16', 'gjhgjklhgfd', 'ronde1dfghjk'),
(4, 9, '00:00:26', '', 'rondeNaam2'),
(5, 9, '00:00:02', 'nog geen notities', 'Chungqert');

-- --------------------------------------------------------

--
-- Table structure for table `spel`
--

CREATE TABLE `spel` (
  `id_spel` int(11) NOT NULL,
  `naam` varchar(50) DEFAULT NULL,
  `instructies` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spel`
--

INSERT INTO `spel` (`id_spel`, `naam`, `instructies`) VALUES
(1, 'Kies een spel!', 'Kies een spel!'),
(2, 'Sh*t Head', 'Hier in komt de Instructies voor het spel Sh*t Head'),
(3, 'Pesten', 'Hier in komt de Instructies voor het spel Pesten'),
(4, 'Paardenrace', 'Hier in komt de Instructies voor het spel Paardenrace'),
(9, 'Oorlogje', 'Alle kaarten worden geschud en verdeeld onder de spelers. De speler naast de deler mag het spel beginnen. Iedereen heeft zijn kaarten in een dichte stapel voor zich op tafel liggen. Om de beurt moet iedere speler de bovenste kaart van zijn stapel omdraaien. Speluitleg Oorlogje spelenDe speler die de hoogste kaart heeft mag alle omgedraaide kaarten pakken en in en dichte stapel naast zich leggen. Wanneer er een 2, 6, boer en Aas zijn opgelegt heeft de speler die de Aas oplegde deze ronde dus gewonnen. Wanneer je stapel op is dan moet je de kaarten die je gewonnen hebt schudden en weer voor je leggen in een dichte stapel, hier speel je dan me verder. Wanneer een speler door al zijn kaarten heen is, is hij dood en ligt hij uit het spel. Diegene die als laatste overblijft is de overlever en heeft het spel gewonnen. De joker is altijd de hoogste kaart in het spel. Maar je kunt bijvoorbeeld ook van te voren afspreken dat de 8 de Joker verslaat, dit maakt het dan nog net wat spannender. Wanneer er 2 spelers zijn die allebei de hoogste kaart opleggen dan moet ze beide nog een gesloten kaart en dan weer een open kaart opleggen. Diegene die dan de hoogste kaart heeft opgelegt wint het spel. Hetzelfde geldt als het spel met 2 spelers gespeeld wordt. Als de spelers allebei dezelfde kaart opleggen moeten ze eerst een gesloten kaart opleggen gevolgd door een open kaart. De hoogste kaart wint. Lees meer: http://www.speluitleg.com/spelregels-oorlogje/');

-- --------------------------------------------------------

--
-- Table structure for table `speler`
--

CREATE TABLE `speler` (
  `id_speler` int(11) NOT NULL,
  `naam` varchar(50) DEFAULT NULL,
  `wachtwoord` varchar(50) DEFAULT NULL,
  `rol` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `speler`
--

INSERT INTO `speler` (`id_speler`, `naam`, `wachtwoord`, `rol`) VALUES
(1, 'Chung', 'test', 'admin'),
(2, 'Ward', 'test', 'admin'),
(3, 'Henkie', 'test', 'user'),
(4, 'Klaas', 'test', 'user'),
(5, 'Geen speler', 'test', 'user'),
(6, 'Arnoud', 'test', 'user'),
(7, 'Michiel', 'test', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `resultaat`
--
ALTER TABLE `resultaat`
  ADD PRIMARY KEY (`id_speler`,`id_ronde`),
  ADD KEY `id_speler` (`id_speler`),
  ADD KEY `FK_resultaat_ronde` (`id_ronde`),
  ADD KEY `FK_resultaat_speler_2` (`winnaar`);

--
-- Indexes for table `ronde`
--
ALTER TABLE `ronde`
  ADD PRIMARY KEY (`id_ronde`),
  ADD UNIQUE KEY `naam` (`naam`),
  ADD KEY `FK_ronde_spel` (`id_spel`);

--
-- Indexes for table `spel`
--
ALTER TABLE `spel`
  ADD PRIMARY KEY (`id_spel`);

--
-- Indexes for table `speler`
--
ALTER TABLE `speler`
  ADD PRIMARY KEY (`id_speler`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ronde`
--
ALTER TABLE `ronde`
  MODIFY `id_ronde` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `spel`
--
ALTER TABLE `spel`
  MODIFY `id_spel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `speler`
--
ALTER TABLE `speler`
  MODIFY `id_speler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `resultaat`
--
ALTER TABLE `resultaat`
  ADD CONSTRAINT `FK_resultaat_ronde` FOREIGN KEY (`id_ronde`) REFERENCES `ronde` (`id_ronde`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_resultaat_speler` FOREIGN KEY (`id_speler`) REFERENCES `speler` (`id_speler`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_resultaat_speler_2` FOREIGN KEY (`winnaar`) REFERENCES `speler` (`id_speler`) ON DELETE CASCADE;

--
-- Constraints for table `ronde`
--
ALTER TABLE `ronde`
  ADD CONSTRAINT `FK_ronde_spel` FOREIGN KEY (`id_spel`) REFERENCES `spel` (`id_spel`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
