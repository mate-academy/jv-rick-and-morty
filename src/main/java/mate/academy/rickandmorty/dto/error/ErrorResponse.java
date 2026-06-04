package mate.academy.rickandmorty.dto.error;

import java.util.List;

public record ErrorResponse(List<FieldErrorDto> errors) {
}
