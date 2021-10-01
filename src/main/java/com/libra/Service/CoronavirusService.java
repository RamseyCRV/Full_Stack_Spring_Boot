package com.libra.Service;

import com.libra.Models.CoronavirusAPI.CoronavirusAPIModel;

import java.util.List;

public interface CoronavirusService {

    List<CoronavirusAPIModel> getRequest();
}
