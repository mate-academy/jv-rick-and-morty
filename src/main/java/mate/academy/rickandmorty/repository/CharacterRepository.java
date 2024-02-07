package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    @Query("""
            select c
            from Character c
             where lower(c.name) like lower(concat('%', :namePart, '%'))
            """)
    List<Character> findAllByNameLikeIgnoreCase(String namePart);
}
