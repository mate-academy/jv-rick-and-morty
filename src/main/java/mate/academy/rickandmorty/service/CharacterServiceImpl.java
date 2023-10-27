package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterResponseDto> addAll(List<CharacterRequestDto> dto) {
        return characterRepository.saveAll(dto.stream()
                        .map(characterMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream().map(characterMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponseDto generateRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public List<CharacterResponseDto> searchCharactersByName(
            String searchString, Pageable pageable) {
        return characterRepository.searchByNameContainsIgnoreCase(
                searchString, pageable).stream().map(
                characterMapper::toDto).collect(Collectors.toList());
    }
}
