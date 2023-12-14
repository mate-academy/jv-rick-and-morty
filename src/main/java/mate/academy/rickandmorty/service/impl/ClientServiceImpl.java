package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mate.academy.rickandmorty.dto.CreateCharacterRequestDto;
import mate.academy.rickandmorty.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private static final String URL = "https://rickandmortyapi.com/api/character?page=%s";
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CreateCharacterRequestDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();

        List<CreateCharacterRequestDto> dtosList = new ArrayList<>();
        for (int i = 0; true; i++) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(String.format(URL, i)))
                    .build();
            try {
                HttpResponse<String> response =
                        httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String results =
                        objectMapper.readTree(response.body()).get("results").toString();
                CreateCharacterRequestDto[] createCharacterRequestDtos =
                        objectMapper.readValue(results, CreateCharacterRequestDto[].class);
                dtosList.addAll(Arrays.asList(createCharacterRequestDtos));
            } catch (NullPointerException e) {
                break;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return dtosList;
    }
}
