package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rick-and-morty")
@RequiredArgsConstructor
public class RickAndMortyController {
    private final PersonageRepository characterRepository;

    @GetMapping("/random-personage")
    public Object getRandomPersonage() {
        return characterRepository;
    }

    @GetMapping("/search")
    public List<Personage> getPersonageByName(@RequestParam String name) {
        return characterRepository.findAllByNameLike(name);
    }
}
