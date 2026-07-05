package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterDataInitializer implements CommandLineRunner {
    private final CharacterClient characterClient;
    private final CharacterService service;

    @Override
    public void run(String... args) {
        System.out.println("!!!Run");

        importCharacters();
    }

    private void importCharacters() {
        CharacterResponseDataDto firstPage =
                characterClient.getCharacters(1);
        int totalPages = firstPage.getInfo().getPages();
        System.out.println("Total pages: " + totalPages);

        service.saveAll(firstPage);

        for (int page = 2; page <= totalPages; page++) {
            System.out.println("Page: " + page);
            CharacterResponseDataDto response =
                    characterClient.getCharacters(page);

            service.saveAll(response);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
