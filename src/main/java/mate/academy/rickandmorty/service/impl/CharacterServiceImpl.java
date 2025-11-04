package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.RickAndMortyClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyClient characterClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @PostConstruct
    public void saveAllCharacters() {
        List<CharacterDto> characterDtos = characterClient.getCharacters();
        List<RickAndMortyCharacter> rickAndMortyCharacters = characterDtos.stream()
                .map(characterMapper::toModel).toList();
        characterRepository.saveAll(rickAndMortyCharacters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<RickAndMortyCharacter> rickAndMortyCharacters = characterRepository.findAll();
        if (rickAndMortyCharacters.isEmpty()) {
            throw new RuntimeException("No characters found in database");
        }
        int index = random.nextInt(rickAndMortyCharacters.size());
        RickAndMortyCharacter rickAndMortyCharacter = rickAndMortyCharacters.get(index);
        return characterMapper.toDto(rickAndMortyCharacter);
    }

    @Override
    public List<CharacterDto> findCharactersByName(String name) {
        List<RickAndMortyCharacter> charactersByName = characterRepository
                .findByNameContainingIgnoreCase(name);
        return charactersByName.stream()
                .map(characterMapper::toDto).toList();
    }
}
