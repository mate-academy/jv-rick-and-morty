package mate.academy.rickandmorty.external.api.service;

import mate.academy.rickandmorty.dto.external.CharacterFullResponseDto;

public interface CharacterClient {
    CharacterFullResponseDto getDtoFromApi(String url);
}
