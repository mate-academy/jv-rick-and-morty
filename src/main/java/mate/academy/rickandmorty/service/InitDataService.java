package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.MovieCharacterRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitDataService {

    private final MovieCharacterRepository movieCharacterRepository;
    private final ExternalApiService externalApiService;
    @Value("${rick.and.morty.url}")
    private String url;

    public void saveDataIntoDB() {
        List<MovieCharacter> characters = externalApiService.getListOfMovieCharacter(url);
        movieCharacterRepository.saveAll(characters);
    }

    @PostConstruct
    private void init() {
        if (movieCharacterRepository.count() == 0) {
            saveDataIntoDB();
        }
    }
}
