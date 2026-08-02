package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class FigureResponseDataDto {
    private List<FigureResponseDto> results;
}
