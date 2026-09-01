package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1", nativeQuery = true)
    MovieCharacter getRandomCharacterNative();
}
