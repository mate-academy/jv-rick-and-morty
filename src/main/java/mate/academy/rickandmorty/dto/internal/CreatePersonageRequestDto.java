package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Schema(description = "Request DTO for creating a new personage.")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePersonageRequestDto {
    @Positive
    @Schema(description = "The ID of the personage.", example = "12")
    private Long id;

    @NotBlank
    @Schema(description = "The name of the personage.", example = "Beth Smith")
    private String name;

    @NotBlank
    @Schema(description = "The status of the personage.", example = "Alive")
    private String status;

    @NotBlank
    @Schema(description = "The gender of the personage.", example = "Female")
    private String gender;
}
