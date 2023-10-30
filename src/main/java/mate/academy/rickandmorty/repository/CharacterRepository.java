package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findRickMortiesByNameContainingIgnoreCase(String name);

    Character findRickMortyById(Long externalId);

    @Query(value = "SELECT MAX(id) FROM characters;",nativeQuery = true)
    Long getMaxId();
}
