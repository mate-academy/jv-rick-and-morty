package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class ResponseInfoDto {
    private long count;
    private long pages;
    private String next;
    private String prev;
}
