package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandom() {
        return characterMapper.toDto(
                characterRepository.getRandomCharacter().orElseThrow(
                        () -> new RuntimeException("There are no characters.")));
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name) {
        return characterRepository.findByNameIgnoreCaseContaining(name)
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
