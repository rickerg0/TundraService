-- -----------------------------------------------------
-- Table `tundra`.`users_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`users_location`;

CREATE TABLE `tundra`.`users_location` (
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  INDEX `fk_user_location_user` (`user_id` ASC),
  CONSTRAINT `fk_user_location_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `tundra`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_location_location`
    FOREIGN KEY (`location_id`)
    REFERENCES `tundra`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;  

-- -----------------------------------------------------
-- Table `tundra`.`users_authority`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tundra`.`users_authority` ;

CREATE TABLE `tundra`.`users_authority` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `authority` VARCHAR(255) NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin',
  `updated_user` VARCHAR(45) NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_user_authority_user` (`user_id` ASC),
  CONSTRAINT `fk_user_authority_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `tundra`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;  