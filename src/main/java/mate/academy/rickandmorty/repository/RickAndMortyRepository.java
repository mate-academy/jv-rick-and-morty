package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RickAndMortyRepository extends JpaRepository<RickAndMortyCharacter, Long> {
    List<RickAndMortyCharacter> findAllByNameContainsIgnoreCase(String nameSegment);
}
