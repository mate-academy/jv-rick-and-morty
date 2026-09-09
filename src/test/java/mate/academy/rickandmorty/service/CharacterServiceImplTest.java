package mate.academy.rickandmorty.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterResponseDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {
    @Mock
    private CharacterRepository repository;
    private CharacterService characterService;

    @BeforeEach
    void setUp() {
        characterService = new CharacterServiceImpl(repository, new CharacterMapper());
    }

    @Test
    void findByName_matchingCharacters_returnsMappedDtos() {
        Character character = createCharacter();
        when(repository.findAllByNameContainingIgnoreCase("rick"))
                .thenReturn(List.of(character));

        List<CharacterResponseDto> actual = characterService.findByName(" rick ");

        assertThat(actual).containsExactly(new CharacterResponseDto(
                1L,
                "1",
                "Rick Sanchez",
                "Alive",
                "Male"
        ));
    }

    @Test
    void getRandomCharacter_nonEmptyDatabase_returnsCharacter() {
        Character character = createCharacter();
        when(repository.findAll()).thenReturn(List.of(character));

        CharacterResponseDto actual = characterService.getRandomCharacter();

        assertThat(actual.name()).isEqualTo("Rick Sanchez");
    }

    private Character createCharacter() {
        return Character.builder()
                .id(1L)
                .externalId("1")
                .name("Rick Sanchez")
                .status("Alive")
                .gender("Male")
                .build();
    }
}
