package com.parkhomovsky.rickandmorty.repository;

import com.parkhomovsky.rickandmorty.model.MovieCharacter;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Long> {
    List<MovieCharacter> findAllByExternalIdIn(Set<Long> externalIds);

    List<MovieCharacter> findAllByNameContains(String namePart);
}
