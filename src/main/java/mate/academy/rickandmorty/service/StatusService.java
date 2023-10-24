package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.model.Status;

public interface StatusService {
    Status getByName(String name);
}
