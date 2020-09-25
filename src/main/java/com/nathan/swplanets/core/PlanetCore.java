package com.nathan.swplanets.core;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.GetPlanetByNameResult;
import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.ports.inbound.PlanetInboundPort;
import com.nathan.swplanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetCore implements PlanetInboundPort {

    @Autowired
    PlanetService planetService;

    @Override
    public List<PlanetDTO> getPlanets(String name) {
        return this.planetService.getPlanets(name);
    }

    @Override
    public PlanetDTO createPlanet(Planet planet) {
        Planet newPlanet = this.planetService.createPlanet(planet);
        GetPlanetByNameResponse listPlanets = this.planetService.swapiSearchPlanetByName(newPlanet.getName());

        String newPlanetName = newPlanet.getName();
        if(listPlanets.getCount() > 0)
            listPlanets.getResults().forEach((result) -> {
                if(result.getName().equalsIgnoreCase(newPlanetName))
                    newPlanet.setFilms_appearances(result.getFilms().size());
            });
        else newPlanet.setFilms_appearances(0);
        return PlanetDTO.create(this.planetService.createPlanet(newPlanet));
    }

    @Override
    public Optional<PlanetDTO> getPlanetById(String id) {
        return this.planetService.getPlanetById(id);
    }

    @Override
    public boolean deletePlanetById(String id) {
        return this.planetService.deletePlanetById(id);
    }
}
