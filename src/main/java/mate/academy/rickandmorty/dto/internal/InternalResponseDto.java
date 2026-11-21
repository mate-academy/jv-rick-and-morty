package mate.academy.rickandmorty.dto.internal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalResponseDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
