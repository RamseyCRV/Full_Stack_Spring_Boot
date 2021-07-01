package com.libra.Service;

import java.util.List;
import java.util.Optional;

/**
 * Basic CRUD Service
 */
public interface CrudService<T> {

    /**
     * Return objects T from Database
     */
    List<T> getObjects();

    /**
     * Return Object T with specified id
     */
    Optional<T> findObjectById(final int id);

    /**
     * Delete Object T with specified id
     */
    void deleteObject(final int id);

    /**
     * Save Object T
     */
    void saveObject(final T object);

}
