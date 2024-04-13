package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickAndMortyRepository extends JpaRepository<Character,Long> {
    List<Character> findAllByNameContainsIgnoreCase(String params);
}
