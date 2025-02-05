package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonageRepository extends JpaRepository<Personage, Long>,
        JpaSpecificationExecutor<Personage> {
}
