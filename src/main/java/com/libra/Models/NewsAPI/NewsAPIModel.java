package com.libra.Models.NewsAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class NewsAPIModel {

    private String status;
    private Integer totalResults;
    private ArticlesModel[] articles;
}
