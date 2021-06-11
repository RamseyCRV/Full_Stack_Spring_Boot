package com.libra.Controllers;

import com.libra.Config.LibraConstants.HomeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(HomeConstants.URL_HOME)
    public String getHomePage() {

        return HomeConstants.HTML;
    }

}
