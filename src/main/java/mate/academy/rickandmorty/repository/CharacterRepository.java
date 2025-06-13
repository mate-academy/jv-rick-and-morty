package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c.id FROM Character c")
    List<Long> findAllIds();
}
