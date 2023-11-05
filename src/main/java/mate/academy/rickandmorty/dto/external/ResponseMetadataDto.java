package mate.academy.rickandmorty.dto.external;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ResponseMetadataDto {
    private BigDecimal count;
    private BigDecimal pages;
    private String next;
    private String prev;
}
