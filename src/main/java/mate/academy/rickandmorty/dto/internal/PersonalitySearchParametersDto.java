package mate.academy.rickandmorty.dto.internal;

public record PersonalitySearchParametersDto(
        String[] name,
        String[] status,
        String[] gender) {
}
