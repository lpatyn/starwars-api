package com.starwars.StarWars.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.StarWars.entities.SWCharacter;
import com.starwars.StarWars.repositories.interfaces.ICharacterRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepository implements ICharacterRepository {

    private List<SWCharacter> characterList;

    @Override
    public List<SWCharacter> find(String name, String homeworld, String species, String gender) {
        getList();

        if (name != null) characterList = characterList.stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        if (homeworld != null) characterList = characterList.stream()
                .filter(e -> {
                    if (e.getHomeworld() == null) return false;
                    return e.getHomeworld().toLowerCase().contains(homeworld.toLowerCase());
                })
                .toList();
        if (species != null) characterList = characterList.stream()
                .filter(e -> {
                    if (e.getSpecies() == null) return false;
                    return e.getSpecies().toLowerCase().contains(species.toLowerCase());
                })
                .toList();
        if (gender != null) characterList = characterList.stream()
                .filter(e -> {
                    if (e.getGender() == null) return false;
                    return e.getGender().equalsIgnoreCase(gender);
                })
                .toList();

        return characterList;
    }

    @Override
    public Optional<SWCharacter> findById(int id) {
        getList();
        return characterList.stream().filter(e -> e.getId() == id).findFirst();
    }

    private void getList() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            String filePath = "starwars.json";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new FileNotFoundException(filePath + " not found in resources folder");
            }

            characterList = mapper.readValue(inputStream, new TypeReference<List<SWCharacter>>() {});
        } catch (Exception e){
            System.out.println("File not found. " + e.getMessage());
        }
    }

}
