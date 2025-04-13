package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.RickAndMortyCharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final RickAndMortyCharacterRepository rickAndMortyCharacterRepository;
    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void save() {
        List<RickAndMortyCharacter> characters =
                rickAndMortyClient.getRickAndMortyCharacter()
                        .stream()
                        .map(characterMapper::toModel)
                        .toList();
        rickAndMortyCharacterRepository.saveAll(characters);
    }

    @Override
    public RickAndMortyCharacter getRandomCharacter() {
        return rickAndMortyCharacterRepository
                .findAll().get(new Random()
                        .nextInt(rickAndMortyCharacterRepository.findAll().size()));
    }

    @Override
    public List<RickAndMortyCharacter> findByNameContaining(String name) {
        return rickAndMortyCharacterRepository.findAllByNameContaining(name);
    }
}
