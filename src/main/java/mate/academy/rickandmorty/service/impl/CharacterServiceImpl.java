package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    @Override
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get book by id: " + id));
    }

    @Override
    public Character getRandomCharacter() {
        long repositorySize = characterRepository.count();
        Long randomNumber = (long) (Math.random() * repositorySize);
        return findById(randomNumber);
    }

    @Override
    public List<Character> searchCharactersByNamePart(String namePart) {
        return characterRepository.searchCharacters(namePart);
    }
}
