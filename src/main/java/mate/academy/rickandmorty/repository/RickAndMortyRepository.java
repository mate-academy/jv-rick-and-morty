package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RickAndMortyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RickAndMortyRepository extends JpaRepository<RickAndMortyModel, Long> {
    List<RickAndMortyModel> findByNameContainsIgnoreCase(String name);
}
