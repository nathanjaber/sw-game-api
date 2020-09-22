package com.nathan.swplanets.domain;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;

@Data
public class PlanetDTO {
    private String _id;
    private String name;
    private String climate;
    private String terrain;
    private int films_appearances;

    public static PlanetDTO create(Planet planet) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(planet, PlanetDTO.class);
    }

}
