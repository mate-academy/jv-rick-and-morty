package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final ApiService apiService;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void loadDataToDb() {
        characterRepository.saveAll(apiService.fetchDataFromApi());
    }
}
