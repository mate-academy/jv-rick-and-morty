package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
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
    public static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @PostConstruct
    public void init() {
        ApiResponse response = restTemplate.getForObject(API_URL, ApiResponse.class);
        if (response != null) {
            for (CharacterDto dto : response.getResults()) {
                CharacterEntity characterEntity = new CharacterEntity();
                characterEntity.setExternalId(dto.getId().toString());
                characterEntity.setName(dto.getName());
                characterEntity.setStatus(dto.getStatus());
                characterEntity.setGender(dto.getGender());
                characterRepository.save(characterEntity);
            }
        }
    }

    @Override
    public CharacterEntity getRandomCharacter() {
        List<CharacterEntity> characters = characterRepository.findAll();
        return characters.get(new Random().nextInt(characters.size()));
    }

    @Override
    public List<CharacterEntity> search(String name) {
        return characterRepository.findByNameContaining(name);
    }

    @Override
    public List<CharacterEntity> getCharacters() {
        return characterRepository.findAll();
    }
}
