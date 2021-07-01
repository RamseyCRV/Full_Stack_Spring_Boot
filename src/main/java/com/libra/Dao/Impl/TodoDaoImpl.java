package com.libra.Dao.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.TodoDao;
import com.libra.Models.Todo;
import com.libra.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoDaoImpl implements CrudDao<Todo>, TodoDao {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getObjects() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findObjectById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public void deleteObjectById(int id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void saveObject(Todo object) {
        todoRepository.save(object);
    }


    @Override
    public int countAllTodosForActiveUser(String username) {
        return todoRepository.countByCreatedBy(username);
    }

    @Override
    public List<Todo> deleteAllTodosByActiveUser(String username) {
        return todoRepository.deleteByCreatedBy(username);
    }

    @Override
    public List<Todo> findTodosForActiveUser(String username) {
        return todoRepository.findByCreatedBy(username);
    }
}
