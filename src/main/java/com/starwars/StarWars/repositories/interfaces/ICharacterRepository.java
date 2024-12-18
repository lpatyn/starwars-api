package com.starwars.StarWars.repositories.interfaces;

import com.starwars.StarWars.entities.SWCharacter;

import java.util.List;
import java.util.Optional;

public interface ICharacterRepository {

    List<SWCharacter> find(String name, String homeworld, String species, String gender);

    Optional<SWCharacter> findById(int id);

}
