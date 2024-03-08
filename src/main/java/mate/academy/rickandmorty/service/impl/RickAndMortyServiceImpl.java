package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterRickAndMortyDataResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterRickAndMortyDto;
import mate.academy.rickandmorty.exception.DataProcessingException;
import mate.academy.rickandmorty.mapper.RickAndMortyMapper;
import mate.academy.rickandmorty.model.RickAndMortyModel;
import mate.academy.rickandmorty.repository.RickAndMortyRepository;
import mate.academy.rickandmorty.service.RickAndMortyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RickAndMortyServiceImpl implements RickAndMortyService {
    private final RickAndMortyRepository rickAndMortyRepository;
    private final RickAndMortyMapper rickAndMortyMapper;

    @Override
    public void save(CharacterRickAndMortyDataResponseDto responseDto) {
        RickAndMortyModel rickAndMortyModel = rickAndMortyMapper.toModel(responseDto);
        RickAndMortyModel saved = rickAndMortyRepository.save(rickAndMortyModel);
        rickAndMortyMapper.toDto(saved);
    }

    @Override
    public CharacterRickAndMortyDto getById(Long id) {
        Optional<RickAndMortyModel> rickAndMortyRepositoryById
                = rickAndMortyRepository.findById(id);
        return rickAndMortyMapper.toDto(rickAndMortyRepositoryById.orElseThrow(()
                -> new DataProcessingException("It is not possible to get this "
                + "character by this ID: "
        + id)));
    }

    @Override
    public List<CharacterRickAndMortyDto> getByName(String name) {
        return rickAndMortyRepository.findByNameContainsIgnoreCase(name).stream()
                .map(rickAndMortyMapper::toDto)
                .toList();
    }
}
