package com.parkhomovsky.rickandmorty.controller;

import com.parkhomovsky.rickandmorty.dto.CharacterResponseDto;
import com.parkhomovsky.rickandmorty.service.MovieCharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-characters")
@Tag(name = "Character management",
        description = "Endpoints for characters")
public class MovieCharacterController {
    private final MovieCharacterService characterService;

    public MovieCharacterController(MovieCharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/random")
    @Operation(description = "Return random character")
    public CharacterResponseDto getRandom() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/by-name")
    @Operation(description = "Get all characters by name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return characterService.getAllByNamePart(namePart);
    }
}
