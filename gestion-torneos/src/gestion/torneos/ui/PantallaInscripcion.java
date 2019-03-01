package gestion.torneos.ui;

import gestion.torneos.controller.GestorInscripcion;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import gestion.torneos.model.Disciplina;
import gestion.torneos.model.Escuela;
import gestion.torneos.model.Inscripcion;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * Clase que representa la pantalla de administración de inscripciones.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class PantallaInscripcion extends javax.swing.JFrame {

    private static final Logger _logger = Logger.getLogger(PantallaInscripcion.class);

    private final GestorInscripcion gestorInscripcion;

    private List<Escuela> escuelas = null;
    private List<Disciplina> disciplinas = null;
    private List<Categoria> categorias = null;

    /**
     * Constructor que recibe una instancia del gestor de inscripción.
     *
     * @param gestorInscripcion Gestor de Inscripción
     * @param escuelas Lista de escuelas
     * @param disciplinas Lista de disciplinas
     * @param categorias Lista de categorías
     */
    public PantallaInscripcion(GestorInscripcion gestorInscripcion, List<Escuela> escuelas, List<Disciplina> disciplinas, List<Categoria> categorias) {
        _logger.debug("Iniciando GUI...");
        this.gestorInscripcion = gestorInscripcion;
        this.escuelas = escuelas;
        this.disciplinas = disciplinas;
        this.categorias = categorias;
        initComponents();
        configurarTabla(this.competenciaTable, 250, 20, 80);
        centrarPantalla();
        deshabilitarControles();
    }

    private void deshabilitarControles() {
        TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
        tablaDisciplinasModel.soloLectura(true);
        this.categoriasList.setEnabled(false);
    }

    private void habilitarControles() {
        TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
        tablaDisciplinasModel.soloLectura(false);
        this.categoriasList.setEnabled(true);
    }

    private void restablecerTablaDisciplinas() {
        TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
        tablaDisciplinasModel.desmarcarTodasLasDisciplinas();
        this.competenciaTable.clearSelection();
        this.competenciaTable.updateUI();
    }

    private void centrarPantalla() {
        this.setLocationRelativeTo(null);
    }

    private void gestionarSeleccionDeAspirante() {
        int aspiranteSelecciondadoIndex = this.aspirantesList.getSelectedIndex();
        if (aspiranteSelecciondadoIndex < 0) {
            return;
        }
        deshabilitarControles();
        ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
        Aspirante aspiranteSeleccionado = listaAspiranteModel.getElementAt(aspiranteSelecciondadoIndex);
        restablecerTablaDisciplinas();
        this.categoriasList.setSelectedIndex(-1);
        seleccionarDisciplinasPorAspirante(aspiranteSeleccionado);
        seleccionarCategoriaPorAspirante(aspiranteSeleccionado);

        // Control para NO permitir la edición de un aspirante cargado de la base de datos...
        if (aspiranteSeleccionado.getIdCompetidor() <= 0) {
            habilitarControles();
        }
    }

    private void seleccionarCategoriaPorAspirante(Aspirante aspiranteSeleccionado) {
        Categoria categoriaRecomendada = this.gestorInscripcion.obtenerCategoriaRecomendada(aspiranteSeleccionado);
        if (categoriaRecomendada != null) {
            this.categoriasList.setSelectedValue(categoriaRecomendada, true);
        }
    }

    private void seleccionarDisciplinasPorAspirante(Aspirante aspiranteSeleccionado) {
        List<Disciplina> disciplinasAspirante = this.gestorInscripcion.obtenerDisciplinasPorAspirante(aspiranteSeleccionado);
        if (CollectionUtils.isNotEmpty(disciplinasAspirante)) {
            TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
            tablaDisciplinasModel.seleccionarDisciplinas(disciplinasAspirante);
            this.competenciaTable.updateUI();
        }
    }

    private void guardarInscripciones() {
        try {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "Se guardaran las inscripciones generadas, ¿confirma la operación?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                return;
            }
            this.gestorInscripcion.guardarInscripciones();
            JOptionPane.showMessageDialog(this, "Las inscripciones se generaron exitosamente. "
                    + "Las inscripciones guardadas se mostrarán en modo solo lectura",
                    "Operación exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
            deshabilitarControles();
        } catch (Exception ex) {
            _logger.error("Error al guardar las inscripciones: " + ex.getMessage(), ex);
            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al persistir la información: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarErroresDeValidacion(StringBuilder errorStringBuilder) {
        String errorMessage = errorStringBuilder.toString();
        _logger.debug("Errores de validación: " + errorMessage);
        JTextArea consolaDeErrores = new JTextArea(errorMessage);
        consolaDeErrores.setEditable(false);
        JScrollPane jsp = new JScrollPane(consolaDeErrores) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(450, 150);
            }
        };
        JOptionPane.showMessageDialog(
                this, jsp, "Error de validación",
                JOptionPane.ERROR_MESSAGE);
    }

    private void configurarTabla(JTable table, int tablePreferredWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            int tamanioColumna = (int) (tablePreferredWidth * (percentages[i] / total));
            column.setPreferredWidth(tamanioColumna);
            if (i == TablaDisciplinasModel.COLUMNA_BOOLEAN) {
                column.setMaxWidth(tamanioColumna);
                column.setResizable(false);
            }
        }

        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);

        /* Agregamos listener... */
        table.getModel().addTableModelListener((TableModelEvent tme) -> {

            int aspiranteSelectedIndex = this.aspirantesList.getSelectedIndex();
            int row = tme.getFirstRow();
            int column = tme.getColumn();
            TablaDisciplinasModel model = (TablaDisciplinasModel) tme.getSource();
            Boolean checked = (Boolean) model.getValueAt(row, column);
            // Obtenemos alumno seleccionado... 
            ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
            Aspirante aspiranteSeleccionado = listaAspiranteModel.getElementAt(aspiranteSelectedIndex);

            int categoriaSelectionIndex = this.categoriasList.getSelectedIndex();
            ListaCategoriaModel listaCategoriaModel = (ListaCategoriaModel) this.categoriasList.getModel();
            Categoria categoriaSeleccionada = listaCategoriaModel.getElementAt(categoriaSelectionIndex);

            Disciplina disciplinaSeleccionda = (Disciplina) model.getValueAt(row, TablaDisciplinasModel.COLUMNA_DISCIPLINA);

            if (checked) {
                this.gestorInscripcion.agregarInscripcion(aspiranteSeleccionado, disciplinaSeleccionda, categoriaSeleccionada);
            } else {
                this.gestorInscripcion.borrarInscripcion(aspiranteSeleccionado, disciplinaSeleccionda);
            }
        });

        table.getSelectionModel().addListSelectionListener((ListSelectionEvent lse) -> {
            if (!lse.getValueIsAdjusting()) {
                int aspiranteSelectedIndex = this.aspirantesList.getSelectedIndex();
                if (aspiranteSelectedIndex < 0) {
                    return;
                }

                int selectedRow = this.competenciaTable.getSelectedRow();
                if (selectedRow >= 0) {
                    TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
                    Disciplina disciplinaSeleccionada = (Disciplina) tablaDisciplinasModel.getValueAt(selectedRow, TablaDisciplinasModel.COLUMNA_DISCIPLINA);

                    // Obtenemos alumno seleccionado... 
                    ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
                    Aspirante aspiranteSeleccionado = listaAspiranteModel.getElementAt(aspiranteSelectedIndex);
                    Inscripcion inscripcion = this.gestorInscripcion.obtenerInscripcionPorDisciplina(aspiranteSeleccionado, disciplinaSeleccionada);
                    // Si existe una inscripción del aspirante en esa disciplina, 
                    // mostramos la categoría seleccionada...
                    Categoria categoriaASeleccionar = null;
                    if (inscripcion != null) {
                        categoriaASeleccionar = inscripcion.getCategoria();
                    } else {
                        // Si no existe una inscripción en la disciplina, mostramos como
                        // seleccionada la categoría recomendada por edad y sexo del aspirante...
                        categoriaASeleccionar = this.gestorInscripcion.obtenerCategoriaRecomendada(aspiranteSeleccionado);
                    }
                    this.categoriasList.setSelectedValue(categoriaASeleccionar, true);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ListaEscuelasModel listaEscuelasModel = new ListaEscuelasModel(escuelas);
        escuelaList = new javax.swing.JList<>(listaEscuelasModel);
        centerPanel = new javax.swing.JPanel();
        aspirantesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        aspirantesList = new javax.swing.JList<>();
        nuevoAspirantePanel = new javax.swing.JPanel();
        nuevoAspiranteButton = new javax.swing.JButton();
        seleccionCategoriaYCompetenciaPanel = new javax.swing.JPanel();
        disciplinaPanel = new javax.swing.JScrollPane();
        competenciaTable = new javax.swing.JTable();
        categoriaPanel = new javax.swing.JScrollPane();
        categoriasList = new javax.swing.JList<>();
        southPanel = new javax.swing.JPanel();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Inscripciones v1.0");
        setIconImage(Toolkit.getDefaultToolkit().getImage(
            getClass().getResource(
                "/resources/img/athletics-16x16.png")));

    jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccione una Entidad Educativa..."));

    escuelaList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    escuelaList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            escuelaListValueChanged(evt);
        }
    });
    jScrollPane1.setViewportView(escuelaList);

    getContentPane().add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

    centerPanel.setPreferredSize(new java.awt.Dimension(750, 300));
    centerPanel.setLayout(new java.awt.GridLayout(1, 2));

    aspirantesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Aspirantes"));
    aspirantesPanel.setLayout(new java.awt.BorderLayout());

    aspirantesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ListaAspiranteModel listaAspiranteModel = new ListaAspiranteModel();
    aspirantesList.setModel(listaAspiranteModel);
    aspirantesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            aspirantesListValueChanged(evt);
        }
    });
    jScrollPane2.setViewportView(aspirantesList);

    aspirantesPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

    nuevoAspiranteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/add-16x16.png"))); // NOI18N
    nuevoAspiranteButton.setText("Nuevo aspirante...");
    nuevoAspiranteButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            nuevoAspiranteButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout nuevoAspirantePanelLayout = new javax.swing.GroupLayout(nuevoAspirantePanel);
    nuevoAspirantePanel.setLayout(nuevoAspirantePanelLayout);
    nuevoAspirantePanelLayout.setHorizontalGroup(
        nuevoAspirantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevoAspirantePanelLayout.createSequentialGroup()
            .addGap(0, 220, Short.MAX_VALUE)
            .addComponent(nuevoAspiranteButton))
    );
    nuevoAspirantePanelLayout.setVerticalGroup(
        nuevoAspirantePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nuevoAspirantePanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nuevoAspiranteButton)
            .addContainerGap())
    );

    aspirantesPanel.add(nuevoAspirantePanel, java.awt.BorderLayout.SOUTH);

    centerPanel.add(aspirantesPanel);

    seleccionCategoriaYCompetenciaPanel.setLayout(new java.awt.GridLayout(2, 1));

    disciplinaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Competencias"));

    TablaDisciplinasModel tablaDisciplinasModel = new TablaDisciplinasModel(disciplinas);
    competenciaTable.setModel(tablaDisciplinasModel);
    competenciaTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    disciplinaPanel.setViewportView(competenciaTable);

    seleccionCategoriaYCompetenciaPanel.add(disciplinaPanel);

    categoriaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Categorias"));

    categoriasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    ListaCategoriaModel listaCategoriaModel = new ListaCategoriaModel(categorias);
    categoriasList.setModel(listaCategoriaModel);
    categoriasList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            categoriasListValueChanged(evt);
        }
    });
    categoriaPanel.setViewportView(categoriasList);

    seleccionCategoriaYCompetenciaPanel.add(categoriaPanel);

    centerPanel.add(seleccionCategoriaYCompetenciaPanel);

    getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

    aceptarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/accept-16x16.png"))); // NOI18N
    aceptarButton.setText("Aceptar");
    aceptarButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            aceptarButtonActionPerformed(evt);
        }
    });
    southPanel.add(aceptarButton);

    cancelarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/cancel-16x16.png"))); // NOI18N
    cancelarButton.setText("Cancelar");
    cancelarButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelarButtonActionPerformed(evt);
        }
    });
    southPanel.add(cancelarButton);

    getContentPane().add(southPanel, java.awt.BorderLayout.SOUTH);

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoAspiranteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoAspiranteButtonActionPerformed
        int escuelaSelectedIndex = this.escuelaList.getSelectedIndex();
        if (escuelaSelectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Para agregar un aspirante debe seleccionar una escuela", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ListaEscuelasModel listaEscuelasModel = (ListaEscuelasModel) this.escuelaList.getModel();
        Escuela escuelaSeleccionada = listaEscuelasModel.getElementAt(escuelaSelectedIndex);
        Aspirante nuevoAspirante = this.gestorInscripcion.agregarAspirante(escuelaSeleccionada);
        if (nuevoAspirante != null) {
            ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
            listaAspiranteModel.addElement(nuevoAspirante);
            this.aspirantesList.setSelectedValue(nuevoAspirante, true);
            Categoria categoriaRecomendada = this.gestorInscripcion.obtenerCategoriaRecomendada(nuevoAspirante);
            if (categoriaRecomendada != null) {
                this.categoriasList.setSelectedValue(categoriaRecomendada, true);
            }
            restablecerTablaDisciplinas();
            habilitarControles();
        }
    }//GEN-LAST:event_nuevoAspiranteButtonActionPerformed

    private void escuelaListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_escuelaListValueChanged
        if (!evt.getValueIsAdjusting()) {
            restablecerTablaDisciplinas();
            this.categoriasList.setSelectedIndex(-1);

            int escuelaSelectionIndex = this.escuelaList.getSelectedIndex();
            ListaEscuelasModel listaEscuelaModel = (ListaEscuelasModel) this.escuelaList.getModel();
            Escuela escuelaSeleccionada = listaEscuelaModel.getElementAt(escuelaSelectionIndex);
            List<Aspirante> aspirantes = escuelaSeleccionada.getAspirantes();
            ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
            listaAspiranteModel.clear();

            if (CollectionUtils.isNotEmpty(aspirantes)) {
                listaAspiranteModel.addAll(aspirantes);
                this.aspirantesList.setSelectedIndex(0);
                gestionarSeleccionDeAspirante();
            } else {
                deshabilitarControles();
            }
        }
    }//GEN-LAST:event_escuelaListValueChanged

    private void aspirantesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_aspirantesListValueChanged
        if (!evt.getValueIsAdjusting()) {
            gestionarSeleccionDeAspirante();
        }
    }//GEN-LAST:event_aspirantesListValueChanged

    private void categoriasListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoriasListValueChanged
        if (!evt.getValueIsAdjusting()) {
            int aspiranteSelectionIndex = this.aspirantesList.getSelectedIndex();
            int selectedCompetenciasCount = this.competenciaTable.getSelectedRowCount();
            if (aspiranteSelectionIndex >= 0 && selectedCompetenciasCount > 0) {
                ListaAspiranteModel listaAspiranteModel = (ListaAspiranteModel) this.aspirantesList.getModel();
                Aspirante aspiranteActual = listaAspiranteModel.getElementAt(aspiranteSelectionIndex);

                // En caso que el aspirante seleccionado ya se encuentre persistido,
                // no debemos actualizar sus datos...
                if (aspiranteActual.getIdCompetidor() > 0) {
                    return;
                }

                int categoriaSelectionIndex = this.categoriasList.getSelectedIndex();
                ListaCategoriaModel listaCategoriaModel = (ListaCategoriaModel) this.categoriasList.getModel();
                Categoria categoriaSeleccionada = listaCategoriaModel.getElementAt(categoriaSelectionIndex);

                // Buscamos las Disciplinas seleccionadas para actualizar la inscripción
                // con la nueva categoría...
                TablaDisciplinasModel tablaDisciplinasModel = (TablaDisciplinasModel) this.competenciaTable.getModel();
                int selectedRow = this.competenciaTable.getSelectedRow();
                boolean isChecked = (Boolean) tablaDisciplinasModel.getValueAt(selectedRow, TablaDisciplinasModel.COLUMNA_BOOLEAN);
                Disciplina disciplinaSeleccionada = (Disciplina) tablaDisciplinasModel.getValueAt(selectedRow, TablaDisciplinasModel.COLUMNA_DISCIPLINA);
                if (isChecked) {
                    this.gestorInscripcion.actualizarCategoria(aspiranteActual, disciplinaSeleccionada, categoriaSeleccionada);
                }
            }
        }
    }//GEN-LAST:event_categoriasListValueChanged

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        ResultadoValidacionInscripcion validacionInscripcion = this.gestorInscripcion.validarInscripciones();
        if (!validacionInscripcion.isInscripcionesValidas()) {
            StringBuilder errorStringBuilder = new StringBuilder();

            // Aspirantes que no tienen inscripciones generadas...
            List<Aspirante> aspirantesConInscripcionesInvalidas = validacionInscripcion.getAspirantesConInscripcionesIncompletas();
            if (CollectionUtils.isNotEmpty(aspirantesConInscripcionesInvalidas)) {
                errorStringBuilder.append("Los siguientes aspirantes tienen inscripciones incompletas: \n").append("\n");
                for (Iterator<Aspirante> iterator = aspirantesConInscripcionesInvalidas.iterator(); iterator.hasNext();) {
                    Aspirante aspiranteActual = iterator.next();
                    errorStringBuilder.append("Nombre de Aspirante: ").append(aspiranteActual).append("\n");
                    errorStringBuilder.append("Institución Educativa: ").append(aspiranteActual.getEscuela()).append("\n");
                    errorStringBuilder.append("\n");
                }
            }

            if (errorStringBuilder.length() > 0) {
                errorStringBuilder.append("\n");
            }

            // Inscripciones donde no coinciden el sexo del aspirante 
            // con la categoría seleccionada...
            List<Inscripcion> inscripcionesConError = validacionInscripcion.getInscripcionesConError();
            if (CollectionUtils.isNotEmpty(inscripcionesConError)) {
                errorStringBuilder.append("Los siguientes aspirantes tienen inscripciones con datos inconsistentes: \n").append("\n");
                for (Iterator<Inscripcion> it = inscripcionesConError.iterator(); it.hasNext();) {
                    Inscripcion inscripcionActual = it.next();
                    errorStringBuilder.append("Nombre de Aspirante: ").append(inscripcionActual.getAspirante()).append("\n");
                    errorStringBuilder.append("Institución Educativa: ").append(inscripcionActual.getAspirante().getEscuela()).append("\n");
                    errorStringBuilder.append("Disciplina: ").append(inscripcionActual.getDisciplina()).append("\n");
                    errorStringBuilder.append("Sexo de Aspirante: ").append(inscripcionActual.getAspirante().getSexo()).append("\n");
                    errorStringBuilder.append("Género de Categoría seleccionada: ").append(inscripcionActual.getCategoria().getGenero()).append("\n");
                    errorStringBuilder.append("El sexo del aspirante y el género de la categoría no coinciden.").append("\n");
                    errorStringBuilder.append("\n");

                }
            }
            mostrarErroresDeValidacion(errorStringBuilder);
        } else {
            int cantidadDeNuevosAspirantes = this.gestorInscripcion.obtenerCantidadDeNuevosAspirantes();
            if (cantidadDeNuevosAspirantes > 0) {
                guardarInscripciones();
            } else {
                JOptionPane.showMessageDialog(this, "No hay nuevas inscripciones a generar", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        int cantidadDeNuevosAspirantes = this.gestorInscripcion.obtenerCantidadDeNuevosAspirantes();
        if (cantidadDeNuevosAspirantes > 0) {
            int respuesta = JOptionPane.showConfirmDialog(this, "Si cancela la operación se perderán las inscripciones generadas, ¿Confirma salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                return;
            }
        }
        this.gestorInscripcion.cancelar();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JList<String> aspirantesList;
    private javax.swing.JPanel aspirantesPanel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JScrollPane categoriaPanel;
    private javax.swing.JList<String> categoriasList;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JTable competenciaTable;
    private javax.swing.JScrollPane disciplinaPanel;
    private javax.swing.JList<String> escuelaList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton nuevoAspiranteButton;
    private javax.swing.JPanel nuevoAspirantePanel;
    private javax.swing.JPanel seleccionCategoriaYCompetenciaPanel;
    private javax.swing.JPanel southPanel;
    // End of variables declaration//GEN-END:variables
}
