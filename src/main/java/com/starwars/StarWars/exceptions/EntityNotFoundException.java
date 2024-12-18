package com.starwars.StarWars.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntityNotFoundException extends RuntimeException {

    private int value;

    public EntityNotFoundException(String message, int value) {
        super(message);
        this.value = value;
    }

}
