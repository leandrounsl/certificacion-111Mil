package gestion.torneos.dao.impl;

import gestion.torneos.dao.DisciplinaDao;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Disciplina;
import gestion.torneos.model.Inscripcion;
import gestion.torneos.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Implementación Hibernate de la interface DisciplinaDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class DisciplinaDaoHibernateImpl implements DisciplinaDao {
    
    private static final Logger _logger = Logger.getLogger(DisciplinaDaoHibernateImpl.class);

    @Override
    public List<Disciplina> obtenerDisciplinas() throws Exception {
        _logger.debug("Iniciando obtenerDisciplinas()");
        List<Disciplina> disciplinas = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Disciplina> query = builder.createQuery(Disciplina.class);
            Root<Disciplina> categoriaRoot = query.from(Disciplina.class);
            query.select(categoriaRoot);
            disciplinas = session.createQuery(query).list();
        } catch (Exception ex) {
            _logger.error("Error al obtener las disciplinas: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return disciplinas;
    }

    @Override
    public List<Disciplina> buscarDisciplinasPorAspirante(Aspirante aspirante) {
        List<Disciplina> disciplinasAspirante = new ArrayList<>();
        List<Inscripcion> inscripcions = aspirante.getInscripcions();
        if (CollectionUtils.isNotEmpty(inscripcions)) {
            for (Iterator<Inscripcion> iterator = inscripcions.iterator(); iterator.hasNext();) {
                Inscripcion inscripcion = iterator.next();
                Disciplina disciplina = inscripcion.getDisciplina();
                disciplinasAspirante.add(disciplina);
            }
        }
        return disciplinasAspirante;
    }
}
