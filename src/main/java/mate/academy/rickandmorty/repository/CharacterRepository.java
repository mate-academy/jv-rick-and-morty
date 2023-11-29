package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Set;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByExternalIdIn(Set<Long> externalIds);

    List<Character> findAllByNameContains(String namePart);
}
