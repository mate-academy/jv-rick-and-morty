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
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    public List<CharacterResponseDto> addAll(List<CharacterRequestDto> dto) {
        return repository.saveAll(dto.stream()
                        .map(mapper::toEntity)
                        .collect(Collectors.toList()))
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponseDto generateRandomCharacter() {
        return mapper.toDto(repository.getRandomCharacter());
    }

    @Override
    public List<CharacterResponseDto> searchCharactersByName(
            String searchString, Pageable pageable) {
        return repository.searchByNameContainsIgnoreCase(searchString, pageable).stream().map(
                mapper::toDto).collect(Collectors.toList());
    }
}
