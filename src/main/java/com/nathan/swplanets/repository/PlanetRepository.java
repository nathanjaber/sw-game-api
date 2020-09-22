package com.nathan.swplanets.repository;

import com.nathan.swplanets.domain.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
    List<Planet> findAll();
}
