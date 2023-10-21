package mate.academy.rickandmorty.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterEndpointResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterMetaInformationResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.specification.CharacterSpecification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private static final String URL = "https://rickandmortyapi.com/api/character/?page=%d";
    private final CharacterRepository characterRepository;
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;

    private Long charactersCount;
    private Integer pagesCount;
    private Random random = new Random();

    @PostConstruct
    public void saveCharactersToDB() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            initCounts(httpClient);
            for (int i = 1; i <= pagesCount; i++) {
                characterRepository.saveAll(getCharactersFromApi(httpClient, i).getResults()
                        .stream()
                        .map(characterMapper::toModel)
                        .toList());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get characters from API",e);
        }
    }

    @Override
    public CharacterDto getRandomCharacter() {
        Long randomId = random.nextLong(1L, charactersCount);
        return characterMapper.toDto(characterRepository.findById(randomId)
                .orElseThrow(() -> new RuntimeException("Can't get character with id: "
                        + randomId + " from API")));
    }

    @Override
    public List<CharacterDto> getCharactersContainName(String name) {
        return characterRepository.findAll(CharacterSpecification.nameContains(name))
                .stream()
                .map(characterMapper::toDto)
                .toList();
    }

    private void initCounts(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest httpRequest = getCharacterRequest(1);
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());
        CharacterMetaInformationResponseDto responseDto = objectMapper
                .readValue(httpResponse.body(), CharacterMetaInformationResponseDto.class);
        charactersCount = responseDto.getInfo().getCount();
        pagesCount = responseDto.getInfo().getPages();
    }

    private CharacterEndpointResponseDto getCharactersFromApi(HttpClient httpClient, int page)
            throws IOException, InterruptedException {
        HttpRequest httpRequest = getCharacterRequest(page);
        HttpResponse<String> httpResponse = httpClient.send(httpRequest,
                HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(httpResponse.body(), CharacterEndpointResponseDto.class);
    }

    private HttpRequest getCharacterRequest(int page) {
        return HttpRequest.newBuilder()
               .GET()
                .uri(URI.create(String.format(URL, page)))
                .build();
    }
}
