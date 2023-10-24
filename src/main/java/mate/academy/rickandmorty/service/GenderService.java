package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Gender;

public interface GenderService {
    Gender getByName(String name);
}
