package mate.academy.rickandmorty.dto.external;

import lombok.Data;

@Data
public class InfoResponseDto {
    private Long count;
    private Long pages;
    private String next;
    private String prev;
}
