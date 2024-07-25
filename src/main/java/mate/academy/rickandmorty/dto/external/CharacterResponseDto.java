package mate.academy.rickandmorty.dto.external;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.model.Origin;

@Data
public class CharacterResponseDto {
    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private URL image;
    private List<URL> episode;
    private URL url;
    private LocalDateTime created;
}
