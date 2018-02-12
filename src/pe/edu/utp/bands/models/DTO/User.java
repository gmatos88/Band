package pe.edu.utp.bands.models.DTO;

public class User {
    //User DTO
    private int id;
    private String username;
    private String email;
    private String password;

    private String firstname;
    private String lastname;

    private UserType type;
    private Country country;

    public User() {
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public User setType(UserType type) {
        this.type = type;
        return this;
    }

    public Country getCountry() {
        return country;
    }

    public User setCountry(Country country) {
        this.country = country;
        return this;
    }
}
