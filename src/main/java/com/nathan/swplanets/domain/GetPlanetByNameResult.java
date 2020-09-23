package com.nathan.swplanets.domain;

import lombok.Data;

import java.util.List;

@Data
public class GetPlanetByNameResult {
    private String name;
    private List<String> films;
}
