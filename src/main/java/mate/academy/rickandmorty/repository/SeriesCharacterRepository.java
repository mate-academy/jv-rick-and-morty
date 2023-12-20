package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesCharacterRepository extends JpaRepository<SeriesCharacter, Long> {
    List<SeriesCharacter> findAllByNameContainingIgnoreCase(String name);
}
