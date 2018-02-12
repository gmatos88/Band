package pe.edu.utp.bands.models.DTO;

public class Country {
    private int id;
    private String country;

    public Country() {
    }

    public int getId() {
        return id;
    }

    public Country setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Country setCountry(String country) {
        this.country = country;
        return this;
    }
}
