package com.nathan.swplanets.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Planet {
    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int films_appearances;
}
