package mate.academy.rickandmorty;

import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationRunner implements ApplicationRunner {
    private final CharacterService characterService;

    @Autowired
    public MyApplicationRunner(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Characters are being loaded...");
        characterService.loadingAndSavingCharacters();
        System.out.println("Characters are loaded!");
    }
}
