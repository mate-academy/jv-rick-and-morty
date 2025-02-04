package mate.academy.rickandmorty.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCharacterRequestDto {
    private Long externalId;
    private String name;
    private String status;
    private String gender;
}
