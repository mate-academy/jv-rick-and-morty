package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class CharacterResponseInfoDto {
    private int pages;
    private String next;
    private String prev;
}
