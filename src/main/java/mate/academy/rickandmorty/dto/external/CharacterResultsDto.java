package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.util.List;

@Data
public class CharacterResultsDto {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private CharacterOriginDto origin;
    private CharacterLocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
}
