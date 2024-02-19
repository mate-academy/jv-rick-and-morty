package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharactersInfoDto {
    @JsonProperty("pages")
    private int numberOfPages;
}
