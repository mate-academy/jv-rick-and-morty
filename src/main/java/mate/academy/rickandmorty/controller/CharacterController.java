package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character Management", description = "endpoints to manage client character data base")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService service;
    private final CharacterMapper mapper;

    @Operation(summary = "show random character",
            description = "show random character from db")
    @GetMapping("/random")
    public ExternalCharResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @Operation(summary = "show character by search name parameter",
                description = "show character by string occurrence in his/her/its name")
    @GetMapping
    public List<ExternalCharResponseDto> getCharactersBySearchString(
            @RequestParam String searchString
    ) {
        List<Character> charactersBySearchString =
                service.getCharactersBySearchString(searchString);
        return charactersBySearchString.stream()
                .map(mapper::toDto)
                .toList();
    }
}
