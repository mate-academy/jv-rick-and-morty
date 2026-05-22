package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM character ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Character findRandom();
}
