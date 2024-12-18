package com.starwars.StarWars.services.interfaces;

import com.starwars.StarWars.dto.CharacterDTO;

import java.util.List;

public interface ICharacterService {

    List<CharacterDTO> find(String name, String homeworld, String species, String gender);

    CharacterDTO findById(int id);

}
