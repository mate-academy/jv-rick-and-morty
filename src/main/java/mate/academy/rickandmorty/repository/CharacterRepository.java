package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<RickAndMortyCharacter, Long> {
    Optional<RickAndMortyCharacter> findByExternalId(String externalId);

    List<RickAndMortyCharacter> findByNameContainingIgnoreCase(String namePart);
}
