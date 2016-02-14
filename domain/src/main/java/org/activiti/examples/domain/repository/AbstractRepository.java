package org.activiti.examples.domain.repository;

import java.io.Serializable;
import java.util.List;

public interface AbstractRepository<T, PK extends Serializable>{


    /**
     * Find entity by its unique identifier.
     *
     * @param id unique identifier
     * @return the entity retrieved using it's id or <code>null</code>
     * in case no entity could be found with the given id
     */
    T findById(PK id);

    /**
     * Find entity by its unique identifier.
     *
     * @return All entities
     */
    List<T> findAll();

    /**
     * Save or update an entity.
     *
     * @param entity the entity to save or update
     */
    void saveOrUpdate(T entity);

    /**
     * Delete an entity.
     *
     * @param entity the entity to be deleted
     */
    void delete(T entity);
}
