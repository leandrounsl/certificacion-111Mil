package gestion.torneos.dao;

import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import java.util.List;

/**
 * Interface que define las operaciones de acceso a datos sobre la entidad
 * Categoria.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public interface CategoriaDao {

    /**
     * Retorna las categorías definidas en el sistema.
     *
     * @return Lista de categorías.
     * @throws Exception
     */
    public List<Categoria> obtenerCategorias() throws Exception;
    
    /**
     * Retorna la categoría recomendada para el aspirante argumentado, según su
     * edad y sexo.
     *
     * @param aspirante El aspirante para el cual queremos obtener la categoría
     * recomendada.
     * @return Categoria La categoría recomendada.
     */
    public Categoria obtenerCategoriaRecomendadaPorAspirante(Aspirante aspirante);
}
