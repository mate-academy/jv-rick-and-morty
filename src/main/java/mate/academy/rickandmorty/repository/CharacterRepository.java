package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Character> findCharacterByNameLikeIgnoreCase(String name);
}
