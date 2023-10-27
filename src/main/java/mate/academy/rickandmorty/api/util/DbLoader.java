package mate.academy.rickandmorty.api.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.repository.PersonageRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbLoader {
    private final RickAndMortyApiClient rickAndMortyApiClient;
    private final PersonageRepository personageRepository;

    @PostConstruct
    public void init() {
        if (personageRepository.count() == 0) {
            personageRepository.saveAll(rickAndMortyApiClient.getAllPersonages());
        }
    }

}
