#################################################
# Certificación de Intructores Programa 111 Mil #
#              Leandro Giménez                  #
#################################################

Contenido del proyecto
----------------------

- Directorio DB:
    * 01-estructura.sql
         Script MySQL que genera el esquema db-torneos y las tablas utilizadas por la aplicación.
    * 02-datos.sql
        Contiene los insert de los datos nomenclados: escuelas, categorías, disciplinas, 
        torneo y estado de inscripción.
    * DER.mwb 
        Diagrama de entidad-relación en formato mwb, para ser procesado por MySQL Workbench.
        Permite la generación de la base de datos a partir del diagrama.
    * DER.png
        Diagrama de entidad-relación en formato png.

- Directorio diagramas:
    * Diagrama_Clases.png
        Diagrama de clases en formato png.
    * Diagrama_Secuencia.png
        Diagrama de secuencia en formato png.

- Directorio lib: Contiene las dependencias de la aplicación.

- Directorio logs: Contiene los archivos de logs de la aplicación.

Aplicación de Gestión de Inscripciones
--------------------------------------

- La aplicación de gestión de inscripciones va generando aspirantes y sus inscripciones
  en memoria. Al presionar el botón aceptar, previa validación de las inscripciones, se
  persiste la información generada en la base de datos.

- Una vez generadas las insripciones, las mismas se muestran en modo solo lectura, no siendo posible
  modificar la información.

- Si al iniciar la aplicación existen aspirantes e inscripciones para una escuela particular,
  la información se mostrará en modo solo lectura.

- El sistema permite generar inscripciones, cada una tendrá asociada una disciplina y categoría 
  particular. Por ejemplo:
    * 100 metros llanos - Infantiles varones.
    * Salto en alto - Menores varones.

- Las categorías definidas son:
    * Infantiles Varones, de 6 a 8 años.
    * Infantiles Mujeres, de 6 a 8 años.
    * Menores Varones, de 9 a 12 años.
    * Menores Mujeres, de 9 a 12 años.
    * Pre-Adolescente Varones, de 13 a 15 años.
    * Pre-Adolescente Mujeres, de 13 a 15 años.
    * Adolescente Varones, de 16 a 18 años.
    * Adolescente Mujeres, de 16 a 18 años.

- Validaciones
    * Nuevo aspirante
        . Nombre (obligatorio).
        . Apellido (obligatorio).
        . Fecha de Nacimiento (obligatorio, formato dd/mm/aaaa, menor a la fecha actual, 
          edad calculada entre 6 y 18 años).
        . Dirección (opcional).
        . Sexo (obligatorio).
        . DNI (obligatorio, solo numérico, de longitud=8, único en el sistema).
    * Inscripciones
        . Todos los aspirantes deben tener al menos una inscripción.
        . El sexo del aspirante debe coincidir con el género de la categoría.