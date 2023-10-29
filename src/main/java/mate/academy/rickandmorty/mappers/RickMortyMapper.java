package mate.academy.rickandmorty.mappers;

import mate.academy.rickandmorty.dto.RickMortyDtoResponse;
import mate.academy.rickandmorty.models.RickMorty;

public interface RickMortyMapper {
    RickMorty toModel(RickMortyDtoResponse rickMortyDtoRequest);

}
