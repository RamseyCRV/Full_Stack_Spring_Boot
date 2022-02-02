package com.libra.Controllers;

import com.libra.Config.LibraConstants.Controllers.Coronavirus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = Coronavirus.URL_PAGE)
public class CoronavirusController{

    @GetMapping
    public ModelAndView getRequest(){

        return new ModelAndView(Coronavirus.VIEW);
    }
}
