package gestion.torneos.dao.impl;

import gestion.torneos.dao.CategoriaDao;
import gestion.torneos.model.Aspirante;
import gestion.torneos.model.Categoria;
import gestion.torneos.model.Genero;
import gestion.torneos.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Implementación Hibernate de la interface CategoriaDao.
 *
 * @author Leandro Giménez
 * @version 1.0
 */
public class CategoriaDaoHibernateImpl implements CategoriaDao {
    
    private static final Logger _logger = Logger.getLogger(CategoriaDaoHibernateImpl.class);
    
    private List<Categoria> categoriasDefinidas;

    public CategoriaDaoHibernateImpl() throws Exception {
        cargarCategorias();
    }   
    
    private void cargarCategorias() throws Exception {
        this.categoriasDefinidas = obtenerCategorias();
    }

    @Override
    public List<Categoria> obtenerCategorias() throws Exception {
        _logger.debug("Iniciando obtenerCategorias()");
        if (CollectionUtils.isNotEmpty(this.categoriasDefinidas)) 
            return this.categoriasDefinidas;
        
        List<Categoria> categorias = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Categoria> query = builder.createQuery(Categoria.class);
            Root<Categoria> categoriaRoot = query.from(Categoria.class);
            query.select(categoriaRoot);
            categorias = session.createQuery(query).list();
        } catch (Exception ex) {
            _logger.error("Error al obtener las categorías: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        
        this.categoriasDefinidas = categorias;
        return this.categoriasDefinidas;
    }

    @Override
    public Categoria obtenerCategoriaRecomendadaPorAspirante(Aspirante aspirante) {
        int edadAspirante = aspirante.calcularEdad();
        Genero sexoAspirante = aspirante.getSexo();
        Categoria categoriaRecomendada = null;
        for (Iterator<Categoria> iterator = categoriasDefinidas.iterator(); iterator.hasNext();) {
            Categoria categoriaActual = iterator.next();
            // Se selecciona Categoría según el sexo y edad del aspirante...
            if ((edadAspirante >= categoriaActual.getEdadDesde() && edadAspirante <= categoriaActual.getEdadHasta()) && sexoAspirante == categoriaActual.getGenero()) {
                categoriaRecomendada = categoriaActual;
                break;
            }
        }
        return categoriaRecomendada;
    }
}
