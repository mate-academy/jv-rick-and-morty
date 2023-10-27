package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageResponseDto;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.data.domain.Pageable;
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
    public PersonageResponseDto getRandomPersonage() {
        return personageRepository.getRandomPersonage();
    }

    @GetMapping("/search")
    public List<PersonageResponseDto> getPersonageByName(
            Pageable pageable, @RequestParam String name
    ) {
        return personageRepository.getPersonageByNameLike(pageable, name);
    }
}
