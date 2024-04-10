package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter, Long> {
    List<CartoonCharacter> findByNameContainsIgnoreCase(String name);
}
