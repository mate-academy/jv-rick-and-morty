package mate.academy.rickandmorty.service.character;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.character.RickAndMortyCharacterRepository;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RickAndMortyCharacterServiceImpl implements RickAndMortyCharacterService {
    private final RickAndMortyClient client;
    private final RickAndMortyCharacterRepository repository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void saveAllCharacters() {
        List<RickAndMortyCharacter> list =
                client.getAllCharacter()
                        .stream()
                        .map(characterMapper::toModel)
                        .toList();
        repository.saveAll(list);
    }

    @Override
    public RickAndMortyCharacter getRandomCharacter() {
        List<RickAndMortyCharacter> all = repository.findAll();
        return all.get(new Random().nextInt(all.size()));
    }

    @Override
    public List<RickAndMortyCharacter> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }
}
