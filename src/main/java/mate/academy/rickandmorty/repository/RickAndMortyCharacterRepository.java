package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickAndMortyCharacterRepository
        extends JpaRepository<RickAndMortyCharacter, Long> {
    Page<RickAndMortyCharacter> findRickAndMortyCharactersByNameContains(
            String name, Pageable pageable);
}
