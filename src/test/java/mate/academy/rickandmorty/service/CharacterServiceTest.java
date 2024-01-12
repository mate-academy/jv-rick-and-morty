package mate.academy.rickandmorty.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.character.CharacterRepository;
import mate.academy.rickandmorty.service.impl.CharacterServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
public class CharacterServiceTest {
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterServiceImpl characterService;

    @Test
    void getRandomCharacterFromDb_ok() {
        CharacterDto result = characterService.getRandomCharacterFromDb();

        assertNotNull(result);
        assertNotNull(result.getName());
    }

    @Test
    void findCharactersByName_notOk() {
        String unusedName = "unusedName";
        assertThrows(EntityNotFoundException.class,
                () -> characterService.findCharactersByName(unusedName, PageRequest.of(0, 10)));
    }

    @Test
    void findCharactersByName_ok() {
        String nameRick = "Toxic Rick";
        List<CharacterDto> result
                = characterService.findCharactersByName(nameRick, PageRequest.of(0, 10));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(nameRick, result.get(0).getName());

        String nameRick2 = "Toxic Rick-2";
        Character characterRick2 = new Character();
        characterRick2.setName(nameRick2);
        characterRick2.setGender("male");
        characterRick2.setStatus("alive");
        Character savedCharacter = characterRepository.save(characterRick2);

        List<CharacterDto> result2
                = characterService.findCharactersByName(nameRick, PageRequest.of(0, 10));
        assertNotNull(result);
        assertEquals(2, result2.size());
    }
}
