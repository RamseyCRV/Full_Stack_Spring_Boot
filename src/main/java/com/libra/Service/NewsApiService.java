package com.libra.Service;

import com.libra.Models.NewsAPI.NewsAPIModel;

public interface NewsApiService {

    NewsAPIModel getRequest(final String search, final String sort);
}
