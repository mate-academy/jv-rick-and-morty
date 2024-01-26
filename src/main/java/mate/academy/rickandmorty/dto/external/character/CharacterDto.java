package mate.academy.rickandmorty.dto.external.character;

import lombok.Data;
import mate.academy.rickandmorty.model.Location;
import mate.academy.rickandmorty.model.Origin;

@Data
public class CharacterDto {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private String[] episode;
    private String url;
    private String created;
}
