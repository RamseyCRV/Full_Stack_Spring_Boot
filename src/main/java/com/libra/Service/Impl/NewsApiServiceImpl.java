package com.libra.Service.Impl;

import com.libra.Models.NewsAPI.NewsAPIModel;
import com.libra.Service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsApiServiceImpl implements NewsApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${news.api.key}")
    private String KEY;

    @Value("${news.api.url}")
    private String URL;

    @Value("${news.api.sort}")
    private String SORT;

    @Override
    public NewsAPIModel getRequest(String search, final String sort) {
        if(search == null || search.isEmpty()){
            search = "everything";
        }
        final String urlBuild = URL + search + SORT + sort + KEY;
        return restTemplate.getForObject(urlBuild, NewsAPIModel.class);
    }

}
