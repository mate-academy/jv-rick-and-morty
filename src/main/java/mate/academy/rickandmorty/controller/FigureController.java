package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import mate.academy.rickandmorty.service.FigureService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Figure management", description = "Endpoints for managing Figures")
@RequiredArgsConstructor
@RestController
@RequestMapping("/Figures")
public class FigureController {

    private final FigureService figureService;

    @GetMapping("/random")
    @Operation(summary = "Get random character",
            description = "Returns character with random ID")
    public FigureDto getRandomFigure() {
        return figureService.findRandomFigure();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters",
            description = "Return characters matching the name")
    public Page<FigureDto> searchCharacters(FigureSearchParametersDto params,
                                            @ParameterObject Pageable pageable) {

        return figureService.search(params, pageable);
    }
}
