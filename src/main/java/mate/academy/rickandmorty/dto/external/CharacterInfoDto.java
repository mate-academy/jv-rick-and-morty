package mate.academy.rickandmorty.dto.external;

public record CharacterInfoDto(
        Long externalId,
        String name,
        String status,
        String gender
) {

}
