package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findById(Long id);

    List<Character> getAllByNameContainsIgnoreCase(String pattern);
}
