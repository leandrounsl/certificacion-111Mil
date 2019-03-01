package gestion.torneos.model;

/**
 * Clase que representa una inscripción.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class Inscripcion implements java.io.Serializable {

    private int idInscripcion;
    private Aspirante aspirante;
    private Categoria categoria;
    private Disciplina disciplina;
    private EstadoInscripcion estado;
    private Torneo torneo;

    /**
     * Constructor por defecto, sin parámetros.
     */
    public Inscripcion() {

    }

    /**
     * Constructor que recibe los siguientes datos de la inscripción:
     * <li>Id de la inscripción</li>
     * <li>Aspirante al cual pertenece la inscripción</li>
     * <li>Categoría del aspirante</li>
     * <li>Disciplina a competir</li>
     * <li>Estado de la inscripción</li>
     * <li>Torneo al cual pertenece la inscripción</li>
     *
     * @param idInscripcion Id de la inscripción.
     * @param aspirante Aspirante al cual pertenece la inscripción.
     * @param categoria Categoría del aspirante.
     * @param disciplina Disciplina a competir.
     * @param estado Estado de la inscripción.
     * @param torneo Torneo al cual pertenece la inscripción.
     */
    public Inscripcion(int idInscripcion, Aspirante aspirante, Categoria categoria, Disciplina disciplina, EstadoInscripcion estado, Torneo torneo) {
        this.idInscripcion = idInscripcion;
        this.aspirante = aspirante;
        this.categoria = categoria;
        this.disciplina = disciplina;
        this.estado = estado;
        this.torneo = torneo;
    }

    /**
     * Retorna el Id de la inscripción.
     *
     * @return
     */
    public int getIdInscripcion() {
        return this.idInscripcion;
    }

    /**
     * Setea el Id de la inscripción.
     *
     * @param idInscripcion Id de la inscripción.
     */
    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    /**
     * Retorna un objeto de tipo Aspirante. Representa el aspirante al cual
     * pertenece la inscripción.
     *
     * @return Aspirante aspirante.
     */
    public Aspirante getAspirante() {
        return this.aspirante;
    }

    /**
     * Setea un objeto de tipo Aspirante. Representa el aspirante al cual
     * pertenece la inscripción.
     *
     * @param aspirante
     */
    public void setAspirante(Aspirante aspirante) {
        this.aspirante = aspirante;
    }

    /**
     * Retorna un objeto de tipo Categoría. Representa la categoría en la cual
     * se inscribió el aspirante.
     *
     * @return Categoria categoria
     */
    public Categoria getCategoria() {
        return this.categoria;
    }

    /**
     * Setea un objeto de tipo Categoria. Representa la categoría en la cual se
     * inscribió el aspirante.
     *
     * @param categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna un objeto de tipo Disciplina. Representa la disciplina en la cual
     * competirá el aspirante.
     *
     * @return Disciplina disciplina
     */
    public Disciplina getDisciplina() {
        return this.disciplina;
    }

    /**
     * Setea un objeto de tipo Disciplina. Representa la disciplina en la cual
     * competirá el aspirante.
     *
     * @param disciplina
     */
    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    /**
     * Retorna un objeto de tipo EstadoInscripcion. Representa el estado de la
     * inscripción.
     *
     * @return EstadoInscripcion estadoInscripcion
     */
    public EstadoInscripcion getEstado() {
        return this.estado;
    }

    /**
     * Setea un objeto de tipo EstadoInscripcion. Representa el estado de la
     * inscripción.
     *
     * @param estado
     */
    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    /**
     * Retorna un objeto de tipo Torneo. Representa el torneo al cual pertenece
     * la inscripción.
     *
     * @return Torneo torneo.
     */
    public Torneo getTorneo() {
        return this.torneo;
    }

    /**
     * Setea un objeto de tipo Torneo. Representa el torneo al cual pertenece la
     * inscripción.
     *
     * @param torneo
     */
    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }
}
