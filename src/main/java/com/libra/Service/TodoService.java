package com.libra.Service;

import com.libra.Models.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> findAllTodosForActiveUser(final String username);

    int countAllTodosForActiveUser(final String username);

    List<Todo> deleteAllTodosByActiveUser(final String username);

}
