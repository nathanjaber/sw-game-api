package com.nathan.swplanets.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetPlanetByNameResponse {
    private int count;
    private List<GetPlanetByNameResult> results;
}
