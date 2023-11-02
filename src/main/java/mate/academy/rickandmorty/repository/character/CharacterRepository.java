package mate.academy.rickandmorty.repository.character;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByExternalId(Long externalId);

    List<Character> findAll(Specification<Character> specification, Pageable pageable);
}
