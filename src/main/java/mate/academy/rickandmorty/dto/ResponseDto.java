package mate.academy.rickandmorty.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "externalId", "name", "status", "gender"})
public class ResponseDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
