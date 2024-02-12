package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharacterRequestDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.exception.StartApplicationException;
import mate.academy.rickandmorty.mapper.CharactersMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.FetchApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final Long PAGE_SIZE = 20L;
    private final CharacterRepository characterRepository;
    private final CharactersMapper charactersMapper;
    private final FetchApiService fetchApiService;
    private final Random random = new Random();

    @Value("${rick.api.url}")
    private String url;
    @Value("${rick.api.startPage}")
    private int startPage;
    @Value("${rick.api.endPage}")
    private int endPage;

    @PostConstruct
    @Override
    public void insertCharactersToDB() {
        if (startPage < 1 || endPage < 1 || startPage > endPage) {
            throw new StartApplicationException("Dont valid start or end pages in properties");
        }
        for (int i = startPage; i <= endPage; i++) {
            characterRepository.saveAll(fetchDataToModel(
                    fetchApiService.fetchDataFromApi(String.format(url, i))
            ));
        }
    }

    @Override
    public CharacterDto getRandom() {
        Long randomId = random.nextLong((endPage - startPage) * PAGE_SIZE);
        return charactersMapper.toDto(characterRepository.findById(randomId).orElseThrow(
                () -> new EntityNotFoundException("Cant find character with random id: "
                        + randomId)
        ));
    }

    @Override
    public List<CharacterDto> getListCharactersByName(String name) {
        return characterRepository.findCharacterByNameLikeIgnoreCase(name).stream()
                .map(charactersMapper::toDto)
                .toList();
    }

    private List<Character> fetchDataToModel(String responseBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode resultsInfoNode = rootNode.get("info");
            validPageCount(resultsInfoNode);
            JsonNode resultsNode = rootNode.get("results");
            List<CharacterRequestDto> characterRequestDtoList = objectMapper
                    .convertValue(resultsNode, new TypeReference<>() {});
            return characterRequestDtoList.stream()
                    .map(charactersMapper::toModel)
                    .toList();
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
