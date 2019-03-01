package gestion.torneos.dao.impl;

import gestion.torneos.dao.AspiranteDao;
import gestion.torneos.model.Aspirante;
import gestion.torneos.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Implementación Hibernate de la interface AspiranteDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class AspiranteDaoHibernateImpl implements AspiranteDao {
    
    private static final Logger _logger = Logger.getLogger(AspiranteDaoHibernateImpl.class);

    @Override
    public Aspirante obtenerAspirantePorDni(int dni) throws Exception {
        _logger.debug("obtenerAspirantePorDni() - dni=" + dni);
        Aspirante aspirante = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Aspirante> query = builder.createQuery(Aspirante.class);
            Root<Aspirante> root = query.from(Aspirante.class);
            query.select(root);
            query.where(builder.equal(root.get("dni"), dni));
            aspirante = session.createQuery(query).uniqueResult();
        } catch (Exception ex) {
            _logger.error("Error al buscar aspirante con dni=" + dni + ": " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return aspirante;
    }

    @Override
    public void persistirAspirante(Aspirante aspirante) throws Exception {
        Session session = null;
        Transaction tx = null;
        try {
            _logger.debug("Persistiendo aspirante " + aspirante);
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(aspirante);
            tx.commit();
        } catch (Exception ex) {
            _logger.error("Error al persistir el aspirante " + aspirante + ": " + ex.getMessage(), ex);
            if (tx != null) {
                tx.rollback();
            }
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
