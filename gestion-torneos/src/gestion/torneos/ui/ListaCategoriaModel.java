package gestion.torneos.ui;

import gestion.torneos.model.Categoria;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * Implementación de un modelo de datos para JList que mantiene referencias a
 * objetos de tipo Categoria.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class ListaCategoriaModel extends AbstractListModel {

    private List<Categoria> categorias = null;

    /**
     * Constructor que recibe por parámetro la lista de categorías que contendrá
     * el modelo.
     *
     * @param categorias Lista de categorías.
     */
    public ListaCategoriaModel(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int getSize() {
        return this.categorias.size();
    }

    @Override
    public Categoria getElementAt(int i) {
        return this.categorias.get(i);
    }
}
