package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT c FROM Character c WHERE c.name LIKE CONCAT('%', :name, '%')")
    List<Character> findAllByNameContainingIgnoreCase(String name);
}
