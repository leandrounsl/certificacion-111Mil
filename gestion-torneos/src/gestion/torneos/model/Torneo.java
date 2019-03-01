package gestion.torneos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un torneo.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Torneo implements java.io.Serializable {

    private int idTorneo;
    private String nombre;
    private String descripcion;
    private List<Inscripcion> inscripcions = new ArrayList<>();

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Torneo() {

    }

    /**
     * Constructor que recibe los siguientes datos del torneo:
     * <li>Id de torneo</li>
     * <li>Nombre del torneo</li>
     *
     * @param idTorneo Id del torneo.
     * @param nombre Nombre del torneo.
     */
    public Torneo(int idTorneo, String nombre) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
    }

    /**
     * Constructor que recibe los siguientes datos del torneo:
     * <li>Id de torneo</li>
     * <li>Nombre del torneo</li>
     * <li>Descripción del torneo</li>
     * <li>Lista de inscripciones al torneo</li>
     *
     * @param idTorneo Id del torneo.
     * @param nombre Nombre del torneo.
     * @param descripcion Descripción del torneo.
     * @param inscripcions Lista de inscripciones.
     */
    public Torneo(int idTorneo, String nombre, String descripcion, List<Inscripcion> inscripcions) {
        this.idTorneo = idTorneo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inscripcions = inscripcions;
    }

    /**
     * Retorna el Id del torneo.
     *
     * @return int - Id del torneo
     */
    public int getIdTorneo() {
        return this.idTorneo;
    }

    /**
     * Setea el Id del torneo.
     *
     * @param idTorneo Id del torneo.
     */
    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }

    /**
     * Retorna el nombre del torneo.
     *
     * @return String - El nombre del torneo.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre del torneo.
     *
     * @param nombre El nombre del torneo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripción del torneo.
     *
     * @return String - La descripción del torneo.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setea la descripción del torneo.
     *
     * @param descripcion La descripción del torneo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Retorna la lista de inscripciones del torneo.
     *
     * @return Lista de inscripciones.
     */
    public List<Inscripcion> getInscripcions() {
        return inscripcions;
    }

    /**
     * Setea la lista de inscripciones del torneo.
     *
     * @param inscripcions Lista de inscripciones.
     */
    public void setInscripcions(List<Inscripcion> inscripcions) {
        this.inscripcions = inscripcions;
    }

    @Override
    public String toString() {
        return nombre;
    }    
}
