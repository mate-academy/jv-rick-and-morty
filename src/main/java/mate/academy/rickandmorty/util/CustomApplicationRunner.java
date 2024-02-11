package mate.academy.rickandmorty.util;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.ApplicationRunnerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomApplicationRunner implements ApplicationRunner {
    private final ApplicationRunnerService applicationRunnerService;

    @Override
    public void run(ApplicationArguments args) {
        applicationRunnerService.processCharactersFromApi();
    }
}
