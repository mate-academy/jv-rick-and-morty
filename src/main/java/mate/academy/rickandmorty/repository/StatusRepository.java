package mate.academy.rickandmorty.repository;

import java.util.Optional;
import mate.academy.rickandmorty.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findStatusByName(Status.StatusEnum name);
}
