package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiResponse;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization {
    private final String url = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient;
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void loadDataToDb() {
        ApiResponse apiResponse = httpClient.get(
                url,
                ApiResponse.class
        );
        List<Character> characters = apiResponse.data()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterService.saveAll(characters);
    }
}
