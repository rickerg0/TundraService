-- MySQL Script generated by MySQL Workbench
-- 08/01/16 20:13:51
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tundra
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tundra` ;

-- -----------------------------------------------------
-- Schema tundra
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tundra` DEFAULT CHARACTER SET utf8 ;
USE `tundra` ;

-- -----------------------------------------------------
-- Table `tundra`.`organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`organization` ;

CREATE TABLE IF NOT EXISTS `tundra`.`organization` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address1` VARCHAR(45) NOT NULL,
  `address2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(10) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT 1,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`location` ;

CREATE TABLE IF NOT EXISTS `tundra`.`location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `latitude` DECIMAL NULL,
  `longitude` DECIMAL NULL,
  `organization_id` INT NOT NULL,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_location_organization_idx` (`organization_id` ASC),
  CONSTRAINT `fk_location_organization`
    FOREIGN KEY (`organization_id`)
    REFERENCES `tundra`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`itemtag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`itemtag` ;

CREATE TABLE IF NOT EXISTS `tundra`.`itemtag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `tag` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `location_id` INT NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT 0,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_item_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_item_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `tundra`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`itemtagmedia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`itemtagmedia` ;

CREATE TABLE IF NOT EXISTS `tundra`.`itemtagmedia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `mimetype` VARCHAR(45) NOT NULL,
  `content` LONGBLOB NOT NULL,
  `item_id` INT NOT NULL,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_itemmedia_item1_idx` (`item_id` ASC),
  CONSTRAINT `fk_itemmedia_item1`
    FOREIGN KEY (`item_id`)
    REFERENCES `tundra`.`itemtag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tundra`.`registereddevice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`registereddevice` ;

CREATE TABLE `tundra`.`registereddevice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `platform` VARCHAR(255) NOT NULL,
  `deviceid` VARCHAR(255) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`));
  
-- -----------------------------------------------------
-- Table `tundra`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`users` ;

CREATE TABLE `tundra`.`users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `user_name` VARCHAR(255) NOT NULL,
  `organization_id` INT NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_users_organization_idx` (`organization_id` ASC),
  CONSTRAINT `fk_users_organization`
    FOREIGN KEY (`organization_id`)
    REFERENCES `tundra`.`organization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;  

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
