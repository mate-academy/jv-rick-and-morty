package mate.academy.rickandmorty.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    void getRandom_ShouldReturnCharacterJson() throws Exception {
        CharacterDto mockDto = CharacterDto.builder()
                .name("Rick Sanchez")
                .build();
        when(characterService.getRandom()).thenReturn(mockDto);

        mockMvc.perform(get("/api/characters/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rick Sanchez"));
    }

    @Test
    void searchByName_ShouldReturnList() throws Exception {
        when(characterService.searchByName("Rick")).thenReturn(List.of(
                CharacterDto.builder().name("Rick Sanchez").build()
        ));

        mockMvc.perform(get("/api/characters/search")
                        .param("name", "Rick"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rick Sanchez"));
    }
}
