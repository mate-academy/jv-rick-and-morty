package mate.academy.rickandmorty.dto.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
