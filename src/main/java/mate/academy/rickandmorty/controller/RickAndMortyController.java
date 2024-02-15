package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final RickAndMortyServiceImpl rickAndMortyService;

    @GetMapping("/random")
    public RickAndMortyCharacterDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @GetMapping("/name-contains")
    public List<RickAndMortyCharacterDto> findCharactersByNameContaining(
            @RequestParam String nameSegment) {
        return rickAndMortyService.findCharactersByNameContaining(nameSegment);
    }
}
