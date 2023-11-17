package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> getAllByNameContainsIgnoreCase(String template);

    @Query("FROM Character ORDER BY RAND() LIMIT 1")
    Character getRandomCharacter();
}
