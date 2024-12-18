package com.starwars.StarWars.services;

import com.starwars.StarWars.dto.EntityDTO;
import com.starwars.StarWars.entities.Entity;
import com.starwars.StarWars.exceptions.EntityNotFoundException;
import com.starwars.StarWars.repositories.interfaces.IEntityRepository;
import com.starwars.StarWars.repositories.EntityRepository;
import com.starwars.StarWars.services.interfaces.IEntityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntityService implements IEntityService {

    IEntityRepository entityRepository;

    public EntityService(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public List<EntityDTO> find(String name, String homeworld, String species, String gender) {
        List<Entity> foundList = entityRepository.find(name, homeworld, species, gender);
        return foundList
                .stream()
                .map(e -> new EntityDTO(
                        e.getId(), e.getName(), e.getHeight(),
                        e.getMass(), e.getGender(), e.getHomeworld(),
                        e.getSpecies()))
                .toList();
    }

    @Override
    public EntityDTO findById(int id) {
        Optional<Entity> optionalEntity = entityRepository.findById(id);

        if (optionalEntity.isEmpty()) throw new EntityNotFoundException("Character id could not be found", id);

        Entity entity = optionalEntity.get();

        return new EntityDTO(entity.getId(), entity.getName(), entity.getHeight(),
                entity.getMass(), entity.getGender(), entity.getHomeworld(),
                entity.getSpecies());
    }

}
