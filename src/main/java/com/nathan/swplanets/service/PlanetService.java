package com.nathan.swplanets.service;

import com.nathan.swplanets.domain.Planet;
import com.nathan.swplanets.domain.PlanetDTO;
import com.nathan.swplanets.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    @Autowired
    PlanetRepository repository;

    public List<PlanetDTO> getPlanets() {
        return repository.findAll().stream().map(PlanetDTO::create).collect(Collectors.toList());
    }
}
