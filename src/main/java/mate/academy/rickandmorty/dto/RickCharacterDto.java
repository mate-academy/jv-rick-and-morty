package mate.academy.rickandmorty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RickCharacterDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
