package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.AnimationCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimationCharacterRepository extends JpaRepository<AnimationCharacter, Long> {
    @Query("FROM AnimationCharacter c WHERE c.name LIKE :partName")
    List<AnimationCharacter> findAllByNameLike(String partName);

    @Query("SELECT COUNT(*) FROM AnimationCharacter")
    Long getCountOfRecords();
}
