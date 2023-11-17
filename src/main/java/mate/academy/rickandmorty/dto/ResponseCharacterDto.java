package mate.academy.rickandmorty.dto;

public record ResponseCharacterDto(Long id,
                                   Long externalId,
                                   String name,
                                   String status,
                                   String gender) {}
