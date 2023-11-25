package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import lombok.Data;

@Data
public class ApiCharacterDto {
    private Long id;
    private String name;
    private String status;
    @JsonIgnore
    private String species;
    @JsonIgnore
    private String type;
    private String gender;
    @JsonIgnore
    private Object origin;
    @JsonIgnore
    private Object location;
    @JsonIgnore
    private String image;
    @JsonIgnore
    private List<Object> episode;
    @JsonIgnore
    private String url;
    @JsonIgnore
    private String created;
}
