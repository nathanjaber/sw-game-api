package com.nathan.swplanets.ports.inbound;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;

public interface PlanetInboundPort {
    GetPlanetByNameResponse teste(String name);
}
