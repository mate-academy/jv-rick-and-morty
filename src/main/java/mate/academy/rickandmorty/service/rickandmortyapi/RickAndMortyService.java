package mate.academy.rickandmorty.service.rickandmortyapi;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterResultsDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyService {
    private static final int DEFAULT_NUMBER_PAGE = 1;

    private final RickAndMortyClient rickAndMortyClient;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Value("${base.url}")
    private String baseUrl;

    private final List<CharacterResultsDataDto> allResults = new ArrayList<>();

    public void saveAllCharacters() {
        int page = DEFAULT_NUMBER_PAGE;
        List<CharacterResultsDataDto> results;

        do {
            String url = String.format(baseUrl, page);
            results = rickAndMortyClient.getAllCharactersFromApi(url);

            if (results != null && !results.isEmpty()) {
                allResults.addAll(results);
                page++;
            }
        } while (results != null && !results.isEmpty());

        characterRepository.saveAll(characterMapper.toModelList(allResults));
    }
}
