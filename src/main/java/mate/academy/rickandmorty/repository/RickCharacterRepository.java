package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RickCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RickCharacterRepository extends JpaRepository<RickCharacter, Long> {
    @Query(value = "SELECT * FROM rick_character ORDER BY RAND() LIMIT 1", nativeQuery = true)
    RickCharacter findRandomCharacter();

    List<RickCharacter> findByNameContainingIgnoreCase(String name);
}
