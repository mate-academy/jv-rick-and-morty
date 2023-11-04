package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long>,
        JpaSpecificationExecutor<MovieCharacter> {

    @Query("SELECT mc FROM MovieCharacter mc ORDER BY RAND() LIMIT 1")
    Optional<MovieCharacter> findRandom();

    @Query("SELECT mc FROM MovieCharacter mc WHERE mc.name LIKE :namepart%")
    List<MovieCharacter> getMovieCharacterByNameContainsIgnoreCase(String namepart);
}
