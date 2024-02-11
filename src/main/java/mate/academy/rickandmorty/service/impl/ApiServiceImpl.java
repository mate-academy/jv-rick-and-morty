package mate.academy.rickandmorty.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.InfoResponseDataDto;
import mate.academy.rickandmorty.service.ApiService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiServiceImpl implements ApiService {
    private final ObjectMapper objectMapper;
    @Override
    public List<CharacterResponseDto> fetchDataFromApi() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://rickandmortyapi.com/api/character"))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            InfoResponseDataDto infoResponseDataDto = objectMapper.readValue(response.body(),
                    InfoResponseDataDto.class);

            return infoResponseDataDto.getResults();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
