package mate.academy.rickandmorty.dto.external.location;

import lombok.Data;

@Data
public class LocationSearchParameters {
    private String name;
    private String type;
    private String dimension;
}
