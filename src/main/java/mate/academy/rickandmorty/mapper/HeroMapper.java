package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.dto.external.HeroDto;
import mate.academy.rickandmorty.dto.internal.HeroInternalDto;
import mate.academy.rickandmorty.mapperconfig.MapperConfig;
import mate.academy.rickandmorty.model.Hero;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface HeroMapper {

    @Mapping(source = "id", target = "externalId")
    Hero toModel(HeroDto heroDto);

    HeroInternalDto toInternalDto(Hero hero);
}
