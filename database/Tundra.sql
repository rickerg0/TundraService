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
-- Table `tundra`.`Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`Organization` ;

CREATE TABLE IF NOT EXISTS `tundra`.`Organization` (
  `Id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Address1` VARCHAR(45) NOT NULL,
  `Address2` VARCHAR(45) NULL,
  `City` VARCHAR(45) NOT NULL,
  `State` VARCHAR(45) NOT NULL,
  `Zip` VARCHAR(10) NOT NULL,
  `Phone` VARCHAR(20) NOT NULL,
  `Created` DATETIME NOT NULL,
  `Updated` DATETIME NOT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`Location` ;

CREATE TABLE IF NOT EXISTS `tundra`.`Location` (
  `Id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Latitude` DECIMAL NULL,
  `Longitude` DECIMAL NULL,
  `Organization_Id` INT NOT NULL,
  `Created` DATETIME NOT NULL,
  `Updated` DATETIME NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Location_Organization_idx` (`Organization_Id` ASC),
  CONSTRAINT `fk_Location_Organization`
    FOREIGN KEY (`Organization_Id`)
    REFERENCES `tundra`.`Organization` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`ExibitTag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`ExibitTag` ;

CREATE TABLE IF NOT EXISTS `tundra`.`ExibitTag` (
  `Id` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Tag` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NOT NULL,
  `Created` DATETIME NOT NULL,
  `Updated` DATETIME NOT NULL,
  `Location_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Exibit_Location1_idx` (`Location_Id` ASC),
  CONSTRAINT `fk_Exibit_Location1`
    FOREIGN KEY (`Location_Id`)
    REFERENCES `tundra`.`Location` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tundra`.`ExibitTagMedia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`ExibitTagMedia` ;

CREATE TABLE IF NOT EXISTS `tundra`.`ExibitTagMedia` (
  `Id` INT NOT NULL,
  `MimeType` VARCHAR(45) NOT NULL,
  `Content` BLOB NOT NULL,
  `Created` DATETIME NOT NULL,
  `Updated` DATETIME NOT NULL,
  `Exibit_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_ExibitMedia_Exibit1_idx` (`Exibit_Id` ASC),
  CONSTRAINT `fk_ExibitMedia_Exibit1`
    FOREIGN KEY (`Exibit_Id`)
    REFERENCES `tundra`.`ExibitTag` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
