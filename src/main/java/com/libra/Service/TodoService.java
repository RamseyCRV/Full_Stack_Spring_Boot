package com.libra.Service;

import com.libra.Models.Todo;

import java.util.List;

public interface TodoService {

    /**
     * Return a list of all todos for current user
     * @param username for active user
     * @return List<Todos>
     */
    List<Todo> findAllTodosForActiveUser(final String username);
}
