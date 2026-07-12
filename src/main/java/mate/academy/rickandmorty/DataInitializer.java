package mate.academy.rickandmorty;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.model.response.RickAndMortyResponse;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private static final String API_URL = "https://rickandmortyapi.com/api/character?page=";
    private final CharacterRepository repository;
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            log.info("Baza jest pusta. Pobieranie danych z zewnętrznego API...");
            RickAndMortyResponse firstPage = restTemplate.getForObject(
                    API_URL + "1", RickAndMortyResponse.class);
            if (firstPage != null) {
                savePage(firstPage);
                int totalPages = firstPage.getInfo().getPages();
                for (int i = 2; i <= totalPages; i++) {
                    RickAndMortyResponse page = restTemplate.getForObject(
                            API_URL + i, RickAndMortyResponse.class);
                    if (page != null) {
                        savePage(page);
                    }
                }
                log.info("Pobieranie zakończone pomyślnie.");
            }
        }
    }

    private void savePage(RickAndMortyResponse response) {
        List<RickAndMortyCharacter> rickAndMortyCharacters = response.getResults().stream().map(
                res -> {
                    RickAndMortyCharacter c = new RickAndMortyCharacter();
                    c.setExternalId(res.getId());
                    c.setName(res.getName());
                    c.setStatus(res.getStatus());
                    c.setGender(res.getGender());
                    return c;
                }).collect(Collectors.toList());

        repository.saveAll(rickAndMortyCharacters);
    }
}
