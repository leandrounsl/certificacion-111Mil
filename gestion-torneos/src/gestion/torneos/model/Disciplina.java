package gestion.torneos.model;

import java.util.Objects;

/**
 * Clase que representa una disciplina.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Disciplina implements java.io.Serializable {

    private int idDisciplina;
    private String nombre;
    private String descripcion;

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Disciplina() {

    }

    /**
     * Constructor que recibe los siguientes datos de la disciplina:
     * <li>Id de la disciplina</li>
     * <li>Nombre de la disciplina</li>
     *
     * @param idDisciplina Id de la disciplina.
     * @param nombre Nombre de la disciplina.
     */
    public Disciplina(int idDisciplina, String nombre) {
        this.idDisciplina = idDisciplina;
        this.nombre = nombre;
    }

    /**
     * Constructor que recibe los siguientes datos de la disciplina:
     * <li>Id de la disciplina</li>
     * <li>Nombre de la disciplina</li>
     * <li>Descripción de la disciplina</li>
     *
     * @param idDisciplina Id de la disciplina.
     * @param nombre Nombre de la disciplina.
     * @param descripcion Descripción de la disciplina.
     */
    public Disciplina(int idDisciplina, String nombre, String descripcion/*, Set inscripcions*/) {
        this.idDisciplina = idDisciplina;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Retorna el Id de la disciplina.
     *
     * @return int - Id de disciplina.
     */
    public int getIdDisciplina() {
        return this.idDisciplina;
    }

    /**
     * Setea el Id de la disciplina.
     *
     * @param idDisciplina El Id de la disciplina.
     */
    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    /**
     * Retorna el nombre de la disciplina.
     *
     * @return String - El nombre de la disciplina.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre de la disciplina.
     *
     * @param nombre El nombre de la disciplina.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la descripción de la disciplina.
     *
     * @return String - La descripción de la disciplina.
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Setea la descripción de la disciplina.
     *
     * @param descripcion La descripción de la disciplina.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disciplina other = (Disciplina) obj;
        if (this.idDisciplina != other.idDisciplina) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
}
