package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @GetMapping("/random")
    public CharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterService.getRandomCharacter());
    }

    @GetMapping("/search")
    public List<CharacterDto> searchCharacter(@RequestParam String name) {
        return characterService.findAllByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
