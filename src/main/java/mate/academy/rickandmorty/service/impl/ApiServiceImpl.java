package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.client.ApiClient;
import mate.academy.rickandmorty.dto.api.ApiCharacterDto;
import mate.academy.rickandmorty.dto.api.ApiResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.ApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiServiceImpl implements ApiService {
    private final CharacterRepository repository;
    private final CharacterMapper mapper;
    private final ApiClient api;
    @Value("${api.url}")
    private String apiUrl;

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public void getAllCharacters() {
        String url = apiUrl;
        while (url != null) {
            try {
                ApiResponseDto response = api.fetchDataFromApi(url);
                saveCharacters(response.getResults());
                url = response.getInfo().getNext();
            } catch (Exception e) {
                throw new RuntimeException("Unable to get characters from API" + e.getMessage());
            }
        }
    }

    private void saveCharacters(List<ApiCharacterDto> characters) {
        repository.saveAll(characters.stream()
                                     .map(mapper::toModel)
                                     .toList()
        );
    }
}
