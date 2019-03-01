package gestion.torneos.dao;

import gestion.torneos.model.Aspirante;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Aspirante.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface AspiranteDao {

    /**
     * Realiza una búsqueda del Aspirante por DNI.
     *
     * @param dni DNI del aspirante.
     * @return Aspirante si existe un aspirante con ese DNI, null en otro caso.
     * @throws Exception
     */
    public Aspirante obtenerAspirantePorDni(int dni) throws Exception;

    /**
     * Persiste los datos de la entidad Aspirante argumentada.
     *
     * @param aspirante Aspirante a persistir.
     * @throws Exception
     */
    public void persistirAspirante(Aspirante aspirante) throws Exception;
}
