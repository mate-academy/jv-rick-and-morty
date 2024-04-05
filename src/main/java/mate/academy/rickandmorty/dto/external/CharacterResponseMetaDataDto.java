package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponseMetaDataDto {
    private String count;
    private String pages;
    private String next;
    private String prev;
}
