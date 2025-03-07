-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 07 mars 2025 à 13:19
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `easytrain`
--

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `dateEmbauche` date NOT NULL,
  `role` enum('admin','employee') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `login`, `mdp`, `nom`, `prenom`, `dateEmbauche`, `role`) VALUES
(1, 'barryh', 'flashpoint1946', 'Bartholomew Henry', 'Allen', '2023-06-14', 'employee'),
(2, 'barryh', 'flashpoint1946', 'Bartholomew Henry', 'Allen', '2023-06-14', 'employee'),
(3, 'superman', 'cryptonite1938', 'Kent', 'Clark', '2025-01-08', 'employee'),
(4, 'superman', 'cryptonite1938', 'Kent', 'Clark', '2025-01-08', 'employee'),
(5, 'superman', 'cryptonite1938', 'Kent', 'Clark', '2025-01-09', 'employee');

--
-- Déclencheurs `utilisateur`
--
DROP TRIGGER IF EXISTS `before_insert_utilisateur`;
DELIMITER $$
CREATE TRIGGER `before_insert_utilisateur` BEFORE INSERT ON `utilisateur` FOR EACH ROW BEGIN
    IF NEW.nom = 'test' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Le nom ne peut pas être égal à "test" pour un utilisateur';
    END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `before_update_utilisateur`;
DELIMITER $$
CREATE TRIGGER `before_update_utilisateur` BEFORE UPDATE ON `utilisateur` FOR EACH ROW BEGIN
    IF NEW.nom = 'test' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Le nom ne peut pas être égal à "test" pour un utilisateur';
    END IF;
END
$$
DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
