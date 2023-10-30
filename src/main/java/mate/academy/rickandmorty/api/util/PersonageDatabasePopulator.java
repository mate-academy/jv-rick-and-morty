package mate.academy.rickandmorty.api.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.repository.PersonageRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonageDatabasePopulator {
    private final RickAndMortyClient rickAndMortyApiClient;
    private final PersonageRepository personageRepository;

    @PostConstruct
    public void init() {
        if (personageRepository.count() != 0) {
            personageRepository.deleteAll();
        }
        personageRepository.saveAll(rickAndMortyApiClient.getAllPersonages());
    }

}
