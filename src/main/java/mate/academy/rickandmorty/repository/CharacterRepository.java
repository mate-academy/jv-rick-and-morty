package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CustomCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CustomCharacter, Long> {
    List<CustomCharacter> findByNameIgnoreCase(String name);
}
