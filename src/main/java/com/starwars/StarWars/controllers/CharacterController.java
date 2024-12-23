package com.starwars.StarWars.controllers;

import com.starwars.StarWars.dto.CharacterDTO;
import com.starwars.StarWars.services.interfaces.ICharacterService;
import com.starwars.StarWars.services.CharacterService;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    ICharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping()
    ResponseEntity<List<CharacterDTO>> find(
            @Nullable @RequestParam String name,
            @Nullable @RequestParam String homeworld,
            @Nullable @RequestParam String species,
            @Nullable @RequestParam String gender
    ) {
        return new ResponseEntity<>(characterService.find(name, homeworld, species, gender), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    ResponseEntity<CharacterDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(characterService.findById(id), HttpStatus.OK);
    }

}
