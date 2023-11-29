package mate.academy.rickandmorty.controller;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie-characters")
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping("/random")
    public CharacterResponseDto getRandom() {
        CharacterResponseDto characterResponseDto = characterService.getRandomCharacter();
        return characterResponseDto;
    }

    @GetMapping("/by-name")
    public List<CharacterResponseDto> findAllByName(@RequestParam("name") String namePart) {
        return characterService.findAllByNameContains(namePart)
                .stream()
                .collect(Collectors.toList());
    }
}
