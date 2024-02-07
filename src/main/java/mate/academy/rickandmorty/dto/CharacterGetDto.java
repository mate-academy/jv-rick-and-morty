package mate.academy.rickandmorty.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CharacterGetDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginDto origin;
    private LocationDto location;
    private String image;
    private List<String> episode;
    private String url;
    private LocalDateTime created;
}
