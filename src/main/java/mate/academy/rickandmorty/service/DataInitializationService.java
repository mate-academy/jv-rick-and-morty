package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataInitializationService {
    private final RickAndMortyApiClient apiClient;
    private final RickAndMortyMapper rickAndMortyMapper;
    private final RickAndMortyRepository apiRepository;

    public void initializeDbData() {
        apiRepository.saveAll(
                apiClient.fetchAllData().stream()
                .map(rickAndMortyMapper::toModel)
                .toList());
    }
}
