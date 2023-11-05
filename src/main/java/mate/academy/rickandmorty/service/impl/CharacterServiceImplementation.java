package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImplementation implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponseDto getRandomCharacter() {
        Random random = new Random();
        return characterMapper.toDto(characterRepository
                .findById(random.nextLong(characterRepository
                        .findAll().size())).orElseThrow(()
                                -> new RuntimeException("Entity was not found")));
    }

    @Override
    public List<CharacterResponseDto> getCharactersByName(String name) {
        return characterRepository.findCharacterByNameIgnoreCase(name)
                .stream()
                .map(characterMapper::toDto)
                .collect(Collectors.toList());
    }
}
