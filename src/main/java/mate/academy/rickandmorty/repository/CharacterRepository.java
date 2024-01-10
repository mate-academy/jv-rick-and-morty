package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> getCharactersByNameLikeIgnoreCase(String name);

    @Query(value = "SELECT MAX(c.id) FROM Character c")
    Long findLastId();
}
