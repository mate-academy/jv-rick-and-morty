package mate.academy.rickandmorty.service.impl;

import java.util.List;
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
        return characterMapper.toResponseDto(characterRepository.findRandomCharacter());
    }

    @Override
    public List<CharacterResponseDto> searchCharacterByName(String name) {
        return characterRepository.findAllByNameContainingIgnoreCase(name).stream()
                .map(characterMapper::toResponseDto)
                .toList();
    }
}
