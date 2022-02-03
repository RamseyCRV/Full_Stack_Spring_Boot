package com.libra.Dao;

import com.libra.Models.TodoModel;

import java.util.List;

public interface TodoDao {

    int countAllTodosForActiveUser(String username);

    List<TodoModel> deleteAllTodosByActiveUser(String username);

    List<TodoModel> findTodosForActiveUser(final String username);

}
