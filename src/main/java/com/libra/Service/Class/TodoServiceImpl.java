package com.libra.Service.Class;

import com.libra.Dao.CrudDao;
import com.libra.Models.Notes;
import com.libra.Models.Todo;
import com.libra.Service.Interface.CrudService;
import com.libra.Service.Interface.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements CrudService<Todo> {

    @Autowired
    private CrudDao<Todo> todoCrudDao;

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
    public List<Todo> findObjectsForActiveUser(String username) {
        return todoCrudDao.findObjectsForActiveUser(username);
    }

    @Override
    public int countAllObjectsForActiveUser(String username) {
        return todoCrudDao.countAllObjectsForActiveUser(username);
    }

    @Override
    public List<Todo> deleteAllObjectsByActiveUser(String username) {
        return todoCrudDao.deleteAllObjectsByActiveUser(username);
    }
}
