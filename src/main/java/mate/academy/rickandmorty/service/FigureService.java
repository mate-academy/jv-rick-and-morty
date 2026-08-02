package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import mate.academy.rickandmorty.model.Figure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FigureService {

    FigureDto findRandomFigure();

    Page<FigureDto> search(FigureSearchParametersDto params, Pageable pageable);

    List<Figure> saveListOfFigures(List<Figure> figures);

    void saveAllFigures() throws InterruptedException;
}
