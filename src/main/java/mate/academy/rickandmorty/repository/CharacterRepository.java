package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>,
        JpaSpecificationExecutor<CharacterEntity> {

    @Query("SELECT c FROM CharacterEntity c WHERE UPPER(c.name) "
            + "LIKE UPPER(CONCAT('%', :name, '%'))")
    List<CharacterEntity> findByName(String name);
}
