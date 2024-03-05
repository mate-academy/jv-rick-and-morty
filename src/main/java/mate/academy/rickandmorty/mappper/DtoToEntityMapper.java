package mate.academy.rickandmorty.mappper;

public interface DtoToEntityMapper<D, E> {
    E toEntity(D dto);
}
