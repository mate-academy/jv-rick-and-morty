package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
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
    private final Random random = new Random();
    private int charactersCount;

    @Override
    public List<CharacterResponseDto> addAll(List<CharacterRequestDto> dtoList) {
        charactersCount += dtoList.size();

        return characterRepository.saveAll(dtoList.stream()
                        .map(characterMapper::toEntity)
                        .collect(Collectors.toList()))
                .stream().map(characterMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CharacterResponseDto generateRandomCharacter() {
        Long generatedId = random.nextLong(charactersCount - 1) + 1;

        return characterMapper.toDto(characterRepository.findById(generatedId)
                .orElseThrow(() -> new RuntimeException("Can't get character with id: "
                        + generatedId)));
    }

    @Override
    public List<CharacterResponseDto> searchCharactersByName(
            String searchString, Pageable pageable) {
        return characterRepository.searchByNameContainsIgnoreCase(
                        searchString, pageable).stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
