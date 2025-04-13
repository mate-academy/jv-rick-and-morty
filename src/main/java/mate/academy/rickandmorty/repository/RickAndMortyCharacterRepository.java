package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RickAndMortyCharacterRepository extends JpaRepository<RickAndMortyCharacter, Long>,
        JpaSpecificationExecutor<RickAndMortyCharacter> {

    List<RickAndMortyCharacter> findAllByNameContaining(String name);
}
