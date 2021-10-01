package com.libra.Service.Impl;

import com.libra.Models.CoronavirusAPI.CoronavirusAPIModel;
import com.libra.Service.CoronavirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class CoronavirusServiceImpl implements CoronavirusService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${coronavirus.api.url}")
    private String url;

    @Value("${coronavirus.api.countries}")
    private String countries;

    @Override
    public List<CoronavirusAPIModel> getRequest() {
//        return restTemplate.getForObject(url + countries, List<CoronavirusAPIModel.class>);
        return Collections.emptyList();
    }
}
