package gestion.torneos.ui;

import gestion.torneos.model.Aspirante;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * Implementación de un modelo de datos para JList que mantiene referencias a
 * objetos de tipo Aspirante.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class ListaAspiranteModel extends AbstractListModel {

    private final List<Aspirante> aspirantesDataModel = new ArrayList();

    @Override
    public int getSize() {
        return this.aspirantesDataModel.size();
    }

    @Override
    public Aspirante getElementAt(int i) {
        return this.aspirantesDataModel.get(i);
    }

    /**
     * Borra la lista de aspirantes que contiene el modelo.
     */
    public void clear() {
        int index1 = this.aspirantesDataModel.size() - 1;
        this.aspirantesDataModel.clear();
        if (index1 >= 0) {
            fireIntervalRemoved(this, 0, index1);
        }
    }

    /**
     * Agrega un nuevo aspirante al modelo.
     *
     * @param aspirante Aspirante agregado.
     */
    public void addElement(Aspirante aspirante) {
        int index = this.aspirantesDataModel.size();
        if (this.aspirantesDataModel.add(aspirante)) {
            fireIntervalAdded(this, index, index);
        }
    }

    /**
     * Agrega todos los aspirantes que contiene la lista argumentada al modelo.
     *
     * @param aspirantes Lista de Aspirantes.
     */
    public void addAll(List<Aspirante> aspirantes) {
        int oldSize = this.aspirantesDataModel.size();
        int newSize = aspirantes.size();
        this.aspirantesDataModel.addAll(aspirantes);
        if (oldSize > newSize) {
            fireIntervalRemoved(this, newSize, oldSize - 1);
        } else if (oldSize < newSize) {
            fireIntervalAdded(this, oldSize, newSize - 1);
        }
        fireContentsChanged(this, -1, -1);
    }
}
