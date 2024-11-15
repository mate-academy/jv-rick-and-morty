package mate.academy.rickandmorty.config;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.PersonageDataDto;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.service.PersonageClient;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataLoadConfiguration {
    private final PersonageClient personageClient;
    private final PersonageService personageService;
    private final PersonageMapper personageMapper;

    @Bean
    public ApplicationRunner loadDataAtStartup() {
        return args -> {
            List<PersonageDataDto> charactersData = personageClient.fetchPersonages();
            List<Personage> personages = charactersData.stream()
                    .map(personageMapper::toModelFromMeteData)
                    .toList();
            personageService.savePersonages(personages);
        };
    }
}
