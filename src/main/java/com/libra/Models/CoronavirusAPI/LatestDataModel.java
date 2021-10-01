package com.libra.Models.CoronavirusAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
class LatestDataModel {

    private int deaths;
    private int confirmed;
    private int recovered;
    private int critical;

}
