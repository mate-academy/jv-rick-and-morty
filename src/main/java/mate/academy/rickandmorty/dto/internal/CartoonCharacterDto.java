package mate.academy.rickandmorty.dto.internal;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.dto.external.LocationInputDataDto;

@Data
public class CartoonCharacterDto {
    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationInputDataDto origin;
    private LocationInputDataDto location;
    private List<String> episode;
    private String url;
    private LocalDateTime created;
}
