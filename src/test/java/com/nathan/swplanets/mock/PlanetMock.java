package com.nathan.swplanets.mock;

import com.nathan.swplanets.domain.Planet;


public class PlanetMock {

    public static Planet success() {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("arid");
        planet.setTerrain("desert");
        planet.setFilms_appearances(5);

        return planet;
    }
}
