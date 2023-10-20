package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class CharacterMetaInformationDto {
    private Long count;
    private Integer pages;
    private String next;
    private String prev;
}
