package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<CharacterFromRickAndMorty, Long> {
    List<CharacterFromRickAndMorty> findCharactersByNameIsContaining(String name);
}
