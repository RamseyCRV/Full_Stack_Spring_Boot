package com.libra.Controllers;

import com.libra.Config.LibraConstants.HomeConstants;
import com.libra.RESTfulAPI.NewsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApi newsApi;

    @GetMapping(HomeConstants.URL_HOME)
    public String getHomePage(final Model model) {
        model.addAttribute("newsApi", newsApi.getRequest(HomeConstants.NEWS_DEFULT, HomeConstants.NEWS_SORT).getArticles());
        return HomeConstants.HTML;
    }

}
