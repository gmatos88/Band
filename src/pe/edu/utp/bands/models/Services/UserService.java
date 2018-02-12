package pe.edu.utp.bands.models.Services;

import pe.edu.utp.bands.models.DAO.CountriesEntity;
import pe.edu.utp.bands.models.DAO.UsersEntity;
import pe.edu.utp.bands.models.DAO.UsersTypesEntity;
import pe.edu.utp.bands.models.DTO.User;

import java.sql.Connection;

public class UserService {
    private Connection connection;
    private UsersEntity usersEntity;
    private CountriesEntity countriesEntity;
    private UsersTypesEntity usersTypesEntity;

    //    Patron FACADE
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    //    Patron SINGLETON
    public UsersEntity getUsersEntity() {
        if (getConnection() != null){
            if (usersEntity == null){
                usersEntity = new UsersEntity();
                usersEntity.setConnection(getConnection());
            }
        }
        return usersEntity;
    }

    public CountriesEntity getCountriesEntity(){
        if (getConnection() != null){
            if (countriesEntity == null){
                countriesEntity = new CountriesEntity();
                countriesEntity.setConnection(getConnection());
            }
        }
        return countriesEntity;
    }

    public UsersTypesEntity getUsersTypesEntity(){
        if (getConnection() != null){
            if (usersTypesEntity == null){
                usersTypesEntity = new UsersTypesEntity();
                usersTypesEntity.setConnection(getConnection());
            }
        }
        return usersTypesEntity;
    }

    public User logIn(String username, String password){
        return getUsersEntity() != null ?
                getUsersEntity().logIn(username, password, getUsersTypesEntity(), getCountriesEntity()) : null;
    }

}