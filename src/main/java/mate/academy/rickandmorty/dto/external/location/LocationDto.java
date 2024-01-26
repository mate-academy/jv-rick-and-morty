package mate.academy.rickandmorty.dto.external.location;

import lombok.Data;

@Data
public class LocationDto {
    private Integer id;
    private String name;
    private String type;
    private String dimension;
    private String[] residents;
    private String url;
    private String created;
}
