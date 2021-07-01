package com.libra.Dao;

import java.util.List;
import java.util.Optional;

/**
 * Basic CRUD Dao interface
 */
public interface CrudDao <T>{

    /**
     * Get all objects T from DB using JPARepository
     * @return
     */
    List<T> getObjects();

    /**
     * Find object T from DB using id
     * @param id of object
     */
    Optional<T> findObjectById(final int id);

    /**
     * Delete object T from DB using id
     * @param id of object
     */
    void deleteObjectById(final int id);

    /**
     * Save object T in DB
     * @param object T
     */
    void saveObject(final T object);

}
