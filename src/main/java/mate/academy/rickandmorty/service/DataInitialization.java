package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization {
    @Value("${api.url}")
    private String url;
    private final HttpClient httpClient;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;

    @PostConstruct
    public void loadDataToDb() {
        ApiResponseDto apiResponseDto = httpClient.get(url, ApiResponseDto.class);
        List<Character> characters = apiResponseDto.getCharacterApiResponseDto()
                .stream()
                .map(characterMapper::toModel)
                .toList();
        characterRepository.saveAll(characters);
    }
}
