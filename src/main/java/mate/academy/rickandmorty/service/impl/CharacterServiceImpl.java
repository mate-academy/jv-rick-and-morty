package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterClient characterClient;
    private final CharacterMapper characterMapper;
    private Random random = new Random();

    @PostConstruct
    public void saveCharactersIntoDB() {
        List<CharacterResponseDto> charactersIntoDB = characterClient.getCharactersIntoDB();
        saveAll(charactersIntoDB);
    }

    @Override
    public void saveAll(List<CharacterResponseDto> responseDtoList) {
        List<Character> characters = responseDtoList.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getCharacterByRandomId() {
        long id = random.nextLong(1L, characterRepository.count());
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by id" + id)
        );
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> searchByName(String name) {
        return characterRepository.findAllByNameContaining(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
