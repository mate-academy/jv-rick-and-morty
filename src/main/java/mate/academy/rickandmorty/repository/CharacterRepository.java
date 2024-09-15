package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.CharacterRickAndMorty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterRickAndMorty, Long> {
    long count();

    List<CharacterRickAndMorty> findByNameContaining(String name);
}
