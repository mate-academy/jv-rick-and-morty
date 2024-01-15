package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findByExternalId(Long externalId);

    List<CharacterEntity> findByNameContainingIgnoreCase(String name);
}
