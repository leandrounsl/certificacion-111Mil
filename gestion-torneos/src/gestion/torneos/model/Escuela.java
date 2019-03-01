package gestion.torneos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una escuela.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Escuela implements java.io.Serializable {

    private int idEscuela;
    private String nombre;
    private List<Aspirante> aspirantes = new ArrayList<>();

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Escuela() {

    }

    /**
     * Constructor que recibe los siguientes datos de la escuela:
     * <li>Id de la escuela</li>
     * <li>Nombre de la escuela</li>
     *
     * @param idEscuela Id de la escuela.
     * @param nombre Nombre de la escuela.
     */
    public Escuela(int idEscuela, String nombre) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
    }

    /**
     * Constructor que recibe los siguientes datos de la escuela:
     * <li>Id de la escuela</li>
     * <li>Nombre de la escuela</li>
     * <li>Lista de aspirantes que pertenecen a la escuela</li>
     *
     * @param idEscuela Id de la escuela.
     * @param nombre Nombre de la escuela.
     * @param aspirantes Lista de aspirantes.
     */
    public Escuela(int idEscuela, String nombre, List<Aspirante> aspirantes) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
        this.aspirantes = aspirantes;
    }

    /**
     * Retorna el Id de la escuela.
     *
     * @return int - Id de la escuela.
     */
    public int getIdEscuela() {
        return this.idEscuela;
    }

    /**
     * Setea el Id de la escuela.
     *
     * @param idEscuela El Id de la escuela.
     */
    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    /**
     * Retorna el nombre de la escuela.
     *
     * @return String - El nombre de la escuela.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre de la escuela.
     *
     * @param nombre El nombre de la escuela.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la lista de aspirantes que pertenecen a la escuela.
     *
     * @return Lista de aspirantes.
     */
    public List<Aspirante> getAspirantes() {
        return aspirantes;
    }

    /**
     * Setea la lista de aspirantes que pertenecen a la escuela.
     *
     * @param aspirantes Lista de aspirantes.
     */
    public void setAspirantes(List<Aspirante> aspirantes) {
        this.aspirantes = aspirantes;
    }

    /**
     * Agrega un nuevo aspirante a la escuela.
     *
     * @param aspirante El nuevo aspirante.
     */
    public void addAspirante(Aspirante aspirante) {
        this.aspirantes.add(aspirante);
        aspirante.setEscuela(this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idEscuela;
        hash = 11 * hash + Objects.hashCode(this.nombre);
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
        final Escuela other = (Escuela) obj;
        if (this.idEscuela != other.idEscuela) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
