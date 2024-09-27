package mate.academy.rickandmorty.external.api.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterFullResponseDto;
import mate.academy.rickandmorty.external.api.service.DbSynchronizationService;
import mate.academy.rickandmorty.external.api.service.ExternalCharacterService;
import mate.academy.rickandmorty.external.api.service.CharacterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbSynchronizationServiceImpl implements DbSynchronizationService {
    private final CharacterClient characterClient;
    private final ExternalCharacterService externalCharacterService;

    @Value("${mate.academy.rickandmorty.api}")
    private String RICK_AND_MORTY_API_CHARACTER_URL;

    @Override
    public void synchronize() {
        CharacterFullResponseDto fullResponseDto = characterClient.getDtoFromApi(RICK_AND_MORTY_API_CHARACTER_URL);

        if (fullResponseDto.getResults().isEmpty()) {
            return;
        }

        while (true) {
            externalCharacterService.saveOrUpdate(fullResponseDto.getResults());

            if (fullResponseDto.getInfo().getNext() == null) {
                break;
            }

            fullResponseDto = characterClient.getDtoFromApi(fullResponseDto.getInfo().getNext());
        }

    }
}
