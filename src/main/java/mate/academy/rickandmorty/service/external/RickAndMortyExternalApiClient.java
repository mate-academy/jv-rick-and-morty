package mate.academy.rickandmorty.service.external;

import mate.academy.rickandmorty.dto.internal.CartoonCharacterCreateRequestDto;

public interface RickAndMortyExternalApiClient {
    CartoonCharacterCreateRequestDto uploadCartoonCharacterById(Long characterId);

    void uploadDataBase();
}
