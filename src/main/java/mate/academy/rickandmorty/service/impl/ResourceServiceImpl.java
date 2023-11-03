package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.ResourceService;
import mate.academy.rickandmorty.service.UrlService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// R - resource data page, C - the class with the result data , S - search parameters class
public class ResourceServiceImpl<R, C, S> implements ResourceService<R, C, S> {
    private static final String PAGE_PATTERN = "?page=%s";
    private static final String ID_PATTERN = "/%s";
    private static final String QUESTION_MARK_SEPARATOR = "?";
    private static final String EQUAL_SIGN_SEPARATOR = "=";
    private static final String COMMA_SEPARATOR = ",";
    private static final String AMPERSAND_SEPARATOR = "&";
    private final UrlService urlServices;
    private final ObjectMapper objectMapper;

    @Override
    public R getPageFromPageNumber(String resourceName, Class<R> className, int pageNumber) {
        String resourcesUrl = urlServices.getResourcesUrl(resourceName);
        String urlWithPage = resourcesUrl.concat(PAGE_PATTERN).formatted(pageNumber);
        String url = pageNumber >= 0 && pageNumber <= 1 ? resourcesUrl : urlWithPage;
        return fetchDataFromUrl(url, className);
    }

    @Override
    public C getDataFromId(String resourceName, Class<C> className, int id) {
        String resourcesUrl = urlServices.getResourcesUrl(resourceName);
        String urlWithCharacterId = resourcesUrl.concat(ID_PATTERN).formatted(id);
        return fetchDataFromUrl(urlWithCharacterId, className);
    }

    @Override
    public R getDataFromSearchParam(S searchParam, Class<R> className, String resourceName) {
        StringBuilder url = new StringBuilder(urlServices.getResourcesUrl(resourceName));
        String parsedUrl = parseSearchParamsAndGetUrl(searchParam, url);
        return fetchDataFromUrl(parsedUrl, className);
    }

    @Override
    public R getPageFromUrl(String url, Class<R> className) {
        return fetchDataFromUrl(url, className);
    }

    private <T> T fetchDataFromUrl(String url, Class<T> className) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(getHttpRequest(url),
                    HttpResponse.BodyHandlers.ofString()
            );
            return objectMapper.readValue(response.body(), className);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("An error occurred while fetching data from URL", e);
        }
    }

    private HttpRequest getHttpRequest(String url) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
    }

    private String parseSearchParamsAndGetUrl(S searchParam, StringBuilder url) {
        try {
            int count = 0;

            for (Field field : searchParam.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();

                if (field.getType().isArray() && field.get(searchParam) != null) {
                    if (count < 1) {
                        url.append(QUESTION_MARK_SEPARATOR)
                                .append(fieldName)
                                .append(EQUAL_SIGN_SEPARATOR);
                        count++;
                    } else {
                        url.append(AMPERSAND_SEPARATOR)
                                .append(fieldName)
                                .append(EQUAL_SIGN_SEPARATOR);
                    }
                    url.append(String.join(COMMA_SEPARATOR, ((String[]) field.get(searchParam))));
                }
            }
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(
                    "Error parsing search parameters and generating URL", e
            );
        }

        return url.toString();
    }
}
