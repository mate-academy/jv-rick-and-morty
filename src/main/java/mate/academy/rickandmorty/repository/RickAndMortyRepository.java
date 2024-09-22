package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.CharacterInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RickAndMortyRepository extends JpaRepository<CharacterInfo, Long> {
    @Query("SELECT c FROM CharacterInfo c "
            + "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    Page<CharacterInfo> findByNamePart(@Param("namePart") String namePart,
                                       Pageable pageable);
}
