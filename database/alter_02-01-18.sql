-- alters for 2-1-18.  all of these should be in the create script (tundra.sql)

-- add audit and active columns to organization

ALTER TABLE `tundra`.`organization` ADD `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`organization` ADD `updated_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`organization` ADD `active` BOOLEAN NOT NULL DEFAULT 1;

-- add audit columns to location

ALTER TABLE `tundra`.`location` ADD `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`location` ADD `updated_user` VARCHAR(45) NOT NULL DEFAULT 'admin';

-- add audit and active columns to itemtag
ALTER TABLE `tundra`.`itemtag` ADD `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`itemtag` ADD `updated_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`itemtag` ADD `active` BOOLEAN NOT NULL DEFAULT 1;

-- add audit columns to itemtagmedia

ALTER TABLE `tundra`.`itemtagmedia` ADD `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`itemtagmedia` ADD `updated_user` VARCHAR(45) NOT NULL DEFAULT 'admin';

-- add audit columns to registereddevice

ALTER TABLE `tundra`.`registereddevice` ADD `created_user` VARCHAR(45) NOT NULL DEFAULT 'admin';
ALTER TABLE `tundra`.`registereddevice` ADD `updated_user` VARCHAR(45) NOT NULL DEFAULT 'admin';


-- add user table

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