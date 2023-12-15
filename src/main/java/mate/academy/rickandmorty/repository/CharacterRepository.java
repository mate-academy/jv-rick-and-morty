package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterFromRickAndMorty, Long> {
    List<CharacterFromRickAndMorty> findCharactersByNameIsContaining(String name);
}
