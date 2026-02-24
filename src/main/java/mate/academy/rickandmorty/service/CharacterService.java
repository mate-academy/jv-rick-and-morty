package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterService {

    private final CharacterRepository repository;
    private final RestTemplate restTemplate;

    public CharacterService(CharacterRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    @EventListener(ContextRefreshedEvent.class)
    public void syncCharacters() {
        String url = "https://rickandmortyapi.com/api/character";

        ApiResponseDto response = restTemplate.getForObject(url, ApiResponseDto.class);

        if (response != null && response.getResults() != null) {
            List<CharacterEntity> entities = response.getResults().stream()
                    .map(dto -> {
                        CharacterEntity entity = new CharacterEntity();
                        entity.setExternalId(dto.getId());
                        entity.setName(dto.getName());
                        entity.setStatus(dto.getStatus());
                        entity.setGender(dto.getGender());
                        return entity;
                    })
                    .collect(Collectors.toList());
            repository.saveAll(entities);
            System.out.println("Дані успішно завантажені! Кількість: " + entities.size());
        }
    }
}
