package mate.academy.rickandmorty.dto.internal;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;
    private LocalDateTime created;
}
