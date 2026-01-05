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
import mate.academy.rickandmorty.dto.external.ApiCharacterDto;
import mate.academy.rickandmorty.dto.internal.ResponseCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.CharacterEntity;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppService {

    @Value("${api.character.url}")
    private String baseUrl;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final Integer maxIdCharacter = 826;
    private final Random random = new Random();

    /**
     * @return Random Character from database
     */
    public ResponseCharacterDto getRandomCharacter() {

        Long randomId = random.nextLong(1, maxIdCharacter + 1);

        return characterRepository
                .findByExternalId(randomId)
                .map(characterMapper::mapToResponseDto)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Character with externalId=" + randomId + " not found in DB"
                        )
                );
    }

    public List<ResponseCharacterDto> searchCharactersByNameContaining(String name) {
        List<CharacterEntity> byNameContainingIgnoreCase = characterRepository
                .findByNameContainingIgnoreCase(name);

        return byNameContainingIgnoreCase.stream()
                .map(characterMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Downloads all characters from the API if they do not already exist in the database.
     * <p>
     * If the database already contains characters, no fetch is performed.
     * @return the number of characters saved to the repository after fetching,
     *         or 0 if no fetch was necessary.
     */
    public int downloadAllCharactersFromApi() {

        if (isDataAlreadyFetchedInDb()) {
            System.out.println("📦 Data already exists in DB. ✅ API ready to use");
            return 0;
        }

        System.out.println("🚀 Starting characters download...");

        List<CharacterEntity> entities = new ArrayList<>();

        for (long id = 1; id <= maxIdCharacter; id++) {

            ApiCharacterDto dto = getCharacterFromApi(id);
            CharacterEntity entity = characterMapper.mapToEntity(dto);
            entities.add(entity);

            if (id % 50 == 0) {
                System.out.println("⏳ Loaded " + id + " / " + maxIdCharacter);
            }
        }

        characterRepository.saveAll(entities);

        System.out.println("✅ Download finished. Total saved: " + entities.size());

        return entities.size();
    }

    private ApiCharacterDto getCharacterFromApi(Long id) {
        String url = baseUrl.formatted(id);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            ApiCharacterDto responseCharacterDto = objectMapper
                    .readValue(response.body(), ApiCharacterDto.class);

            return responseCharacterDto;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //method that checks if Data already fetched
    private Boolean isDataAlreadyFetchedInDb() {
        return characterRepository.count() > 0;
    }

}
