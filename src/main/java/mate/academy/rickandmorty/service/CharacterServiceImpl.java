package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiResponse;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();

    @PostConstruct
    public void init() {
        String url = API_URL;
        while (url != null) {
            ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
            if (response != null) {
                List<CharacterEntity> characters = getCharacters(response);
                characterRepository.saveAll(characters);
                characterRepository.flush();
                url = response.getInfo().getNext();
            } else {
                url = null;
            }
        }
    }

    @Override
    public CharacterEntity getRandomCharacter() {
        long count = characterRepository.count();
        int index = random.nextInt((int) count);
        return characterRepository.findAll().get(index);
    }

    @Override
    public List<CharacterEntity> search(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }

    private List<CharacterEntity> getCharacters(ApiResponse response) {
        List<CharacterEntity> characters = new ArrayList<>();
        for (CharacterDto characterDto : response.getResults()) {
            if (!characterRepository.existsByExternalId(String.valueOf(characterDto.getId()))) {
                CharacterEntity character = new CharacterEntity();
                character.setExternalId(String.valueOf(characterDto.getId()));
                character.setName(characterDto.getName());
                character.setStatus(characterDto.getStatus());
                character.setGender(characterDto.getGender());
                characters.add(character);
            }
        }
        return characters;
    }
}
