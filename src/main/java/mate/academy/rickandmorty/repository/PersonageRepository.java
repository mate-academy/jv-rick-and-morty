package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Personage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonageRepository extends JpaRepository<Personage, Long> {
    List<Personage> findAllByNameLike(String name);
}
