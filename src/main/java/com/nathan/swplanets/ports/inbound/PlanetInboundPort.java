package com.nathan.swplanets.ports.inbound;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.PlanetDTO;

import java.util.List;

public interface PlanetInboundPort {
    GetPlanetByNameResponse teste(String name);
    List<PlanetDTO> getPlanets(String name);
}
