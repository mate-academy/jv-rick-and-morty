package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.AnimationCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimationCharacterRepository extends JpaRepository<AnimationCharacter, Long> {
    List<AnimationCharacter> findAllByNameContains(String partName);
}
