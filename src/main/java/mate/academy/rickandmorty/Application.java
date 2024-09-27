package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;

import mate.academy.rickandmorty.external.api.service.DbSynchronizationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final DbSynchronizationService dbSynchronizationService;

    @Override
    public void run(String... args) throws Exception {
        dbSynchronizationService.synchronize();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
