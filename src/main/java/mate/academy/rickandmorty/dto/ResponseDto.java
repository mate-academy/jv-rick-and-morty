package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class ResponseDto {
    private List<CharacterDto> results;
}
