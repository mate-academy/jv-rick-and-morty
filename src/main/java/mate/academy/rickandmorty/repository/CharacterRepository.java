package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query(value = "SELECT COUNT(1) FROM characters", nativeQuery = true)
    int countRecords();

    List<Character> findAllByNameContaining(String name, Pageable pageable);

    Optional<Character> findByExternalId(String externalId);
}
