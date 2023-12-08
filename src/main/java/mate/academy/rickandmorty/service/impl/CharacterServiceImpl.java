package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.SeriesCharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public void saveAll() {
        characterRepository.saveAll(client.getAllCharacters().stream()
                .map(characterMapper::toModel)
                .toList());
    }

    @Override
    public SeriesCharacterResponseDto getRandomWiki() {
        long count = characterRepository.count();
        long randomId = ThreadLocalRandom.current().nextLong(1, count + 1);
        return characterMapper.toDto(characterRepository.findById(randomId).get());
    }

    @Override
    public List<SeriesCharacterResponseDto> findCharactersByName(String name, Pageable pageable) {
        return characterRepository.findByNameContains(name, pageable).stream()
                .map(characterMapper::toDto)
                .toList();
    }

    @PostConstruct
    public void init() {
        saveAll();
    }
}
