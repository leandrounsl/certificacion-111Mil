package gestion.torneos.dao;

import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Disciplina;
import java.util.List;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Disciplina.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface DisciplinaDao {

    /**
     * Retorna las disciplinas definidas en el sistema.
     *
     * @return Lista de disciplinas.
     * @throws Exception
     */
    public List<Disciplina> obtenerDisciplinas() throws Exception;
    
     /**
     * Retorna todas las disciplinas asociadas el Aspirante
     * argumentado.
     *
     * @param aspirante El aspirante del cual se obtendrán las disciplinas
     * asociadas.
     * @return Lista de Disciplinas.
     */
    public List<Disciplina> buscarDisciplinasPorAspirante(Aspirante aspirante);
}
