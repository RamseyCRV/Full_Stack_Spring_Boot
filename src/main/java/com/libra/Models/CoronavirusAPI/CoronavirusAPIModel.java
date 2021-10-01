package com.libra.Models.CoronavirusAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CoronavirusAPIModel {

    private String name;
    private String code;
    private Integer population;
    private String date;
    private TodayModel today;
    private LatestDataModel latest_data;
}
