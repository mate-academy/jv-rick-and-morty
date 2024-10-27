package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.ApiResponse;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterInitializer {
    @Value("${api.url}")
    private String apiUrl;
    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void init() {
        if(characterRepository.count() == 0) {
            initCharactersFromExternalApi(apiUrl);
        }
    }
    private void initCharactersFromExternalApi(String apiUrl) {
        HttpClient client = HttpClient.newHttpClient();
        List<CharacterDto> characterDtoList = new ArrayList<>();
        try {
            while (apiUrl != null) {
                HttpRequest getRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(apiUrl))
                        .build();
                HttpResponse<String> response = client.send(getRequest,
                        HttpResponse.BodyHandlers.ofString());
                ApiResponse apiResponse = objectMapper.readValue(
                        response.body(),
                        ApiResponse.class
                );
                characterDtoList.addAll(apiResponse.results());
                apiUrl = apiResponse.info().next();
            }
            List<Character> characters = characterMapper.toModelList(characterDtoList);
            characterRepository.saveAll(characters);
        } catch (IOException | InterruptedException e) {
            throw new EntityNotFoundException("Can't access results from external API", e);
        }
    }
}
