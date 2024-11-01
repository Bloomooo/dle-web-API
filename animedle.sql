-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 08 oct. 2024 à 19:55
-- Version du serveur : 8.0.31
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `animedle`
--

-- --------------------------------------------------------
CREATE DATABASE IF NOT EXISTS animedle;

USE animedle;

DROP TABLE IF EXISTS `banner`;
CREATE TABLE IF NOT EXISTS `banner` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `id_loottable` int NOT NULL,
                                        PRIMARY KEY (`id`),
    KEY `FK5m8fcdougjdex0b1niv2jiogh` (`id_loottable`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `characters`
--

DROP TABLE IF EXISTS `characters`;
CREATE TABLE IF NOT EXISTS `characters` (
                                            `id` int NOT NULL AUTO_INCREMENT,
                                            `id_media` int NOT NULL,
                                            PRIMARY KEY (`id`),
    KEY `FKf3ymq6jbi9hcrsgryow3yf3aa` (`id_media`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `nb_player` int DEFAULT NULL,
                                      `on_going` bit(1) DEFAULT NULL,
    `start_time` varchar(255) DEFAULT NULL,
    `id_gamemode` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK32ixoah5ndkad0hwjc0270hrf` (`id_gamemode`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `game_data`
--

DROP TABLE IF EXISTS `game_data`;
CREATE TABLE IF NOT EXISTS `game_data` (
                                           `id` bigint NOT NULL AUTO_INCREMENT,
                                           `additional_answers` varchar(255) DEFAULT NULL,
    `already_played` bit(1) DEFAULT NULL,
    `answer` varchar(255) DEFAULT NULL,
    `start_time` varchar(255) DEFAULT NULL,
    `id_game` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKobs6nd0q2vo9069kf39hiq0is` (`id_game`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `game_mode`
--

DROP TABLE IF EXISTS `game_mode`;
CREATE TABLE IF NOT EXISTS `game_mode` (
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `base_reward` int DEFAULT NULL,
                                           `img` varbinary(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `status` bit(1) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
CREATE TABLE IF NOT EXISTS `inventory` (
                                           `id` int NOT NULL AUTO_INCREMENT,
                                           `id_character` int NOT NULL,
                                           `id_player` int NOT NULL,
                                           PRIMARY KEY (`id`),
    KEY `FKp2syl3hhyh7ycdom1wo76y3af` (`id_character`),
    KEY `FKm63n9ti3otiyvj9yw7s8sgbpk` (`id_player`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `level_base`
--

DROP TABLE IF EXISTS `level_base`;
CREATE TABLE IF NOT EXISTS `level_base` (
                                            `id` bigint NOT NULL AUTO_INCREMENT,
                                            `level` int DEFAULT NULL,
                                            `reward` int DEFAULT NULL,
                                            `xp` int DEFAULT NULL,
                                            PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `loot_table`
--

DROP TABLE IF EXISTS `loot_table`;
CREATE TABLE IF NOT EXISTS `loot_table` (
                                            `id` int NOT NULL AUTO_INCREMENT,
                                            `limited` bit(1) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `rarity` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `media`
--

DROP TABLE IF EXISTS `media`;
CREATE TABLE IF NOT EXISTS `media` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `img` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `banner_id_character` int DEFAULT NULL,
                                        `icon_id_character` int DEFAULT NULL,
                                        `isdailydone` bit(1) DEFAULT NULL,
    `ishourlydone` bit(1) DEFAULT NULL,
    `level` int DEFAULT NULL,
    `money` int DEFAULT NULL,
    `pity_4` int DEFAULT NULL,
    `pity_5` int DEFAULT NULL,
    `pity_6` int DEFAULT NULL,
    `secure` bit(1) DEFAULT NULL,
    `skin_money` int DEFAULT NULL,
    `xp` int DEFAULT NULL,
    `id_user` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK9jhfka9w8iibi1vnqsfnwxgnw` (`id_user`)
    ) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `player_game_data`
--

DROP TABLE IF EXISTS `player_game_data`;
CREATE TABLE IF NOT EXISTS `player_game_data` (
                                                  `id` int NOT NULL AUTO_INCREMENT,
                                                  `correct` bit(1) DEFAULT NULL,
    `nb_correct` int DEFAULT NULL,
    `skipped` bit(1) DEFAULT NULL,
    `id_game` bigint NOT NULL,
    `id_player` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKmm33fokmo5pkxpad29e0v69g4` (`id_game`),
    KEY `FKnnbkgdxq6gpqxr1vldap1vpe2` (`id_player`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `pull_history`
--

DROP TABLE IF EXISTS `pull_history`;
CREATE TABLE IF NOT EXISTS `pull_history` (
                                              `id` int NOT NULL AUTO_INCREMENT,
                                              `id_loottable` int NOT NULL,
                                              `id_player` int NOT NULL,
                                              PRIMARY KEY (`id`),
    KEY `FK55a02mhmhg3r0scql3ueurqbu` (`id_loottable`),
    KEY `FKhue46xr25wnkfgw0b1cc8p64l` (`id_player`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `skin`
--

DROP TABLE IF EXISTS `skin`;
CREATE TABLE IF NOT EXISTS `skin` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `character_id` int NOT NULL,
                                      `id_loottable` int NOT NULL,
                                      PRIMARY KEY (`id`),
    KEY `FKq7h4ok3dnsnfvqtl7e651cjnw` (`character_id`),
    KEY `FKkmj9otkp51jmjxk6gdcwhqka1` (`id_loottable`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `mail` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `role` tinyint DEFAULT NULL,
    `token` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `banner`
--
ALTER TABLE `banner`
    ADD CONSTRAINT `FK5m8fcdougjdex0b1niv2jiogh` FOREIGN KEY (`id_loottable`) REFERENCES `loot_table` (`id`);

--
-- Contraintes pour la table `characters`
--
ALTER TABLE `characters`
    ADD CONSTRAINT `FKf3ymq6jbi9hcrsgryow3yf3aa` FOREIGN KEY (`id_media`) REFERENCES `media` (`id`);

--
-- Contraintes pour la table `game`
--
ALTER TABLE `game`
    ADD CONSTRAINT `FK32ixoah5ndkad0hwjc0270hrf` FOREIGN KEY (`id_gamemode`) REFERENCES `game_mode` (`id`);

--
-- Contraintes pour la table `game_data`
--
ALTER TABLE `game_data`
    ADD CONSTRAINT `FKobs6nd0q2vo9069kf39hiq0is` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`);

--
-- Contraintes pour la table `inventory`
--
ALTER TABLE `inventory`
    ADD CONSTRAINT `FKm63n9ti3otiyvj9yw7s8sgbpk` FOREIGN KEY (`id_player`) REFERENCES `player` (`id`),
  ADD CONSTRAINT `FKp2syl3hhyh7ycdom1wo76y3af` FOREIGN KEY (`id_character`) REFERENCES `characters` (`id`);

--
-- Contraintes pour la table `player`
--
ALTER TABLE `player`
    ADD CONSTRAINT `FK9jhfka9w8iibi1vnqsfnwxgnw` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `player_game_data`
--
ALTER TABLE `player_game_data`
    ADD CONSTRAINT `FKmm33fokmo5pkxpad29e0v69g4` FOREIGN KEY (`id_game`) REFERENCES `game` (`id`),
  ADD CONSTRAINT `FKnnbkgdxq6gpqxr1vldap1vpe2` FOREIGN KEY (`id_player`) REFERENCES `player` (`id`);

--
-- Contraintes pour la table `pull_history`
--
ALTER TABLE `pull_history`
    ADD CONSTRAINT `FK55a02mhmhg3r0scql3ueurqbu` FOREIGN KEY (`id_loottable`) REFERENCES `loot_table` (`id`),
  ADD CONSTRAINT `FKhue46xr25wnkfgw0b1cc8p64l` FOREIGN KEY (`id_player`) REFERENCES `player` (`id`);

--
-- Contraintes pour la table `skin`
--
ALTER TABLE `skin`
    ADD CONSTRAINT `FKkmj9otkp51jmjxk6gdcwhqka1` FOREIGN KEY (`id_loottable`) REFERENCES `loot_table` (`id`),
  ADD CONSTRAINT `FKq7h4ok3dnsnfvqtl7e651cjnw` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
