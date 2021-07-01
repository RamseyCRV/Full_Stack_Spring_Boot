package com.libra.Dao;

import com.libra.Models.Todo;

import java.util.List;

public interface TodoDao {

    int countAllTodosForActiveUser(String username);

    List<Todo> deleteAllTodosByActiveUser(String username);

    List<Todo> findTodosForActiveUser(final String username);

}
