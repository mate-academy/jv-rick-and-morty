package mate.academy.rickandmorty.dto;

import java.util.Objects;

public class CharactersDto {

    private Long id;
    private Long externalId;
    private String name;
    private String status;
    private String gender;

    public CharactersDto() {
    }

    public CharactersDto(Long id, Long externalId, String name, String status, String gender) {
        this.id = id;
        this.externalId = externalId;
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

    public Long getExternalId() {
        return externalId;
    }

    public void setExternalId(Long externalId) {
        this.externalId = externalId;
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
        return "CharactersDto{"
                + "id=" + id
                + ", externalId=" + externalId
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
        if (!(o instanceof CharactersDto)) {
            return false;
        }
        CharactersDto that = (CharactersDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(externalId, that.externalId)
                && Objects.equals(name, that.name)
                && Objects.equals(status, that.status)
                && Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, externalId, name, status, gender);
    }
}
