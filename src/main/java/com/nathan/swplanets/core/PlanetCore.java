package com.nathan.swplanets.core;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;
import com.nathan.swplanets.ports.inbound.PlanetInboundPort;
import com.nathan.swplanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanetCore implements PlanetInboundPort {

    @Autowired
    PlanetService planetService;

    public GetPlanetByNameResponse teste(String name) {
        return this.planetService.swapiplan(name);
    }
}
