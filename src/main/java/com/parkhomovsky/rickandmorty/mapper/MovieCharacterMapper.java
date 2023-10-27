package com.parkhomovsky.rickandmorty.mapper;

import com.parkhomovsky.rickandmorty.dto.ApiCharacterDto;
import com.parkhomovsky.rickandmorty.dto.CharacterResponseDto;
import com.parkhomovsky.rickandmorty.model.Gender;
import com.parkhomovsky.rickandmorty.model.MovieCharacter;
import com.parkhomovsky.rickandmorty.model.Status;
import org.springframework.stereotype.Component;

@Component
public class MovieCharacterMapper {
    public MovieCharacter parseApiCharacterResponseDto(ApiCharacterDto dto) {
        MovieCharacter movieCharacter = new MovieCharacter();
        movieCharacter.setName(dto.getName());
        movieCharacter.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        movieCharacter.setStatus(Status.valueOf(dto.getStatus().toUpperCase()));
        movieCharacter.setExternalId(dto.getId());
        return movieCharacter;
    }

    public CharacterResponseDto toResponseDto(MovieCharacter model) {
        CharacterResponseDto dto = new CharacterResponseDto();
        dto.setId(model.getId());
        dto.setExternalId(model.getExternalId());
        dto.setStatus(model.getStatus());
        dto.setGender(model.getGender());
        dto.setName(model.getName());
        return dto;
    }
}
