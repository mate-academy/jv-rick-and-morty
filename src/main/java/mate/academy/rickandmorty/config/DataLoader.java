package mate.academy.rickandmorty.config;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.rickandmorty.model.RickCharacter;
import mate.academy.rickandmorty.repository.RickCharacterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataLoader {
    private static final String API_URL = "https://rickandmortyapi.com/api/character";

    @Bean
    public CommandLineRunner initDatabase(RickCharacterRepository repository,
                                          RestTemplate restTemplate) {
        return args -> {
            repository.deleteAll();
            String url = API_URL;
            do {
                CharacterResponse response = restTemplate.getForObject(url,
                        CharacterResponse.class);
                if (response != null) {
                    repository.saveAll(response.results.stream()
                            .map(apiChar -> new RickCharacter(
                                    null,
                                    String.valueOf(apiChar.id()),
                                    apiChar.name(),
                                    apiChar.status(),
                                    apiChar.gender()))
                            .collect(Collectors.toList()));
                    url = response.info().next();
                } else {
                    url = null;
                }
            } while (url != null);
        };
    }

    private record CharacterResponse(Info info, List<ExternalCharacter> results) {}

    private record Info(String next) {}

    private record ExternalCharacter(int id, String name, String status, String gender) {}
}
