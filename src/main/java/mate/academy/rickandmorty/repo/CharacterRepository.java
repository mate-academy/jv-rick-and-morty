package mate.academy.rickandmorty.repo;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    CharacterEntity findByExternalId(String externalId);

    List<CharacterEntity> findByNameContaining(String name);

}
