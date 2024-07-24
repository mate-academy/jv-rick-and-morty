package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.model.CharacterFromApi;
import mate.academy.rickandmorty.repository.CharacterFromApiRepository;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterFromApiRepository characterFromApiRepository;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public CharacterDto getRandomCharacter() {
        CharacterFromApi characterFromApi =
                characterFromApiRepository.getRandomCharacter();
        CharacterDto characterDto =
                characterMapper.toDto(characterFromApi);
        Character character =
                characterRepository.save(characterMapper.toModel(characterDto));
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return characterFromApiRepository
                .findCharactersByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toDto)
                .map(characterMapper::toModel)
                .map(characterRepository::save)
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void initCharacters() {
        characterClient.getCharactersFromDb().stream()
                .map(characterMapper::toModel)
                .forEach(characterFromApiRepository::save);
    }
}
