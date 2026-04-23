package mate.academy.rickandmorty.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;

@MapperConfig(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        implementationPackage = "mate.academy.rickandmorty.mapper.impl",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public class MapStructConfig {
}
