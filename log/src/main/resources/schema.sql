SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `log` DEFAULT CHARACTER SET utf8mb4;
USE `log`;

DROP TABLE IF EXISTS `log`.`log_height_request`;

CREATE TABLE IF NOT EXISTS `log`.`log_height_request`
(
    `id`          BIGINT     NOT NULL AUTO_INCREMENT,
    `height`       FLOAT NOT NULL,
    `month` INT(11) NULL     DEFAULT NULL,
    `created_at`    DATETIME     NULL     DEFAULT NULL,
    `sex`      VARCHAR(255)  NOT NULL DEFAULT 'MALE',
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `log`.`aggregation_height_request`;
CREATE TABLE IF NOT EXISTS `log`.`aggregation_height_request`
(
    `id`          BIGINT     NOT NULL AUTO_INCREMENT,
    `height_average`       FLOAT  NULL,
    `month_average`       FLOAT  NULL,
    `request_total_count` INT(11) NULL     DEFAULT NULL,
    `male_count` INT(11) NULL     DEFAULT NULL,
    `female_count` INT(11) NULL     DEFAULT NULL,
    `updated_at`    DATETIME     NULL     DEFAULT NULL,
    `created_at`    DATETIME     NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
