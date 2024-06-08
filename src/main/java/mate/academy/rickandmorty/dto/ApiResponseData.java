package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;

@Data
public class ApiResponseData {
    private InfoDto info;
    private List<ResponseDto> results;
}
