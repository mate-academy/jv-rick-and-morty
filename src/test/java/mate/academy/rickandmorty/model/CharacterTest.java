package mate.academy.rickandmorty.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterTest {

    @Test
    void testCharacterConstructorAndGetters() {
        Character character = new Character("1", "Rick Sanchez", "Alive", "Male");

        assertEquals("1", character.getExternalId());
        assertEquals("Rick Sanchez", character.getName());
        assertEquals("Alive", character.getStatus());
        assertEquals("Male", character.getGender());
    }

    @Test
    void testSetters() {
        Character character = new Character();
        character.setExternalId("2");
        character.setName("Morty Smith");
        character.setStatus("Alive");
        character.setGender("Male");

        assertEquals("2", character.getExternalId());
        assertEquals("Morty Smith", character.getName());
        assertEquals("Alive", character.getStatus());
        assertEquals("Male", character.getGender());
    }

    @Test
    void testEqualsAndHashCode() {
        Character char1 = new Character("1", "Rick Sanchez", "Alive", "Male");
        Character char2 = new Character("1", "Rick Sanchez", "Alive", "Male");

        assertEquals(char1, char2);
        assertEquals(char1.hashCode(), char2.hashCode());
    }

    @Test
    void testToString() {
        Character character = new Character("1", "Rick Sanchez", "Alive", "Male");

        String expectedString = "Character(id=null, externalId=1, name=Rick Sanchez, status=Alive, gender=Male)";
        assertEquals(expectedString, character.toString());
    }
}
