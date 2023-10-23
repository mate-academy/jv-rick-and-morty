package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long>,
        JpaSpecificationExecutor<Character> {
    List<Character> getCharacterByNameContains(String name, Pageable pageable);

    @Query(value = "SELECT MAX(id) FROM rick_and_morty_db.characters", nativeQuery = true)
    Long getMaxIdFromCharacters();
}
