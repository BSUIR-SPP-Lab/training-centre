-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema training_service_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema training_service_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `training_service_db` DEFAULT CHARACTER SET utf8 ;
USE `training_service_db` ;

-- -----------------------------------------------------
-- Table `training_service_db`.`course_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`course_info` (
  `course_info_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`course_info_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`course` (
  `course_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_info_id` INT(10) UNSIGNED NOT NULL,
  `start` DATE NOT NULL,
  `end` DATE NOT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `IXFK_course_course_info` (`course_info_id` ASC),
  CONSTRAINT `FK_course_course_info`
    FOREIGN KEY (`course_info_id`)
    REFERENCES `training_service_db`.`course_info` (`course_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` CHAR(64) NOT NULL,
  `role` ENUM('user', 'student', 'teacher', 'coordinator', 'admin') NOT NULL,
  `email` VARCHAR(256) NOT NULL,
  `phone` CHAR(16) NULL DEFAULT NULL,
  `first_name` VARCHAR(50) NULL DEFAULT NULL,
  `last_name` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`student` (
  `student_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`student_id`),
  INDEX `IXFK_student_user` (`student_id` ASC),
  CONSTRAINT `FK_student_user`
    FOREIGN KEY (`student_id`)
    REFERENCES `training_service_db`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`application` (
  `applcation_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` INT(10) UNSIGNED NOT NULL,
  `course_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`applcation_id`),
  INDEX `IXFK_application_course` (`course_id` ASC),
  INDEX `IXFK_application_student` (`student_id` ASC),
  CONSTRAINT `FK_application_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `training_service_db`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_application_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `training_service_db`.`student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`group` (
  `group_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_id` INT(10) UNSIGNED NOT NULL,
  `coordinator_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `IXFK_group_course` (`course_id` ASC),
  INDEX `IXFK_group_user` (`coordinator_id` ASC),
  CONSTRAINT `FK_group_course`
    FOREIGN KEY (`course_id`)
    REFERENCES `training_service_db`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_group_user`
    FOREIGN KEY (`coordinator_id`)
    REFERENCES `training_service_db`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`student_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`student_group` (
  `student_id` INT(10) UNSIGNED NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  `course_complete` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`group_id`, `student_id`),
  INDEX `IXFK_student_group_group` (`group_id` ASC),
  INDEX `IXFK_student_group_student` (`student_id` ASC),
  CONSTRAINT `FK_student_group_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `training_service_db`.`group` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_student_group_student`
    FOREIGN KEY (`student_id`)
    REFERENCES `training_service_db`.`student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`certificate` (
  `certificate_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_id` INT(10) UNSIGNED NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`certificate_id`),
  INDEX `IXFK_certificate_student` (`student_id` ASC, `group_id` ASC),
  INDEX `FK_certificate_student` (`group_id` ASC, `student_id` ASC),
  CONSTRAINT `FK_certificate_student`
    FOREIGN KEY (`group_id` , `student_id`)
    REFERENCES `training_service_db`.`student_group` (`group_id` , `student_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`task_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`task_info` (
  `task_info_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `body` TEXT NOT NULL,
  PRIMARY KEY (`task_info_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`teacher` (
  `teacher_id` INT(10) UNSIGNED NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`group_id`, `teacher_id`),
  INDEX `IXFK_teacher_group` (`group_id` ASC),
  INDEX `IXFK_teacher_user` (`teacher_id` ASC),
  CONSTRAINT `FK_teacher_group`
    FOREIGN KEY (`group_id`)
    REFERENCES `training_service_db`.`group` (`group_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_teacher_user`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `training_service_db`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`task` (
  `task_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `teacher_id` INT(10) UNSIGNED NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  `task_info_id` INT(10) UNSIGNED NOT NULL,
  `upload_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`),
  INDEX `IXFK_task_task_info` (`task_info_id` ASC),
  INDEX `IXFK_task_teacher` (`group_id` ASC, `teacher_id` ASC),
  CONSTRAINT `FK_task_task_info`
    FOREIGN KEY (`task_info_id`)
    REFERENCES `training_service_db`.`task_info` (`task_info_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_task_teacher`
    FOREIGN KEY (`group_id` , `teacher_id`)
    REFERENCES `training_service_db`.`teacher` (`group_id` , `teacher_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `training_service_db`.`solution`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `training_service_db`.`solution` (
  `task_id` INT(10) UNSIGNED NOT NULL,
  `user_id` INT(10) UNSIGNED NOT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  `filepath` TEXT NULL DEFAULT NULL,
  `teacher_notes` TEXT NULL DEFAULT NULL,
  `mark` INT(10) UNSIGNED NULL DEFAULT NULL,
  `upload_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`task_id`, `user_id`),
  INDEX `IXFK_solution_task` (`task_id` ASC),
  INDEX `FK_solution_user_idx` (`user_id` ASC),
  CONSTRAINT `FK_solution_task`
    FOREIGN KEY (`task_id`)
    REFERENCES `training_service_db`.`task` (`task_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_solution_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `training_service_db`.`student` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
