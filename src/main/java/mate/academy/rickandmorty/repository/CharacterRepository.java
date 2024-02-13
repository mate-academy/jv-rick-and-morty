package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.entity.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("FROM Character ORDER BY RAND() LIMIT 1")
    Optional<Character> findRandom();

    List<Character> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
