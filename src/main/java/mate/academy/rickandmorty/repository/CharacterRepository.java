package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
    List<CharacterModel> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "FROM CharacterModel ORDER BY RAND() LIMIT 1")
    CharacterModel getRandomCharacter();
}
