package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFromApiDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharactersServiceImpl implements CharacterService {
    private final RickAndMortyApiClient client;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        List<CharacterFromApiDto> characterDtos = client.getResponse().results();
        List<Character> characters = characterDtos.stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }

    @Override
    public CharacterDto getRandomCharacter() {
        List<Character> characters = characterRepository.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(characters.size());
        return characterMapper.toDto(characters.get(randomIndex));
    }

    @Override
    public List<CharacterDto> getCharactersWithName(String name) {
        return characterRepository.findCharacterByNameContains(name).stream()
                .map(characterMapper::toDto)
                .toList();
    }
}
