package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class ApiCharacterResultDataDto {
    private Long id;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private String name;
    private String url;
}
