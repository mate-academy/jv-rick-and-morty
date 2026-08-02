package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureResponseDataDto;
import mate.academy.rickandmorty.mapper.FigureMapper;
import mate.academy.rickandmorty.repository.FigureRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FigureClient implements ApplicationRunner {

    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/?page=%s";

    private final ObjectMapper objectMapper;
    private final FigureRepository figureRepository;
    private final FigureMapper mapper;

    public void getFiguresFromApi(Integer i) {
        HttpClient httpClient = HttpClient.newHttpClient();

        String url = BASE_URL.formatted(i.toString());

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            FigureResponseDataDto figureResponseDataDto =
                    objectMapper.readValue(response.body(), FigureResponseDataDto.class);
            figureRepository.saveAll(figureResponseDataDto.getResults()
                    .stream()
                    .map(mapper::toModel)
                    .toList());

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 1; i < 42; i++) {
            getFiguresFromApi(i);
        }

    }
}
