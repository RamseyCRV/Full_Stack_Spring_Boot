package com.libra.Service;


import com.libra.Models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Generic Service for Producitivity Module
 * @param <T> Object to pass
 */
@Service
public interface CRUDService<T> {

    /**
     * Return objects T from Database
     * @return List of T Objects
     */
    List<T> getObjects();

    /**
     * Return Object T with specified id
     * @param id
     * @return Object what have that id
     */
    Optional<T> findObjectById(int id);

    /**
     * Delete Object T with specified id
     * @param id
     */
    void deleteObject(int id);

    /**
     * Save Object
     * @param object
     */
    void saveObject(T object);

    /**
     * Find all objects for active user
     * @param username of the active userr
     * @return a list of objects
     */
    List<T> findObjectsForActiveUser(final String username);
}
