package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    List<CharacterEntity> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT * FROM character_entity ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<CharacterEntity> findRandom();
}
