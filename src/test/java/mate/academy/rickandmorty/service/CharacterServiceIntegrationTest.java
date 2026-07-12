package mate.academy.rickandmorty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CharacterServiceIntegrationTest {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        RickAndMortyCharacter rick = new RickAndMortyCharacter();
        rick.setName("Rick Sanchez");
        rick.setStatus("Alive");
        rick.setGender("Male");

        RickAndMortyCharacter morty = new RickAndMortyCharacter();
        morty.setName("Morty Smith");
        morty.setStatus("Alive");
        morty.setGender("Male");

        repository.saveAll(List.of(rick, morty));
    }

    @Test
    void searchByName_ValidName_ReturnsList() {
        List<CharacterDto> result = characterService.searchByName("Rick");

        assertEquals(1, result.size());
        assertEquals("Rick Sanchez", result.get(0).getName());
    }

    @Test
    void searchByName_PartialName_ReturnsList() {
        List<CharacterDto> result = characterService.searchByName("smith");

        assertEquals(1, result.size());
        assertEquals("Morty Smith", result.get(0).getName());
    }

    @Test
    void getRandom_ReturnsCharacter() {
        CharacterDto random = characterService.getRandom();

        assertTrue(random.getName().contains("Rick") || random.getName().contains("Morty"));
    }
}
