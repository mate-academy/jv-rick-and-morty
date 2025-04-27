package mate.academy.rickandmorty.dto;

import java.util.List;
import java.util.Objects;

public class CharacterResponseDataDto {

    private List<RickAndMortyApiCharacterDto> results;
    private InfoDto info;

    public CharacterResponseDataDto() {
    }

    public CharacterResponseDataDto(List<RickAndMortyApiCharacterDto> results, InfoDto info) {
        this.results = results;
        this.info = info;
    }

    public List<RickAndMortyApiCharacterDto> getResults() {
        return results;
    }

    public void setResults(List<RickAndMortyApiCharacterDto> results) {
        this.results = results;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(InfoDto info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "CharacterResponseDataDto{"
                + "results=" + results
                + ", info=" + info
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CharacterResponseDataDto)) {
            return false;
        }
        CharacterResponseDataDto that = (CharacterResponseDataDto) o;
        return Objects.equals(results, that.results)
                && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, info);
    }
}
