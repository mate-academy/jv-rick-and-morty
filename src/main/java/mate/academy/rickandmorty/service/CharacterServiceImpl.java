package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterExternalResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterInternalResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @Override
    public CharacterInternalResponseDto getRandomCharacter() {
        long count = characterRepository.count();
        long randomId = random.nextLong(count);

        Character character = characterRepository.findById(randomId)
                .orElseThrow(() -> new RuntimeException(
                        "Cannot find character by id: " + randomId)
                );

        return characterMapper.toDto(character);
    }

    @Override
    public List<CharacterInternalResponseDto> getCharactersByName(String name) {
        return characterRepository.getCharactersByName(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @Override
    public void saveAll(List<CharacterExternalResponseDto> dtos) {
        List<Character> characters = dtos.stream().map(characterMapper::toModel).toList();
        characterRepository.saveAll(characters);
    }
}
