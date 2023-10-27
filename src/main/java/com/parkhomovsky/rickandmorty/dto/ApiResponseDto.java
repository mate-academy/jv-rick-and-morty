package com.parkhomovsky.rickandmorty.dto;

import lombok.Data;

@Data
public class ApiResponseDto {
    private ApiInfoDto info;
    private ApiCharacterDto[] results;
}
