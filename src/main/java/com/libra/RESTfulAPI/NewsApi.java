package com.libra.RESTfulAPI;

import com.libra.Models.NewsAPI.NewsAPIModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsApi{

    @Value("${news.api.key}")
    private String KEY;

    @Value("${news.api.url}")
    private String URL;

    @Value("${news.api.sort}")
    private String SORT;

    @Autowired
    private RestTemplate restTemplate;

    public NewsAPIModel getRequest(final String search, final String sort) {
        String urlBuild = URL + search + SORT + sort + KEY;
        return restTemplate.getForObject(urlBuild, NewsAPIModel.class);
    }
}
