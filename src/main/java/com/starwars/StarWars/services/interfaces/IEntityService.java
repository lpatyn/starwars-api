package com.starwars.StarWars.services.interfaces;

import com.starwars.StarWars.dto.EntityDTO;

import java.util.List;

public interface IEntityService {

    List<EntityDTO> find(String name, String homeworld, String species, String gender);

    EntityDTO findById(int id);

}
