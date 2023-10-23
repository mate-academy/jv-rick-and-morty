package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickNMortyClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final Random RANDOM = new Random();
    private final RickNMortyClient client;
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @PostConstruct
    public void init() {
        repository.saveAll(client.getAllCharacters().stream()
                .map(mapper::toCharacter)
                .toList());
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return mapper.toDto(repository.getReferenceById(randomId()));
    }

    @Override
    public List<CharacterDto> findAllByName(String name, Pageable pageable) {
        return repository.findAllByNameContainingIgnoreCase(name, pageable).stream()
                .map(mapper::toDto)
                .toList();
    }

    private Long randomId() {
        return RANDOM.nextLong(repository.count()) + 1;
    }
}
