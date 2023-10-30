package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("""
            FROM Character c
            ORDER BY FUNCTION('RAND')
            LIMIT 1
            """)
    Optional<Character> findRandomCharacter();

    @Query("""
            SELECT c.name
            FROM Character c
            """)
    List<String> getAllNames();

    List<Character> getAllByNameIn(List<String> name);
}
