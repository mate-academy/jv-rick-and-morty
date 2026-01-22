package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import mate.academy.rickandmorty.dto.RickMortyResponse;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CharacterImportService {

    private final CharacterRepository repository;
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CharacterImportService(
            CharacterRepository repository,
            RestTemplate restTemplate,
            @Value("${rickmorty.api.url}") String apiUrl) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @PostConstruct
    public void init() {
        if (repository.count() > 0) {
            return;
        }

        int page = 1;
        int totalPages;

        do {
            RickMortyResponse response =
                    restTemplate.getForObject(
                            apiUrl + "?page=" + page,
                            RickMortyResponse.class
                    );

            totalPages = response.info().pages();

            response.results().forEach(dto -> {
                CharacterEntity entity = new CharacterEntity();
                entity.setExternalId(dto.id().toString());
                entity.setName(dto.name());
                entity.setStatus(dto.status());
                entity.setGender(dto.gender());
                repository.save(entity);
            });

            page++;
        } while (page <= totalPages);
    }
}
