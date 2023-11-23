package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharactersRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByName(String name);
}
