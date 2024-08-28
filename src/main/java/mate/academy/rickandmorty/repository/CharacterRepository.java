package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>,
        JpaSpecificationExecutor<Character> {
    @Query("SELECT c FROM Character c WHERE LOWER(c.name) LIKE LOWER("
            + "CONCAT('%', :namePart, '%'))")
    List<Character> searchCharacters(@Param("namePart") String namePart);
}
