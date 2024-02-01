package mate.academy.rickandmorty.repository.character;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c WHERE LOWER(c.name)"
            + " LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Character> findByNameContainingIgnoreCase(@Param("searchTerm") String searchTerm);

    @Query("FROM Character ORDER BY RAND() LIMIT 1")
    Character getRandomCharacter();
}
