package mate.academy.rickandmorty.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class InfoResponseDetails {

    private BigDecimal count;
    private Integer pages;
}
