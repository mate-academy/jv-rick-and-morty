package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.RickyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickyCharacterRepository extends JpaRepository<RickyCharacter, Long> {
}
