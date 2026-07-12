package mate.academy.rickandmorty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.RickAndMortyCharacter;
import mate.academy.rickandmorty.model.dto.CharacterDto;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository repository;

    @Mock
    private CharacterMapper mapper;

    @InjectMocks
    private CharacterService characterService;

    @Test
    void searchByName_ShouldReturnList() {
        String name = "Rick";
        RickAndMortyCharacter character = RickAndMortyCharacter.builder()
                .name("Rick Sanchez").build();
        when(repository.findAllByNameContainsIgnoreCase(name)).thenReturn(List.of(character));
        when(mapper.toDto(character)).thenReturn(
                CharacterDto.builder().name("Rick Sanchez").build());
        List<CharacterDto> result = characterService.searchByName(name);
        assertEquals(1, result.size());
        assertEquals("Rick Sanchez", result.get(0).getName());
    }

    @Test
    void getRandom_ShouldReturnCharacterDto() {
        RickAndMortyCharacter character = RickAndMortyCharacter
                .builder().name("Rick Sanchez").build();
        when(repository.getRandomCharacter()).thenReturn(Optional.of(character));
        when(mapper.toDto(character)).thenReturn(
                CharacterDto.builder().name("Rick Sanchez").build());
        CharacterDto result = characterService.getRandom();
        assertEquals("Rick Sanchez", result.getName());
    }

    @Test
    void getRandom_WhenDatabaseIsEmpty_ShouldThrowException() {
        when(repository.getRandomCharacter()).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            characterService.getRandom();
        });
        assertEquals("No characters found in database", exception.getMessage());
    }
}
