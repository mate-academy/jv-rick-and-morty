package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class InfoResourcesDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
