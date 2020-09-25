package com.nathan.swplanets.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;

@Document
@Data
public class Planet {
    @Id
    private String _id;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "climate is mandatory")
    private String climate;
    @NotBlank(message = "terrain is mandatory")
    private String terrain;
    private int films_appearances;
}
