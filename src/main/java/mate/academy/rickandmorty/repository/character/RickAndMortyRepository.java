package mate.academy.rickandmorty.repository.character;

import mate.academy.rickandmorty.model.RickAndMorty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RickAndMortyRepository extends JpaRepository<RickAndMorty, Long>,
        JpaSpecificationExecutor<RickAndMorty> {
}
