package mate.academy.rickandmorty.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class RandMResponseDto {

    private BigDecimal id;
    private String name;
    private String status;
    private String gender;
}
