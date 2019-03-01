package gestion.torneos.dao.impl;

import gestion.torneos.dao.EstadoInscripcionDao;
import gestion.torneos.dao.InscripcionDao;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import gestion.torneos.model.Disciplina;
import gestion.torneos.model.EstadoInscripcion;
import gestion.torneos.model.Inscripcion;
import gestion.torneos.model.Torneo;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

/**
 * Implementación Hibernate de la interface InscripcionDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class InscripcionDaoImpl implements InscripcionDao {

    private static final Logger _logger = Logger.getLogger(InscripcionDaoImpl.class);

    private static final String ESTADO_INSCRIPCION_PRE_APROBADA = "Pre-Aprobada";

    private final EstadoInscripcionDao estadoInscripcionDao;
    private final EstadoInscripcion estadoInscripcionPreAprobado;

    public InscripcionDaoImpl(EstadoInscripcionDao estadoInscripcionDao) throws Exception {
        this.estadoInscripcionDao = estadoInscripcionDao;
        // Se recupera de la base el estado Pre-aprobado, para la generación
        // de las inscripciones al torneo.
        this.estadoInscripcionPreAprobado = this.estadoInscripcionDao.bucarPorNombre(ESTADO_INSCRIPCION_PRE_APROBADA);
    }

    @Override
    public void agregarInscripcion(Aspirante aspirante, Disciplina disciplina, Categoria categoria, Torneo torneo) {
        _logger.debug("agregarInscripcion() - aspirante=" + aspirante + ", disciplina=" + disciplina
                + ", categoria=" + categoria + ", torneo=" + torneo);
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCategoria(categoria);
        inscripcion.setDisciplina(disciplina);
        inscripcion.setTorneo(torneo);
        inscripcion.setEstado(this.estadoInscripcionPreAprobado);
        aspirante.agregarInscripcion(inscripcion);
    }

    @Override
    public void eliminarInscripcion(Aspirante aspirante, Disciplina disciplina) {
        _logger.debug("eliminarInscripcion() - aspirante=" + aspirante + ", disciplina=" + disciplina);
        List<Inscripcion> inscripcions = aspirante.getInscripcions();
        Inscripcion inscripcionABorrar = null;
        if (CollectionUtils.isNotEmpty(inscripcions)) {
            for (Iterator<Inscripcion> iterator = inscripcions.iterator(); iterator.hasNext();) {
                Inscripcion inscripcionActual = iterator.next();
                if (inscripcionActual.getDisciplina() != null && inscripcionActual.getDisciplina().equals(disciplina)) {
                    inscripcionABorrar = inscripcionActual;
                    break;
                }
            }
            if (inscripcionABorrar != null) {
                aspirante.borrarInscripcion(inscripcionABorrar);
                _logger.debug("Inscripción de disciplina=" + disciplina + " eliminada correctamente");
            }
        }
    }

    @Override
    public Inscripcion buscarInscripcionPorAspiranteYDisciplina(Aspirante aspirante, Disciplina disciplina) {
        _logger.debug("buscarInscripcionPorAspiranteYDisciplina() - aspirante=" + aspirante
                + ", disciplina=" + disciplina);
        List<Inscripcion> inscripcions = aspirante.getInscripcions();
        Inscripcion inscripcionARetornar = null;
        if (CollectionUtils.isNotEmpty(inscripcions)) {
            for (Iterator<Inscripcion> iterator = inscripcions.iterator(); iterator.hasNext();) {
                Inscripcion inscripcionActual = iterator.next();
                if (inscripcionActual.getDisciplina() != null && inscripcionActual.getDisciplina().equals(disciplina)) {
                    inscripcionARetornar = inscripcionActual;
                    break;
                }
            }
        }
        if (inscripcionARetornar != null)
            _logger.debug("Retornando inscripción a disciplina=" + disciplina);
        else 
            _logger.debug("No se encontró inscripción a disciplina=" + disciplina);
        return inscripcionARetornar;
    }

    @Override
    public void actualizarCategoriaDeInscripcionPorAspiranteYDisciplina(Aspirante aspirante, Disciplina disciplina, Categoria nuevaCategoria) {
        _logger.debug("Actualizando categoría en inscripción del aspirante=" + aspirante
                + " en la disciplina=" + disciplina);
        _logger.debug("Nueva categoría=" + nuevaCategoria);
        List<Inscripcion> inscripcionesAspirante = aspirante.getInscripcions();
        if (CollectionUtils.isNotEmpty(inscripcionesAspirante)) {
            for (Iterator<Inscripcion> iterator = inscripcionesAspirante.iterator(); iterator.hasNext();) {
                Inscripcion inscripcionActual = iterator.next();
                if (disciplina.equals(inscripcionActual.getDisciplina())) {
                    inscripcionActual.setCategoria(nuevaCategoria);
                    break;
                }
            }
        }
    }
}
