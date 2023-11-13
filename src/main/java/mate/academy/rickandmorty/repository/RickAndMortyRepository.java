package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RickAndMortyRepository extends JpaRepository<Character, Long> {
    @Query("SELECT ch FROM Character ch WHERE ch.name LIKE %:string%")
    List<Character> findByNameContaining(String string);
}
