package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharactersInfoDataDto {
    private int count;
    private int pages;
    private String next;
    private String prev;
}
