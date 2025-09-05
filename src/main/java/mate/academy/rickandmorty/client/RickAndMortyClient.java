package mate.academy.rickandmorty.client;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final RestTemplate restTemplate;

    public RickAndMortyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CharacterResponse> fetchAllCharacters() {
        List<CharacterResponse> results = new ArrayList<>();
        String url = BASE_URL;
        while (url != null) {
            PageResponse page = restTemplate.getForObject(url, PageResponse.class);
            if (page == null || page.getResults() == null) {
                break;
            }
            results.addAll(page.getResults());
            url = page.getInfo() != null ? page.getInfo().getNext() : null;
        }
        return results;
    }

    @Data
    public static class PageResponse {
        private Info info;
        private List<CharacterResponse> results;
    }

    @Data
    public static class Info {
        private Integer count;
        private Integer pages;
        private String next;
        private String prev;
    }

    @Data
    public static class CharacterResponse {
        private String id;
        private String name;
        private String status;
        private String gender;
    }
}
