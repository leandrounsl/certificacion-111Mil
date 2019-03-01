package gestion.torneos.dao.impl;

import gestion.torneos.dao.EstadoInscripcionDao;
import gestion.torneos.model.EstadoInscripcion;
import gestion.torneos.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Implementación Hibernate de la interface EstadoInscripcionDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class EstadoInscripcionDaoHibernateImpl implements EstadoInscripcionDao {
    
    private static final Logger _logger = Logger.getLogger(EstadoInscripcionDaoHibernateImpl.class);

    @Override
    public EstadoInscripcion bucarPorNombre(String nombre) throws Exception {
        _logger.debug("Iniciando bucarPorNombre() - nombre=" + nombre);
        EstadoInscripcion estado = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EstadoInscripcion> query = builder.createQuery(EstadoInscripcion.class);
            Root<EstadoInscripcion> root = query.from(EstadoInscripcion.class);
            query.select(root);
            query.where(builder.equal(root.get("nombre"), nombre));
            estado = session.createQuery(query).uniqueResult();
        } catch (Exception ex) {
            _logger.error("Error al buscar EstadoInscripcion con nombre=" + nombre + ": " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return estado;
    }

}
