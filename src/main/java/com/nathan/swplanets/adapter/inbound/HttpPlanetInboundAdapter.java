package com.nathan.swplanets.adapter.inbound;

import com.nathan.swplanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public String createPlanet() {
        return "Planeta criado";
    }

    @GetMapping("/{id}")
    public String getPlanetById(@PathVariable("id") Long id) {
        return "Planeta" + id;
    }

    @DeleteMapping("/{id}")
    public String deletePlanetById(@PathVariable("id") Long id) {
        return "Planeta " + id +" removido";
    }
}