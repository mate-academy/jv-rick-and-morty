package mate.academy.rickandmorty.dto;

import java.util.Objects;

public class RickAndMortyApiCharacterDto {

    private Long id;
    private String name;
    private String status;
    private String gender;

    public RickAndMortyApiCharacterDto() {
    }

    public RickAndMortyApiCharacterDto(Long id, String name, String status, String gender) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "RickAndMortyApiCharacterDto{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", status='" + status + '\''
                + ", gender='" + gender + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RickAndMortyApiCharacterDto)) {
            return false;
        }
        RickAndMortyApiCharacterDto that = (RickAndMortyApiCharacterDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(status, that.status)
                && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, gender);
    }
}
