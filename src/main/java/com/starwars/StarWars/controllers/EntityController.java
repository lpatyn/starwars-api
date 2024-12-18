package com.starwars.StarWars.controllers;

import com.starwars.StarWars.dto.EntityDTO;
import com.starwars.StarWars.services.interfaces.IEntityService;
import com.starwars.StarWars.services.EntityService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class EntityController {

    IEntityService entityService;

    public EntityController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping()
    ResponseEntity<List<EntityDTO>> find(
            @Nullable @RequestParam String name,
            @Nullable @RequestParam String homeworld,
            @Nullable @RequestParam String species,
            @Nullable @RequestParam String gender
    ) {
        return new ResponseEntity<>(entityService.find(name, homeworld, species, gender), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EntityDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(entityService.findById(id), HttpStatus.OK);
    }

}
