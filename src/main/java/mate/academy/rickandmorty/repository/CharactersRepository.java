package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CharactersRepository extends JpaRepository<Characters, Long>,
        JpaSpecificationExecutor<Characters> {
    List<Characters> findByNameContaining(String name);
}
