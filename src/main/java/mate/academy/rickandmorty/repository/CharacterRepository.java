package mate.academy.rickandmorty.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.rickandmorty.dto.RandMResponseDto;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long> {

    @Query(value = "SELECT external_id FROM characters", nativeQuery = true)
    List<BigDecimal> findAllExternalId();

    @Query(value = "SELECT * FROM characters WHERE external_id = externalId", nativeQuery = true)
    RandMResponseDto findByExternalId(BigDecimal externalId);

    List<Character> findAllByNameContainsIgnoreCase(String name);
}
