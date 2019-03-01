package gestion.torneos.ui;

import gestion.torneos.model.Disciplina;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.collections.CollectionUtils;

/**
 * Implementación de un modelo de datos para JTable que mantiene referencias a
 * objetos de tipo Disciplina. Mantiene la información sobre que disciplinas
 * fueron seleccionadas durante el proceso de inscripción de un aspirante.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class TablaDisciplinasModel extends AbstractTableModel {

    // Definición de columnas...
    private static final String[] COLUMNAS = {"", "Disciplina"};

    // Constantes para referencias a las columnas desde afuera del modelo...
    public static final int COLUMNA_BOOLEAN = 0;
    public static final int COLUMNA_DISCIPLINA = 1;

    // Lista de Disciplinas...
    private List<Disciplina> disciplinas = null;

    // Estructura que contendrá información de la tabla...
    private Object[][] dataTable = null;

    private boolean soloLectura;

    /**
     * Constructor que recibe la lista de disciplinas que contendrá el modelo.
     *
     * @param disciplinas Lista de disciplinas.
     */
    public TablaDisciplinasModel(List<Disciplina> disciplinas) {
        super();
        this.disciplinas = disciplinas;
        this.dataTable = new Object[disciplinas.size()][2];
        int index = 0;
        for (Iterator<Disciplina> iterator = disciplinas.iterator(); iterator.hasNext();) {
            Disciplina disciplina = iterator.next();
            this.dataTable[index][0] = Boolean.FALSE;
            this.dataTable[index][1] = disciplina;
            index++;
        }

    }

    @Override
    public int getRowCount() {
        return this.disciplinas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Object retorno = this.dataTable[fila][columna];
        return retorno;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 0:
                clazz = Boolean.class;
                break;
            case 2:
                clazz = String.class;
                break;
        }
        return clazz;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column < 1 && !this.soloLectura) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setValueAt(Object objectValue, int row, int column) {
        if (objectValue instanceof Boolean && column == 0) {
            this.dataTable[row][column] = (Boolean) objectValue;
            fireTableCellUpdated(row, column);
        }
    }

    @Override
    public String getColumnName(int index) {
        return COLUMNAS[index];
    }

    /**
     * Marca como seleccionadas las disciplinas que contiene la lista
     * argumentada.
     *
     * @param disciplinasSeleccionadas Lista de Disciplinas seleccionadas.
     */
    public void seleccionarDisciplinas(List<Disciplina> disciplinasSeleccionadas) {
        if (CollectionUtils.isNotEmpty(disciplinasSeleccionadas)) {
            for (Iterator<Disciplina> iterator = disciplinasSeleccionadas.iterator(); iterator.hasNext();) {
                Disciplina disciplina = iterator.next();
                for (int i = 0; i < dataTable.length; i++) {
                    Disciplina disciplinaDataModel = (Disciplina) dataTable[i][COLUMNA_DISCIPLINA];
                    if (disciplinaDataModel.equals(disciplina)) {
                        dataTable[i][COLUMNA_BOOLEAN] = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Marca todas las disciplinas que contiene el modelo como no seleccionadas.
     */
    public void desmarcarTodasLasDisciplinas() {
        for (int i = 0; i < dataTable.length; i++) {
            dataTable[i][COLUMNA_BOOLEAN] = false;
        }
    }

    /**
     * Bandera que indica si la tabla se comporta en modo solo lectura.
     *
     * @param b true Indica solo lectura. No se pueden marcar ni desmarcar
     * disciplinas, false modo normal.
     */
    public void soloLectura(boolean b) {
        this.soloLectura = b;
    }
}
