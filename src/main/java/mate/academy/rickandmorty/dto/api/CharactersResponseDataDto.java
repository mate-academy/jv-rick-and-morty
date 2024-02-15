package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharactersResponseDataDto {
    private CharactersInfoDataDto info;
    private SingleCharacterDataDto[] results;
}
