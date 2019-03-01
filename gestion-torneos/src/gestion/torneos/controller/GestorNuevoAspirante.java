package gestion.torneos.controller;

import gestion.torneos.dao.AspiranteDao;
import gestion.torneos.dao.impl.AspiranteDaoHibernateImpl;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Genero;
import gestion.torneos.ui.PantallaNuevoAspirante;
import gestion.torneos.ui.ResultadoValidacionNuevoAspirante;
import gestion.torneos.util.AppUtils;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Esta clase define los métodos necesarios para creación de un nuevo aspirante.
 * Resuelve las operaciones invocadas desde la interfaz gráfica de usuario.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class GestorNuevoAspirante {

    /**
     * Edad mínima de un nuevo aspirante.
     */
    public static int EDAD_MINIMA_ASPIRANTE = 6;
    /**
     * Edad máxima de un nuevo aspirante.
     */
    public static int EDAD_MAXIMA_ASPIRANTE = 18;
    /**
     * Longitud de un DNI
     */
    public static int LONGITUD_MAXIMA_DNI = 8;

    private PantallaNuevoAspirante pantallaNuevoAspirante = null;
    private AspiranteDao aspiranteDao = null;

    private Aspirante nuevoAspirante = null;

    private List<Aspirante> listaDeAspirantesGenerados = null;

    private void cerrarDialogo() {
        pantallaNuevoAspirante.setVisible(false);
        pantallaNuevoAspirante.dispose();
    }

    /**
     * Constructor que recibe la lista de Aspirantes generados hasta el momento.
     * Esta lista se utiliza para validar que el DNI ingresado sea único.
     *
     * @param listaDeAspirantesGenerados Lista de aspirantes generados.
     */
    public GestorNuevoAspirante(List<Aspirante> listaDeAspirantesGenerados) {
        this.listaDeAspirantesGenerados = listaDeAspirantesGenerados;
        this.aspiranteDao = new AspiranteDaoHibernateImpl();
    }

    /**
     * Inicia la operación de alta de Aspirante. Genera el formulario de Nuevo
     * Aspirante.
     */
    public void iniciarOperacion() {
        this.pantallaNuevoAspirante = new PantallaNuevoAspirante(this);
        this.pantallaNuevoAspirante.setVisible(true);
    }

    /**
     * Valida si los datos argumentados son correctos.<br>
     * <li>Nombre - Se valida que no sea null ni un String vacío.</li>
     * <li>Apellido - Se valida que no sea null ni un String vacío.</li>
     * <li>Feha De Nacimiento - Se valida que no sea null ni un String vacío y
     * que tenga formato dd/mm/aaaa.</li>
     * <li>DNI - Se valida que sea un valor solo numérico, que contenga 8
     * números y que sea único en el sistema.</li>
     *
     * @param nombre Nombre del aspirante.
     * @param apellido Apellido del aspirante.
     * @param fechaNacimiento Fecha de Nacimiento del aspirante.
     * @param dni DNI del aspirante.
     * @return
     * @throws Exception
     */
    public ResultadoValidacionNuevoAspirante validarDatosNuevoAspirante(String nombre, String apellido, String fechaNacimiento, String dni) throws Exception {

        ResultadoValidacionNuevoAspirante resultadoValidacion = new ResultadoValidacionNuevoAspirante();
        resultadoValidacion.setNombreOk(StringUtils.isNotBlank(nombre));
        resultadoValidacion.setApellidoOk(StringUtils.isNotBlank(apellido));
        resultadoValidacion.setFechaNacimientoOk(StringUtils.isNotBlank(fechaNacimiento));
        // Si la fecha de nacimiento no es vacia, validamos formato...
        if (resultadoValidacion.isFechaNacimientoOk()) {
            resultadoValidacion.setFechaNacimientoFormatoOk(AppUtils.esFormatoFechaValido(fechaNacimiento));
            if (resultadoValidacion.isFechaNacimientoFormatoOk()) {
                Date fechaIngresada = AppUtils.stringToDate(fechaNacimiento);
                boolean esFechaIngresadaMenorQueLaFechaActualOk = AppUtils.esFechaIngresadaMenorQueFechaActual(fechaIngresada);
                resultadoValidacion.setFechaIngresadaMenorQueActualOk(esFechaIngresadaMenorQueLaFechaActualOk);
                if (resultadoValidacion.isFechaIngresadaMenorQueActualOk()) {
                    // Creamos un objeto aspirante para calcular la edad de la persona...
                    Aspirante aspAux = new Aspirante();
                    aspAux.setFechaNacimiento(fechaIngresada);
                    int edad = aspAux.calcularEdad();
                    boolean rangoDeEdadOk = (edad >= EDAD_MINIMA_ASPIRANTE && edad <= EDAD_MAXIMA_ASPIRANTE);
                    resultadoValidacion.setRangoDeEdadOk(rangoDeEdadOk);

                }
            }
        }
        resultadoValidacion.setDniOk(StringUtils.isNotBlank(dni));
        if (resultadoValidacion.isDniOk()) {
            boolean esNumeroValido = AppUtils.esNumeroValido(dni);
            resultadoValidacion.setDniSoloNumerosOk(esNumeroValido);

            if (esNumeroValido) {
                int longitudDni = dni.length();
                resultadoValidacion.setLongitudDniOk(longitudDni == LONGITUD_MAXIMA_DNI);

                int dniInteger = Integer.parseInt(dni);
                Aspirante aspiranteByDni = this.aspiranteDao.obtenerAspirantePorDni(dniInteger);
                boolean noExisteAspiranteEnBaseDeDatos = aspiranteByDni == null;
                resultadoValidacion.setDniExistenteOk(noExisteAspiranteEnBaseDeDatos);

                // Validamos si existe el DNI en los aspirantes generados hasta el momento...
                if (noExisteAspiranteEnBaseDeDatos && CollectionUtils.isNotEmpty(this.listaDeAspirantesGenerados)) {
                    for (Iterator<Aspirante> aspiranteIterator = listaDeAspirantesGenerados.iterator(); aspiranteIterator.hasNext();) {
                        Aspirante aspiranteActual = aspiranteIterator.next();
                        if (dniInteger == aspiranteActual.getDni()) {
                            resultadoValidacion.setDniExistenteOk(false);
                            break;
                        }
                    }
                }
            }
        }
        return resultadoValidacion;
    }

    /**
     * Crea un objeto de tipo Aspirante con la información argumentada y lo
     * almacena en la variable de instancia nuevoAspirante.
     *
     * @param nombre Nombre del aspirante.
     * @param apellido Apellido del aspirante.
     * @param fechaNacimiento Fecha de nacimiento de aspirante.
     * @param dni DNI de Aspirante.
     * @param sexo Sexo de Aspirante (FEMENINO/MASCULINO)
     * @param domicilio Domicio de Aspirante
     */
    public void crearAspirante(String nombre, String apellido, String fechaNacimiento, String dni, String sexo, String domicilio) {

        Date fechaNacimientoDate = AppUtils.stringToDate(fechaNacimiento);
        Genero sexoEnum = Genero.valueOf(sexo);
        this.nuevoAspirante = new Aspirante(nombre, apellido, fechaNacimientoDate, Integer.parseInt(dni), sexoEnum, domicilio);
        cerrarDialogo();
    }

    /**
     * Retorna el Aspirante generado en la invocación del método crearAspirante.
     *
     * @return Aspirante El aspirante generado
     */
    public Aspirante getNuevoAspirante() {
        return nuevoAspirante;
    }

    /**
     * Cancela la operación de creación de nuevo aspirante y cierra el
     * formulario.
     */
    public void cancelarOperacion() {
        cerrarDialogo();
    }
}
