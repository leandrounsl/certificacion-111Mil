package gestion.torneos.dao.impl;

import gestion.torneos.dao.TorneoDao;
import gestion.torneos.model.Torneo;
import gestion.torneos.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Implementación Hibernate de la interface TorneoDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class TorneoDaoHibernateImpl implements TorneoDao {
    
    private static final Logger _logger = Logger.getLogger(TorneoDaoHibernateImpl.class);

    @Override
    public Torneo buscarPorNombre(String nombre) throws Exception {
        _logger.debug("Iniciando bucarPorNombre() - nombre=" + nombre);
        Torneo torneo = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Torneo> query = builder.createQuery(Torneo.class);
            Root<Torneo> root = query.from(Torneo.class);
            query.select(root);
            query.where(builder.equal(root.get("nombre"), nombre));
            torneo = session.createQuery(query).uniqueResult();
        } catch (Exception ex) {
            _logger.error("Error al buscar torneo con nombre=" + nombre + ": " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return torneo;
    }

}
