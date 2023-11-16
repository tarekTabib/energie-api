-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema energie
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `energie` ;

-- -----------------------------------------------------
-- Schema energie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `energie` DEFAULT CHARACTER SET utf8mb3 ;
USE `energie` ;

-- -----------------------------------------------------
-- Table `energie`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `energie`.`customer` ;

CREATE TABLE IF NOT EXISTS `energie`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reference` VARCHAR(45) NOT NULL DEFAULT 'EKL123456789',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 61
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `energie`.`business`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `energie`.`business` ;

CREATE TABLE IF NOT EXISTS `energie`.`business` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `siret` VARCHAR(45) NULL DEFAULT NULL,
  `capital` INT NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  INDEX `fk_business_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_business_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `energie`.`customer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `energie`.`monthly_consumption`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `energie`.`monthly_consumption` ;

CREATE TABLE IF NOT EXISTS `energie`.`monthly_consumption` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_consumption_date` DATE NULL DEFAULT NULL,
  `end_consumption_date` DATE NULL DEFAULT NULL,
  `gaz_consumed` INT NULL DEFAULT NULL,
  `electricity_consumed` INT NULL DEFAULT NULL,
  `total_electricity_amount` FLOAT NULL DEFAULT NULL,
  `total_gaz_amount` FLOAT NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_electricity_consumption_detail_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_electricity_consumption_detail_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `energie`.`customer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 501
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `energie`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `energie`.`person` ;

CREATE TABLE IF NOT EXISTS `energie`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `civility` VARCHAR(45) NULL DEFAULT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`, `customer_id`),
  INDEX `fk_person_customer1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_person_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `energie`.`customer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `energie`.`tariff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `energie`.`tariff` ;

CREATE TABLE IF NOT EXISTS `energie`.`tariff` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `label` VARCHAR(200) NULL DEFAULT NULL,
  `electricity_price` FLOAT NULL DEFAULT NULL,
  `gaz_price` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
