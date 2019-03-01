package gestion.torneos.dao;

import gestion.torneos.model.EstadoInscripcion;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * EstadoInscripcion.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface EstadoInscripcionDao {

    /**
     * Realiza una búsqueda de un estado de inscripción por nombre.
     *
     * @param nombre Nombre del estado a buscar.
     * @return EstadoInscripcion - El estado de inscripción con el nombre
     * argumentado, si no lo encuentra retorna null.
     * @throws Exception
     */
    public EstadoInscripcion bucarPorNombre(String nombre) throws Exception;
}
