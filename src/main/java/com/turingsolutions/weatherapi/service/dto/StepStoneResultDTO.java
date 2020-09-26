package com.turingsolutions.weatherapi.service.dto;

import java.util.Map;

public class StepStoneResultDTO {
    private String providerName;
    private Map<String, String> providerResult;

    public Map<String, String> getProviderResult() {
        return providerResult;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderName() {
        return providerName;
    }

    public StepStoneResultDTO(String providerName, Map<String, String> providerResult) {
        this.providerName = providerName;
        this.providerResult = providerResult;
    }
    public StepStoneResultDTO() {
        this(null, null);
    }
}
