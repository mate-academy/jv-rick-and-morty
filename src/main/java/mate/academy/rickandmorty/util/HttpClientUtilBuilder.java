package mate.academy.rickandmorty.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtilBuilder {
    private final HttpClient client = HttpClient.newHttpClient();
    private final HttpRequest.Builder request = HttpRequest.newBuilder();

    public HttpClientUtilBuilder setUrl(String url) {
        request.uri(URI.create(url));
        return this;
    }

    public HttpClientUtilBuilder httpGetRequest() {
        request.GET();
        return this;
    }

    public String getResponseBody() {
        HttpResponse<String> response = null;
        try {
            response = client.send(request.build(),
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Occurred api issue.", e);
        }
        return response.body();
    }
}
