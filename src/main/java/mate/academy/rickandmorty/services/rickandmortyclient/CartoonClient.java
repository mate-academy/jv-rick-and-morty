package mate.academy.rickandmorty.services.rickandmortyclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import mate.academy.rickandmorty.dto.external.RickAndMortyDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CartoonClient {

    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate = new RestTemplateBuilder().build();

    public CartoonClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T extends RickAndMortyDto> T fetch(String endpoint, Class<T> clazz) {
        try {
            ResponseEntity<String> responseEntity
                    = restTemplate.getForEntity(endpoint, String.class);
            return objectMapper.readValue(responseEntity.getBody(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
