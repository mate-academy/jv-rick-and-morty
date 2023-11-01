package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT ch FROM Character ch WHERE ch.name LIKE %:regex%")
    List<Character> findAllByNameMatchesRegex(String regex);
}
