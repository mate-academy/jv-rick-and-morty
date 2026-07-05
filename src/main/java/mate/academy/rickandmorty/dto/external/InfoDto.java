package mate.academy.rickandmorty.dto.external;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class InfoDto {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;
}
