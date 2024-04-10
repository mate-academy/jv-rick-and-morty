package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;

public interface CartoonCharacterService extends GenericService<CartoonCharacter> {
    CartoonCharacter getRandomCharacter();

    List<CartoonCharacter> getAll(String name);

    void saveAll(List<CartoonCharacter> characters);
}
