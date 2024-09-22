package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Find a character from Rick and Morty")
@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_PAGE = 0;
    private static final String DEFAULT_SORT_PARAMETER = "id";
    private final RickAndMortyService service;

    @GetMapping
    public CharacterResponseDto getRandomCharacter() {
        return service.getRandomCharacter();
    }

    @GetMapping("/{name}")
    public List<CharacterResponseDto> searchByName(
            @PathVariable String name,
            @PageableDefault(size = DEFAULT_PAGE_SIZE, page = DEFAULT_PAGE,
                    sort = DEFAULT_SORT_PARAMETER) Pageable pageable) {
        return service.getCharacterBySearchParam(name, pageable);
    }
}
