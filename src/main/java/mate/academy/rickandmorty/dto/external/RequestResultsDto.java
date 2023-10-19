package mate.academy.rickandmorty.dto.external;

public record RequestResultsDto(
        int id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        Object origin,
        Object location,
        String image,
        String[] episode,
        String url,
        String created
) {
}
