package mate.academy.rickandmorty.repo;

import java.util.List;
import mate.academy.rickandmorty.model.internal.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character,Long> {
    List<Character> findByNameContaining(String searchString);
}
