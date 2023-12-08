package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<SeriesCharacter, Long> {
    List<SeriesCharacter> findByNameContains(String name, Pageable pageable);
}
