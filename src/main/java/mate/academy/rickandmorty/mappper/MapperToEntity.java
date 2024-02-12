package mate.academy.rickandmorty.mappper;

public interface MapperToEntity<D, E> {
    E toEntity(D dto);
}
