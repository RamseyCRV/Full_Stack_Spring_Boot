package com.libra.Controllers;

import com.libra.Config.LibraConstants.Controllers.Todo;
import com.libra.Service.CrudService;
import com.libra.Service.TodoService;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for "Todos" page
 */
@Controller
@RequestMapping(value = Todo.URL_PAGE)
public class TodoController {

    @Autowired
    @Qualifier(Todo.CRUD_SERVICE_QUALIFIER)
    private CrudService<com.libra.Models.Todo> crudTodoService;
    @Autowired
    private TodoService todoService;

    /**
     * Load all todos on page for authenticated user
     */
    @GetMapping
    public String getTodos(Model model){
        List<com.libra.Models.Todo> todos = todoService.findAllTodosForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<com.libra.Models.Todo> activeTodos= new ArrayList<com.libra.Models.Todo>();
        List<com.libra.Models.Todo> finishedTodos = new ArrayList<com.libra.Models.Todo>();

        if (todos != null){
            for(com.libra.Models.Todo todo : todos){
                if(BooleanUtils.isTrue(todo.getIsDone())){
                    finishedTodos.add(todo);
                }else{
                    activeTodos.add(todo);
                }
            }
        }

        model.addAttribute(Todo.MODEL_ACTIVE_TODOS, activeTodos);
        model.addAttribute(Todo.MODEL_FINISHED_TODOS, finishedTodos);

        return Todo.VIEW;
    }

    /**
     * Add todo to DB
     */
    @PostMapping(Todo.URL_SAVE)
    public String addTodo(com.libra.Models.Todo todo){
        crudTodoService.saveObject(todo);

        return Todo.REDIRECT_TO_TODOS;
    }

    /**
     * Delete todo
     */
    @RequestMapping(value = Todo.URL_DELETE, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteTodo(int id){
        crudTodoService.deleteObject(id);

        return Todo.REDIRECT_TO_TODOS;
    }

    /**
     * Find todo by id
     */
    @RequestMapping(Todo.URL_FIND_BY_ID)
    @ResponseBody
    public Optional<com.libra.Models.Todo> findTodoById(int id){
        return crudTodoService.findObjectById(id);
    }

    /**
     * Update todo
     */
    @RequestMapping(value = Todo.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateTodo(com.libra.Models.Todo todo){
        crudTodoService.saveObject(todo);

        return Todo.REDIRECT_TO_TODOS;
    }

    /**
     * If user press "Done" & "Restore" than update the value
     */
    @RequestMapping(value = Todo.URL_IS_DONE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String isDone(int id){
        Optional<com.libra.Models.Todo> todoOptional = crudTodoService.findObjectById(id);

        if(todoOptional != null) {
            com.libra.Models.Todo todos = todoOptional.get();
            if(BooleanUtils.isTrue(todos.getIsDone())) {
                todos.setIsDone(Boolean.FALSE);
            }else{
                todos.setIsDone(Boolean.TRUE);
            }
            crudTodoService.saveObject(todos);
        }

        return Todo.REDIRECT_TO_TODOS;
    }
}
