package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;

public interface CharacterService {
    CartoonCharacter add(CartoonCharacter character);

    CartoonCharacter getById(Long id);

    List<CartoonCharacter> search(String name);
}
