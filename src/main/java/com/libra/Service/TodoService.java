package com.libra.Service;

import com.libra.Models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    int countAllTodosForActiveUser(final String username);

    List<Todo> deleteAllTodosByActiveUser(final String username);
}
