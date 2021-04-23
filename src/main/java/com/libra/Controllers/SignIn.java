package com.libra.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignIn {

    @GetMapping("/signIn")
    public String getSignInPage(){
        return "signIn";
    }

}
