package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonalityDto;
import mate.academy.rickandmorty.dto.internal.PersonalitySearchParametersDto;
import mate.academy.rickandmorty.service.PersonalityService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Personality management", description = "Endpoints to managing characters ")
@RestController
@RequiredArgsConstructor
@RequestMapping("/personalities")
public class PersonalityController {
    private final PersonalityService personalityService;

    @GetMapping("/random")
    @Operation(summary = "Getting random character.",
            description = "Getting random character from db")
    public PersonalityDto getRandomPersonality() {
        return personalityService.getRandomPersonality();
    }

    @Operation(summary = "Search characters.",
            description = "Search characters by params")
    @GetMapping("/search")
    public List<PersonalityDto> search(
            PersonalitySearchParametersDto params,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String[] sort) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return personalityService.search(params, pageable);
    }
}
