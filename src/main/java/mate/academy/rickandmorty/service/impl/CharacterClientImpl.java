package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDataDto;
import mate.academy.rickandmorty.exception.InitializedDatabaseException;
import mate.academy.rickandmorty.service.CharacterClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CharacterClientImpl implements CharacterClient {

    private static final String GET_ALL_CHARS_URL = "https://rickandmortyapi.com/api/character";

    private static final Logger logger = LoggerFactory.getLogger(CharacterClientImpl.class);

    private final ObjectMapper mapper;

    public CharacterClientImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<CharacterResponseDataDto> initializeDatabase() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        HttpResponse<String> response;

        try {
            List<CharacterResponseDataDto> responseDataDtoTotal = new ArrayList<>();
            CharacterResponseDataDto characterResponseDataDto;
            String nextPage = GET_ALL_CHARS_URL;
            do {
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(nextPage))
                        .build();
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
                characterResponseDataDto = mapper.readValue(response.body(),
                        CharacterResponseDataDto.class);
                responseDataDtoTotal.add(characterResponseDataDto);
            } while ((nextPage = characterResponseDataDto.info().next()) != null);

            return responseDataDtoTotal;
        } catch (IOException e) {
            logger.error("IOException occurred while initializing database: {}", e.getMessage(), e);
            throw new InitializedDatabaseException("Failed to initialize database"
                    + " due to I/O error.", e);
        } catch (InterruptedException e) {
            logger.error("Request interrupted while initializing database: {}", e.getMessage(), e);
            Thread.currentThread().interrupt();
            throw new InitializedDatabaseException("Database initialization "
                    + "was interrupted.", e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while "
                    + "initializing database: {}", e.getMessage(), e);
            throw new InitializedDatabaseException("An unexpected error occurred "
                    + "while initializing the database.", e);
        }
    }
}
