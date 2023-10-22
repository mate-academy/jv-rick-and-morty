package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getCharacterById(Long id) {
        return characterMapper.toDto(characterRepository.getReferenceById(id));
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Long id = RANDOM.nextLong(characterRepository.count());
        return getCharacterById(id);
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name) {
        return characterRepository.findCharactersByNameContaining(name);
    }

    @Override
    public void saveAllCharacters(List<CharacterResponseDto> characterResponseDtos) {
        List<Character> characters = characterResponseDtos.stream()
                .map(characterMapper::toEntity)
                .toList();
        characterRepository.saveAll(characters);
    }
}
