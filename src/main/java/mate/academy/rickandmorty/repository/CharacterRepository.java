package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByNameContainsIgnoreCase(Pageable pageable, String name);

    @Query(
            value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1",
            nativeQuery = true
    )
    Character getRandomCharacter();
}
