package mate.academy.rickandmorty.dto.external;

public record CharacterDataDto(
        Long id, 
        String name, 
        String status, 
        String gender) {
}
