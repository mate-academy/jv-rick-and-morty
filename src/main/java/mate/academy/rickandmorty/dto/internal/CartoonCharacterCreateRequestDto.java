package mate.academy.rickandmorty.dto.internal;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CartoonCharacterCreateRequestDto {
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationCreateRequestDto origin;
    private LocationCreateRequestDto location;
    private String image;
    private List<EpisodeCreateRequestDto> episodes;
    private String url;
    private LocalDateTime created;
}
