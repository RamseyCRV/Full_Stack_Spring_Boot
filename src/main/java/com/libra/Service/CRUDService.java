package com.libra.Service;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CRUD Service
 */
@Service
public interface CRUDService<T> {

    /**
     * Return objects T from Database
     */
    List<T> getObjects();

    /**
     * Return Object T with specified id
     */
    Optional<T> findObjectById(int id);

    /**
     * Delete Object T with specified id
     */
    void deleteObject(int id);

    /**
     * Save Object T
     */
    void saveObject(T object);

    /**
     * Find all objects for active user
     * @param username of the active user
     */
    List<T> findObjectsForActiveUser(final String username);
}
