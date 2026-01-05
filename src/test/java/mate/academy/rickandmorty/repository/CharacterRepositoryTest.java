package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.CharacterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CharacterRepositoryTest {

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    void shouldReturnCharacter_whenExternalIdExist(){
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setExternalId(1L);
        CharacterEntity saved = characterRepository.save(characterEntity);

        Optional<CharacterEntity> byExternalId = characterRepository
                .findByExternalId(saved.getExternalId());

        assertTrue(byExternalId.isPresent());
    }

    @Test
    void shouldReturnEmptyCharacter_whenExternalIdNotExist() {
        Optional<CharacterEntity> byExternalId = characterRepository.findByExternalId(1L);

        assertTrue(byExternalId.isEmpty());
    }

    @Test
    void shouldReturnListOfCharacters_whenNameContainingIgnoreCase() {
        int expectedListSize = 3;
        List<String> expectedNames = List.of("Rick", "rick Sanchez", "rick Alien");

        loadTenTestCharacters();

        List<CharacterEntity> actual = characterRepository
                .findByNameContainingIgnoreCase("rick");

        List<String> actualNames = actual.stream()
                .map(CharacterEntity::getName)
                .toList();


        assertEquals(expectedListSize, actual.size());
        assertEquals(expectedNames, actualNames);

    }

    private void loadTenTestCharacters() {
        List<String> names = List.of("Rick",
                "rick Sanchez",
                "Morty",
                "Morty Zombie",
                "Summer",
                "rick Alien");

        names.forEach(name -> {
            CharacterEntity entity = new CharacterEntity();
            entity.setName(name);
            characterRepository.save(entity);
        });
    }
}
