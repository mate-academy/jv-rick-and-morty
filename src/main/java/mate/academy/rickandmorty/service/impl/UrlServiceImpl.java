package mate.academy.rickandmorty.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Pattern;
import mate.academy.rickandmorty.exception.UrlNotFoundException;
import mate.academy.rickandmorty.service.UrlService;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String URL_PATTERN = "https://[a-zA-Z0-9.-]+/[^/]+/[^/]+";
    private static final String COLON_SEPARATOR = ":";
    private static final String COMMA_SEPARATOR = ",";

    @Override
    public String getResourcesUrl(String resourcesName) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );
            return parseResponseAndGetUrl(response, resourcesName);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String parseResponseAndGetUrl(HttpResponse<String> response, String resourcesName) {
        return Arrays.stream(response.body().split(COMMA_SEPARATOR))
                .filter(value -> value.contains(resourcesName))
                .map(str -> {
                    int colonIndex = str.indexOf(COLON_SEPARATOR);

                    if (colonIndex != -1) {
                        String url = str.substring(colonIndex + 2, str.length() - 1);

                        if (Pattern.compile(URL_PATTERN).matcher(url).matches()) {
                            return url;
                        } else {
                            throw new UrlNotFoundException(
                                    "URL format doesn't match the expected pattern"
                            );
                        }
                    }
                    throw new RuntimeException("Couldn't find a colon separator");
                })
                .findFirst()
                .orElseThrow(() -> new UrlNotFoundException("Couldn't find the correct URL"));
    }
}
