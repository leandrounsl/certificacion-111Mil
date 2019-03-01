-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_torneos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_torneos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_torneos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `db_torneos` ;

-- -----------------------------------------------------
-- Table `db_torneos`.`escuela`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`escuela` (
  `id_escuela` INT(11) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_escuela`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`aspirante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`aspirante` (
  `id_competidor` INT(11) NOT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(255) NOT NULL,
  `direccion` VARCHAR(255) NULL DEFAULT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `dni` INT(11) NOT NULL,
  `escuela_id_escuela` INT(11) NOT NULL,
  `sexo` INT(4) NOT NULL,
  PRIMARY KEY (`id_competidor`),
  INDEX `fk_competidor_escuela1_idx` (`escuela_id_escuela` ASC) VISIBLE,
  CONSTRAINT `fk_competidor_escuela1`
    FOREIGN KEY (`escuela_id_escuela`)
    REFERENCES `db_torneos`.`escuela` (`id_escuela`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`categoria` (
  `id_categoria` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  `genero` INT(4) NOT NULL,
  `edad_desde` INT(11) NOT NULL,
  `edad_hasta` INT(11) NOT NULL,
  PRIMARY KEY (`id_categoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`disciplina` (
  `id_disciplina` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`id_disciplina`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`estado` (
  `id_estado` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`id_estado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`torneo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`torneo` (
  `id_torneo` INT(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`id_torneo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `db_torneos`.`inscripcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_torneos`.`inscripcion` (
  `id_inscripcion` INT(11) NOT NULL,
  `torneo_id_torneo` INT(11) NOT NULL,
  `categoria_id_categoria` INT(11) NOT NULL,
  `disciplina_id_disciplina` INT(11) NOT NULL,
  `estado_id_estado` INT(11) NOT NULL,
  `aspirante_id_competidor` INT(11) NOT NULL,
  PRIMARY KEY (`id_inscripcion`),
  INDEX `fk_inscripcion_torneo1_idx` (`torneo_id_torneo` ASC) VISIBLE,
  INDEX `fk_inscripcion_categoria1_idx` (`categoria_id_categoria` ASC) VISIBLE,
  INDEX `fk_inscripcion_disciplina1_idx` (`disciplina_id_disciplina` ASC) VISIBLE,
  INDEX `fk_inscripcion_estado1_idx` (`estado_id_estado` ASC) VISIBLE,
  INDEX `fk_inscripcion_aspirante1_idx` (`aspirante_id_competidor` ASC) VISIBLE,
  CONSTRAINT `fk_inscripcion_aspirante1`
    FOREIGN KEY (`aspirante_id_competidor`)
    REFERENCES `db_torneos`.`aspirante` (`id_competidor`),
  CONSTRAINT `fk_inscripcion_categoria1`
    FOREIGN KEY (`categoria_id_categoria`)
    REFERENCES `db_torneos`.`categoria` (`id_categoria`),
  CONSTRAINT `fk_inscripcion_disciplina1`
    FOREIGN KEY (`disciplina_id_disciplina`)
    REFERENCES `db_torneos`.`disciplina` (`id_disciplina`),
  CONSTRAINT `fk_inscripcion_estado1`
    FOREIGN KEY (`estado_id_estado`)
    REFERENCES `db_torneos`.`estado` (`id_estado`),
  CONSTRAINT `fk_inscripcion_torneo1`
    FOREIGN KEY (`torneo_id_torneo`)
    REFERENCES `db_torneos`.`torneo` (`id_torneo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
