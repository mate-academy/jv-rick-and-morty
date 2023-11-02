package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CharacterRepository extends
        JpaRepository<Character, Long>, JpaSpecificationExecutor<Character> {
    List<Character> findByNameContaining(String name);
}
