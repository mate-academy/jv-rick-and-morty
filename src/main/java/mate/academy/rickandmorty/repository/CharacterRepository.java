package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>,
        JpaSpecificationExecutor<CharacterEntity> {
}
