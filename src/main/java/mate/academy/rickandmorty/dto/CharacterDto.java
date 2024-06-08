package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"id", "externalId", "name", "status", "gender"})
public class CharacterDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;

    @JsonSetter("id")
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @JsonGetter("id")
    public String getExternalId() {
        return externalId;
    }
}
