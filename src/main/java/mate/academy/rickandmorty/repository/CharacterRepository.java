package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RnMCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<RnMCharacter, Long> {
    List<RnMCharacter> findAllByNameContaining(String name);
}
