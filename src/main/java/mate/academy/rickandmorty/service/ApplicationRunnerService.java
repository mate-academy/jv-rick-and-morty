package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.CharacterDtoWithoutExternalId;

public interface ApplicationRunnerService {
    List<CharacterDtoWithoutExternalId> getCharactersFromApi();
}
