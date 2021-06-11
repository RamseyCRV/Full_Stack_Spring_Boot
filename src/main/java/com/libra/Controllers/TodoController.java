package com.libra.Controllers;

import com.libra.Config.LibraConstants.TodoConstants;
import com.libra.Models.Todo;
import com.libra.Service.CRUDService;
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
public class TodoController {

    @Autowired
    @Qualifier(TodoConstants.CRUD_SERVICE_QUALIFIER)
    CRUDService<Todo> crudTodoService;

    /**
     * Load all todos on page for authenticated user
     */
    @GetMapping(TodoConstants.URL_PAGE)
    public String getTodos(Model model){
        List<Todo> todos = crudTodoService.findObjectsForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Todo> activeTodos= new ArrayList<Todo>();
        List<Todo> finishedTodos = new ArrayList<Todo>();

        if (todos != null){
            for(Todo todo : todos){
                if(BooleanUtils.isTrue(todo.getIsDone())){
                    finishedTodos.add(todo);
                }else{
                    activeTodos.add(todo);
                }
            }
        }

        model.addAttribute(TodoConstants.MODEL_ACTIVE_TODOS, activeTodos);
        model.addAttribute(TodoConstants.MODEL_FINISHED_TODOS, finishedTodos);

        return TodoConstants.HTML;
    }

    /**
     * Add todo to DB
     */
    @PostMapping(TodoConstants.URL_SAVE)
    public String addTodo(Todo todo){
        crudTodoService.saveObject(todo);

        return TodoConstants.REDIRECT_TO_TODOS;
    }

    /**
     * Delete todo
     */
    @RequestMapping(value = TodoConstants.URL_DELETE, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteTodo(int id){
        crudTodoService.deleteObject(id);

        return TodoConstants.REDIRECT_TO_TODOS;
    }

    /**
     * Find todo by id
     */
    @RequestMapping(TodoConstants.URL_FIND_BY_ID)
    @ResponseBody
    public Optional<Todo> findTodoById(int id){
        return crudTodoService.findObjectById(id);
    }

    /**
     * Update todo
     */
    @RequestMapping(value = TodoConstants.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateTodo(Todo todo){
        crudTodoService.saveObject(todo);

        return TodoConstants.REDIRECT_TO_TODOS;
    }

    /**
     * If user press "Done" & "Restore" than update the value
     */
    @RequestMapping(value = TodoConstants.URL_IS_DONE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String isDone(int id){
        Optional<Todo> todoOptional = crudTodoService.findObjectById(id);

        if(todoOptional != null) {
            Todo todos = todoOptional.get();
            if(BooleanUtils.isTrue(todos.getIsDone())) {
                todos.setIsDone(Boolean.FALSE);
            }else{
                todos.setIsDone(Boolean.TRUE);
            }
            crudTodoService.saveObject(todos);
        }

        return TodoConstants.REDIRECT_TO_TODOS;
    }
}
