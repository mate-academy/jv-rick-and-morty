package mate.academy.rickandmorty.dto.internal;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
