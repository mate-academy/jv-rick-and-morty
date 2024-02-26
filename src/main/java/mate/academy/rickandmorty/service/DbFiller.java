package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.imp.CharacterHttpClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbFiller {
    private static final String URL = "https://rickandmortyapi.com/api/character?page=1";
    private final CharacterMapper characterMapper;
    private final CharacterHttpClient rickAndMortyClient;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void loadDataToDb() {
        String nextUrl = URL;
        while (nextUrl != null) {
            ApiResponseDto apiResponse = rickAndMortyClient.get(nextUrl, ApiResponseDto.class);
            save(apiResponse);
            nextUrl = apiResponse.info().next();
        }
    }

    private void save(ApiResponseDto apiResponse) {
        List<Character> characterList = apiResponse.data().stream()
                .map(characterMapper::toEntity)
                .toList();
        characterRepository.saveAll(characterList);
    }
}
