package com.libra.Service.Impl;

import com.libra.Dao.CrudDao;
import com.libra.Dao.TodoDao;
import com.libra.Models.TodoModel;
import com.libra.Service.CrudService;
import com.libra.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements CrudService<TodoModel>, TodoService {

    @Autowired
    private CrudDao<TodoModel> todoCrudDao;
    @Autowired
    private TodoDao todoDao;

    @Override
    public List<TodoModel> getObjects(){
        return todoCrudDao.getObjects();
    }

    @Override
    public Optional<TodoModel> findObjectById(int id){
        return todoCrudDao.findObjectById(id);
    }

    @Override
    public void deleteObject(int id){
        todoCrudDao.deleteObjectById(id);
    }

    @Override
    public void saveObject(TodoModel object) {
        todoCrudDao.saveObject(object);
    }

    @Override
    public List<TodoModel> findAllTodosForActiveUser(String username) {
        return todoDao.findTodosForActiveUser(username);
    }

    @Override
    public int countAllTodosForActiveUser(String username) {
        return todoDao.countAllTodosForActiveUser(username);
    }

    @Override
    public List<TodoModel> deleteAllTodosByActiveUser(String username) {
        return todoDao.deleteAllTodosByActiveUser(username);
    }

}
