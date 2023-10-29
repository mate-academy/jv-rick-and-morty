package mate.academy.rickandmorty.service.implementation;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.CharacterDtoResponse;
import mate.academy.rickandmorty.mappers.RickMortyMapper;
import mate.academy.rickandmorty.models.RickMorty;
import mate.academy.rickandmorty.repository.RickMortyRepository;
import mate.academy.rickandmorty.service.RickMortyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RickMortyServiceImpl implements RickMortyService {
    private static final String characterURL = "https://rickandmortyapi.com/api/character";
    private static boolean isInitialized = false;
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
        downloadToDB();
    }

    private void downloadToDB() {
        if (!isInitialized) {
            CharacterDtoResponse characterDtoResponse = restTemplate.getForObject(characterURL,
                    CharacterDtoResponse.class);
            assert characterDtoResponse != null;
            rickMortyRepository.saveAll(characterDtoResponse.getResults()
                    .stream()
                    .map(rickMortyMapper::toModel)
                    .collect(Collectors.toList()));
        }
        isInitialized = true;
    }

    public List<RickMorty> getAllCharactersByName(String name) {
        return rickMortyRepository.findRickMortiesByNameContainingIgnoreCase(name);
    }

    public RickMorty getRandomCharacter() {
        Random random = new Random();
        Long maxId = rickMortyRepository.getMaxId();
        return rickMortyRepository.findRickMortyById(random.nextLong(1, maxId));
    }
}
