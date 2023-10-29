package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.models.RickMorty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RickMortyRepository extends JpaRepository<RickMorty, Long> {
    List<RickMorty> findRickMortiesByNameContainingIgnoreCase(String name);

    RickMorty findRickMortyById(Long externalId);

    @Query(value = "SELECT MAX(id) FROM characters;",nativeQuery = true)
    Long getMaxId();
}
