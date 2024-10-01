package mate.academy.rickandmorty.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.Getter;
import lombok.Setter;
import mate.academy.rickandmorty.exception.ResponseParsingException;
import mate.academy.rickandmorty.exception.SendRequestException;
import org.springframework.stereotype.Component;

@Component
public class Client<T> {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    @Setter
    @Getter
    private String url;
    private final ObjectMapper objectMapper;

    public Client(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                false
        );
    }

    public T getDataFromApi(Class<T> clazz) {
        HttpRequest httpRequest = buildRequest();
        HttpResponse<String> response = sendRequest(httpRequest);
        return responseBodyToObject(response, clazz);
    }

    private HttpRequest buildRequest() {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(this.url))
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest httpRequest) {
        try {
            return httpClient
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new SendRequestException("Cannot send request to the URL: "
                    + httpRequest.uri(), e);
        }
    }

    private T responseBodyToObject(HttpResponse<String> response, Class<T> clazz) {
        try {
            return objectMapper.readValue(response.body(), clazz);
        } catch (JsonProcessingException e) {
            throw new ResponseParsingException("Cannot parse the response body to the class: "
                    + clazz.getSimpleName(), e);
        }
    }
}
