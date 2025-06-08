package mate.academy.rickandmorty.repository;

import java.util.Optional;
import mate.academy.rickandmorty.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HeroRepository extends JpaRepository<Hero, Long> {

    Optional<Hero> findHeroByName(String name);

    @Query(value = "SELECT * FROM heroes ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Hero getRandomHero();
}
