package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterGetDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ApiClient apiClient;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void loadDataToDb() {
        List<CharacterGetDto> charactersFromApi = apiClient.getCharacters();
    }
}
