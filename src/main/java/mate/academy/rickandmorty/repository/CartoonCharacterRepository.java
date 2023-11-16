package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CartoonCharacter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartoonCharacterRepository extends JpaRepository<CartoonCharacter, Long> {
    List<CartoonCharacter> findAllByNameContains(Pageable pageable, String name);

    @Query(nativeQuery = true, value = "SELECT *  FROM characters ORDER BY RAND() LIMIT 1")
    CartoonCharacter getRandom();
}
