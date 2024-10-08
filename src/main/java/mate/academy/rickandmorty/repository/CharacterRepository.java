package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<RickAndMortyCharacter, Long> {
    @Query(value = "SELECT * FROM rick_and_morty_characters ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    RickAndMortyCharacter getRandomCharacter();
}
