package com.nathan.swplanets.ports.inbound;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;

import java.util.List;
import java.util.Optional;

public interface PlanetInboundPort {
    List<PlanetDTO> getPlanets(String name);
    PlanetDTO createPlanet(Planet planet);
    Optional<PlanetDTO> getPlanetById(String id);
    boolean deletePlanetById(String id);

}
