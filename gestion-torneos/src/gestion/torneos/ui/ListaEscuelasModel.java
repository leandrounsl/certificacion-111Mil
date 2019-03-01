package gestion.torneos.ui;

import gestion.torneos.model.Escuela;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * Implementación de un modelo de datos para JList que mantiene referencias a
 * objetos de tipo Escuela.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class ListaEscuelasModel extends AbstractListModel {

    private List<Escuela> escuelas = null;

    /**
     * Constructor que recibe por parámetro la lista de escuelas que contendrá
     * el modelo.
     *
     * @param escuelas Lista de escuelas.
     */
    public ListaEscuelasModel(List<Escuela> escuelas) {
        this.escuelas = escuelas;
    }

    @Override
    public int getSize() {
        return this.escuelas.size();
    }

    @Override
    public Escuela getElementAt(int i) {
        return this.escuelas.get(i);
    }

}
