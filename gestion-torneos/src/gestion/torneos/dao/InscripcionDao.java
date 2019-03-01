package gestion.torneos.dao;

import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import gestion.torneos.model.Disciplina;
import gestion.torneos.model.Inscripcion;
import gestion.torneos.model.Torneo;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Inscripción.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface InscripcionDao {

    /**
     * Genera un objeto de tipo Inscripcion con los datos argumentados y la
     * agrega a la lista de inscripciones del Aspirante pasado como parámetro.
     *
     * @param aspirante Aspirante al cual pertenece la inscripción.
     * @param disciplina Disciplina donde se inscribe el aspirante.
     * @param categoria Categoría donde se inscribe el aspirante.
     * @param torneo Torneo al cual se inscribe el aspirante.
     */
    public void agregarInscripcion(Aspirante aspirante, Disciplina disciplina, Categoria categoria, Torneo torneo);

    /**
     * Elimina la inscripción correspondiente al aspirante y a la disciplina
     * pasados como parámetro.
     *
     * @param aspirante
     * @param disciplina
     */
    public void eliminarInscripcion(Aspirante aspirante, Disciplina disciplina);

    /**
     * Retorna la inscripción correspondiente al aspirante y a la disciplina
     * pasados como parámetro.
     *
     * @param aspirante
     * @param disciplina
     * @return Inscripcion inscripcion - Inscripción correspondiente a el
     * aspirante y disciplina argumentados si existe, null en otro caso.
     */
    public Inscripcion buscarInscripcionPorAspiranteYDisciplina(Aspirante aspirante, Disciplina disciplina);

    /**
     * Actualiza la categoría de la inscripcion a la disciplina argumentada del
     * Aspirante pasado como parámetro.
     *
     * @param aspirante Aspirante al cual pertenece la inscripción.
     * @param disciplina Disciplina de la inscripción.
     * @param nuevaCategoria Nueva categoría.
     */
    public void actualizarCategoriaDeInscripcionPorAspiranteYDisciplina(Aspirante aspirante, Disciplina disciplina, Categoria nuevaCategoria);
}
