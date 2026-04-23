package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    private final CharacterMapper characterMapper;

    public CharacterDto generateRandomCharacterWikiDto() {
        Character character = characterRepository.findRandom();

        if (character == null) {
            throw new DataProcessingException("No characters found in DB");
        }

        return characterMapper.toDto(character);
    }

    public List<CharacterDto> findCharactersByName(String name) {
        if (name == null || name.isBlank()) {
            return characterRepository.findAll().stream()
                    .map(characterMapper::toDto)
                    .toList();
        }

        return characterRepository.findAllByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
