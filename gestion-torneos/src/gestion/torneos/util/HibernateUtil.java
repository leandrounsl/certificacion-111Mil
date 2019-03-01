package gestion.torneos.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Clase que contiene la funcionalidad para conectarse a la base de datos
 * mediante Hibernate.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class HibernateUtil {
    
    private static final Logger _logger = Logger.getLogger(HibernateUtil.class);

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    /**
     * Crea el SessionFactory.
     *
     * @return SessionFactory de Hibernate.
     */
    private static SessionFactory buildSessionFactory() {

        _logger.info("Creando sessionFactory...");
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("resources/hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            _logger.error("Error al crear SessionFactory: " + ex.getMessage(), ex);
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);

            throw ex;
        }
        _logger.info("SessionFactory creado correctamente");
        return sessionFactory;
    }

    /**
     * Retorna el SessionFactory.
     *
     * @return SessionFactory de Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    /**
     * Cierra el SessionFactory para liberar todos los recursos.
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
