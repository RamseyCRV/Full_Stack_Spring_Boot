package com.libra.Service.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.TodoDao;
import com.libra.Models.Todo;
import com.libra.Service.CrudService;
import com.libra.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements CrudService<Todo>, TodoService {

    @Autowired
    private CrudDao<Todo> todoCrudDao;
    @Autowired
    private TodoDao todoDao;

    @Override
    public List<Todo> getObjects(){
        return todoCrudDao.getObjects();
    }

    @Override
    public Optional<Todo> findObjectById(int id){
        return todoCrudDao.findObjectById(id);
    }

    @Override
    public void deleteObject(int id){
        todoCrudDao.deleteObjectById(id);
    }

    @Override
    public void saveObject(Todo object) {
        todoCrudDao.saveObject(object);
    }

    @Override
    public List<Todo> findAllTodosForActiveUser(String username) {
        return todoDao.findTodosForActiveUser(username);
    }

    @Override
    public int countAllTodosForActiveUser(String username) {
        return todoDao.countAllTodosForActiveUser(username);
    }

    @Override
    public List<Todo> deleteAllTodosByActiveUser(String username) {
        return todoDao.deleteAllTodosByActiveUser(username);
    }

}
