package com.libra.Dao.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.TodoDao;
import com.libra.Models.TodoModel;
import com.libra.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TodoDaoImpl implements CrudDao<TodoModel>, TodoDao {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<TodoModel> getObjects() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<TodoModel> findObjectById(int id) {
        return todoRepository.findById(id);
    }

    @Override
    public void deleteObjectById(int id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void saveObject(TodoModel object) {
        todoRepository.save(object);
    }


    @Override
    public int countAllTodosForActiveUser(String username) {
        return todoRepository.countByCreatedBy(username);
    }

    @Override
    public List<TodoModel> deleteAllTodosByActiveUser(String username) {
        return todoRepository.deleteByCreatedBy(username);
    }

    @Override
    public List<TodoModel> findTodosForActiveUser(String username) {
        return todoRepository.findByCreatedBy(username);
    }
}
