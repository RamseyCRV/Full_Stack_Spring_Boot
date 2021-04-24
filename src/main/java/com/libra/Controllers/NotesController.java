package com.libra.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotesController {

    @GetMapping("/notes")
    public String getNotes(){
        return "notes";
    }
}
