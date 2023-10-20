package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CharacterInformationDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
    private LocalDateTime created;
}
