package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("FROM Character ORDER BY RAND() LIMIT 1")
    Character getRandomCharacter();

    @Query("FROM Character c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<Character> findCharacterBySearchString(@Param("searchString") String searchString);
}
