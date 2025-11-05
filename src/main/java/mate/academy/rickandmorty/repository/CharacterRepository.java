package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<RickAndMortyCharacter, Long> {
    List<RickAndMortyCharacter> findByNameContainingIgnoreCase(String name);
}
