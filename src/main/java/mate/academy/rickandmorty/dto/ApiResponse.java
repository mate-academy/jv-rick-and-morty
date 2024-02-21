package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ApiResponse(Info info, @JsonProperty("results") List<CharacterFromApi> data) {

}
