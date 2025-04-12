package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.OriginDto;
import mate.academy.rickandmorty.model.Origin;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface OriginMapper {
    Origin toModel(OriginDto originDto);
}
