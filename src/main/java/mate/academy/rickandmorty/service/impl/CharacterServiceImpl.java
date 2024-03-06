package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mappper.DtoToEntityMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.util.RandomNumberGenerator;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final DtoToEntityMapper<CharacterResponseDto, Character> mapperToEntity;
    private final CharacterRepository characterRepository;

    @Override
    public List<Character> saveAll(List<CharacterResponseDto> dtoList) {
        List<Character> characters = dtoList.stream()
                .map(mapperToEntity::toEntity)
                .toList();
        return characterRepository.saveAll(characters);
    }

    @Override
    public List<Character> findAllByName(String name) {
        return characterRepository.findAllByName(name);
    }

    /* The common method which will be able needed in the future. So I've done it public. */
    @Override
    public Character findById(Long id) {
        return characterRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Didn't find random Character with id " + id));
    }

    @Override
    public Character findRandom() {
        Character character = null;
        long numberOfCharacters = characterRepository.count();
        /* Let's imagine that character with random id
            have been deleted before ore ids not consistent */
        do {
            try {
                character = findById(RandomNumberGenerator.getRandomLong(numberOfCharacters));
            } catch (RuntimeException e) {
                /* Apparently specify what should happen if occur exception */
                continue;
            }
        } while (character == null);

        return character;
    }
}
