package pe.edu.utp.bands.models.DTO;

public class UserType {
    private int id;
    private String description;

    public UserType() {
    }

    public int getId() {
        return id;
    }

    public UserType setId(int id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserType setDescription(String description) {
        this.description = description;
        return this;
    }
}
