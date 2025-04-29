package spring.rickandmorty.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.rickandmorty.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContainingIgnoreCase(String name);
}
