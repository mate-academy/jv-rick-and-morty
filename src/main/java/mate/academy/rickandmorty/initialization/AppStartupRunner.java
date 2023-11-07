package mate.academy.rickandmorty.initialization;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.DataLoader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppStartupRunner implements ApplicationRunner {
    private final DataLoader loader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loader.downloadAndInsertDataIntoDb();
    }
}
