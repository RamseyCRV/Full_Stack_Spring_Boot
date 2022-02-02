package com.libra.Controllers;

import com.libra.Config.LibraConstants.Controllers.News;
import com.libra.Service.NewsApiService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value = News.URL_PAGE)
public class NewsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsApiService newsApi;

    @Timed(value = "news.time", description = "Time taken to load news")
    @GetMapping
    public String getHomePage(final Model model) {
        model.addAttribute(News.NEWS_MODEL, newsApi.getRequest(News.NEWS_DEFULT, News.NEWS_SORT).getArticles());
        return News.VIEW;
    }

    @GetMapping(News.URL_SEARCH)
    public String getHomeWithSearch(@RequestParam("sortBy") String sortBy,
                                    @RequestParam("searchNews") String searchNews,
                                    final Model model){
        model.addAttribute(News.NEWS_MODEL, newsApi.getRequest(searchNews, sortBy).getArticles());
        return News.VIEW;
    }

}
