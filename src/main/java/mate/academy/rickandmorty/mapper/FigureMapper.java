package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.model.Figure;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface FigureMapper {

    FigureDto toDto(Figure figure);

    Figure toModel(FigureDto figureDto);
}
