package gestion.torneos.ui;

import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Inscripcion;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el resultado de la validación de las inscripciones
 * generadas. Contiene los aspirantes que tienen inscripciones incompletas.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class ResultadoValidacionInscripcion {

    // Lista de aspirantes con inscripciones incompletas...
    private List<Aspirante> aspirantesConInscripcionesIncompletas = new ArrayList<>();

    // Lista de Inscripciones con error...
    private List<Inscripcion> inscripcionesConError = new ArrayList<>();

    // true si las inscripciones pasaron la validación, false en otro caso...
    private boolean inscripcionesValidas = true;

    /**
     * Retorna una lista de aspirantes con inscripciones inválidas.
     *
     * @return Lista de Aspirantes
     */
    public List<Aspirante> getAspirantesConInscripcionesIncompletas() {
        return aspirantesConInscripcionesIncompletas;
    }

    /**
     * Setea la lista de aspirantes con inscripciones inválidas.
     *
     * @param aspirantesConInscripcionesIncompletas Lista de Aspirantes
     */
    public void setAspirantesConInscripcionesIncompletas(List<Aspirante> aspirantesConInscripcionesIncompletas) {
        this.aspirantesConInscripcionesIncompletas = aspirantesConInscripcionesIncompletas;
        this.setInscripcionesValidas(false);
    }

    /**
     * Agrega un aspirante a la lista de aspirantes con inscripciones inválidas.
     *
     * @param aspirante
     */
    public void addAspiranteConInscripcionIncompleta(Aspirante aspirante) {
        this.aspirantesConInscripcionesIncompletas.add(aspirante);
        this.setInscripcionesValidas(false);
    }

    public boolean isInscripcionesValidas() {
        return inscripcionesValidas;
    }

    public void setInscripcionesValidas(boolean inscripcionesValidas) {
        this.inscripcionesValidas = inscripcionesValidas;
    }

    /**
     * Retorna la lista de inscripciones con errores.
     *
     * @return Lista de inscripciones con errores.
     */
    public List<Inscripcion> getInscripcionesConError() {
        return inscripcionesConError;
    }

    /**
     * Setea la lista de inscripciones con errores.
     *
     * @param inscripcionesConError
     */
    public void setInscripcionesConError(List<Inscripcion> inscripcionesConError) {
        this.inscripcionesConError = inscripcionesConError;
        this.setInscripcionesValidas(false);
    }

    /**
     * Agrega una inscripción a la lista de inscripción con errores.
     *
     * @param inscripcionConError
     */
    public void addInscripcionConError(Inscripcion inscripcionConError) {
        this.inscripcionesConError.add(inscripcionConError);
        this.setInscripcionesValidas(false);
    }
}
