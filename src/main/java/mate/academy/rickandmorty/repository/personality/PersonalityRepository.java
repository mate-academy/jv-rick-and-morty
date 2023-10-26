package mate.academy.rickandmorty.repository.personality;

import java.util.List;
import mate.academy.rickandmorty.model.Personality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PersonalityRepository
        extends JpaRepository<Personality, Long>, JpaSpecificationExecutor<Personality> {

    @Query(value = "SELECT * FROM personalities p WHERE p.is_deleted "
            + "= false ORDER BY RAND()  LIMIT 1", nativeQuery = true)
    Personality findRandomPersonality();

    List<Personality> findAll();
}
