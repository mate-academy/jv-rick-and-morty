package mate.academy.rickandmorty;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import mate.academy.rickandmorty.controller.CharacterController;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@WebMvcTest(CharacterController.class)
public class ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGenerateRandomCharacter() throws Exception {
        CharacterResponseDto expectedCharacter = new CharacterResponseDto(3L, 3L, "Rick Sanchez",
                "Alive", "Human", "Male");
        when(characterService.generateRandomCharacter()).thenReturn(expectedCharacter);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/characters/generate_random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(expectedCharacter)));
    }

    @Test
    public void testSearchCharactersByName() throws Exception {
        String searchName = "Character";

        List<CharacterResponseDto> expectedCharacters = new ArrayList<>();
        expectedCharacters.add(new CharacterResponseDto(1L, 1L, "Character1", "Alive", "Human", "Male"));
        expectedCharacters.add(  new CharacterResponseDto(2L, 2L, "Character2", "Dead", "Alien", "Female"));
        when(characterService.searchCharactersByName(searchName, PageRequest.of(0, 10)))
                .thenReturn(expectedCharacters);

        String url = "/api/characters/search_by_name/%s?page=%s&size=%s";
        mockMvc.perform(MockMvcRequestBuilders.get( String.format(url, searchName, 0, 10))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(expectedCharacters)));
    }
}
