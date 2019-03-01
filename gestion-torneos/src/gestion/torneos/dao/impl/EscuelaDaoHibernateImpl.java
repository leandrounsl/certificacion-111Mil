package gestion.torneos.dao.impl;

import gestion.torneos.dao.EscuelaDao;
import gestion.torneos.model.Escuela;
import gestion.torneos.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Implementación Hibernate de la interface EscuelaDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class EscuelaDaoHibernateImpl implements EscuelaDao {
    
    private static final Logger _logger = Logger.getLogger(EscuelaDaoHibernateImpl.class);

    @Override
    public List<Escuela> obtenerEscuelas() throws Exception {
        _logger.debug("Iniciando obtenerEscuelas()");
        List<Escuela> escuelas = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Escuela> query = builder.createQuery(Escuela.class);
            Root<Escuela> categoriaRoot = query.from(Escuela.class);
            query.select(categoriaRoot);
            escuelas = session.createQuery(query).list();
        } catch (Exception ex) {
            _logger.error("Error al obtener las escuelas: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return escuelas;
    }

}
