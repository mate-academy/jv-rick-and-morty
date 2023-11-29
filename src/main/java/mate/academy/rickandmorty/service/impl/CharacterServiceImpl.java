package mate.academy.rickandmorty.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDtoResult;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.CharactersClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharactersClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterDto getRandomCharacter() {
        Random random = new Random();
        return characterMapper.toDto(characterRepository
                .findById(random.nextLong(characterRepository.count())).orElseThrow(
                        () -> new EntityNotFoundException("Can't find random character")));
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        List<Character> charactersByName = characterRepository.findCharactersByNameContains(name);
        return charactersByName.stream().map(characterMapper::toDto).toList();
    }

    @Override
    public void setAllCharactersFromExternalDataBaseToInternalDataBase() {
        client.getAllCharactersFromExternalDataBase()
        .stream().map(characterMapper::toInternalDto)
                .map(characterMapper::toModel)
                .forEach(characterRepository::save);
    }
}
