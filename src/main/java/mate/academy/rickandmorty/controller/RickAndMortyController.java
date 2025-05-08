package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.RickAndMortyCharacterDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RickAndMorty_Program", description = "Application that downloads and searches "
        + "for characters from rickandmortyapi.com  ")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/rick-and-morty")
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    @Operation(summary = "Get Random Character", description = "Get Random from database")
    @GetMapping
    public RickAndMortyCharacterDto getRandomCharacter() {
        return rickAndMortyService.getRandomCharacter();
    }

    @Operation(summary = "Search Characters by String", description = "searches for all characters "
            + "that have the given string in their name, method can sort the results and divide "
            + "them into pages")
    @GetMapping("/{name}")
    public List<RickAndMortyCharacterDto> getCharacterByName(@PathVariable String name,
                                                             @ParameterObject Pageable pageable) {
        return rickAndMortyService.getCharacterByName(name,pageable);
    }
}
