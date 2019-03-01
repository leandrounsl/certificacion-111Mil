package gestion.torneos.controller;

import gestion.torneos.dao.AspiranteDao;
import gestion.torneos.dao.CategoriaDao;
import gestion.torneos.dao.DisciplinaDao;
import gestion.torneos.dao.EscuelaDao;
import gestion.torneos.dao.EstadoInscripcionDao;
import gestion.torneos.dao.InscripcionDao;
import gestion.torneos.dao.TorneoDao;
import gestion.torneos.dao.impl.AspiranteDaoHibernateImpl;
import gestion.torneos.dao.impl.CategoriaDaoHibernateImpl;
import gestion.torneos.dao.impl.DisciplinaDaoHibernateImpl;
import gestion.torneos.dao.impl.EscuelaDaoHibernateImpl;
import gestion.torneos.dao.impl.EstadoInscripcionDaoHibernateImpl;
import gestion.torneos.dao.impl.InscripcionDaoImpl;
import gestion.torneos.dao.impl.TorneoDaoHibernateImpl;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import gestion.torneos.model.Disciplina;
import gestion.torneos.model.Escuela;
import gestion.torneos.model.Inscripcion;
import gestion.torneos.model.Torneo;
import gestion.torneos.ui.PantallaInscripcion;
import gestion.torneos.ui.ResultadoValidacionInscripcion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * Esta clase define los métodos necesarios para la administración de las
 * inscripciones a un torneo. Resuelve las operaciones invocadas desde la
 * interfaz gráfica de usuario.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class GestorInscripcion {
    
    private static final Logger _logger = Logger.getLogger(GestorInscripcion.class);

    private static final String NOMBRE_TORNEO = "Torneo de Atletismo";

    private final CategoriaDao categoriaDao;
    private final DisciplinaDao disciplinaDao;
    private final EscuelaDao escuelaDao;
    private final EstadoInscripcionDao estadoInscripcionDao;
    private final TorneoDao torneoDao;
    private final AspiranteDao aspiranteDao;
    private final InscripcionDao inscripcionDao;

    private Torneo torneo = null;

    // Lista que contendrá los aspirantes generados...
    private List<Aspirante> aspirantesGenerados = new ArrayList<>();

    private PantallaInscripcion pantallaInscripcion = null;
    
    // Lista de Escuelas
    private List<Escuela> escuelas = null;
    
     // Lista de Disciplinas
    private List<Disciplina> disciplinas = null;
    
    // Lista de Categorías
    private List<Categoria> categorias = null;  

    /**
     * Constructor por defecto, si parámetros.
     *
     * @throws java.lang.Exception
     */
    public GestorInscripcion() throws Exception {
        try {
            this.categoriaDao = new CategoriaDaoHibernateImpl();
            this.disciplinaDao = new DisciplinaDaoHibernateImpl();
            this.escuelaDao = new EscuelaDaoHibernateImpl();
            this.estadoInscripcionDao = new EstadoInscripcionDaoHibernateImpl();
            this.torneoDao = new TorneoDaoHibernateImpl();
            this.aspiranteDao = new AspiranteDaoHibernateImpl();
            this.inscripcionDao = new InscripcionDaoImpl(this.estadoInscripcionDao);

            // Se recupera de la base un torneo particular para simular la
            // inscripción a ese torneo. En el sistema real el torneo será
            // generado y seleccionado desde el administrador de torneos
            // y pasado como parámetro al gestor de inscripción.
            this.torneo = this.torneoDao.buscarPorNombre(NOMBRE_TORNEO);
            
            // Cargamos lista de escuelas.
            this.escuelas = this.escuelaDao.obtenerEscuelas();
            
            // Cargamos lista de disciplinas.
            this.disciplinas = this.disciplinaDao.obtenerDisciplinas();
            
            // Cargamos lista de categorías.
            this.categorias = this.categoriaDao.obtenerCategorias();
            
        } catch (Exception ex) {
            _logger.error("Error al crear la instancia de GestorInscripcion: " + ex.getMessage(), ex);
            throw ex;
        }
    }

    private void cerrarVentana() {
        _logger.debug("Finalizando aplicación...");
        this.pantallaInscripcion.setVisible(false);
        this.pantallaInscripcion.dispose();
        System.exit(0);
    }
    
    /**
     * Inicia la operación de inscripción. Genera la interfaz gráfica de
     * usuario.
     */
    public void iniciarProcesoDeInscripcion() {
        _logger.debug("Iniciando proceso de inscripción");
        this.pantallaInscripcion = new PantallaInscripcion(this, escuelas, disciplinas, categorias);
        this.pantallaInscripcion.setVisible(true);
    }

    /**
     * Retorna la lista de escuelas definidas en el sistema.
     *
     * @return Lista de escuelas.
     * @throws Exception
     */
    public List<Escuela> obtenerEscuelas() throws Exception {
        return escuelaDao.obtenerEscuelas();
    }

    /**
     * Retorna la lista de disciplinas definidas en el sistema.
     *
     * @return Lista de disciplinas.
     * @throws Exception
     */
    public List<Disciplina> obtenerDisciplinas() throws Exception {
        return this.disciplinaDao.obtenerDisciplinas();
    }

    /**
     * Retorna la lista de categorías definidas en el sistema.
     *
     * @return Lista de categorías.
     * @throws Exception
     */
    public List<Categoria> obtenerCategorias() throws Exception {
        return this.categoriaDao.obtenerCategorias();
    }

    /**
     * Agrega un aspirante en la Escuela argumentada. Para llevar a cabo esta
     * operación, se crea un formulario (JDialog) que permite el insgreso de los
     * datos del nuevo aspirante. Si la validación del formulario es correcta,
     * se crea un nuevo objeto de tipo Aspirante con la información ingresada
     * por el usuario y se asocia a la Escuela argumentada.
     *
     * @param escuela Objeto Escuela que representa la entidad educativa donde
     * se agregará el nuevo aspirante.
     * @return Aspirante El nuevo aspirante generado.
     */
    public Aspirante agregarAspirante(Escuela escuela) {
        GestorNuevoAspirante gestorNuevoAspirante = new GestorNuevoAspirante(this.aspirantesGenerados);
        gestorNuevoAspirante.iniciarOperacion();// Lanza JDialog Modal...
        Aspirante nuevoAspirante = gestorNuevoAspirante.getNuevoAspirante();
        if (nuevoAspirante != null) {
            escuela.addAspirante(nuevoAspirante);
            this.aspirantesGenerados.add(nuevoAspirante);
        }
        return nuevoAspirante;
    }

    /**
     * Retorna la categoría recomendada para el aspirante argumentado, según su
     * edad y sexo.
     *
     * @param aspirante El aspirante para el cual queremos obtener la categoría
     * recomendada.
     * @return Categoria La categoría recomendada.
     */
    public Categoria obtenerCategoriaRecomendada(Aspirante aspirante) {
        return this.categoriaDao.obtenerCategoriaRecomendadaPorAspirante(aspirante);
    }

    /**
     * Genera un objeto de tipo Inscripcion con la Disciplina y Categoría
     * argumentadas, y la agrega a la lista de inscripciones del Aspirante
     * pasado como parámetro.
     *
     * @param aspirante Aspirante donde se agregará la nueva inscripción.
     * @param disciplina La disciplina seleccionada.
     * @param categoria La categoría seleccionada.
     */
    public void agregarInscripcion(Aspirante aspirante, Disciplina disciplina, Categoria categoria) {
        this.inscripcionDao.agregarInscripcion(aspirante, disciplina, categoria, torneo);
    }

    /**
     * Elimina la inscripción asociada a la disciplina argumentada.
     *
     * @param aspirante - Aspirante del cual se borrará la inscripción.
     * @param disciplinaAEliminar - Disciplina asociada a la inscripción.
     */
    public void borrarInscripcion(Aspirante aspirante, Disciplina disciplinaAEliminar) {
        this.inscripcionDao.eliminarInscripcion(aspirante, disciplinaAEliminar);
    }

   /**
     * Retorna la inscripción correspondiente al aspirante y a la disciplina
     * argumentadas.
     *
     * @param aspirante
     * @param disciplina
     * @return Inscripcion inscripcion - Inscripción correspondiente a el
     * aspirante y disciplina argumentados si existe, null en otro caso.
     */
    public Inscripcion obtenerInscripcionPorDisciplina(Aspirante aspirante, Disciplina disciplina) {
        return this.inscripcionDao.buscarInscripcionPorAspiranteYDisciplina(aspirante, disciplina);
    }

    /**
     * Retorna todas las disciplinas en las que esta inscripto el Aspirante
     * argumentado.
     *
     * @param aspirante El aspirante del cual se obtendrán las disciplinas
     * asociadas.
     * @return Lista de Disciplinas.
     */
    public List<Disciplina> obtenerDisciplinasPorAspirante(Aspirante aspirante) {
        return this.disciplinaDao.buscarDisciplinasPorAspirante(aspirante);
    }

    /**
     * Actualiza la inscripcion del Aspirante que corresponde a la
     * disciplina argumentada con la Categoría pasada como parámetro.
     *
     * @param aspirante El aspirante a actualizar.
     * @param disciplina Disciplina de la inscripción.
     * @param categoria Nueva categoría.
     */
    public void actualizarCategoria(Aspirante aspirante, Disciplina disciplina , Categoria categoria) {
        this.inscripcionDao.actualizarCategoriaDeInscripcionPorAspiranteYDisciplina(aspirante, disciplina, categoria);
    }

    /**
     * Valida que todos los aspirantes generados tengan al menos una inscripción
     * asociada. Si algun Aspirante no tiene inscripciones generadas se
     * considera un error de validación.
     *
     * Adicionalmente, valida que el genero de la categoría y el sexo del
     * aspirante asociados a una inscripción coincidan.
     *
     * @return ResultadoValidacionInscripcion Objeto que contiene una lista de
     * aspirantes con errores de validación.
     */
    public ResultadoValidacionInscripcion validarInscripciones() {
        ResultadoValidacionInscripcion resultadoValidacionInscripcion = new ResultadoValidacionInscripcion();
        for (Iterator<Aspirante> iteratorAspirantes = aspirantesGenerados.iterator(); iteratorAspirantes.hasNext();) {
            Aspirante aspiranteActual = iteratorAspirantes.next();
            List<Inscripcion> inscripcions = aspiranteActual.getInscripcions();
            if (CollectionUtils.isEmpty(inscripcions)) {
                resultadoValidacionInscripcion.addAspiranteConInscripcionIncompleta(aspiranteActual);
            } else {
                for (Iterator<Inscripcion> it = inscripcions.iterator(); it.hasNext();) {
                    Inscripcion inscripcionActual = it.next();
                    Categoria categoria = inscripcionActual.getCategoria();
                    Aspirante aspirante = inscripcionActual.getAspirante();
                    if (aspirante.getSexo() != categoria.getGenero()) {
                        resultadoValidacionInscripcion.addInscripcionConError(inscripcionActual);
                    }
                }
            }
        }
        return resultadoValidacionInscripcion;
    }

    /**
     * Persiste las inscripciones generadas.
     *
     * @throws Exception
     */
    public void guardarInscripciones() throws Exception {
        _logger.debug("Inicia la persistencia de las inscripciones");
        for (Iterator<Aspirante> iterator = aspirantesGenerados.iterator(); iterator.hasNext();) {
            Aspirante aspiranteActual = iterator.next();
            this.aspiranteDao.persistirAspirante(aspiranteActual);
            // Eliminamos de la lista de Aspirantes generados 
            // el aspirante persisitido...
            iterator.remove();
        }
    }

    /**
     * Retorna la cantidad de aspirantes generados que todavía no fueron
     * persistidos hasta el momento.
     *
     * @return cantidad de aspirantes generados.
     */
    public int obtenerCantidadDeNuevosAspirantes() {
        return this.aspirantesGenerados.size();
    }

    /**
     * Cancela la operación de inscripción y cierra la aplicación.
     */
    public void cancelar() {
        cerrarVentana();
    }
}
