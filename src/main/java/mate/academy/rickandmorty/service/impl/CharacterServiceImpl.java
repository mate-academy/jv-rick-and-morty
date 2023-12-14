package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.reposetory.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long characterCount = characterRepository.count();
        if (characterCount == 0) {
            throw new EntityNotFoundException("There are no characters in the database");
        }
        int randomIndex = new Random().nextInt((int) characterCount);
        long randomCharacterId = randomIndex + 1;
        if (randomCharacterId > characterCount) {
            randomCharacterId = characterCount;
        }
        Optional<CharacterDto> characterDto = characterRepository.findById(randomCharacterId)
                .map(characterMapper::toDto);
        long finalRandomCharacterId = randomCharacterId;
        return characterDto.orElseThrow(() ->
                new EntityNotFoundException("Can not get a random character with id "
                        + finalRandomCharacterId));
    }

    @Override
    public List<CharacterDto> searchCharacters(String searchTerm) {
        return characterRepository.findByNameContaining(searchTerm).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
