package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long> {
    @Query(value = "FROM CharacterModel ORDER BY RAND() LIMIT 1")
    Optional<CharacterModel> getRandomCharacter();

    List<CharacterModel> findByNameIgnoreCaseContaining(String name);
}
