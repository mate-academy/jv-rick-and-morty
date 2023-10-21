package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private int numberOfCharacters;

    @Override
    public CharacterResponseDto getById(Long id) {
        return characterMapper.toDto(characterRepository.getReferenceById(id));
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Long id = RANDOM.nextLong(numberOfCharacters);
        return getById(id);
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name) {
        return characterRepository.findCharactersByNameContaining(name);
    }

    @Override
    public void saveAllCharacters(List<CharacterResponseDto> characterResponseDtos) {
        List<CharacterEntity> characters = characterResponseDtos.stream()
                .map(characterMapper::toEntity)
                .toList();
        numberOfCharacters = characters.size();
        characterRepository.saveAll(characters);
    }
}
