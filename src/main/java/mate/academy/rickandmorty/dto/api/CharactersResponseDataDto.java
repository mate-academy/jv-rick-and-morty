package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharactersResponseDataDto {
    @JsonProperty("info")
    private CharactersInfoDataDto info;
    @JsonProperty("results")
    private SingleCharacterDataDto[] results;
}
