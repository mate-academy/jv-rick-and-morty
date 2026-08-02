package mate.academy.rickandmorty.service.impl;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import mate.academy.rickandmorty.mapper.FigureMapper;
import mate.academy.rickandmorty.model.Figure;
import mate.academy.rickandmorty.repository.FigureRepository;
import mate.academy.rickandmorty.repository.FigureSpecificationBuilder;
import mate.academy.rickandmorty.service.FigureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FigureServiceImpl implements FigureService {

    private final FigureRepository figureRepository;
    private final FigureMapper figureMapper;
    private final FigureSpecificationBuilder figureSpecificationBuilder;

    @Override
    public FigureDto findRandomFigure() {

        Random random = new Random();

        return figureMapper.toDto(figureRepository.findById(
                        (long) random.nextInt(1, (int) figureRepository.count()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can't find random Character")));
    }

    @Override
    public Page<FigureDto> search(FigureSearchParametersDto params, Pageable pageable) {
        Specification<Figure> figureSpecification = figureSpecificationBuilder.build(params);
        return figureRepository.findAll(figureSpecification, pageable)
                .map(figureMapper::toDto);
    }
}
