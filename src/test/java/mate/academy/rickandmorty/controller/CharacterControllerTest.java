package mate.academy.rickandmorty.controller;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CharacterController.class)
@ExtendWith(MockitoExtension.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    void getRandomCharacter_ShouldReturnCharacter() throws Exception {
        Character character = new Character("1", "Rick Sanchez", "Alive", "Male");

        when(characterService.getRandomCharacter()).thenReturn(character);

        mockMvc.perform(get("/rickandmortyapi/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rick Sanchez"))
                .andExpect(jsonPath("$.status").value("Alive"))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void getCharacterByName_ShouldReturnCharacterList() throws Exception {
        List<Character> characters = List.of(
                new Character("1", "Rick Sanchez", "Alive", "Male"),
                new Character("2", "Morty Smith", "Alive", "Male")
        );

        when(characterService.searchCharacterByName("Rick")).thenReturn(characters);

        mockMvc.perform(get("/rickandmortyapi/search")
                        .param("name", "Rick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Rick Sanchez"))
                .andExpect(jsonPath("$[1].name").value("Morty Smith"));
    }
}
