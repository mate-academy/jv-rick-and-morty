package spring.rickandmorty.dto;

import lombok.Data;

@Data
public class ExternalCharacterDto {
    private Long id;
    private String name;
    private String status;
    private String gender;
}
