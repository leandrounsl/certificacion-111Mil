package gestion.torneos.model;

/**
 * Clase que representa el estado de una inscripción.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class EstadoInscripcion implements java.io.Serializable {

    private int idEstado;
    private String nombre;
    private String descripcion;

    /**
     * Constructor por defecto, sin parámetros.
     */
    public EstadoInscripcion() {

    }

    /**
     * Constructor que recibe los siguientes datos del estado de la insripción:
     * <li>Id del estado</li>
     * <li>Nombre del estado</li>
     *
     * @param idEstado Id del estado.
     * @param nombre Nombre del estado.
     */
    public EstadoInscripcion(int idEstado, String nombre) {
        this.idEstado = idEstado;
        this.nombre = nombre;
    }

    /**
     * Constructor que recibe los siguientes datos del estado de la insripción:
     * <li>Id del estado</li>
     * <li>Nombre del estado</li>
     * <li>Descripción del estado</li>
     *
     * @param idEstado Id del estado.
     * @param nombre Nombre del estado.
     * @param descripcion Descripción del estado.
     */
    public EstadoInscripcion(int idEstado, String nombre, String descripcion/*, Set inscripcions*/) {
        this.idEstado = idEstado;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Retorna el Id del estado.
     *
     * @return int - Id del estado.
     */
    public int getIdEstado() {
        return this.idEstado;
    }

    /**
     * Setea el Id del estado.
     *
     * @param idEstado Id del estado.
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    /**
     * Retorna el nombre del estado.
     *
     * @return String - Nombre del estado.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre del estado.
     *
     * @param nombre Nombre del estado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descricpión del estado.
     *
     * @return String - Descripción del estado.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setea la descripción del estado.
     *
     * @param descripcion Descripción del estado.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
