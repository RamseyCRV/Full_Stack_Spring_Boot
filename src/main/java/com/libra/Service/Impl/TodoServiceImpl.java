package com.libra.Service.Impl;

import com.libra.Models.Todo;
import com.libra.Repository.TodoRepository;
import com.libra.Service.CRUDService;
import com.libra.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements CRUDService<Todo>, TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> getObjects(){
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findObjectById(int id){
        return todoRepository.findById(id);
    }

    @Override
    public void deleteObject(int id){
        todoRepository.deleteById(id);
    }

    @Override
    public void saveObject(Todo object) {
        todoRepository.save(object);
    }

    @Override
    public List<Todo> findAllTodosForActiveUser(String username) {
        return todoRepository.findByCreatedBy(username);

    }
}
