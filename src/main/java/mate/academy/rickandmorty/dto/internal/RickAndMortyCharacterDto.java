package mate.academy.rickandmorty.dto.internal;

import lombok.Data;

@Data
public class RickAndMortyCharacterDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String type;
    private String gender;
    private String image;
}
