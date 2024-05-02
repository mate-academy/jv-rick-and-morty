package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class InfoResponseDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
