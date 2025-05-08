package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RickAndMortyInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
