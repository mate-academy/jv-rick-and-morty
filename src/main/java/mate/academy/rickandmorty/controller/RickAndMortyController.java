package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageResponseDto;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rick-and-morty")
public class RickAndMortyController {
    private final PersonageService personageRepository;

    @GetMapping("/random-personage")
    @Operation(summary = "Get random personage",
            description = "Get random personage from Rick and Morty universe")
    public PersonageResponseDto getRandomPersonage() {
        return personageRepository.getRandomPersonage();
    }

    @GetMapping("/search")
    @Operation(summary = "Get personages by name",
            description = "Get from Rick and Morty universe "
                    + "personages witch has name in their name")
    public List<PersonageResponseDto> getPersonageByName(
            @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable,
            @RequestParam String name
    ) {
        return personageRepository.getPersonageByNameLike(pageable, name);
    }
}
