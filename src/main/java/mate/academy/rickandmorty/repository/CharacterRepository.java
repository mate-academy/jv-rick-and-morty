package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CartoonCharacter, Long> {
    List<CartoonCharacter> findAllByNameContainingIgnoreCase(String name);

    @Query(value = "FROM CartoonCharacter ORDER BY RAND() LIMIT 1")
    CartoonCharacter getRandom();
}
