package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.LocalCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<LocalCharacter, Long> {
    @Query(value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1", nativeQuery = true)
    LocalCharacter getRandom();

    @Query("SELECT c FROM LocalCharacter c WHERE c.name LIKE %:name%")
    List<LocalCharacter> findByName(@Param("name") String name);
}
