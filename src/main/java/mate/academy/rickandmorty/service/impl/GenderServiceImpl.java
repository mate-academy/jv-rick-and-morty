package mate.academy.rickandmorty.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Gender;
import mate.academy.rickandmorty.repository.GenderRepository;
import mate.academy.rickandmorty.service.GenderService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;
    
    @Override
    public Gender getByName(String name) {
        Gender.GenderEnum existGender = null;
        for (Gender.GenderEnum currentGender : Gender.GenderEnum.values()) {
            if (currentGender.getTitle().equalsIgnoreCase(name)) {
                existGender = currentGender;
                break;
            }
        }
        if (existGender == null) {
            throw new RuntimeException("Send incorrect Gender");
        }

        Optional<Gender> optionalGender = genderRepository.findGenderByName(existGender);
        if (optionalGender.isPresent()) {
            return optionalGender.get();
        }
        return genderRepository.save(new Gender(existGender));
    }
}
