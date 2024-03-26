package mate.academy.rickandmorty.controller;


import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final RickAndMortyInternalService rickAndMortyInternalService;
    @GetMapping
    public List<CharacterDto> getCharacterByName(@RequestParam String name) {
        return rickAndMortyInternalService.getAllCharactersLike(name);
    }

    @GetMapping(path = "/random")
    public CharacterDto getRandomCharacter() {
        return rickAndMortyInternalService.getRandomCharacter();
    }

}
