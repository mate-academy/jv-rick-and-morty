package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Set;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByNameContainsIgnoreCase(String name);

    List<MovieCharacter> findAllByExternalIdIn(Set<Long> externalIds);
}
