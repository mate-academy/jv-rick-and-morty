package mate.academy.rickandmorty.dto.external;

import java.util.List;

public class ArrayCharactersResponseDto {
    private List<CharacterResponseDto> results;

    public List<CharacterResponseDto> getResults() {
        return results;
    }

    public void setResults(List<CharacterResponseDto> results) {
        this.results = results;
    }
}
