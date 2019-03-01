package gestion.torneos.dao;

import gestion.torneos.model.Escuela;
import java.util.List;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Escuela.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface EscuelaDao {

    /**
     * Retorna las escuelas definidas en el sistema.
     *
     * @return Lista de escuelas.
     * @throws Exception
     */
    public List<Escuela> obtenerEscuelas() throws Exception;

}
