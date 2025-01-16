package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository
        extends JpaRepository<CharacterModel, Long>,
        JpaSpecificationExecutor<CharacterModel> {
    List<CharacterModel> findByNameContainingIgnoreCase(String name);

    @Query("SELECT c.id FROM CharacterModel c")
    List<Long> findAllIds();
}
