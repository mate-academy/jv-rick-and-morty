package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfoDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
