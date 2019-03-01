-- Tabla escuela
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (1, 'Colegio Carbó');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (2, 'Colegio Corazón de María');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (3, 'Colegio del Carmen');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (4, 'Colegio Montserrat');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (5, 'Colegio Nacional General San Martín');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (6, 'Escuela Agrotécnica Número 23');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (7, 'Escuela Normal Superior Número 23');
INSERT INTO `escuela` (`id_escuela`, `nombre`) VALUES (8, 'Instituto Hellen Keller');

-- Tabla estado
INSERT INTO `estado` (`id_estado`, `nombre`, `descripcion`) VALUES (1, 'Pre-Aprobada', 'Inscripción pre-aprobada');
INSERT INTO `estado` (`id_estado`, `nombre`, `descripcion`) VALUES (2, 'Aprobada', 'Inscripción aprobada');
INSERT INTO `estado` (`id_estado`, `nombre`, `descripcion`) VALUES (3, 'No Aprobada', 'Inscripción no aprobada');

-- Tabla torneo
INSERT INTO `torneo` (`id_torneo`, `nombre`, `descripcion`) VALUES (1, 'Torneo de Atletismo', 'Torneo de Atletismo de Villa María, en el cual participan todas las escuelas privadas y publicas de la ciudad');

-- Tabla categoria
-- Sexo: 0 - Masculino, 1 - Femenino
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (1,'Infantiles Varones','Infantiles Varones, de 6 a 8 años',0,6,8);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (2,'Infantiles Mujeres','Infantiles Mujeres, de 6 a 8 años',1,6,8);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (3,'Menores Varones','Menores Varones, de 9 a 12 años',0,9,12);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (4,'Menores Mujeres','Menores Mujeres, de 9 a 12 años',1,9,12);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (5,'Pre-Adolescente Varones','Pre-Adolescente Varones, de 13 a 15 años',0,13,15);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (6,'Pre-Adolescente Mujeres','Pre-Adolescente Mujeres, de 13 a 15 años',1,13,15);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (7,'Adolescente Varones','Adolescente Varones, de 16 a 18 años',0,16,18);
INSERT INTO `categoria` (`id_categoria`, `nombre`, `descripcion`, `genero`, `edad_desde`, `edad_hasta`) VALUES (8,'Adolescente Mujeres','Adolescente Mujeres, de 16 a 18 años',1,16,18);

-- Tabla disciplina
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (1, '100 metros llanos', '100 metros llanos');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (2, '300 metros con posta', '300 metros con posta');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (3, '500 metros con posta', '500 metros con posta');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (4, '50 metros con vallas', '50 metros con vallas');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (5, 'Salto en Largo', 'Salto en Largo');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (6, 'Carrera de velocidad', 'Carrera de velocidad de hasta 400 metros');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (7, 'Carrera de media distancia', 'Carrera de media distancia entre 800 y 3000 metros');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (8, 'Carrera de fondo', 'Carrera de fondo superiores a los 3000 metros');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (9, 'Salto en Alto', 'Salto en Alto');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (10, 'Lanzamiento de bala', 'Lanzamiento de bala');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (11, 'Lanzamiento de disco', 'Lanzamiento de disco');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (12, 'Lanzamiento de jabalina', 'Lanzamiento de jabalina');
INSERT INTO `disciplina` (`id_disciplina`, `nombre`, `descripcion`) VALUES (13, 'Lanzamiento de martillo', 'Lanzamiento de martillo');
