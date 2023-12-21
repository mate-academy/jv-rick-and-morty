package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CharacterRepository extends JpaRepository<Character, Long>,
        JpaSpecificationExecutor<Character> {

    @Query("FROM Character ch WHERE ch.externalId = :external_id")
    Optional<Character> findByExternalId(@Param("external_id") long externalId);

    @Query("FROM Character ch WHERE ch.name LIKE %:name%")
    List<Character> findAllByName(@Param("name") String name);
}
