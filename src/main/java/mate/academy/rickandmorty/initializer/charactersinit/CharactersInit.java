package mate.academy.rickandmorty.initializer.charactersinit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.exception.StartApplicationException;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.FetchApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersInit {
    private final CharacterService characterService;
    private final FetchApiService fetchApiService;
    @Value("${rick.api.url}")
    private String url;
    @Value("${rick.api.startPage}")
    private int startPage;
    @Value("${rick.api.endPage}")
    private int endPage;

    public void init() {
        if (startPage < 1 || endPage < 1 || startPage > endPage) {
            throw new StartApplicationException("Dont valid start or end pages in properties");
        }
        for (int i = startPage; i <= endPage; i++) {
            characterService.saveAll(fetchDataToRequestDto(
                    fetchApiService.fetchDataFromApi(String.format(url, i))
            ));
        }
    }

    private List<CharacterRequestDto> fetchDataToRequestDto(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode resultsInfoNode = rootNode.get("info");
            validPageCount(resultsInfoNode);
            JsonNode resultsNode = rootNode.get("results");
            return objectMapper.convertValue(resultsNode, new TypeReference<>() {});

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cant convert response body to characters", e);
        }
    }

    private void validPageCount(JsonNode resultsInfoNode) {
        int pagesCount = Integer.parseInt(String.valueOf(resultsInfoNode.get("pages")));
        if (pagesCount < endPage) {
            throw new StartApplicationException("Count of pages less then end page number");
        }
    }
}
