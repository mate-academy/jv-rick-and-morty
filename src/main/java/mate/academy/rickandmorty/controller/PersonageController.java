package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Personage management", description = "Endpoints for managing personages.")
@RestController
@RequestMapping("personages")
@RequiredArgsConstructor
public class PersonageController {
    private final PersonageService personageService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a random personage.")
    public PersonageDto getPersonage() {
        return personageService.getRandomPersonage();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Filtering and sorting personages by name.")
    public List<PersonageDto> searchPersonage(String name, Pageable pageable) {
        return personageService.searchPersonage(name, pageable);
    }
}
