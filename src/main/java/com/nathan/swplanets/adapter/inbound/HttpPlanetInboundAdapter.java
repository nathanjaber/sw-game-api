package com.nathan.swplanets.adapter.inbound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swplanets/v1/planets")
public class HttpPlanetInboundAdapter {

    @GetMapping()
//    public ResponseEntity get() {
//        return ResponseEntity.ok(service.getCars());
//    }
    public String getPlanets() {
        return "Todos os planetas";
    }
}