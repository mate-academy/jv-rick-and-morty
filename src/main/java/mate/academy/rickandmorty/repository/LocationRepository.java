package mate.academy.rickandmorty.repository;

import java.util.Optional;
import mate.academy.rickandmorty.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends
        JpaRepository<Location, Long>,
        JpaSpecificationExecutor<Location> {
    Optional<Location> findByName(String name);
}
