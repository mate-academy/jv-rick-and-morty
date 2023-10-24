package mate.academy.rickandmorty.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import mate.academy.rickandmorty.dto.internal.AnimationCharacterResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimationCharacterControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext,
                          @Autowired DataSource dataSource) throws SQLException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/characters/clear_test_characters.sql"));
        }

    }

    @AfterEach
    void tearDown(@Autowired DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/characters/clear_test_characters.sql"));
        }
    }

    @Sql(scripts = "classpath:database/characters/create_test_3_characters.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Get list characters by the search string")
    @Test
    void getAll_ListValidAnimationCharacterResponseDto_Success() throws Exception {
        MvcResult result = mockMvc.perform(get("/rickandmorty?search=Test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<AnimationCharacterResponseDto> actual = List.of(objectMapper.readValue(
                result.getResponse().getContentAsString(), AnimationCharacterResponseDto[].class));

        Assertions.assertEquals(2, actual.size());
        for (AnimationCharacterResponseDto dto: actual) {
            Assertions.assertNotNull(dto);
            assertThat(dto.getId()).isIn(1L, 2L);
            assertThat(dto.getName()).isIn("Rick Test", "Morty Test");
        }
    }

    @Sql(scripts = "classpath:database/characters/create_test_3_characters.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @DisplayName("Get random test character")
    @Test
    void get_ValidRandomAnimationCharacterResponseDto_Success() throws Exception {
        MvcResult result = mockMvc.perform(get("/rickandmorty/random")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        AnimationCharacterResponseDto actual = objectMapper.readValue(
                result.getResponse().getContentAsString(), AnimationCharacterResponseDto.class);

        Assertions.assertNotNull(actual);
        assertThat(actual.getId()).isIn(1L, 2L, 3L);
        assertThat(actual.getName()).isIn("Rick Test", "Morty Test", "Alice");
    }
}
