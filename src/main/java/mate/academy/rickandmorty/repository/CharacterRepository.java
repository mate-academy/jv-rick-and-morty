package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.SeriesCharacter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CharacterRepository extends JpaRepository<SeriesCharacter, Long>,
        PagingAndSortingRepository<SeriesCharacter, Long>,
        JpaSpecificationExecutor<SeriesCharacter> {
    List<SeriesCharacter> findAllByNameContainsIgnoreCase(String name, Pageable pageable);
}
