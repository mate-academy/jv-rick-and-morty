package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.client.RickAndMortyClient.CharacterResponse;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyClient client;
    private final CharacterMapper mapper;
    private final CharacterRepository repository;
    private final Random random = new Random();

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDto> findCharactersByName(String namePart) {
        return repository.findByNameContainingIgnoreCase(namePart).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CharacterDto getRandomCharacter() {
        long count = repository.count();
        if (count == 0) {
            throw new IllegalStateException("No characters in DB");
        }
        int idx = random.nextInt((int) count);
        var page = repository.findAll(org.springframework.data.domain.PageRequest.of(idx, 1));
        var entity = page.getContent().stream().findFirst()
                .orElseGet(() -> repository.findAll().get(0));
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public void loadAllCharactersOnce() {
        List<CharacterResponse> fetched = client.fetchAllCharacters();
        if (fetched.isEmpty()) {
            return;
        }
        Set<String> existing = repository.findAll().stream()
                .map(RickAndMortyCharacter::getExternalId)
                .collect(Collectors.toSet());

        List<RickAndMortyCharacter> toSave = fetched.stream()
                .filter(r -> r.getId() != null && !existing.contains(r.getId()))
                .map(mapper::toModel)
                .collect(Collectors.toList());

        if (!toSave.isEmpty()) {
            repository.saveAll(toSave);
        }
    }
}
