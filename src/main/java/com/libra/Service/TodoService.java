package com.libra.Service;

import org.springframework.stereotype.Service;

@Service
public interface TodoService {

    int countAllTodosForActiveUser(final String username);
}
