package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.mapper.PersonageMapper;
import mate.academy.rickandmorty.model.Personage;
import mate.academy.rickandmorty.repository.PersonageRepository;
import mate.academy.rickandmorty.repository.specification.SpecificationBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonageServiceImpl implements PersonageService {
    private final PersonageRepository personageRepository;
    private final PersonageMapper personageMapper;
    private final SpecificationBuilder specificationBuilder;

    @Override
    public PersonageDto getRandomPersonage() {
        Personage personage = personageRepository.findById(randomLongGenerator())
                .orElseThrow(() -> new RuntimeException("Personage not found"));
        return personageMapper.intoDto(personage);
    }

    @Override
    public List<PersonageDto> searchPersonage(String name, Pageable pageable) {
        Specification<Personage> personageSpecification =
                specificationBuilder.buildSpecification(name);

        return personageRepository.findAll(personageSpecification, pageable)
                .stream()
                .map(personageMapper::intoDto)
                .toList();
    }

    private long randomLongGenerator() {
        Supplier<Long> generator = () -> new Random().nextLong(countRowsInTable());
        return generator.get();
    }

    private long countRowsInTable() {
        return personageRepository.count();
    }
}
