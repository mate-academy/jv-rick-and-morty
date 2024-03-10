package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickAndMortyRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContainsIgnoreCase(String name);
}
