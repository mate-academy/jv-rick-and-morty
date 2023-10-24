package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT * FROM characters c WHERE c.external_id = :id", nativeQuery = true)
    Character findByExternalId(Long id);
}
