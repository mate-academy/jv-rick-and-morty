package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FigureService {

    FigureDto findRandomFigure();

    Page<FigureDto> search(FigureSearchParametersDto params, Pageable pageable);

}
