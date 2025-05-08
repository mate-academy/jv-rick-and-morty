package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record RickAndMortyCharacterDto(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        @JsonDeserialize(using = RickAndMortyDeserializer.class)
        String origin,
        @JsonDeserialize(using = RickAndMortyDeserializer.class)
        String location,
        String image
) {}
