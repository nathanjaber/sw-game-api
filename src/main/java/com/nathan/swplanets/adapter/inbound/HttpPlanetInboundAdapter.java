package com.nathan.swplanets.adapter.inbound;

import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.ports.inbound.PlanetInboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/swplanets/v1/planets")
public class HttpPlanetInboundAdapter {

    @Autowired
    PlanetInboundPort planetInboundPort;

    @GetMapping()
    public ResponseEntity<List<PlanetDTO>> getPlanets(@RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(this.planetInboundPort.getPlanets(name));
    }

    @PostMapping()
    public ResponseEntity createPlanet(@RequestBody @Valid Planet planet) {
        try {
            PlanetDTO newPlanet = this.planetInboundPort.createPlanet(planet);

            URI location = getUri(newPlanet.get_id());
            return ResponseEntity.created(location).build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlanetById(@PathVariable("id") String id) {
        Optional<PlanetDTO> planet = planetInboundPort.getPlanetById(id);

        return planet.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePlanetById(@PathVariable("id") String id) {
        return planetInboundPort.deletePlanetById(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.badRequest().build();
    }

    private URI getUri(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}