package mate.academy.rickandmorty.config;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.AppService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Class responsible for initializing data in the database before the API starts working
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final AppService appService;

    @Override
    public void run(ApplicationArguments args) {
        appService.downloadAllCharactersFromApi();
    }
}
