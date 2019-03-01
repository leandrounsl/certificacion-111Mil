package gestion.torneos.dao;

import gestion.torneos.model.Torneo;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Torneo.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface TorneoDao {

    /**
     * Realiza una búsqueda de un torneo por nombre.
     *
     * @param nombre Nombre del torneo a buscar.
     * @return Torneo - El torneo con el nombre argumentado, si no lo encuentra
     * retorna null.
     * @throws Exception
     */
    public Torneo buscarPorNombre(String nombre) throws Exception;
}
