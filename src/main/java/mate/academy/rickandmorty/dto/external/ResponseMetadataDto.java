package mate.academy.rickandmorty.dto.external;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseMetadataDto {
    private BigDecimal count;
    private BigDecimal pages;
    private String next;
    private String prev;
}
