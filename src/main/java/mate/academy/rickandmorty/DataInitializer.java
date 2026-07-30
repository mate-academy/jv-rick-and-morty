package mate.academy.rickandmorty;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickMortyCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.RickMortyClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final RickMortyClient client;
    private final CharacterRepository repository;
    private final CharacterMapper mapper;

    @Override
    public void run(ApplicationArguments args) {
        if (repository.count() > 0) {
            return; // уже загружено, не дублируем при рестарте
        }
        
        System.out.println("DB is empty. Loading new entries...");
        List<RickMortyCharacter> entities = client.getAllCharacters().stream()
                .map(mapper::toModel)
                .toList();
        
        repository.saveAll(entities);
    }
}
