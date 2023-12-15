package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.reposetory.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random randomIndex = new Random();
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
        long randomCharacterId = randomIndex.nextInt((int) characterCount);
        Optional<CharacterDto> characterDto = characterRepository.findById(randomCharacterId)
                .map(characterMapper::toDto);
        return characterDto.orElseThrow(() ->
                new EntityNotFoundException("Can not get a random character with id "
                        + randomCharacterId));
    }

    @Override
    public List<CharacterDto> searchCharacters(String searchTerm) {
        return characterRepository.findByNameContaining(searchTerm).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveCharacter(List<CreateCharacterRequestDto> createCharacterRequestDto) {
        createCharacterRequestDto.stream()
                .map(characterMapper::toModel)
                .forEach(characterRepository::save);
    }
}
