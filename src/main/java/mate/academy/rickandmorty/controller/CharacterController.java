package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping("/random")
    public CharacterResponseDto getRandomCharacter() {
        Character randomCharacter = characterService.getRandomCharacter();
        return characterMapper.toDto(randomCharacter);
    }

    @GetMapping("/search")
    public List<CharacterResponseDto> searchCharactersByName(@RequestParam String name) {
        return characterService.findAllByNameContains(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
