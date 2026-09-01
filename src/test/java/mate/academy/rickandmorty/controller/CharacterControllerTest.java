package mate.academy.rickandmorty.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mate.academy.rickandmorty.model.MovieCharacter;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CharacterRepository characterRepository;

    @BeforeEach
    void setUp() {
        characterRepository.deleteAll();

        MovieCharacter rick = new MovieCharacter();
        rick.setExternalId(1L);
        rick.setName("Rick Sanchez");
        rick.setStatus("Alive");
        rick.setGender("Male");

        MovieCharacter morty = new MovieCharacter();
        morty.setExternalId(2L);
        morty.setName("Morty Smith");
        morty.setStatus("Alive");
        morty.setGender("Male");

        characterRepository.save(rick);
        characterRepository.save(morty);
    }

    @Test
    @DisplayName("Verify search endpoint returns correct characters")
    void searchCharacter_ValidName_ReturnsMatchingCharacters() throws Exception {
        mockMvc.perform(get("/characters/search")
                        .param("name", "Rick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Rick Sanchez"))
                .andExpect(jsonPath("$[0].status").value("Alive"));
    }
}
