package com.libra.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {

    @GetMapping("/todos")
    public String getTodos(Model model){



        return "todos";
    }
}
