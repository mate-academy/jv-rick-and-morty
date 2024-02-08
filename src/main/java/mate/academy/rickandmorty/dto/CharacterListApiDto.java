package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class CharacterListApiDto {
    List<CharacterApiDto> results  = new ArrayList<>();
}
