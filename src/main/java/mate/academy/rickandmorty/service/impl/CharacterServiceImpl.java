package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Character randomCharacter = characterRepository.getRandomCharacter();
        return characterMapper.toDto(randomCharacter);
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name, Pageable pageable) {
        return characterRepository.findByNameContainsIgnoreCase(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
