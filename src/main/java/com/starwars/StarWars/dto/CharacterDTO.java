package com.starwars.StarWars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterDTO {

    private int id;
    private String name;
    private int height;
    private int mass;
    private String gender;
    private String homeworld;
    private String species;

}
