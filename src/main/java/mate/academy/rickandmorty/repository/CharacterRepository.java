package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContaining(String name);

    @Query("SELECT c FROM Character c ORDER BY FUNCTION('RAND') LIMIT 1")
    Optional<Character> findRandomCharacter();
}
