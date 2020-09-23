package com.nathan.swplanets.ports.outbound;

import com.nathan.swplanets.domain.GetPlanetByNameResponse;

public interface SwapiOutboundPort {
    GetPlanetByNameResponse getPlanetByName(String name);
}
