package com.libra.Controllers;

import com.libra.Models.Todo;
import com.libra.Service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    @Qualifier("todoServiceImpl")
    CRUDService<Todo> CRUDService;

    @GetMapping("/todos")
    public String getTodos(Model model){



        return "todos";
    }

    @PostMapping("/todos/add")
    public String addTodo(@RequestParam("text")  String text){

        Date creationDate = new Date();


        return "redirect:/todos";
    }
}
