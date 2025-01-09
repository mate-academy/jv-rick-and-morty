package mate.academy.rickandmorty.service.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CartoonCharacterInputDataDto;
import mate.academy.rickandmorty.dto.internal.CartoonCharacterCreateRequestDto;
import mate.academy.rickandmorty.mapper.CartoonCharacterMapper;
import mate.academy.rickandmorty.service.internal.CartoonCharacterService;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RickAndMortyExternalApiClientImpl implements RickAndMortyExternalApiClient {
    private static final int NUMBER_OF_CHARACTERS = 826;
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private final CartoonCharacterService cartoonCharacterService;
    private final CartoonCharacterMapper cartoonCharacterMapper;
    private final ObjectMapper mapper;
    private boolean ifDataWasUploaded = false;

    @Override
    public CartoonCharacterCreateRequestDto uploadCartoonCharacterById(Long characterId) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = BASE_URL + characterId;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return cartoonCharacterMapper.toModel(
                    mapper.readValue(response.body(), CartoonCharacterInputDataDto.class));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadDataBase() {
        if (ifDataWasUploaded) {
            return;
        }

        for (long i = 1; i <= NUMBER_OF_CHARACTERS; i++) {
            cartoonCharacterService.save(uploadCartoonCharacterById(i));
        }
        ifDataWasUploaded = true;
    }
}
