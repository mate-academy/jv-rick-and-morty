package mate.academy.rickandmorty.service.impl;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.FigureDto;
import mate.academy.rickandmorty.dto.FigureResponseDataDto;
import mate.academy.rickandmorty.dto.FigureSearchParametersDto;
import mate.academy.rickandmorty.mapper.FigureMapper;
import mate.academy.rickandmorty.model.Figure;
import mate.academy.rickandmorty.repository.FigureRepository;
import mate.academy.rickandmorty.repository.FigureSpecificationBuilder;
import mate.academy.rickandmorty.service.FigureClient;
import mate.academy.rickandmorty.service.FigureService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FigureServiceImpl implements FigureService, ApplicationRunner {

    private final FigureRepository figureRepository;
    private final FigureMapper figureMapper;
    private final FigureSpecificationBuilder figureSpecificationBuilder;
    private final FigureClient figureClient;

    @Override
    public FigureDto findRandomFigure() {

        Random random = new Random();

        return figureMapper.toDto(figureRepository.findById(
                        (long) random.nextInt(1, (int) figureRepository.count() + 1))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Can't find random Character")));
    }

    @Override
    public Page<FigureDto> search(FigureSearchParametersDto params, Pageable pageable) {
        Specification<Figure> figureSpecification = figureSpecificationBuilder.build(params);
        return figureRepository.findAll(figureSpecification, pageable)
                .map(figureMapper::toDto);
    }

    @Override
    public List<Figure> saveListOfFigures(List<Figure> figures) {
        return figureRepository.saveAll(figures);
    }

    @Override
    public void saveAllFigures() {

        FigureResponseDataDto firstPage =
                figureClient.getFiguresFromApi(1);

        int pages = firstPage.getInfo().getPages();

        saveListOfFigures(
                firstPage.getResults()
                        .stream()
                        .map(figureMapper::toModel)
                        .toList());

        for (int page = 2; page <= pages; page++) {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            FigureResponseDataDto response =
                    figureClient.getFiguresFromApi(page);
            saveListOfFigures(
                    response.getResults()
                            .stream()
                            .map(figureMapper::toModel)
                            .toList());
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveAllFigures();
    }
}
