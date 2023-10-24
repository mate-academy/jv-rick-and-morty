package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character Management", description = "endpoints to manage client character data base")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final RickAndMortyClient client;
    private final CharacterService service;
    private final CharacterMapper mapper;

    @Operation(summary = "show random character",
            description = "show and save random character from client db")
    @GetMapping("/random")
    public ExternalCharResponseDto getRandomCharacter() {
        return client.getRandomCharacter();
    }

    @Operation(summary = "saving all characters from client to db",
            description = "save all char info from client db to yours,"
                    + " only to use once for the first time with empty table")
    @GetMapping("/save")
    public void save() {
        service.save();
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
