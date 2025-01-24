-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 24 jan. 2025 à 14:15
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
-- Structure de la table `arret`
--

DROP TABLE IF EXISTS `arret`;
CREATE TABLE IF NOT EXISTS `arret` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `type_arret` enum('TERMINUS','INTERMEDIAIRE') NOT NULL DEFAULT 'INTERMEDIAIRE',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `arret`
--

INSERT INTO `arret` (`id`, `nom`, `type_arret`) VALUES
(1, 'Central City', 'INTERMEDIAIRE'),
(2, 'Star City', 'INTERMEDIAIRE'),
(3, 'Coast City', 'INTERMEDIAIRE'),
(4, 'Central City', 'INTERMEDIAIRE'),
(5, 'Star City', 'INTERMEDIAIRE'),
(6, 'Coast City', 'INTERMEDIAIRE'),
(7, 'Gare de Pontoise', 'TERMINUS');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `nom` enum('admin','employee') NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `trajet`
--

DROP TABLE IF EXISTS `trajet`;
CREATE TABLE IF NOT EXISTS `trajet` (
  `code` varchar(100) NOT NULL,
  `tempsDepart` datetime NOT NULL,
  `tempsArrivee` datetime NOT NULL,
  `arretDepart_id` int NOT NULL,
  `arretArrivee_id` int NOT NULL,
  PRIMARY KEY (`code`),
  KEY `arretDepart_id` (`arretDepart_id`),
  KEY `arretArrivee_id` (`arretArrivee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `trajet`
--

INSERT INTO `trajet` (`code`, `tempsDepart`, `tempsArrivee`, `arretDepart_id`, `arretArrivee_id`) VALUES
('FLASH1', '2025-01-08 14:55:52', '2025-01-08 16:55:52', 2, 3);

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
