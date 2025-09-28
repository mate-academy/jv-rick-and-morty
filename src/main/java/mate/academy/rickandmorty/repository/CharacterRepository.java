package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Импорт Query

public interface CharacterRepository extends JpaRepository<RickAndMortyCharacter, Long> {
    List<RickAndMortyCharacter> findByNameContainingIgnoreCase(String name);

    // НОВОЕ: Кастомный запрос для получения одного случайного персонажа
    @Query(value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1", nativeQuery = true)
    RickAndMortyCharacter findRandomCharacter();
}
