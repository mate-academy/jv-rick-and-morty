package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static Long MAX_ID_OF_CHARACTER = 826L;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<Character> saveAllCharacters(List<CharacterDto> characterDtos) {
        List<Character> characters = characterDtos.stream()
                .map(characterMapper::toCharacter)
                .toList();
        return characterRepository.saveAll(characters);
    }

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Random random = new Random();
        return characterMapper.toResponseDto(characterRepository
                .findById(random.nextLong(MAX_ID_OF_CHARACTER))
                .orElseThrow(() -> new RuntimeException("Can`t found random character")));
    }

    @Override
    public List<CharacterResponseDto> searchCharacterByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toResponseDto)
                .toList();
    }
}
