package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final Random random;

    @Override
    public Character getRandomCharacter() {
        long characterNumber = characterRepository.count();
        return characterRepository.findById(random.nextLong(characterNumber))
                .orElseThrow();
    }

    @Override
    public List<Character> getCharactersByNamePart(String namePart) {
        return characterRepository.findAllByNameLikeIgnoreCase(namePart);
    }
}
