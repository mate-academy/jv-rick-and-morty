package mate.academy.rickandmorty.dto.external;

import java.util.List;
import lombok.Data;

@Data
public class CharacterDto {

    private Long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private Origin origin;
    private Location location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }

    public void setGender(String gender) {
        this.gender = gender.toUpperCase();
    }
}
