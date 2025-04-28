package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExtCharacterRecordDto;
import mate.academy.rickandmorty.dto.external.ExtRnMCharacterDto;
import mate.academy.rickandmorty.dto.internal.RnMCharacterDto;
import mate.academy.rickandmorty.dto.search.CharacterSearchParameters;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RnMCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.spec.RnMSpecBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static final String RnM_BASE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final RnMSpecBuilder rnMSpecBuilder;

    @Override
    public RnMCharacterDto getRandomCharacter() {
        return mapper.toDto(repository.findFirst());
    }

    @Override
    public Page<RnMCharacterDto> search(CharacterSearchParameters requestParams,
                                        Pageable pageable) {
        return repository.findAll(rnMSpecBuilder
                .buildSpec(requestParams), pageable)
                .map(mapper::toDto);
    }

    @Override
    public RnMCharacter save(RnMCharacter character) {
        return repository.save(character);
    }

    @Override
    public RnMCharacter save(ExtCharacterRecordDto character) {
        return repository.save(mapper.toEntity(character));
    }

    @Override
    public String initDb() {
        if (repository.count() != 0) {
            repository.deleteAll();
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            ExtRnMCharacterDto extRnMCharacterDto;
            ObjectMapper mapper = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String currentPageUrl = RnM_BASE_URL + "?page=1";
            do {
                HttpRequest request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(currentPageUrl))
                        .build();
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                extRnMCharacterDto = mapper.readValue(response.body(),
                        ExtRnMCharacterDto.class);
                extRnMCharacterDto.getResults().forEach(this::save);
                currentPageUrl = extRnMCharacterDto.getInfo().getNextPageUrl();
            } while (extRnMCharacterDto.getInfo().getNextPageUrl() != null);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Database table 'characters' initialized";
    }
}
