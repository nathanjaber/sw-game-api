package com.nathan.swplanets.adapter.inbound;

import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/swplanets/v1/planets")
public class HttpPlanetInboundAdapter {

    @Autowired
    PlanetService service;

    @GetMapping()
    public ResponseEntity getPlanets() {
        return ResponseEntity.ok(service.getPlanets());
    }

    @PostMapping()
    public ResponseEntity createPlanet(@RequestBody Planet planet) {
        try {
            PlanetDTO newPlanet = service.createPlanet(planet);

            URI location = getUri(newPlanet.get_id());
            return ResponseEntity.created(location).build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public String getPlanetById(@PathVariable("id") Long id) {
        return "Planeta" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePlanetById(@PathVariable("id") Long id) {
        return "Planeta " + id +" removido";
    }

    private URI getUri(String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}