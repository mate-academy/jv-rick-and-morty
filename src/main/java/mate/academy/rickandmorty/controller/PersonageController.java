package mate.academy.rickandmorty.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CreatePersonageRequestDto;
import mate.academy.rickandmorty.dto.internal.PersonageDto;
import mate.academy.rickandmorty.service.PersonageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personages")
@RequiredArgsConstructor
public class PersonageController {
    private final PersonageService personageService;

    @GetMapping
    public List<PersonageDto> getAll() {
        return personageService.findAll();
    }

    @GetMapping("/{id}")
    public PersonageDto getPersonageById(@PathVariable Long id) {
        return personageService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PersonageDto createPersonage(@RequestBody @Valid CreatePersonageRequestDto requestDto) {
        return personageService.save(requestDto);
    }

    @PutMapping("/{id}")
    public PersonageDto updatePersonage(@PathVariable Long id,
                                        @RequestBody @Valid CreatePersonageRequestDto requestDto) {
        return personageService.updateById(id, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePersonage(@PathVariable Long id) {
        personageService.deleteById(id);
    }
}
