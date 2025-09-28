package mate.academy.rickandmorty.service.impl;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.client.RickAndMortyClient;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyClient characterClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();

    @PostConstruct
    public void saveAllCharacters() {
        characterRepository.deleteAll();

        List<CharacterDto> characterDtos = characterClient.getCharacters();
        List<RickAndMortyCharacter> rickAndMortyCharacters = characterDtos.stream()
                .map(characterMapper::toModel).toList();
        characterRepository.saveAll(rickAndMortyCharacters);
    }

    @Override
    public CharacterDto getRandomCharacter() throws EntityNotFoundException {
        RickAndMortyCharacter rickAndMortyCharacter = characterRepository.findRandomCharacter();

        if (rickAndMortyCharacter == null) {
            throw new EntityNotFoundException("No characters found in database");
        }

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
