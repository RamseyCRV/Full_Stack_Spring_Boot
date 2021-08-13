package com.libra.Controllers;

import com.libra.Config.LibraConstants.NewsConstants;
import com.libra.Service.NewsApiService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class NewsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApiService newsApi;

    @Timed(value = "news.time", description = "Time taken to load news")
    @GetMapping(NewsConstants.URL_NEWS)
    public String getHomePage(final Model model) {
        model.addAttribute(NewsConstants.NEWS_MODEL, newsApi.getRequest(NewsConstants.NEWS_DEFULT, NewsConstants.NEWS_SORT).getArticles());
        return NewsConstants.HTML;
    }

    @GetMapping(NewsConstants.URL_NEWS_SEARCH)
    public String getHomeWithSearch(@RequestParam("sortBy") String sortBy,
                                    @RequestParam("searchNews") String searchNews,
                                    final Model model){
        model.addAttribute(NewsConstants.NEWS_MODEL, newsApi.getRequest(searchNews, sortBy).getArticles());
        return NewsConstants.HTML;
    }

}
