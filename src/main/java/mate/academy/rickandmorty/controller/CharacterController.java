package mate.academy.rickandmorty.controller;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterRepository repository;

    public CharacterController(CharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/random")
    public CharacterEntity getRandom() {
        long count = repository.count();
        if (count == 0) {
            throw new RuntimeException("База даних порожня!");
        }
        int randomIndex = (int) (Math.random() * count);

        Page<CharacterEntity> characterPage = repository.findAll(PageRequest.of(randomIndex, 1));

        return characterPage.getContent().get(0);
    }

    @GetMapping("/search")
    public List<CharacterEntity> search(@RequestParam String name) {
        return repository.findAllByNameContaining(name);
    }
}
