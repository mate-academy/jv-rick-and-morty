package mate.academy.rickandmorty.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RickAndMortyCharacterDto {
    private final List<CharacterDtoWithoutExternalId> results = new ArrayList<>();
}
