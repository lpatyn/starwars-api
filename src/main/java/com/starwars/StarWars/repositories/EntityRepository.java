package com.starwars.StarWars.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.starwars.StarWars.entities.Entity;
import com.starwars.StarWars.repositories.interfaces.IEntityRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Repository
public class EntityRepository implements IEntityRepository {

    private List<Entity> entityList;

    @Override
    public List<Entity> find(String name, String homeworld, String species, String gender) {
        getList();

        if (name != null) entityList = entityList.stream()
                .filter(e -> e.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        if (homeworld != null) entityList = entityList.stream()
                .filter(e -> {
                    if (e.getHomeworld() == null) return false;
                    return e.getHomeworld().toLowerCase().contains(homeworld.toLowerCase());
                })
                .toList();
        if (species != null) entityList = entityList.stream()
                .filter(e -> {
                    if (e.getSpecies() == null) return false;
                    return e.getSpecies().toLowerCase().contains(species.toLowerCase());
                })
                .toList();
        if (gender != null) entityList = entityList.stream()
                .filter(e -> {
                    if (e.getGender() == null) return false;
                    return e.getGender().equalsIgnoreCase(gender);
                })
                .toList();

        return entityList;
    }

    @Override
    public Optional<Entity> findById(int id) {
        getList();
        return entityList.stream().filter(e -> e.getId() == id).findFirst();
    }

    private void getList() {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = null;
        try {
            jsonFile = ResourceUtils.getFile("classpath:starwars.json");
            entityList = mapper.readValue(jsonFile, new TypeReference<>() {});
        }catch (Exception ex){
            System.out.println("No existe el archivo. " + ex.getMessage());
        }
    }

}
