package mate.academy.rickandmorty.dto.internal.cartoon;

import lombok.Data;

@Data
public class CartoonCharacterDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;

}
