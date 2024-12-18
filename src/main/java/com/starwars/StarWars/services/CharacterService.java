package com.starwars.StarWars.services;

import com.starwars.StarWars.dto.CharacterDTO;
import com.starwars.StarWars.entities.SWCharacter;
import com.starwars.StarWars.exceptions.CharacterNotFoundException;
import com.starwars.StarWars.repositories.interfaces.ICharacterRepository;
import com.starwars.StarWars.repositories.CharacterRepository;
import com.starwars.StarWars.services.interfaces.ICharacterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService implements ICharacterService {

    ICharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<CharacterDTO> find(String name, String homeworld, String species, String gender) {
        List<SWCharacter> foundList = characterRepository.find(name, homeworld, species, gender);
        return foundList
                .stream()
                .map(ch -> new CharacterDTO(
                        ch.getId(), ch.getName(), ch.getHeight(),
                        ch.getMass(), ch.getGender(), ch.getHomeworld(),
                        ch.getSpecies()))
                .toList();
    }

    @Override
    public CharacterDTO findById(int id) {
        Optional<SWCharacter> optionalCharacter = characterRepository.findById(id);

        if (optionalCharacter.isEmpty()) throw new CharacterNotFoundException("Character id could not be found", id);

        SWCharacter character = optionalCharacter.get();

        return new CharacterDTO(character.getId(), character.getName(), character.getHeight(),
                character.getMass(), character.getGender(), character.getHomeworld(),
                character.getSpecies());
    }

}
