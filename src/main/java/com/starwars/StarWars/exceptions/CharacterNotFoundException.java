package com.starwars.StarWars.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CharacterNotFoundException extends RuntimeException {

    private int value;

    public CharacterNotFoundException(String message, int value) {
        super(message);
        this.value = value;
    }

}
