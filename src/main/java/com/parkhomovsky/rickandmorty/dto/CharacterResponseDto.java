package com.parkhomovsky.rickandmorty.dto;

import com.parkhomovsky.rickandmorty.model.Gender;
import com.parkhomovsky.rickandmorty.model.Status;
import lombok.Data;

@Data
public class CharacterResponseDto {
    private Long id;
    private Long externalId;
    private String name;
    private Status status;
    private Gender gender;
}
