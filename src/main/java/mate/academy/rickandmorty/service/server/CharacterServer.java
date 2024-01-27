package mate.academy.rickandmorty.service.server;

import java.io.IOException;
import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface CharacterServer {
    void saveAllFromExternalApi() throws IOException, InterruptedException;

    List<Character> findByName(String name);

    Character findById(Integer id);

    Character getRandomCharacter();
}
