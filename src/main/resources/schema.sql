SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `growth_analysis` DEFAULT CHARACTER SET utf8mb4;
USE `growth_analysis`;

DROP TABLE IF EXISTS `growth_analysis`.`height_analysis`;

CREATE TABLE IF NOT EXISTS `growth_analysis`.`height_analysis`
(
    `id`          BIGINT     NOT NULL AUTO_INCREMENT,
    `height`       FLOAT NOT NULL,
    `month` INT(11) NULL     DEFAULT NULL,
    `percentile`    INT(11)        NULL     DEFAULT NULL,
    `sex`      VARCHAR(255)  NOT NULL DEFAULT 'male',
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `growth_analysis`.`height_range_request_log`;

CREATE TABLE IF NOT EXISTS `growth_analysis`.`height_range_request_log`
(
    `id`          BIGINT     NOT NULL AUTO_INCREMENT,
    `height`       FLOAT NOT NULL,
    `month` INT(11) NULL     DEFAULT NULL,
    `created_at`    DATETIME     NULL     DEFAULT NULL,
    `sex`      VARCHAR(255)  NOT NULL DEFAULT 'male',
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
