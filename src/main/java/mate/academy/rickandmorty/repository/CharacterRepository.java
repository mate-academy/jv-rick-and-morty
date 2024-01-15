package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    List<CharacterEntity> findByNameContainingIgnoreCase(String name);

    List<CharacterEntity> findAllByExternalIdIn(List<Long> externalIds);
}
