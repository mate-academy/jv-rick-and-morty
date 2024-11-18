package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RikAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharacterRepository extends JpaRepository<RikAndMortyCharacter, Long>,
        JpaSpecificationExecutor<RikAndMortyCharacter> {

    List<RikAndMortyCharacter> searchRikAndMortyCharactersByNameContaining(String name);
}
