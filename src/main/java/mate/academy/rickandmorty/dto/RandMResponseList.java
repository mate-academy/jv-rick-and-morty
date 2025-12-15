package mate.academy.rickandmorty.dto;

import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RandMResponseList {

    private List<RandMResponseDto> results;
}
