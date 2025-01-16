package mate.academy.rickandmorty.dto.internal;

import jakarta.validation.constraints.NotBlank;

public record CharacterConversionDto(
        @NotBlank
        String externalId,
        @NotBlank
        String name,
        @NotBlank
        String status,
        @NotBlank
        String gender) {
}
