package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.exception.CharacterNotFoundException;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public CartoonCharacter add(CartoonCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public CartoonCharacter getById(Long id) {
        CartoonCharacter characterById = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(
                        "Can't find character with id = " + id));
        return characterById;
    }

    @Override
    public List<CartoonCharacter> search(String name) {
        return characterRepository.findCartoonCharactersByNameContainsIgnoreCase(name)
                .stream()
                .toList();
    }
}
