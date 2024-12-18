package com.starwars.StarWars.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {

    private int status;
    private String code;
    private String value;
    private String description;

}
