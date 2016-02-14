package org.activiti.examples.domain.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by nitillie on 12-02-16.
 */
public class AbstractRepositoryImpl<T, PK extends Serializable> implements AbstractRepository<T, PK> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass;

    protected AbstractRepositoryImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        this.entityClass = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T findById(PK id) {
        LOG.info("Running findById with ID {} ", id);
        return (T) getCurrentSession().get(entityClass, id);
    }

    @Override
    public void saveOrUpdate(T entity) {
        LOG.info("Running saveOrUpdate for entity {} ", entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        LOG.info("Running delete for entity {} ", entity);
        getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        LOG.info("Fainding all entities...");
        return getCurrentSession().createCriteria(entityClass).list();
    }

    /**
     * @return the current Hibernate session from the session factory
     */
    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
