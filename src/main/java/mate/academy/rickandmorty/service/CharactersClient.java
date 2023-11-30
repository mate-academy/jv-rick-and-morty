package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDtoResult;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersClient {
    private static String EXTERNAL_DATABASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public void loadCharactersFromExternalApi() {
        List<ExternalCharacterDtoResult> characters = new ArrayList<>();
        while (EXTERNAL_DATABASE_URL != null) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(EXTERNAL_DATABASE_URL))
                    .build();
            HttpResponse<String> response;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                ExternalCharacterDto externalCharacterDto = objectMapper.readValue(response.body(),
                        ExternalCharacterDto.class);
                characters.addAll(externalCharacterDto.getResults());
                EXTERNAL_DATABASE_URL = externalCharacterDto.getInfo().next();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error while getting all characters "
                        + "from external database");
            }
        }
        characters.stream()
                .map(characterMapper::toInternalDto)
                .map(characterMapper::toModel)
                .forEach(characterService::save);
    }
}
