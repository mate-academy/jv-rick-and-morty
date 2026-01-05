package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.AllCharactersResponseDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppService {

    private static final Logger log = LoggerFactory.getLogger(AppService.class);
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Random random = new Random();
    private final HttpClient client = HttpClient.newHttpClient();
    @Value("${api.character.url}")
    private String baseUrl;
    @Value("${api.character.url.all-characters}")
    private String urlAllCharacters;
    private Integer maxIdCharacter = null;

    /**
     * @return Random Character from database
     */
    public ResponseCharacterDto getRandomCharacter() {
        int maxId = getLastIdCharacter();

        Long randomId = random.nextLong(1, maxId + 1);

        return characterRepository.findByExternalId(randomId)
                .map(characterMapper::mapToResponseDto)
                .orElseThrow(() -> new IllegalStateException(
                        "Character with externalId=" + randomId + " not found in DB"));
    }

    public List<ResponseCharacterDto> searchCharactersByNameContaining(String name) {
        List<CharacterEntity> byNameContainingIgnoreCase = characterRepository
                .findByNameContainingIgnoreCase(name);

        return byNameContainingIgnoreCase
                .stream()
                .map(characterMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Downloads all characters from the API if they do not already exist in the database.
     * <p>
     * If the database already contains characters, no fetch is performed.
     *
     * @return the number of characters saved to the repository after fetching,
     * or 0 if no fetch was necessary.
     */
    public int fetchDataFromApi() {

        int lastPage = getLastPageFromApi();
        int page = 1;
        List<CharacterEntity> entities = new ArrayList<>();

        if (isDataAlreadyFetchedInDb()) {
            log.info("📦 Data already exists in DB. ✅ API ready to use");
            return 0;
        }

        log.info("🚀 Starting characters download...");

        while (page <= lastPage) {

            String u = String.format("/?page=%d", page);
            String url = baseUrl.formatted(u);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                AllCharactersResponseDto allCharactersResponseDto = objectMapper
                        .readValue(response.body(), AllCharactersResponseDto.class);

                List<CharacterEntity> list = allCharactersResponseDto
                        .getResults().stream().map(characterMapper::mapToEntity).toList();

                entities.addAll(list);
                page++;

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        characterRepository.saveAll(entities);

        log.info("✅ Download finished. Total saved: {}", entities.size());

        return 0;
    }

    /**
     * Returns the maximum character ID fetched from the external API.
     * <p>
     * The value is cached in memory after the first API call, so subsequent
     * calls will return the cached value without making additional HTTP requests.
     *
     * @return the maximum character ID
     */
    private Integer getLastIdCharacter() {
        if (maxIdCharacter == null) { // якщо ще не заповнене
            maxIdCharacter = getLastIdCharacterFromApi();
        }
        return maxIdCharacter;
    }

    /**
     * @return Last id page from api
     */
    private Integer getLastPageFromApi() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(urlAllCharacters))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            AllCharactersResponseDto allCharactersResponseDto = objectMapper
                    .readValue(response.body(), AllCharactersResponseDto.class);

            return allCharactersResponseDto.getInfo().getPages();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Last id Character from api
     */
    private Integer getLastIdCharacterFromApi() {
        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(urlAllCharacters))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            AllCharactersResponseDto allCharactersResponseDto = objectMapper
                    .readValue(response.body(), AllCharactersResponseDto.class);

            return allCharactersResponseDto.getInfo().getCount();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //method that checks if Data already fetched
    private Boolean isDataAlreadyFetchedInDb() {
        return characterRepository.count() > 0;
    }
}
