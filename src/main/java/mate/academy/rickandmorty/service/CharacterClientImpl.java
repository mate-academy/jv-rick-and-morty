package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.config.mapper.CharacterMapper;
import mate.academy.rickandmorty.dto.external.ApiResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.exception.ApiResponseParsingException;
import mate.academy.rickandmorty.model.CharacterRickAndMorty;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CharacterClientImpl implements CharacterClient {
    @Value("${rick-and-morty.url}")
    private String baseUrl;
    private final CharacterRepository characterRepository;
    private final CharacterMapper mapper;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @PostConstruct
    public void pullAllFromExternalApiToDb() {
        String nextPageUrl = baseUrl;
        while (nextPageUrl != null) {
            saveAllCharactersFromPage(nextPageUrl);
            ApiResponseDto responseDto = getApiResponseDto(nextPageUrl);
            nextPageUrl = responseDto.getInfo().getNext();
        }

    }

    private ApiResponseDto getApiResponseDto(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            return objectMapper.readValue(response.getBody(), ApiResponseDto.class);
        } catch (JsonProcessingException e) {
            throw new ApiResponseParsingException("Failed to parse API response", e);
        }
    }

    private void saveAllCharactersFromPage(String url) {
        ApiResponseDto apiResponseDto = getApiResponseDto(url);
        List<CharacterResponseDataDto> characterResponseDataDtos = apiResponseDto.getResults();
        List<CharacterRickAndMorty> characterRicksAndMorties = characterResponseDataDtos
                .stream()
                .map(mapper::toCharacterModel)
                .toList();

        characterRepository.saveAll(characterRicksAndMorties);
    }
}
