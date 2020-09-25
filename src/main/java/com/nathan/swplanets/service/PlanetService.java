package com.nathan.swplanets.service;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.domain.GetPlanetByNameResult;
import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.ports.outbound.SwapiOutboundPort;
import com.nathan.swplanets.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    @Autowired
    PlanetRepository repository;

    @Autowired
    SwapiOutboundPort swapiOutboundPort;

    public List<PlanetDTO> getPlanets(String name) {
        return (name == null) ? repository.findAll().stream().map(PlanetDTO::create).collect(Collectors.toList()) :
                this.repository.findByName(name);
    }

    public Planet savePlanet(Planet planet) {
        return this.repository.save(planet);
    }

    public Optional<PlanetDTO> getPlanetById(String id) {
        return repository.findById(id).map(PlanetDTO::create);
    }

    public boolean deletePlanetById(String id) {
        if(getPlanetById(id).isPresent()) {
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    public GetPlanetByNameResponse swapiSearchPlanetByName(String name) {
        return this.swapiOutboundPort.getPlanetByName(name);
    }
}
