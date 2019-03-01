package gestion.torneos.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un aspirante.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Aspirante implements java.io.Serializable {

    private int idCompetidor;
    private Escuela escuela;
    private String nombre;
    private String apellido;
    private String direccion;
    private Date fechaNacimiento;
    private int dni;
    private Genero sexo;
    private List<Inscripcion> inscripcions = new ArrayList<>();

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Aspirante() {

    }

    /**
     * Constructor que recibe los siguientes datos del Aspirante:
     * <li>Nombre</li>
     * <li>Apellido</li>
     * <li>Fecha de Nacimiento</li>
     * <li>DNI</li>
     * <li>Sexo</li>
     * <li>Dirección</li>
     *
     * @param nombre Nombre del aspirante.
     * @param apellido Apellido del aspirante.
     * @param fechaNacimiento Fecha de Nacimiento del aspirante.
     * @param dni DNI del aspirante.
     * @param sexo Sexo del aspirante.
     * @param direccion Dirección del aspirante.
     */
    public Aspirante(String nombre, String apellido, Date fechaNacimiento, int dni, Genero sexo, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        this.direccion = direccion;
    }

    /**
     * Constructor que recibe los siguientes datos del Aspirante:
     * <li>ID de Aspirante</li>
     * <li>Nombre</li>
     * <li>Apellido</li>
     * <li>Fecha de Nacimiento</li>
     * <li>DNI</li>
     * <li>Sexo</li>
     * <li>Dirección</li>
     * <li>Lista de Inscripciones</li>
     *
     * @param idCompetidor ID de aspirante.
     * @param nombre Nombre del aspirante.
     * @param apellido Apellido del aspirante.
     * @param fechaNacimiento Fecha de Nacimiento del aspirante.
     * @param dni DNI del aspirante.
     * @param sexo Sexo del aspirante.
     * @param direccion Dirección del aspirante.
     * @param inscripcions Lista de inscripciones.
     */
    public Aspirante(int idCompetidor, String nombre, String apellido, Date fechaNacimiento, int dni, Genero sexo, String direccion, List<Inscripcion> inscripcions) {
        this.idCompetidor = idCompetidor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.sexo = sexo;
        this.direccion = direccion;
        this.inscripcions = inscripcions;
    }

    /**
     * Retorna el Id del competidor.
     *
     * @return int id
     */
    public int getIdCompetidor() {
        return this.idCompetidor;
    }

    /**
     * Setea el Id del competidor.
     *
     * @param idCompetidor El Id del competidor.
     */
    public void setIdCompetidor(int idCompetidor) {
        this.idCompetidor = idCompetidor;
    }

    /**
     * Retorna la escuela a la cual pertenece el aspirante.
     *
     * @return Escuela escuela.
     */
    public Escuela getEscuela() {
        return this.escuela;
    }

    /**
     * Setea la escuela al que pertenece el aspirante.
     *
     * @param escuela Escuela al que pertenece el aprirante.
     */
    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    /**
     * Retorna el nombre del aspirante.
     *
     * @return String - El nombre del aspirante.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Setea el nombre del aspirante.
     *
     * @param nombre El nombre del aspirante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del aspirante.
     *
     * @return String - El apellido del aspirante.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * Setea el apellido del aspirante.
     *
     * @param apellido El apellido del aspirante.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna la dirección del aspirante.
     *
     * @return String - La dirección del aspirante.
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Setea la dirección del aspirante.
     *
     * @param direccion La dirección del aspirante.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna la fecha de nacimiento del aspirante.
     *
     * @return Date - La fecha de nacimiento del aspirante.
     */
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    /**
     * Setea la fecha de nacimiento del aspirante.
     *
     * @param fechaNacimiento La fecha de nacimiento del aspirante.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el dni del aspirante.
     *
     * @return int - El dni del aspirante.
     */
    public int getDni() {
        return this.dni;
    }

    /**
     * Setea el dni del aspirante.
     *
     * @param dni El dni del aspirante.
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Retorna el sexo del aspirante.
     *
     * @return Genero - El sexo del aspirante.
     */
    public Genero getSexo() {
        return sexo;
    }

    /**
     * Setea el sexo del aspirante.
     *
     * @param sexo El sexo del aspirante.
     */
    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    /**
     * Retorna la lista de inscripciones asociadas al aspirante.
     *
     * @return Lista de inscripciones.
     */
    public List<Inscripcion> getInscripcions() {
        return inscripcions;
    }

    /**
     * Setea la lista de inscripciones.
     *
     * @param inscripcions - Lista de inscripciones.
     */
    public void setInscripcions(List<Inscripcion> inscripcions) {
        this.inscripcions = inscripcions;
    }

    /**
     * Agrega una nueva inscripción a la lista de inscripciones.
     *
     * @param inscripcion Inscripción a agregar.
     */
    public void agregarInscripcion(Inscripcion inscripcion) {
        this.inscripcions.add(inscripcion);
        inscripcion.setAspirante(this);
    }

    /**
     * Borra una inscripción de la lista de inscripciones.
     *
     * @param inscripcion Inscripción a borrar.
     */
    public void borrarInscripcion(Inscripcion inscripcion) {
        this.inscripcions.remove(inscripcion);
    }

    /**
     * Calcula la edad del aspirante en base a su fecha de nacimiento.
     *
     * @return int Edad del aspirante.
     */
    public int calcularEdad() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);
        // Cálculo de las diferencias.
        int years = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int months = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);

        // Hay que comprobar si el día de su cumpleaños es posterior
        // a la fecha actual, para restar 1 a la diferencia de años,
        // pues aún no ha sido su cumpleaños.
        if (months < 0 // Aún no es el mes de su cumpleaños
                || (months == 0 && days < 0)) { // o es el mes pero no ha llegado el día.
            years--;
        }
        return years;
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
        final Aspirante other = (Aspirante) obj;
        if (this.idCompetidor > 0 && this.idCompetidor != other.idCompetidor) {
            return false;
        }
        if (this.dni != other.dni) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.apellido + ", " + this.nombre;
    }

}
