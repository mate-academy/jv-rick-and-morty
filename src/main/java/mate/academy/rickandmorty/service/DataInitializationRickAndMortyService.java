package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitializationRickAndMortyService {
    private final RickAndMortyApiClient apiClient;
    private final RickAndMortyMapper rickAndMortyMapper;
    private final RickAndMortyRepository apiRepository;

    @PostConstruct
    public void initializeDbData() {
        apiRepository.saveAll(
                apiClient.getAllCharacters().stream()
                .map(rickAndMortyMapper::toModel)
                .toList());
    }
}
