package com.libra.Controllers;

import com.libra.Models.Todo;
import com.libra.Service.CRUDService;
import com.libra.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller for "Todos" page
 */
@Controller
public class TodoController {

    @Autowired
    @Qualifier("todoServiceImpl")
    CRUDService<Todo> crudTodoService;

    @Autowired
    TodoService todoService;

    /**
     * Load all todos on page for authenticated user
     * @param model pass the list in the model
     * @return todo page
     */
    @GetMapping("/todos")
    public String getTodos(Model model){

        List<Todo> todos = todoService.findAllTodosForActiveUser(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("todos", todos);
        return "todos";
    }

    /**
     * Add "todos"
     * @param todo get from request
     * @return redirect to todos page
     */
    @PostMapping("/todos/add")
    public String addTodo(Todo todo){
        crudTodoService.saveObject(todo);

        return "redirect:/todos";
    }

    /**
     * Delete todo
     * @param id of todo
     * @return Redirect to todo page
     */
    @RequestMapping(value = "/todos/deleteTodo" , method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteDesktop(int id){
        crudTodoService.deleteObject(id);
        return "redirect:/todos";
    }
}
