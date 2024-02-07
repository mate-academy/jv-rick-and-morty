package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.PageResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private static final String URL_PAGE_PARAM = "?page=";
    private final ApiService apiService;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @PostConstruct
    public void loadDataToDb() {
        int pageNumber = 1;
        while (true) {
            String url = API_URL + URL_PAGE_PARAM + pageNumber;
            PageResponseDto pageResponseDto = apiService
                    .fetchDataFromApi(url);
            List<Character> characters = pageResponseDto.getResults()
                    .stream()
                    .map(characterMapper::toModel)
                    .toList();
            characterRepository.saveAll(characters);
            pageNumber++;
            if (pageResponseDto.getInfo().getNext() == null) {
                break;
            }
        }
    }
}
