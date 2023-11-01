package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterDto;
import mate.academy.rickandmorty.dto.internal.CharacterRespondDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        List<CharacterDto> fetchedAllPages = rickAndMortyClient.fetchAllPages();

        saveAll(fetchedAllPages);
    }

    public void saveAll(List<CharacterDto> list) {
        list.forEach(this::save);
    }

    public void save(CharacterDto dto) {
        characterRepository.save(characterMapper.toModel(dto));
    }

    public CharacterRespondDto getRandomCharacter() {
        long count = characterRepository.count();
        Long randomId = Math.abs(random.nextLong() % count + 1);

        Character byId = characterRepository.findById(randomId).orElseThrow(
                () -> new RuntimeException("Cannot find character with id: " + randomId));

        return characterMapper.toDto(byId);
    }

}
