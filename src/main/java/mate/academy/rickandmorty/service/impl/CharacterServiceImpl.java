package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @PostConstruct
    public void getAllCharacters() {
        List<CharacterEntity> charactersList = characterClient
                .getCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        characterRepository.saveAll(charactersList);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        long numberOfCharacters = characterRepository.count();
        if (numberOfCharacters > 0) {
            Random random = new Random();
            long randomCharacterId = random.nextLong() % numberOfCharacters + 1;
            CharacterEntity randomCharacter = characterRepository.findById(randomCharacterId)
                    .orElseThrow(() -> new EntityNotFoundException("Character not found by id "
                            + randomCharacterId));
            return characterMapper.toDto(randomCharacter);
        } else {
            throw new IllegalStateException("No characters in the database.");
        }
    }

    @Override
    public List<CharacterDto> findCharacterByName(String name, Pageable pageable) {
        return characterRepository
                .findAllByNameContainingIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
