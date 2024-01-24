package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharactersRepository extends JpaRepository<Character, Long> {

    @Query("FROM Character ORDER BY RAND() LIMIT 1")
    Character findAnyCharacter();

    @Query("FROM Character c WHERE upper(c.name) LIKE upper('%' || :name || '%')")
    List<Character> findByName(String name);
}
