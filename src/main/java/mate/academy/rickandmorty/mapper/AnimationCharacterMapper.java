package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.AnimationCharacterResponseDto;
import mate.academy.rickandmorty.model.AnimationCharacter;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface AnimationCharacterMapper {
    @Mapping(target = "status", source = "status", qualifiedByName = "statusFromAnimationCharacter")
    @Mapping(target = "gender", source = "gender", qualifiedByName = "genderFromAnimationCharacter")
    AnimationCharacterResponseDto toDto(AnimationCharacter animationCharacter);

    @Named("statusFromAnimationCharacter")
    default String statusFromAnimationCharacter(Status status) {
        return status.getName().getTitle();
    }

    @Named("genderFromAnimationCharacter")
    default String genderFromAnimationCharacter(Gender gender) {
        return gender.getName().getTitle();
    }

}
