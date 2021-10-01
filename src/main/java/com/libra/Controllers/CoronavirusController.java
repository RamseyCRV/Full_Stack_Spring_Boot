package com.libra.Controllers;

import com.libra.Config.LibraConstants.CoronavirusConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoronavirusController{

    @GetMapping(CoronavirusConstants.URL_PAGE)
    public String getRequest(){

        return CoronavirusConstants.HTML;
    }
}
