package mate.academy.rickandmorty.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterApiResponseDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupDataLoader {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void initData() {
        if (characterRepository.count() > 0) {
            return;
        }
        String url = "https://rickandmortyapi.com/api/character";
        List<ExternalCharacterDto> allCharacters = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("test1");
        ObjectMapper objectMapper = new ObjectMapper();
        while (url != null) {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(
                        request, HttpResponse.BodyHandlers.ofString()
                                                           );
                CharacterApiResponseDto apiResponse = objectMapper.readValue(
                        response.body(), CharacterApiResponseDto.class
                                                                            );
                allCharacters.addAll(apiResponse.getResults());
                url = apiResponse.getInfo().getNext();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to fetch data from Rick and Morty API", e);
            }
        }
        characterRepository.saveAll(characterMapper.toEntityList(allCharacters));
        System.out.println("test2");
    }
}
