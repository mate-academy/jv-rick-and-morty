package mate.academy.rickandmorty.service.implementation;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharactersDtoResponse;
import mate.academy.rickandmorty.mappers.RickMortyMapper;
import mate.academy.rickandmorty.models.Character;
import mate.academy.rickandmorty.repository.RickMortyRepository;
import mate.academy.rickandmorty.service.RickMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickMortyServiceImpl implements RickMortyService {
    private static final String CHARACTER_URL = "https://rickandmortyapi.com/api/character";
    private final Random random;
    private final RestTemplate restTemplate;
    private final RickMortyRepository rickMortyRepository;
    private final RickMortyMapper rickMortyMapper;

    @Autowired
    public RickMortyServiceImpl(RestTemplate restTemplate,
                                RickMortyRepository rickMortyRepository,
                                RickMortyMapper rickMortyMapper) {
        this.restTemplate = restTemplate;
        this.rickMortyRepository = rickMortyRepository;
        this.rickMortyMapper = rickMortyMapper;
        random = new Random();
    }

    @PostConstruct
    private void downloadToDB() {
        CharactersDtoResponse charactersDtoResponse = restTemplate.getForObject(CHARACTER_URL,
                CharactersDtoResponse.class);
        assert charactersDtoResponse != null;
        rickMortyRepository.saveAll(charactersDtoResponse.getResults()
                .stream()
                .map(rickMortyMapper::toModel)
                .collect(Collectors.toList()));
    }

    public List<Character> getAllCharactersByName(String name) {
        return rickMortyRepository.findRickMortiesByNameContainingIgnoreCase(name);
    }

    public Character getRandomCharacter() {
        Long maxId = rickMortyRepository.getMaxId();
        return rickMortyRepository.findRickMortyById(random.nextLong(1, maxId));
    }
}
