package com.libra.Service.Interface;


import com.libra.Models.Notes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * CRUD Service
 */
@Service
public interface CrudService<T> {

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

    int countAllObjectsForActiveUser(final String username);

    List<T> deleteAllObjectsByActiveUser(final String username);
}
