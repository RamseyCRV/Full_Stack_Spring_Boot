package com.libra.Controllers;

import com.libra.Config.LibraConstants.Controllers.Todo;
import com.libra.Models.TodoModel;
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
    private CrudService<TodoModel> crudTodoService;
    @Autowired
    private TodoService todoService;

    /**
     * Load all todos on page for authenticated user
     */
    @GetMapping
    public String getTodos(Model model){
        List<TodoModel> todoModels = todoService.findAllTodosForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<TodoModel> activeTodoModels = new ArrayList<TodoModel>();
        List<TodoModel> finishedTodoModels = new ArrayList<TodoModel>();

        if (todoModels != null){
            for(TodoModel todoModel : todoModels){
                if(BooleanUtils.isTrue(todoModel.getIsDone())){
                    finishedTodoModels.add(todoModel);
                }else{
                    activeTodoModels.add(todoModel);
                }
            }
        }

        model.addAttribute(Todo.MODEL_ACTIVE_TODOS, activeTodoModels);
        model.addAttribute(Todo.MODEL_FINISHED_TODOS, finishedTodoModels);

        return Todo.VIEW;
    }

    /**
     * Add todo to DB
     */
    @PostMapping(Todo.URL_SAVE)
    public String addTodo(TodoModel todoModel){
        crudTodoService.saveObject(todoModel);

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
    public Optional<TodoModel> findTodoById(int id){
        return crudTodoService.findObjectById(id);
    }

    /**
     * Update todo
     */
    @RequestMapping(value = Todo.URL_UPDATE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String updateTodo(TodoModel todoModel){
        crudTodoService.saveObject(todoModel);

        return Todo.REDIRECT_TO_TODOS;
    }

    /**
     * If user press "Done" & "Restore" than update the value
     */
    @RequestMapping(value = Todo.URL_IS_DONE, method = {RequestMethod.PUT, RequestMethod.GET})
    public String isDone(int id){
        Optional<TodoModel> todoOptional = crudTodoService.findObjectById(id);

        if(todoOptional != null) {
            TodoModel todos = todoOptional.get();
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
