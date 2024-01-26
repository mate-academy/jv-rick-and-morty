package mate.academy.rickandmorty.service.http.impl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import mate.academy.rickandmorty.service.http.HttpResponseService;
import org.springframework.stereotype.Service;

@Service
public class HttpResponseStringService implements HttpResponseService {
    public HttpResponse<String> getHttpResponse(String url)
            throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
