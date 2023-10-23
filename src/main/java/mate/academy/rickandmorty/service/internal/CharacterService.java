package mate.academy.rickandmorty.service.internal;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.dto.CharactersDataResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.external.RickAndMortyApiClient;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final RickAndMortyApiClient client;

    @PostConstruct
    void init() {
        String url = RickAndMortyApiClient.URL;
        do {
            CharactersDataResponseDto data = client.getData(url);
            this.updateAll(data.getCharacters());
            url = data.getInfo().getNext();
        } while (url != null);
    }

    public void updateAll(List<CharacterDto> dtos) {
        dtos.forEach(this::update);
    }

    private void update(CharacterDto characterDto) {
        Character character = repository.findByExternalId(characterDto.getId())
                .orElseGet(Character::new);
        Character updatedCharacter = mapper.updateModel(character, characterDto);
        mapper.toDto(repository.save(updatedCharacter));
    }

    public CharacterResponseDto getRandomCharacter() {
        int recordsCount = repository.countRecords();
        long randomIndex = ThreadLocalRandom.current().nextLong(1, recordsCount);
        Character character = repository.findById(randomIndex).orElseThrow(() ->
                new EntityNotFoundException("Can't get random character from the database"));
        return mapper.toDto(character);
    }

    public List<CharacterResponseDto> findAllByNameContaining(String name, Pageable pageable) {
        return repository.findAllByNameContaining(name, pageable).stream()
                .map(mapper::toDto)
                .toList();
    }
}
