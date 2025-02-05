package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ResponseDto;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataFetcher {
    private final PersonageRepository personageRepository;
    private final PersonageMapper personageMapper;
    private final PersonageClient personageClient;

    @PostConstruct
    public void createPersonagesFromFetchedData() {
        List<CreatePersonageRequestDto> allPersonages = fetchAllPersonages();
        allPersonages.forEach(this::savePersonage);
    }

    private List<CreatePersonageRequestDto> fetchAllPersonages() {
        List<CreatePersonageRequestDto> allPersonages = new ArrayList<>();
        String nextPageUrl = "https://rickandmortyapi.com/api/character";
        ResponseDto responseDto = null;

        while (nextPageUrl != null) {
            responseDto = personageClient.getPersonages(nextPageUrl);
            allPersonages.addAll(responseDto.getResults());
            nextPageUrl = responseDto.getInfo().next();
        }

        return allPersonages;
    }

    private void savePersonage(CreatePersonageRequestDto requestDto) {
        Personage personage = personageMapper.intoModel(requestDto);
        personageMapper.intoDto(personageRepository.save(personage));
    }
}
