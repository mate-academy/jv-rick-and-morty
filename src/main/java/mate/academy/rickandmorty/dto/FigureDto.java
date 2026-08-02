package mate.academy.rickandmorty.dto;

import lombok.Data;

@Data
public class FigureDto {
    private Long id;
    private String externalId;
    private String name;
    private String status;
    private String gender;
}
