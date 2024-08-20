package mate.academy.rickandmorty;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import mate.academy.rickandmorty.service.impl.CharacterDataFetchingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final CharacterDataFetchingService characterDataFetchingService;
    private final CharacterService characterService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (characterService.countAll() > 0) {
            List<ExternalCharacterDto> externalCharacterDtoList =
                    characterDataFetchingService.fetchAllData();
            characterService.saveAll(externalCharacterDtoList);
        }
    }
}
