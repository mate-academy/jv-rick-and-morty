package mate.academy.rickandmorty.model;

import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.request.ApiCharacterRequestDto;

@Data
public class ApiResponse {
    private Info info;
    private List<ApiCharacterRequestDto> results;
}
