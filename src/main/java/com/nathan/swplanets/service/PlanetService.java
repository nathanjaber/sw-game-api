package com.nathan.swplanets.service;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;

import java.util.List;
import java.util.Optional;

public interface PlanetService {
    List<PlanetDTO> getPlanets(String name);
    Planet savePlanet(Planet planet);
    Optional<PlanetDTO> getPlanetById(String id);
    boolean deletePlanetById(String id);
    GetPlanetByNameResponse swapiSearchPlanetByName(String name);
}
