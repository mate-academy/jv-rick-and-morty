package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterFromRickAndMorty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterFromRickAndMorty, Long> {
    List<CharacterFromRickAndMorty> findAllByNameIsLikeIgnoreCase(String partName);
}
