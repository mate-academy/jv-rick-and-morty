package mate.academy.rickandmorty.dto.external;

import java.util.Collection;
import java.util.List;

public record CharacterResponseDataDto(
        CharacterInfoDataDto info,
        List<CharacterInfo> results) {
    public Collection<CharacterInfo> getResults() {
        return results;
    }
    public CharacterInfoDataDto getInfo() {
        return info;
    }

}
