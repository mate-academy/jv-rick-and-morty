package mate.academy.rickandmorty.repository;

import java.util.Optional;
import mate.academy.rickandmorty.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findGenderByName(Gender.GenderEnum name);
}
