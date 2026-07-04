package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterModel, Long>,
        JpaSpecificationExecutor<CharacterModel> {
    List<CharacterModel> findCharacterModelByNameContainingIgnoreCase(String name);
}
