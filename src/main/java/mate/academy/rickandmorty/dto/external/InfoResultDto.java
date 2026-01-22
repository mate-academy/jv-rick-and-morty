package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoResultDto {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
