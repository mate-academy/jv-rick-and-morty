package mate.academy.rickandmorty.service.implementation;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharactersDtoResponse;
import mate.academy.rickandmorty.mappers.CharacterMapper;
import mate.academy.rickandmorty.models.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterServiceImpl implements CharacterService {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final Random random;
    private final RestTemplate restTemplate;
    private final CharacterRepository characterRepository;
    private final CharacterMapper rickMortyMapper;

    @Autowired
    public CharacterServiceImpl(RestTemplate restTemplate,
                                CharacterRepository characterRepository,
                                CharacterMapper rickMortyMapper) {
        this.restTemplate = restTemplate;
        this.characterRepository = characterRepository;
        this.rickMortyMapper = rickMortyMapper;
        random = new Random();
    }

    @PostConstruct
    private void downloadToDB() {
        CharactersDtoResponse charactersDtoResponse = restTemplate.getForObject(CHARACTER_URL,
                CharactersDtoResponse.class);
        assert charactersDtoResponse != null;
        characterRepository.saveAll(charactersDtoResponse.getResults()
                .stream()
                .map(rickMortyMapper::toModel)
                .collect(Collectors.toList()));
    }

    public List<Character> getAllCharactersByName(String name) {
        return characterRepository.findRickMortiesByNameContainingIgnoreCase(name);
    }

    public Character getRandomCharacter() {
        Long maxId = characterRepository.getMaxId();
        return characterRepository.findRickMortyById(random.nextLong(1, maxId));
    }
}
