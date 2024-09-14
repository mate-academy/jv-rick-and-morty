package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repo.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;

    @Override
    public CharacterDto save(CharacterResponseDto characterResponseDto) {
        Character character = characterMapper.toModel(characterResponseDto);
        return characterMapper.toDto(characterRepository.save(character));
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomId = RANDOM.nextLong(characterRepository.count());
        Character character = characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Can't find character by id: " + randomId)
        );
        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterDto> findByPartName(String partName) {
        return characterMapper.toDtoList(
                characterRepository
                        .findByNameContainingIgnoreCase(partName)
        );
    }

    @PostConstruct
    public void init() {
        List<Character> characters
                = characterMapper.toModelList(characterClient.getDataFromApi());
        characterRepository.saveAll(characters);
    }
}
