package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CharacterServiceImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterServiceImpl characterService;

    @Test
    void getRandomCharacter_ShouldReturnCharacter() {
        Character character = new Character("1", "Rick Sanchez", "Alive", "Male");

        when(characterRepository.findRandomCharacter()).thenReturn(Optional.of(character));

        Character result = characterService.getRandomCharacter();

        assertNotNull(result);
        assertEquals("Rick Sanchez", result.getName());
    }

    @Test
    void getRandomCharacter_ShouldThrowException_WhenNoCharacterFound() {
        when(characterRepository.findRandomCharacter()).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> characterService.getRandomCharacter());
    }

    @Test
    void searchCharacterByName_ShouldReturnCharacterList() {
        List<Character> characters = List.of(
                new Character("1", "Rick Sanchez", "Alive", "Male"),
                new Character("2", "Morty Smith", "Alive", "Male")
        );

        when(characterRepository.findByNameContaining("Rick")).thenReturn(characters);

        List<Character> result = characterService.searchCharacterByName("Rick");

        assertEquals(2, result.size());
        assertEquals("Rick Sanchez", result.get(0).getName());
    }
}
