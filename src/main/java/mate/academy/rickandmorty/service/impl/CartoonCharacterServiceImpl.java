package mate.academy.rickandmorty.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CartoonCharacterDto;
import mate.academy.rickandmorty.dto.CreateCartoonCharacterRequestDto;
import mate.academy.rickandmorty.exceptions.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CartoonCharacterMapper;
import mate.academy.rickandmorty.model.CartoonCharacter;
import mate.academy.rickandmorty.repository.CartoonCharacterRepository;
import mate.academy.rickandmorty.service.CartoonCharacterService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartoonCharacterServiceImpl implements CartoonCharacterService {
    private final CartoonCharacterRepository repository;
    private final CartoonCharacterMapper mapper;

    @Override
    public CartoonCharacterDto save(CreateCartoonCharacterRequestDto requestDto) {
        return mapper.toDto(repository.save(mapper.toModel(requestDto)));
    }

    @Override
    public void saveAll(List<CreateCartoonCharacterRequestDto> requestDtoList) {
        for (CreateCartoonCharacterRequestDto requestDto : requestDtoList) {
            save(requestDto);
        }
    }

    @Override
    public List<CartoonCharacterDto> findByName(String name, Pageable pageable) {
        List<CartoonCharacter> characters = repository.findAllByNameContains(pageable, name);
        if (characters.isEmpty()) {
            throw new EntityNotFoundException("Can't find characters with name: " + name);
        }
        return characters.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CartoonCharacterDto getRandom() {
        return mapper.toDto(repository.getRandom());
    }
}
