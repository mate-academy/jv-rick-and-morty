package mate.academy.rickandmorty.service.implementation;

import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharactersDtoResponse;
import mate.academy.rickandmorty.exceptions.CustomException;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.models.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final Random random;
    private final Properties properties;
    private final RestTemplate restTemplate;
    private final CharacterRepository characterRepository;
    private final CharacterMapper rickMortyMapper;

    @Autowired
    public CharacterServiceImpl(RestTemplate restTemplate,
                                CharacterRepository characterRepository,
                                CharacterMapper rickMortyMapper,
                                Random random, Properties properties) {
        this.restTemplate = restTemplate;
        this.characterRepository = characterRepository;
        this.rickMortyMapper = rickMortyMapper;
        this.random = random;
        this.properties = properties;
    }

    @PostConstruct
    private void downloadToDB() {
        String rootPath = Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResource("")).getPath();
        String appConfigPath = rootPath + "application.properties";
        try {
            properties.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            throw new CustomException("Wrong path!!!", e);
        }

        CharactersDtoResponse charactersDtoResponse = restTemplate.getForObject(properties
                        .getProperty("character-url"),
                CharactersDtoResponse.class);
        if (charactersDtoResponse != null) {
            characterRepository.saveAll(charactersDtoResponse.getResults()
                    .stream()
                    .map(rickMortyMapper::toModel)
                    .collect(Collectors.toList()));
        } else {
            throw new CustomException("Characters response object is NULL!!");
        }
    }

    public List<Character> getAllCharactersByName(String name) {
        return characterRepository.findRickMortiesByNameContainingIgnoreCase(name);
    }

    public Character getRandomCharacter() {
        Long maxId = characterRepository.getMaxId();
        return characterRepository.findRickMortyById(random.nextLong(1, maxId));
    }
}
