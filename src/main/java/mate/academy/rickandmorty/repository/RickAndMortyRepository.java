package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RickAndMortyRepository extends JpaRepository<CharacterModel, Long> {
    List<CharacterModel> findByNameContainingIgnoreCase(String name);
}
