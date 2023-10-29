package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class InternalCharListDto {
    @JsonProperty("info")
    private InfoDto info;
    @JsonProperty("results")
    private List<InternalCharResponseDto> results;
}
