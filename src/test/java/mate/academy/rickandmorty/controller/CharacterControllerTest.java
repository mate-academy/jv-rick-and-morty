package mate.academy.rickandmorty.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class CharacterControllerTest {
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    void getRandomCharacter_ShouldReturnRandomCharacter() throws Exception {
        MvcResult result = mockMvc.perform(get("/characters/random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Character actual = objectMapper.readValue(result.getResponse().getContentAsString(), Character.class);

        assertNotNull(actual);
    }

    @Test
    void findCharacterBySearchString_WhenThreeCharactersMatches_ShouldReturnListOfThree() throws Exception {
        int expectedSize = 3;
        String characterName = "Morty";
        MvcResult result = mockMvc.perform(get("/characters/name/" + characterName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        List<Character> actual = objectMapper
                .readValue(jsonResponse, new TypeReference<>() {});

        assertNotNull(actual);
        assertEquals(expectedSize, actual.size());
    }
}