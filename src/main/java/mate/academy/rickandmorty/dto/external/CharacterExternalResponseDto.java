package mate.academy.rickandmorty.dto.external;

import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Data;

@Data
public class CharacterExternalResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private OriginResponseDto origin;
    private OriginResponseDto location;
    private String image;
    private ArrayList<String> episode;
    private String url;
    private LocalDateTime created;
}
