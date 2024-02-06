package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RickAndMortyChars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<RickAndMortyChars, Long> {
    List<RickAndMortyChars> findRickAndMortyCharsByNameIsContaining(String name);
}
