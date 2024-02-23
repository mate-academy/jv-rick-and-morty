package mate.academy.rickandmorty.dto.external;

public record CharacterInfoDataDto( int count,
         int pages,
         String next,
         String prev) {
    public String getNext() {
        return next;
    }
}
