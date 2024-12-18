package com.starwars.StarWars.repositories.interfaces;

import com.starwars.StarWars.entities.Entity;

import java.util.List;
import java.util.Optional;

public interface IEntityRepository {

    List<Entity> find(String name, String homeworld, String species, String gender);

    Optional<Entity> findById(int id);

}
