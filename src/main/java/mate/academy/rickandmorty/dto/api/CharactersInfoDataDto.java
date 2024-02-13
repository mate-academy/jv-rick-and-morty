package mate.academy.rickandmorty.dto.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CharactersInfoDataDto {
    @JsonProperty("count")
    private int count;
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("next")
    private String next;
    @JsonProperty("prev")
    private String prev;
}
