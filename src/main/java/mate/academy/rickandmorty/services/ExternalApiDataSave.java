package mate.academy.rickandmorty.services;

import java.util.List;
import mate.academy.rickandmorty.dto.ExternalCharacterResponse;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExternalApiDataSave implements ApplicationRunner {
    private final CharacterClient characterClient;
    private final CharacterRepository characterRepository;

    public ExternalApiDataSave(CharacterClient characterClient,
                               CharacterRepository characterRepository) {
        this.characterClient = characterClient;
        this.characterRepository = characterRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (characterRepository.count() > 0) {
            return;
        }

        ExternalCharacterResponse firstPage = characterClient.getCharacters(1);
        int allPages = firstPage.getInfo().getPages();

        for (int i = 1; i <= allPages; i++) {
            ExternalCharacterResponse currentPage;
            if (i == 1) {
                currentPage = firstPage;
            } else {
                currentPage = characterClient.getCharacters(i);
            }
            List<Character> characters = currentPage.getResults().stream().map(dto -> {
                Character c = new Character();
                c.setExternalId(dto.getId());
                c.setName(dto.getName());
                c.setStatus(dto.getStatus());
                c.setGender(dto.getGender());
                return c;
            }).toList();
            characterRepository.saveAll(characters);
        }
    }
}
