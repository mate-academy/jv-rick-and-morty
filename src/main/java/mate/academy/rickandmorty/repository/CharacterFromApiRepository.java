package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterFromApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterFromApiRepository extends JpaRepository<CharacterFromApi, Long> {
    List<CharacterFromApi> findCharactersByNameContainingIgnoreCase(String string);

    @Query(value = "SELECT * FROM characters_from_api ORDER BY RAND() LIMIT 1", nativeQuery = true)
    CharacterFromApi getRandomCharacter();
}
