package com.libra.Service;

import com.libra.Models.TodoModel;

import java.util.List;

public interface TodoService {

    List<TodoModel> findAllTodosForActiveUser(final String username);

    int countAllTodosForActiveUser(final String username);

    List<TodoModel> deleteAllTodosByActiveUser(final String username);

}
